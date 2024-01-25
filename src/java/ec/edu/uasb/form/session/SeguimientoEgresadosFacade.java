/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.session;

import ec.edu.uasb.bean.exceptions.MatriculaException;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.form.entities.EncuestaRealizada;
import ec.edu.uasb.form.entities.EncuestaRealizadaPK;
import ec.edu.uasb.form.entities.SeguimientoEgresados;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.AbstractFacade;
import ec.edu.uasb.session.MatriculaFacadeLocal;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author johana.orozco
 */
@Stateless
public class SeguimientoEgresadosFacade extends AbstractFacade<SeguimientoEgresados> implements SeguimientoEgresadosFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;
    @EJB
    private MatriculaFacadeLocal matriculaFacade;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SeguimientoEgresadosFacade() {
        super(SeguimientoEgresados.class);
    }

//    private BigDecimal findSecuencialBy(String tabla) {
//        Query q = em.createNativeQuery("SELECT numseq FROM maestra.dbo.SEQNO WHERE tabla = ?");
//        q.setParameter(1, tabla);
//        return (BigDecimal) q.getSingleResult();
//    }

    private BigDecimal addSecuencial(String tabla) {
        Query q = em.createNativeQuery("SELECT numseq FROM MAESTRA..seqno  with(rowlock)  WHERE tabla = ?");
        q.setParameter(1, tabla);
        BigDecimal numSec = (BigDecimal) q.getSingleResult();
        q = em.createNativeQuery("update MAESTRA..SEQNO SET NUMSEQ = ? + 1  WHERE tabla = ?");
        q.setParameter(1, numSec);
        q.setParameter(2, tabla);
        q.executeUpdate();
        return numSec;
    }

    @Override
    public SeguimientoEgresados findbyEstAnio(Long codEstudiante, Long anio) {
        Query q = em.createQuery("select object(o) from SeguimientoEgresados o where o.seguimientoEgresadosPK.codigoEstudiante = :codEstudiante and o.seguimientoEgresadosPK.anio = :anio  ");
        q.setParameter("codEstudiante", codEstudiante);
        q.setParameter("anio", anio);
        List<SeguimientoEgresados> temp = q.setHint("eclipselink.refresh", true).getResultList();
        if (!temp.isEmpty()) {
            return (SeguimientoEgresados) temp.get(0);
        } else {
            return null;
        }
    }

    @Override
    public String createEncuesta(Long codigoAlumno, Long codEncuesta, SeguimientoEgresados respuestas, FacesContext context) {
        Query q;
        q = em.createNativeQuery("select count(*) from ENCUESTA_REALIZADA where CODIGO_ALUMNO = ? and ANIO = ? and CICLO = ? "
                + "and CODIGO_ENCUESTA = ? and CODIGO_MATERIA = ? and  CODIGO_PROFESOR = ?"
                + " and TIPO_REGISTRO = 'A'  and CODIGO_ESP = ? AND CODIGO_NIVEL = ? ");
        q.setParameter(1, codigoAlumno);
        q.setParameter(2, respuestas.getSeguimientoEgresadosPK().getAnio());
        q.setParameter(3, respuestas.getSeguimientoEgresadosPK().getCiclo());
        q.setParameter(4, codEncuesta);
        q.setParameter(5, -1);
        q.setParameter(6, -1);
        q.setParameter(7, -1);
        q.setParameter(8, -1);
        if ((Integer) q.getSingleResult() != 0) {
            return "<br/><br/>Encuesta ya fue realizada ! ";
        } else {
            //Ingreso el valor en seguimiento
            try {
                this.create(respuestas);
            } catch (Exception e) {
                return "Error. No puedo generar Transacción (SeguimientoEgresados).<br/><br/>Por Favor, informe al CallCenter de la Universidad Andina Simon Bolivar <br/>sobre este error.<br/><br/>Gracias !";
            }

            EncuestaRealizada enc = new EncuestaRealizada();
            EncuestaRealizadaPK encPk = new EncuestaRealizadaPK(codigoAlumno, respuestas.getSeguimientoEgresadosPK().getAnio(),
                    respuestas.getSeguimientoEgresadosPK().getCiclo(), Long.parseLong("-1"),
                    codEncuesta, Long.parseLong("-1"), "A", Long.parseLong("-1"), Long.parseLong("-1"), Long.parseLong("-1"));
            enc.setEncuestaRealizadaPK(encPk);
            enc.setFecha(new Date());
            enc.setRealizada('1');
            try {
                em.persist(enc);
            } catch (Exception e) {
                return "Error. No puedo generar Transacción (EncuestaRealizada).<br/><br/>Por Favor, informe al CallCenter de la Universidad Andina Simon Bolivar <br/>sobre este error.<br/><br/>Gracias !";
            }
            registraRecordatorio(codigoAlumno, context, enc);
        }
        return null;
    }

    private void registraRecordatorio(Long codAlumno, FacesContext context, EncuestaRealizada enc) {

        StringBuilder sbCab = new StringBuilder();
        StringBuilder sbDet = new StringBuilder();
        BigDecimal sec;
        StringBuilder mensaje;
        Matricula matr = null;

        Query query = em.createNativeQuery("select COD_ESTUDIANTE,CED_PAS_ESTUDIANTE,NOM_ESTUDIANTE,APELL_ESTUDIANTE,CLAVE,EMAIL from ESTUDIANTE where COD_ESTUDIANTE = ? ", Usuario.class);
        query.setParameter(1, codAlumno);
        Usuario user = (Usuario) query.getSingleResult();

        try {
            matr = matriculaFacade.getMatricula(codAlumno);
        } catch (MatriculaException ex) {
            Logger.getLogger(EncuestaRealizadaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        sec =  addSecuencial("RECORDATORIO");

        sbCab.append(" INSERT INTO RECORDATORIO_ESTUDIANTE ( CODIGO_RECORDATORIO,BASE_DIR,COD_ESTUDIANTE,ANIO,CICLO,CODIGO_NIVEACAD,");
        sbCab.append(" CODIGO_FACULTAD,CODIGO_ESCUELA,CODIGO_ESP,NUM_MATRICULA,EMAIL_RECORDATORIO, ");
        sbCab.append(" ESTADO_RECORDATORIO,USUARIO_CREA,USUARIO_ULTMODIFIC,FECHA_CREA,FECHA_ULTMODIFIC,CC_RECORDATORIO, ASUNTO_RECORDATORIO) ");
        sbCab.append(" VALUES (?, 'A', ?, ?, ?, ?, ?, ?, ?, ?, ?,'A', 'iUASB', 'iUASB', getdate(),getdate(),?,? )");
        query = em.createNativeQuery(sbCab.toString());
        query.setParameter(1, sec);
        query.setParameter(2, codAlumno);
        query.setParameter(3, matr.getAnio());
        query.setParameter(4, 1);
        query.setParameter(5, matr.getCodNivelAcad());
        query.setParameter(6, matr.getCodArea());
        query.setParameter(7, matr.getCodEscuela());
        query.setParameter(8, matr.getCodPrograma());
        query.setParameter(9, matr.getMatricula());
        query.setParameter(10, user.getUsuEmail());
        query.setParameter(11, "dga@uasb.edu.ec");
        query.setParameter(12, "Encuesta SocioEconómica");
        query.executeUpdate();

        mensaje = this.genMensaje(matr, user, context, enc);

        sbDet.append("INSERT INTO PROGRAMA_RECORDATORIO ( CODIGO_RECORDATORIO,FECHA_PROGRAMA, MENSAJE_RECORDATORIO,");
        sbDet.append("USUARIO_CREA,USUARIO_ULTMODIFIC,FECHA_CREA,FECHA_ULTMODIFIC,ESTADO_PROGRAMA,FORMATO_MENSAJE_PROGRAMA) ");
        sbDet.append("VALUES ( ?,convert (datetime,CONVERT(char, convert(datetime, getdate()),112) ),?,'iUASB','iUASB',getdate(),getdate(),'A','H')");
        query = em.createNativeQuery(sbDet.toString());
        query.setParameter(1, sec);
        query.setParameter(2, mensaje.toString());
        query.executeUpdate();

       
    }

    private StringBuilder genMensaje(Matricula matr, Usuario user, FacesContext fcontext, EncuestaRealizada enc) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "EC"));
        StringBuilder sbHtml = new StringBuilder();

        sbHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'>");
        sbHtml.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title></title>");
        sbHtml.append("</head>");
        sbHtml.append("<body>");
        sbHtml.append(" Estimado(a) ").append(user.getUsuNombreUsuario().toUpperCase()).append(" ").append(user.getUsuApellUsuario().toUpperCase()).append("<br><br><br>");
        sbHtml.append(" El ").append(formatter.format(new Date())).append(" ha realizado la Encuesta SocioEconómica. <br><br>");
        sbHtml.append(" Gracias por su aporte. ").append("<br><br>");
        sbHtml.append("Atentamente,<br><br><br>Dirección General Académica.<br>");
        sbHtml.append("Telef: (593 2) 299 3669 / (593 2) 299 3670<br>");
        sbHtml.append("Quito, Ecuador<br>");
        HttpServletRequest o = (HttpServletRequest) fcontext.getExternalContext().getRequest();
        String s = o.getRequestURL().toString();
        sbHtml.append("<p align='left'><img src='").append(s.substring(0, s.indexOf("/", 15))).append(fcontext.getExternalContext().getRequestContextPath()).append("/resources/images/logocolor.png' alt='logouasb' width='225' height='75' /></p>");
        sbHtml.append("</body>");
        return sbHtml;
    }

    @Override
    public int veriEncRealizada(Long codigoAlumno, int codEncuesta, Long anio, int ciclo) {
        Query q;
        q = em.createNativeQuery("select count(*) from ENCUESTA_REALIZADA where CODIGO_ALUMNO = ? and ANIO = ? and CICLO = ?"
                + " and CODIGO_ENCUESTA = ? and CODIGO_MATERIA = ? and  CODIGO_PROFESOR = ? and TIPO_REGISTRO = 'A'"
                + " and CODIGO_ESP = ?  AND CODIGO_NIVEL = -1 ");
        q.setParameter(1, codigoAlumno);
        q.setParameter(2, anio);
        q.setParameter(3, ciclo);
        q.setParameter(4, codEncuesta);
        q.setParameter(5, -1);
        q.setParameter(6, -1);
        q.setParameter(7, -1);
        if ((Integer) q.getSingleResult() != 0) {
            return (Integer) q.getSingleResult();
        } else {
            return 0;
        }

    }

    @Override
    public String titTerNivel(Long codigoAlumno) {
        Query q;
        q = em.createNativeQuery(" SELECT case tipo_titulo when 'P' then nombre_titulo + '  /  '+tipo_institucion.nombre_tipin +' ' + INSTITUCION.NOMBRE_INST else ''end ter  FROM ESTUDIANTE,INSTITUCION  ,titulo,  tipo_institucion 	WHERE ( INSTITUCION.CODIGO_INST = ESTUDIANTE.CODIGO_INST )  and ( ESTUDIANTE.CODIGO_TITULO = titulo.codigo_titulo)and ESTUDIANTE.cod_estudiante = ?	and (  tipo_institucion.codigo_tipin = INSTITUCION.codigo_tipin ) ");
        q.setParameter(1, codigoAlumno);

        if ((String) q.getSingleResult() != null) {
            return (String) q.getSingleResult();
        } else {
            return null;
        }

    }

    @Override
    public String titCuarNivel(Long codigoAlumno) {
        Query q;
        q = em.createNativeQuery(" SELECT case tipo_titulo when 'G' then nombre_titulo + '  /  '+tipo_institucion.nombre_tipin +' ' + INSTITUCION.NOMBRE_INST else ''end cuart  FROM ESTUDIANTE,INSTITUCION  ,titulo,  tipo_institucion 	WHERE ( INSTITUCION.CODIGO_INST = ESTUDIANTE.CODIGO_INST )  and ( ESTUDIANTE.CODIGO_TITULO = titulo.codigo_titulo)and ESTUDIANTE.cod_estudiante = ?	and (  tipo_institucion.codigo_tipin = INSTITUCION.codigo_tipin ) ");
        q.setParameter(1, codigoAlumno);

        if ((String) q.getSingleResult() != null) {
            return (String) q.getSingleResult();
        } else {
            return null;
        }

    }
}
