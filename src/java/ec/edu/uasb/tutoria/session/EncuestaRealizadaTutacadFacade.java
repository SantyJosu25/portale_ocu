/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.bean.exceptions.MatriculaException;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.form.datamodel.EncuTutoDispo;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.MatriculaFacadeLocal;
import ec.edu.uasb.tutoria.entities.EncuestaRealizadaTutacad;
import ec.edu.uasb.tutoria.entities.EncuestaRealizadaTutacadPK;
import ec.edu.uasb.tutoria.entities.RespuestaTutacad;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author marjorie.fiallos
 */
@Stateless
public class EncuestaRealizadaTutacadFacade extends AbstractFacade<EncuestaRealizadaTutacad> implements EncuestaRealizadaTutacadFacadeLocal {

    @EJB
    private MatriculaFacadeLocal matriculaFacade;
    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaRealizadaTutacadFacade() {
        super(EncuestaRealizadaTutacad.class);
    }

    @Override
    public List evalTutoriaAcademica(Long codEstud, Long codigoesp) {
        Query q;
        StringBuilder sb = new StringBuilder();
        sb.append(" DECLARE @PROFESOR TABLE (CODIGO_PROFESOR NUMERIC(7,0), NOMBRE_PROFESOR VARCHAR(200), APELLIDO_PROFESOR VARCHAR(200)) \n"
                + " INSERT  INTO @PROFESOR(CODIGO_PROFESOR, NOMBRE_PROFESOR, APELLIDO_PROFESOR) \n"
                + " SELECT CODIGO_PROFESOR, NOMBRE_PROFESOR, APELLIDO_PROFESOR\n"
                + " FROM interfaseOcu.dbo.PROFESOR\n"
                + " SELECT distinct ENC.CODIGO_ENCUESTA,ENC.TITULO,ENC.REFERENCIA,  ENCAL.ANIO,\n"
                + " ENCAL.CICLO, ENCAL.FECHA_INICIO,ENCAL.FECHA_FIN,ENCAL.CODIGO_PROFESOR,ENCAL.CODIGO_INSTANCIA,\n"
                + " (CASE WHEN ENCAL.CODIGO_PROFESOR = -1 THEN(select  I.TIN_NOMBRE from GESTIONACADEMICA.TUT_INSTANCIA as I where I.TIN_CODIGO = ENCAL.CODIGO_INSTANCIA )\n"
                + " ELSE (SELECT P.NOMBRE_PROFESOR +' ' + P.APELLIDO_PROFESOR FROM @PROFESOR AS P WHERE P.CODIGO_PROFESOR=ENCAL.CODIGO_PROFESOR ) END)   NOMBRES,ENCAL.CODIGO_ESP,ENCAL.CODIGO_NIVEL,ENCAL.CODIGO_PARALELO,\n"
                + " (SELECT count(1)  from EVALUACION.ENCUESTA_REALIZADA_TUTACAD  ENCRE WITH (NOLOCK) WHERE  ENCRE.codigo_alumno = ENCAL.COD_ESTUDIANTE\n"
                + " and ENCRE.anio = ENCAL.ANIO and ENCRE.ciclo = ENCAL.CICLO and ENCRE.codigo_esp = ENCAL.CODIGO_ESP and ENCRE.TST_CODIGO = ENCAL.TST_CODIGO\n"
                + " and ENCRE.codigo_materia = ENCAL.CODIGO_MATERIA and ENCRE.codigo_encuesta = ENCAL.CODIGO_ENCUESTA\n"
                + " and ENCRE.codigo_profesor = ENCAL.CODIGO_PROFESOR  and ENCRE.TIPO_REGISTRO = 'A' AND ENCRE.CODIGO_NIVEL =ENCAL.CODIGO_NIVEL ) ESTADO,\n"
                + " IIF(getdate() between ENCAL.fecha_inicio and ENCAL.fecha_fin + 1,'S','N') HABILITADO,\n"
                + " (CASE WHEN TUT.TST_TIPO_TUTORIA = 'A' THEN 'ACADÉMICA' ELSE 'TÉCNICA' END) TIPO_TUTORIA,\n"
                + "  ENCAL.CODIGO_INSTANCIA, ENCAL.TST_CODIGO                \n"
                + "  FROM  EVALUACION.ENCUESTA  ENC WITH (NOLOCK) ,\n"
                + "  EVALUACION.ENCUESTA_CALENDARIO_TUTACAD ENCAL WITH (NOLOCK),\n"
                + "  interfaseOcu.GESTIONACADEMICA.TUT_SOLICITUD_TUTORIA TUT WITH (NOLOCK)"
                + "  WHERE  ENCAL.CODIGO_ENCUESTA = ENC.CODIGO_ENCUESTA   \n"
                + "  AND ENCAL.TST_CODIGO= TUT.TST_CODIGO\n"
                + "  AND ENCAL.TIPO_ENCUESTA= 'R'\n"
                + "  AND ENC.USO='D'\n"
                + "  AND ENCAL.CODIGO_NIVEL = -1\n"
                + "  AND ENCAL.CODIGO_PARALELO = -1\n"
                + "  AND ENCAL.ESTADO_ENCUESTA= 'A'\n"
                + "  AND ENCAL.FECHA_INICIO <= getdate()\n"
                + "  AND ENCAL.COD_ESTUDIANTE= ?\n"
                + "  AND ENCAL.CODIGO_ESP= ? order by  ESTADO, fecha_fin desc");
        q = em.createNativeQuery(sb.toString(), EncuTutoDispo.class);
        q.setParameter(1, codEstud);
        q.setParameter(2, codigoesp);
        return q.getResultList();
    }

    @Override
    public String createEncuesta(Long codigoAlumno, EncuTutoDispo encuTutoDispo,
            List<RespuestaTutacad> respuestas, FacesContext context
    ) {
        Query q;
        q = em.createNativeQuery("select count(*) from EVALUACION.ENCUESTA_REALIZADA_TUTACAD  WITH (NOLOCK) where CODIGO_ALUMNO = ? and ANIO = ? and CICLO = ? and CODIGO_ENCUESTA = ? "
                + "and CODIGO_MATERIA = ? and  CODIGO_PROFESOR = ? and TIPO_REGISTRO = 'A' and CODIGO_ESP= ? and CODIGO_NIVEL = ? and CODIGO_PARALELO = ? and COD_INSTANCIA = ? and TST_CODIGO = ?");
        q.setParameter(1, codigoAlumno);
        q.setParameter(2, encuTutoDispo.getEncuTutoDispoPK().getAnio());
        q.setParameter(3, encuTutoDispo.getCiclo());
        q.setParameter(4, encuTutoDispo.getEncuTutoDispoPK().getCodigoEncuesta());
        q.setParameter(5, -1);
        q.setParameter(6, encuTutoDispo.getCodigoProfesor());
        q.setParameter(7, encuTutoDispo.getEncuTutoDispoPK().getCodigoEsp());
        q.setParameter(8, encuTutoDispo.getEncuTutoDispoPK().getCodigoNivel());
        q.setParameter(9, encuTutoDispo.getEncuTutoDispoPK().getCodigoParalelo());
        q.setParameter(10, encuTutoDispo.getCodigoInstancia());
        q.setParameter(11, encuTutoDispo.getEncuTutoDispoPK().getTstCodigo());
        if ((Integer) q.getSingleResult() != 0) {
            return "<br/><br/>Encuesta ya fue realizada ! ";
        } else {
            for (Iterator<RespuestaTutacad> it = respuestas.iterator(); it.hasNext();) {
                RespuestaTutacad r = it.next();
                try {
                    getEntityManager().persist(r);
                } catch (Exception e) {
                    return "Error. No puedo generar Transacción (respuestas).<br/><br/>Por Favor, informe al CallCenter de la Universidad Andina Simon Bolivar <br/>sobre este error.<br/><br/>Gracias !";
                }
            }
            EncuestaRealizadaTutacad enc = new EncuestaRealizadaTutacad();
            EncuestaRealizadaTutacadPK encPk = new EncuestaRealizadaTutacadPK(codigoAlumno, encuTutoDispo.getEncuTutoDispoPK().getAnio(),
                    encuTutoDispo.getCiclo(), Long.parseLong("-1"), encuTutoDispo.getEncuTutoDispoPK().getCodigoEncuesta(),
                    encuTutoDispo.getCodigoProfesor(), 'A', encuTutoDispo.getEncuTutoDispoPK().getCodigoEsp(),
                    encuTutoDispo.getEncuTutoDispoPK().getCodigoNivel(), encuTutoDispo.getEncuTutoDispoPK().getCodigoParalelo(), encuTutoDispo.getCodigoInstancia(), encuTutoDispo.getEncuTutoDispoPK().getTstCodigo());
            enc.setEncuestaRealizadaTutacadPK(encPk);
            enc.setFecha(new Date());
            enc.setRealizada('1');
            try {
                this.create(enc);
                em.flush();
            } catch (Exception e) {
                return "Error. No puedo generar Transacción (EncuestaRealizada).<br/><br/>Por Favor, informe al CallCenter de la Universidad Andina Simon Bolivar <br/>sobre este error.<br/><br/>Gracias !";
            }

            registraRecordatorio(codigoAlumno, context, enc);
        }
        return null;
    }

    private void registraRecordatorio(Long codAlumno, FacesContext context, EncuestaRealizadaTutacad enc) {

        StringBuilder sbCab = new StringBuilder();
        StringBuilder sbDet = new StringBuilder();
        BigDecimal sec;
        StringBuilder mensaje;
        Matricula matr = null;

        Query query = em.createNativeQuery("select COD_ESTUDIANTE,CED_PAS_ESTUDIANTE,NOM_ESTUDIANTE,APELL_ESTUDIANTE,CLAVE,EMAIL from ESTUDIANTE  WITH (NOLOCK) where COD_ESTUDIANTE = ? ", Usuario.class);
        query.setParameter(1, codAlumno);
        Usuario user = (Usuario) query.getSingleResult();

        try {
            matr = matriculaFacade.getMatricula(codAlumno);
        } catch (MatriculaException ex) {
            Logger.getLogger(EncuestaRealizadaTutacadFacade.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        mensaje = this.genMensajeTuto(user, context, enc);
        if (mensaje != null) {

            sec = addSecuencial("RECORDATORIO");
            sbCab.append(" INSERT INTO RECORDATORIO_ESTUDIANTE ( CODIGO_RECORDATORIO,BASE_DIR,COD_ESTUDIANTE,ANIO,CICLO,CODIGO_NIVEACAD,");
            sbCab.append(" CODIGO_FACULTAD,CODIGO_ESCUELA,CODIGO_ESP,NUM_MATRICULA,EMAIL_RECORDATORIO,ESTADO_RECORDATORIO,USUARIO_CREA, ");
            sbCab.append(" USUARIO_ULTMODIFIC,FECHA_CREA,FECHA_ULTMODIFIC,CC_RECORDATORIO, ASUNTO_RECORDATORIO, CODIGO_MATERIA,CODIGO_NIVEL) ");
            sbCab.append(" VALUES (?, 'A', ?, ?, ?, ?, ?, ?, ?, ?, ?,'A', 'iUASB', 'iUASB', getdate(),getdate(),?,?,?,? )");
            query = em.createNativeQuery(sbCab.toString());
            query.setParameter(1, sec);//CODIGO_RECORDATORIO
            query.setParameter(2, codAlumno);//COD_ESTUDIANTE
            query.setParameter(3, matr.getAnio());
            query.setParameter(4, 1);
            query.setParameter(5, matr.getCodNivelAcad());
            query.setParameter(6, matr.getCodArea());
            query.setParameter(7, matr.getCodEscuela());
            query.setParameter(8, matr.getCodPrograma());
            query.setParameter(9, matr.getMatricula());
            query.setParameter(10, user.getUsuEmail());
            query.setParameter(11, "evaluaciones@uasb.edu.ec");
            query.setParameter(12, "Evaluación de Tutorías académicas");
            query.setParameter(13, enc.getEncuestaRealizadaTutacadPK().getCodigoMateria());
            query.setParameter(14, enc.getEncuestaRealizadaTutacadPK().getCodigoNivel());
            query.executeUpdate();

            sbDet.append("INSERT INTO PROGRAMA_RECORDATORIO ( CODIGO_RECORDATORIO,FECHA_PROGRAMA, MENSAJE_RECORDATORIO,");
            sbDet.append("USUARIO_CREA,USUARIO_ULTMODIFIC,FECHA_CREA,FECHA_ULTMODIFIC,ESTADO_PROGRAMA,FORMATO_MENSAJE_PROGRAMA, PERFIL_CORREO) ");
            sbDet.append("VALUES ( ?,convert (datetime,CONVERT(char, convert(datetime, getdate()),112) ),?,'iUASB','iUASB',getdate(),getdate(),'A','H','Eval')");
            query = em.createNativeQuery(sbDet.toString());
            query.setParameter(1, sec);
            query.setParameter(2, mensaje.toString());
            query.executeUpdate();
        }
    }

    private StringBuilder genMensajeTuto(Usuario user, FacesContext fcontext, EncuestaRealizadaTutacad enc) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "EC"));
        StringBuilder sbHtml = null;

        String datoConsulta = null;
        try {
            Query q = null;
            if (enc.getEncuestaRealizadaTutacadPK().getCodigoProfesor() != -1) {
                StringBuilder sbQuery = new StringBuilder("Select ");
                sbQuery.append(" 'Tutoria del '+");
                sbQuery.append(" 'Profesor: '+NOMBRE_PROFESOR +' '+APELLIDO_PROFESOR+'<br>' +");
                sbQuery.append("  CICLO_ACADEMICO.NOMBRE_CICLO  +'<br>' ");
                sbQuery.append(" from EVALUACION.ENCUESTA_REALIZADA_TUTACAD WITH (NOLOCK) , PROFESOR WITH (NOLOCK) ,CICLO_ACADEMICO  WITH (NOLOCK) ");
                sbQuery.append(" where CICLO_ACADEMICO.ANIO = ENCUESTA_REALIZADA_TUTACAD.ANIO - 1 and CICLO_ACADEMICO.CICLO = ENCUESTA_REALIZADA_TUTACAD.CICLO ");
                sbQuery.append(" and PROFESOR.CODIGO_PROFESOR  = ENCUESTA_REALIZADA_TUTACAD.CODIGO_PROFESOR ");
                sbQuery.append(" and ENCUESTA_REALIZADA_TUTACAD.ANIO = ? and ENCUESTA_REALIZADA_TUTACAD.CICLO = 1 and ENCUESTA_REALIZADA_TUTACAD.CODIGO_MATERIA = ? and  ENCUESTA_REALIZADA_TUTACAD.CODIGO_PROFESOR = ? ");
                sbQuery.append(" and ENCUESTA_REALIZADA_TUTACAD.CODIGO_ALUMNO = ? and ENCUESTA_REALIZADA_TUTACAD.CODIGO_ENCUESTA = ?  and TIPO_REGISTRO = 'A'  and ENCUESTA_REALIZADA_TUTACAD.CODIGO_ESP= ? ");
                sbQuery.append("  and ENCUESTA_REALIZADA_TUTACAD.CODIGO_NIVEL = -1  and ENCUESTA_REALIZADA_TUTACAD.CODIGO_PARALELO = -1 and  ENCUESTA_REALIZADA_TUTACAD.TST_CODIGO = ? ");
                q = em.createNativeQuery(sbQuery.toString());
                q.setParameter(1, enc.getEncuestaRealizadaTutacadPK().getAnio());
                q.setParameter(2, -1);
                q.setParameter(3, enc.getEncuestaRealizadaTutacadPK().getCodigoProfesor());
                q.setParameter(4, enc.getEncuestaRealizadaTutacadPK().getCodigoAlumno());
                q.setParameter(5, enc.getEncuestaRealizadaTutacadPK().getCodigoEncuesta());
                q.setParameter(6, enc.getEncuestaRealizadaTutacadPK().getCodigoEsp());
                q.setParameter(7, enc.getEncuestaRealizadaTutacadPK().getTstCodigo());
            } else if (enc.getEncuestaRealizadaTutacadPK().getCodInstancia() != -1) {
                StringBuilder sbQuery = new StringBuilder(" Select  'Tutoria de la '+ ");
                sbQuery.append("  'Instancia: '+ TIN_NOMBRE +'<br>' +  CICLO_ACADEMICO.NOMBRE_CICLO  +'<br>' ");
                sbQuery.append(" from EVALUACION.ENCUESTA_REALIZADA_TUTACAD WITH (NOLOCK) , GESTIONACADEMICA.TUT_INSTANCIA WITH (NOLOCK) ,CICLO_ACADEMICO  WITH (NOLOCK)  ");
                sbQuery.append(" where CICLO_ACADEMICO.ANIO = ENCUESTA_REALIZADA_TUTACAD.ANIO - 1 and CICLO_ACADEMICO.CICLO = ENCUESTA_REALIZADA_TUTACAD.CICLO  and GESTIONACADEMICA.TUT_INSTANCIA.TIN_CODIGO  = ENCUESTA_REALIZADA_TUTACAD.COD_INSTANCIA  and ENCUESTA_REALIZADA_TUTACAD.ANIO = ? and ENCUESTA_REALIZADA_TUTACAD.CICLO = 1 and  ENCUESTA_REALIZADA_TUTACAD.COD_INSTANCIA = ?  and ENCUESTA_REALIZADA_TUTACAD.CODIGO_ALUMNO = ? and ENCUESTA_REALIZADA_TUTACAD.CODIGO_ENCUESTA = ?  and TIPO_REGISTRO = 'A'  and ENCUESTA_REALIZADA_TUTACAD.CODIGO_ESP= ?  ");
                sbQuery.append("  and ENCUESTA_REALIZADA_TUTACAD.CODIGO_NIVEL = -1  and ENCUESTA_REALIZADA_TUTACAD.CODIGO_PARALELO = -1 and  ENCUESTA_REALIZADA_TUTACAD.TST_CODIGO = ?");
                q = em.createNativeQuery(sbQuery.toString());
                q.setParameter(1, enc.getEncuestaRealizadaTutacadPK().getAnio());
                q.setParameter(2, enc.getEncuestaRealizadaTutacadPK().getCodInstancia());
                q.setParameter(3, enc.getEncuestaRealizadaTutacadPK().getCodigoAlumno());
                q.setParameter(4, enc.getEncuestaRealizadaTutacadPK().getCodigoEncuesta());
                q.setParameter(5, enc.getEncuestaRealizadaTutacadPK().getCodigoEsp());
                q.setParameter(6, enc.getEncuestaRealizadaTutacadPK().getTstCodigo());
            }
            datoConsulta = q.getSingleResult().toString().toUpperCase();
        } catch (NoResultException e) {
        } finally {
            if (datoConsulta != null) {
                sbHtml = new StringBuilder();
                sbHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'>");
                sbHtml.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title></title>");
                sbHtml.append("</head>");
                sbHtml.append("<body>");
                sbHtml.append(" Estimado(a) ").append(user.getUsuNombreUsuario().toUpperCase()).append(" ").append(user.getUsuApellUsuario().toUpperCase()).append("<br><br><br>");
                sbHtml.append(" El ").append(formatter.format(new Date())).append(" ha realizado la Encuesta de Tutorías académicas. <br><br>");
                sbHtml.append(datoConsulta).append("<br><br>");
                sbHtml.append(
                        " Gracias por su aporte. ").append("<br><br>");
                sbHtml.append(
                        "Atentamente,<br><br><br>Dirección General Académica.<br>");
                sbHtml.append(
                        "Telef: (593 2) 299 3669 / (593 2) 299 3670<br>");
                sbHtml.append(
                        "Quito, Ecuador<br>");

                HttpServletRequest o = (HttpServletRequest) fcontext.getExternalContext().getRequest();
                String s = o.getRequestURL().toString();
                sbHtml.append("<p align='left'><img src='").append(s.substring(0, s.indexOf("/", 15))).append(fcontext.getExternalContext().getRequestContextPath()).append("/resources/images/logocolor.png' alt='logouasb' width='225' height='75' /></p>");
                sbHtml.append("</body>");
            }
            return sbHtml;
        }
    }

    private BigDecimal addSecuencial(String tabla) {
        Query q = em.createNativeQuery("SELECT numseq FROM seqno  with(rowlock)  WHERE tabla = ?");
        q.setParameter(1, tabla);
        BigDecimal numSec = (BigDecimal) q.getSingleResult();
        q = em.createNativeQuery("UPDATE seqno SET numseq = ? + 1  WHERE tabla = ?");
        q.setParameter(1, numSec);
        q.setParameter(2, tabla);
        q.executeUpdate();
        return numSec;
    }
}
