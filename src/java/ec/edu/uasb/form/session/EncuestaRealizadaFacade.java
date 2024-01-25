/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.session;

import ec.edu.uasb.bean.exceptions.MatriculaException;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.form.datamodel.EncuAsigDispo;
import ec.edu.uasb.form.datamodel.EncuProgDispo;
import ec.edu.uasb.form.datamodel.ParticipacionCeremGradDispo;
import ec.edu.uasb.form.entities.EncuestaRealizada;
import ec.edu.uasb.form.entities.EncuestaRealizadaPK;
import ec.edu.uasb.form.entities.Respuesta;
import ec.edu.uasb.seg.entities.Usuario;
import ec.edu.uasb.session.MatriculaFacadeLocal;
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
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author victor.barba
 */
//@SqlResultSetMapping(
//      name = "DescripcionColumnAlias",
//columns = {
//    @ColumnResult(name = "CODIGO_MATERIA"),
//    @ColumnResult(name = "TITULO"),
//    @ColumnResult(name = "REFERENCIA"),
//    @ColumnResult(name = "CODIGO_ENCUESTA"),
//    @ColumnResult(name = "ANIO"),
//    @ColumnResult(name = "CICLO"),
//    @ColumnResult(name = "FECHA_INICIO"),
//    @ColumnResult(name = "FECHA_FIN"),
//    @ColumnResult(name = "CODIGO_PROFESOR"),
//    @ColumnResult(name = "NOMBRES"),
//    @ColumnResult(name = "NOMBRE_MATERIA"),
//    @ColumnResult(name = "ESTADO")}
//        )
@Stateless
public class EncuestaRealizadaFacade extends AbstractFacade<EncuestaRealizada> implements EncuestaRealizadaFacadeLocal {

    @EJB
    private MatriculaFacadeLocal matriculaFacade;
    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;
//    private static final Logger LOG = Logger.getLogger(EncuestaRealizadaFacade.class.getName());

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaRealizadaFacade() {
        super(EncuestaRealizada.class);
    }

    @Override
    public void enviaCorreo(String profile_name, String recipients, String copy_recipients, String blind_copy_recipients, String psubject, String pmensaje, String body_format) {
        Query query = em.createNativeQuery("{call msdb.dbo.sp_send_dbmail(?,?,?,?,?,?,?)}");
        query.setParameter(1, profile_name);
        query.setParameter(2, recipients);
        query.setParameter(3, copy_recipients);
        query.setParameter(4, blind_copy_recipients);
        query.setParameter(5, psubject);
        query.setParameter(6, pmensaje);
        query.setParameter(7, body_format);
        query.executeUpdate();
    }

//    private BigDecimal findSecuencialBy(String tabla) {
//        Query q = em.createNativeQuery("SELECT numseq FROM seqno  with(rowlock) WHERE tabla = ?");
//        q.setParameter(1, tabla);
//        return (BigDecimal) q.getSingleResult();
//    }
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

    @Override
    public List encuestasDisponibles(Long codEstud, Long anio, String tipo) {
        if (tipo.equals("EVAL_PROGRAMA")) {
            return getEvalProgramas(codEstud);
        } else if (tipo.equals("EVAL_ASIGNATURA")) {
            return getEvalAsignatura(codEstud, anio);
        } else if (tipo.equals("EVAL_TUTORIA")) {
            return getEvalTutoria(codEstud);
        } else if (tipo.equals("PARTICIPACION_CEREMONIA")) {
            return getParticipacionCeremoniaGrad(codEstud);
        } else {
            return getEvalSeguimiento(codEstud);
        }

    }

    private List getEvalProgramas(Long codEstud) {
        Query q;
        StringBuilder sb = new StringBuilder();

        sb.append("DECLARE @PROGRAMA_EVAL TABLE(CODIGO_ENCUESTA NUMERIC(10,0), TITULO VARCHAR(200), REFERENCIA VARCHAR(30), ANIO  NUMERIC(10,0), CICLO  NUMERIC(10,0),");
        sb.append(" CODIGO_NIVEL NUMERIC(10,0),CODIGO_PARALELO NUMERIC(10,0), FECHA_INICIO datetime, FECHA_FIN datetime,CODIGO_PROFESOR NUMERIC(10,0), ");
        sb.append(" CODIGO_ESP NUMERIC(10,0),ESTADO NUMERIC(1,0), HABILITADO  VARCHAR(1), PROGRAMA  VARCHAR(512),TIPO_ENCUESTA VARCHAR(1)) ");
        sb.append(" INSERT INTO @PROGRAMA_EVAL(CODIGO_ENCUESTA, TITULO, REFERENCIA, ANIO, CICLO, CODIGO_NIVEL,CODIGO_PARALELO, FECHA_INICIO,");
        sb.append(" FECHA_FIN ,CODIGO_PROFESOR, CODIGO_ESP,ESTADO,HABILITADO,PROGRAMA,TIPO_ENCUESTA)");
        sb.append(" SELECT DISTINCT ENC.CODIGO_ENCUESTA,ENC.TITULO,ENC.REFERENCIA,");
        sb.append(" ENCAL.ANIO,  ENCAL.CICLO,ENCAL.CODIGO_NIVEL, ENCAL.CODIGO_PARALELO,");
        sb.append(" ENCAL.FECHA_INICIO,ENCAL.FECHA_FIN,ENCAL.CODIGO_PROFESOR,");
        sb.append(" ENCAL.CODIGO_ESP,");
        sb.append(" (SELECT  count(1) FROM EVALUACION.ENCUESTA_REALIZADA ENCRE WITH (NOLOCK)  ");
        sb.append(" WHERE ENCRE.codigo_alumno = MAT.COD_ESTUDIANTE ");
        sb.append(" AND ENCRE.anio = ENCAL.ANIO and ciclo = ENCAL.CICLO ");
        sb.append(" AND ENCRE.codigo_esp = MAT.CODIGO_ESP and ENCRE.codigo_materia = ENCAL.CODIGO_MATERIA ");
        sb.append(" AND ENCRE.codigo_encuesta = ENC.CODIGO_ENCUESTA");
        sb.append(" AND  ENCRE.codigo_profesor = ENCAL.CODIGO_PROFESOR and ENCRE.TIPO_REGISTRO = 'A' AND ENCRE.CODIGO_NIVEL = -1) ESTADO,");
        sb.append(" IIF(getdate() between ENCAL.fecha_inicio and ENCAL.fecha_fin + 1,'S','N') HABILITADO,");
        sb.append(" MAT.PROGRAMA ,ENCAL.TIPO_ENCUESTA");
        sb.append(" FROM EVALUACION.ENCUESTA ENC WITH (NOLOCK) ,");
        sb.append(" EVALUACION.ENCUESTA_CALENDARIO ENCAL WITH (NOLOCK) ,");
        sb.append(" GESTIONACADEMICA.ESTUDIANTE_ULTIMAMATRICULA  MAT  WITH (NOLOCK)");
        sb.append(" WHERE ENCAL.CODIGO_ENCUESTA = ENC.CODIGO_ENCUESTA");
        sb.append(" AND ENCAL.ANIO = MAT.ANIO_INICIAL");
        sb.append(" AND ENCAL.CICLO = MAT.CICLO");
        sb.append(" AND ENCAL.CODIGO_ESP = MAT.CODIGO_ESP");
        sb.append(" AND MAT.COD_ESTUDIANTE = ? and ENC.USO='S'");
        sb.append(" SELECT DISTINCT * from @PROGRAMA_EVAL ");
        sb.append(" WHERE CODIGO_NIVEL = -1 AND CODIGO_PARALELO = -1 and FECHA_INICIO <= getdate()  and TIPO_ENCUESTA = 'P' ");

        q = em.createNativeQuery(sb.toString(), EncuProgDispo.class);
        return q.setParameter(1, codEstud).getResultList();

    }

    private List getEvalAsignatura(Long codEstud, Long anio) {
        Query q;
        StringBuilder sb = new StringBuilder();
        sb.append("EXECUTE interfaseOcu.dbo.sp_eval_asig_estudiante @codestudiante=");
        sb.append(codEstud);
        sb.append(", @codanio=");
        sb.append(anio);
        q = em.createNativeQuery(sb.toString(), EncuAsigDispo.class);
        //return q.setParameter(1, codEstud).setParameter(2, anio).getResultList();
        return q.getResultList();
    }

    private List getEvalTutoria(Long codEstud) {
        Query q;
        StringBuilder sb = new StringBuilder();
//sb.append(" SELECT distinct ENCUESTA.CODIGO_ENCUESTA,ENCUESTA.TITULO,ENCUESTA.REFERENCIA, ");
//        sb.append(" ENCUESTA_CALENDARIO.ANIO,  ENCUESTA_CALENDARIO.CICLO,  ");
//        sb.append(" ENCUESTA_CALENDARIO.FECHA_INICIO,ENCUESTA_CALENDARIO.FECHA_FIN,ENCUESTA_CALENDARIO.CODIGO_PROFESOR, ");
//        sb.append(" VISTA_ESTUDIANTE_TESIS_CICLO.NOM_TUTOR  NOMBRES,ENCUESTA_CALENDARIO.CODIGO_ESP,ENCUESTA_CALENDARIO.CODIGO_NIVEL,ENCUESTA_CALENDARIO.CODIGO_PARALELO, ");
//        sb.append("(select count(1)  from EVALUACION.ENCUESTA_REALIZADA WITH (NOLOCK) where codigo_alumno = VISTA_ESTUDIANTE_TESIS_CICLO.CODIGO_ESTUDIANTE  ");
//        sb.append(" and anio = ENCUESTA_CALENDARIO.ANIO and ciclo = ENCUESTA_CALENDARIO.CICLO and codigo_esp = VISTA_ESTUDIANTE_TESIS_CICLO.CODIGO_ESP and  ");
//        sb.append(" codigo_materia = ENCUESTA_CALENDARIO.CODIGO_MATERIA and codigo_encuesta = ENCUESTA.CODIGO_ENCUESTA ");
//        sb.append(" and codigo_profesor = ENCUESTA_CALENDARIO.CODIGO_PROFESOR  and TIPO_REGISTRO = 'A' AND CODIGO_NIVEL = -1) ESTADO, ");
//        sb.append(" IIF(getdate() between encuesta_calendario.fecha_inicio and encuesta_calendario.fecha_fin + 1,'S','N') HABILITADO, ");
//        sb.append("  VISTA_ESTUDIANTE_TESIS_CICLO.PROGRAMA ");
//        sb.append(" FROM EVALUACION.ENCUESTA WITH (NOLOCK) ,EVALUACION.ENCUESTA_CALENDARIO WITH (NOLOCK) ,VISTA_ESTUDIANTE_TESIS_CICLO WITH (NOLOCK) ");
//        sb.append(" WHERE ENCUESTA_CALENDARIO.CODIGO_ENCUESTA = ENCUESTA.CODIGO_ENCUESTA ");
//        sb.append(" and ENCUESTA_CALENDARIO.ANIO = VISTA_ESTUDIANTE_TESIS_CICLO.ANIO ");
//        sb.append(" and ENCUESTA_CALENDARIO.CODIGO_ESP = VISTA_ESTUDIANTE_TESIS_CICLO.CODIGO_ESP ");
//        sb.append(" and ENCUESTA_CALENDARIO.CODIGO_PROFESOR = VISTA_ESTUDIANTE_TESIS_CICLO.CODIGO_TUTOR ");
//        sb.append(" and ENCUESTA_CALENDARIO.cod_estudiante =  VISTA_ESTUDIANTE_TESIS_CICLO.CODIGO_ESTUDIANTE  ");
//        sb.append(" and ENCUESTA_CALENDARIO.CODIGO_NIVEL = -1 and ENCUESTA_CALENDARIO.CODIGO_PARALELO = -1 and ENCUESTA_CALENDARIO.FECHA_INICIO <= getdate()  ");
//        sb.append("  and ENCUESTA_CALENDARIO.TIPO_ENCUESTA = 'T' and VISTA_ESTUDIANTE_TESIS_CICLO.CODIGO_ESTUDIANTE = ? and ENCUESTA.USO='S'");
        sb.append(" DECLARE @TUTOR TABLE(ANIO  NUMERIC(10,0),CODIGO_ESTUDIANTE NUMERIC(10,0), CODIGO_ESP  NUMERIC(10,0), CODIGO_TUTOR  NUMERIC(10,0), PROGRAMA  VARCHAR(512),NOM_TUTOR  VARCHAR(512)) ")
                .append(" INSERT INTO @TUTOR select ANIO,CODIGO_ESTUDIANTE,CODIGO_ESP,CODIGO_TUTOR,PROGRAMA,NOM_TUTOR ")
                .append(" from VISTA_ESTUDIANTE_TESIS_CICLO where CODIGO_ESTUDIANTE = ? ")
                .append(" SELECT distinct ENCUESTA.CODIGO_ENCUESTA,ENCUESTA.TITULO,ENCUESTA.REFERENCIA,  ENCUESTA_CALENDARIO.ANIO, ")
                .append(" ENCUESTA_CALENDARIO.CICLO,   ENCUESTA_CALENDARIO.FECHA_INICIO,ENCUESTA_CALENDARIO.FECHA_FIN,ENCUESTA_CALENDARIO.CODIGO_PROFESOR, ")
                .append(" TUTOR.NOM_TUTOR  NOMBRES,ENCUESTA_CALENDARIO.CODIGO_ESP,ENCUESTA_CALENDARIO.CODIGO_NIVEL,ENCUESTA_CALENDARIO.CODIGO_PARALELO, ")
                .append(" (select count(1)  from EVALUACION.ENCUESTA_REALIZADA WITH (NOLOCK) where codigo_alumno = TUTOR.CODIGO_ESTUDIANTE  ")
                .append(" and anio = ENCUESTA_CALENDARIO.ANIO and ciclo = ENCUESTA_CALENDARIO.CICLO and codigo_esp = TUTOR.CODIGO_ESP  ")
                .append(" and   codigo_materia = ENCUESTA_CALENDARIO.CODIGO_MATERIA and codigo_encuesta = ENCUESTA.CODIGO_ENCUESTA ")
                .append(" and codigo_profesor = ENCUESTA_CALENDARIO.CODIGO_PROFESOR  and TIPO_REGISTRO = 'A' AND CODIGO_NIVEL = -1) ESTADO, ")
                .append(" IIF(getdate() between encuesta_calendario.fecha_inicio and encuesta_calendario.fecha_fin + 1,'S','N') HABILITADO, TUTOR.PROGRAMA ")
                .append(" FROM EVALUACION.ENCUESTA WITH (NOLOCK) ,EVALUACION.ENCUESTA_CALENDARIO WITH (NOLOCK) ,@TUTOR TUTOR ")
                .append(" WHERE ENCUESTA_CALENDARIO.CODIGO_ENCUESTA = ENCUESTA.CODIGO_ENCUESTA  ")
                .append(" and ENCUESTA_CALENDARIO.ANIO = TUTOR.ANIO and ENCUESTA_CALENDARIO.CODIGO_ESP = TUTOR.CODIGO_ESP ")
                .append(" and ENCUESTA_CALENDARIO.CODIGO_PROFESOR = TUTOR.CODIGO_TUTOR and ENCUESTA_CALENDARIO.cod_estudiante =  TUTOR.CODIGO_ESTUDIANTE  ")
                .append(" and ENCUESTA_CALENDARIO.CODIGO_NIVEL = -1 and ENCUESTA_CALENDARIO.CODIGO_PARALELO = -1 ")
                .append(" and ENCUESTA_CALENDARIO.FECHA_INICIO <= getdate()  and ENCUESTA_CALENDARIO.TIPO_ENCUESTA = 'T' and ENCUESTA.USO='S' ");
        q = em.createNativeQuery(sb.toString(), EncuProgDispo.class);
//        System.out.println(sb.toString());
        return q.setParameter(1, codEstud).getResultList();
    }

    private List getEvalSeguimiento(Long codEstud) {
        Query q;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT distinct ENCUESTA.CODIGO_ENCUESTA,ENCUESTA.TITULO,ENCUESTA.REFERENCIA,");
        sb.append(" ENCUESTA_CALENDARIO.ANIO,  ENCUESTA_CALENDARIO.CICLO,-1 CODIGO_NIVEL, -1 CODIGO_PARALELO,");
        sb.append(" ENCUESTA_CALENDARIO.FECHA_INICIO,ENCUESTA_CALENDARIO.FECHA_FIN,ENCUESTA_CALENDARIO.CODIGO_PROFESOR,ENCUESTA_CALENDARIO.CODIGO_ESP,");
        sb.append("(select count(*)  from EVALUACION.ENCUESTA_REALIZADA  WITH (NOLOCK) where codigo_alumno = V_ESTUDIANTE_MATRICULA_CICLO.COD_ESTUDIANTE  ");
        sb.append(" and anio = ENCUESTA_CALENDARIO.ANIO and ciclo = ENCUESTA_CALENDARIO.CICLO and codigo_esp = V_ESTUDIANTE_MATRICULA_CICLO.CODIGO_ESP and  ");
        sb.append(" codigo_materia = ENCUESTA_CALENDARIO.CODIGO_MATERIA and codigo_encuesta = ENCUESTA.CODIGO_ENCUESTA  ");
        sb.append(" and codigo_profesor = ENCUESTA_CALENDARIO.CODIGO_PROFESOR and TIPO_REGISTRO = 'A'  AND CODIGO_NIVEL = -1 ) ESTADO, ");
        sb.append(" IIF(getdate() between encuesta_calendario.fecha_inicio and encuesta_calendario.fecha_fin + 1,'S','N') HABILITADO, ");
        sb.append("  V_ESTUDIANTE_MATRICULA_CICLO.PROGRAMA ");
        sb.append(" FROM EVALUACION.ENCUESTA WITH (NOLOCK) ,EVALUACION.ENCUESTA_CALENDARIO WITH (NOLOCK) ,V_ESTUDIANTE_MATRICULA_CICLO WITH (NOLOCK)  ");
        sb.append(" WHERE ENCUESTA_CALENDARIO.CODIGO_ENCUESTA = ENCUESTA.CODIGO_ENCUESTA  ");
        sb.append(" and ENCUESTA_CALENDARIO.ANIO = V_ESTUDIANTE_MATRICULA_CICLO.ANIO  ");
        sb.append(" and ENCUESTA_CALENDARIO.CICLO = V_ESTUDIANTE_MATRICULA_CICLO.CICLO  ");
        sb.append(" and ENCUESTA_CALENDARIO.CODIGO_PARALELO = -1 ");
        // quitar and ENCUESTA_CALENDARIO.CODIGO_NIVEL = -1 and para OCU
        sb.append(" and  ENCUESTA_CALENDARIO.FECHA_INICIO <= getdate() and ENCUESTA_CALENDARIO.ESTADO_ENCUESTA = 'A'");
        sb.append(" and ENCUESTA_CALENDARIO.TIPO_ENCUESTA = 'S' and V_ESTUDIANTE_MATRICULA_CICLO.COD_ESTUDIANTE  = ? and ENCUESTA.USO='D'");
        q = em.createNativeQuery(sb.toString(), EncuProgDispo.class);
//        System.out.println(sb.toString());
//        Query q = em.createNativeQuery(sb.toString(), "DescripcionColumnAlias").setParameter(1, codEstud);
        return q.setParameter(1, codEstud).getResultList();

    }

    @Override
    public String createEncuesta(Long codigoAlumno, EncuAsigDispo asigEncuesta, List<Respuesta> respuestas, FacesContext context) {
        Query q;
        q = em.createNativeQuery("select count(*) from EVALUACION.ENCUESTA_REALIZADA  WITH (NOLOCK) where CODIGO_ALUMNO = ? and ANIO = ? and CICLO = ? and CODIGO_ENCUESTA = ? "
                + "and CODIGO_MATERIA = ? and  CODIGO_PROFESOR = ? and TIPO_REGISTRO = 'A' and CODIGO_ESP=? and CODIGO_NIVEL = ? and CODIGO_PARALELO = ?");
        q.setParameter(1, codigoAlumno);
        q.setParameter(2, asigEncuesta.getAnio());
        q.setParameter(3, asigEncuesta.getCiclo());
        q.setParameter(4, asigEncuesta.getCodigoEncuesta());
        q.setParameter(5, asigEncuesta.getEncuAsigDispoPK().getCodigoMateria());
        q.setParameter(6, asigEncuesta.getEncuAsigDispoPK().getCodigoProfesor());
        q.setParameter(7, asigEncuesta.getEncuAsigDispoPK().getCodigoEsp());
        q.setParameter(8, asigEncuesta.getEncuAsigDispoPK().getCodigoNivel());
        q.setParameter(9, asigEncuesta.getEncuAsigDispoPK().getCodigoParalelo());
        if ((Integer) q.getSingleResult() != 0) {
            return "<br/><br/>Encuesta ya fue realizada ! ";
        } else {
            EncuestaRealizada enc = new EncuestaRealizada();
            EncuestaRealizadaPK encPk = new EncuestaRealizadaPK(codigoAlumno, asigEncuesta.getAnio(), asigEncuesta.getCiclo(),
                    asigEncuesta.getEncuAsigDispoPK().getCodigoMateria(), asigEncuesta.getCodigoEncuesta(),
                    asigEncuesta.getEncuAsigDispoPK().getCodigoProfesor(), "A", asigEncuesta.getEncuAsigDispoPK().getCodigoEsp(),
                    asigEncuesta.getEncuAsigDispoPK().getCodigoNivel(), asigEncuesta.getEncuAsigDispoPK().getCodigoParalelo());
            enc.setEncuestaRealizadaPK(encPk);
            enc.setFecha(new Date());
            enc.setRealizada('1');
            try {
                this.create(enc);
                for (Respuesta r : respuestas) {
                    em.persist(r);
                }
            } catch (Exception e) {
                return "Error. No puedo generar Transacción (EncuestaRealizada).<br/><br/>Por Favor, informe al CallCenter de la Universidad Andina Simon Bolivar <br/>sobre este error.<br/><br/>Gracias !";
            }
            registraRecordatorio(codigoAlumno, context, enc);
        }
        return null;
    }

    @Override
    public String createEncuesta(Long codigoAlumno, EncuProgDispo encuProgDispo, List<Respuesta> respuestas, FacesContext context) {
        Query q;
        q = em.createNativeQuery("select count(*) from EVALUACION.ENCUESTA_REALIZADA  WITH (NOLOCK) where CODIGO_ALUMNO = ? and ANIO = ? and CICLO = ? and CODIGO_ENCUESTA = ? "
                + "and CODIGO_MATERIA = ? and  CODIGO_PROFESOR = ? and TIPO_REGISTRO = 'A' and CODIGO_ESP=? and CODIGO_NIVEL = ? and CODIGO_PARALELO = ?");
        q.setParameter(1, codigoAlumno);
        q.setParameter(2, encuProgDispo.getEncuProgDispoPK().getAnio());
        q.setParameter(3, encuProgDispo.getCiclo());
        q.setParameter(4, encuProgDispo.getEncuProgDispoPK().getCodigoEncuesta());
        q.setParameter(5, -1);
        q.setParameter(6, encuProgDispo.getCodigoProfesor());
        q.setParameter(7, encuProgDispo.getEncuProgDispoPK().getCodigoEsp());
        q.setParameter(8, encuProgDispo.getEncuProgDispoPK().getCodigoNivel());
        q.setParameter(9, encuProgDispo.getEncuProgDispoPK().getCodigoParalelo());
        if ((Integer) q.getSingleResult() != 0) {
            return "<br/><br/>Encuesta ya fue realizada ! ";
        } else {
            for (Iterator<Respuesta> it = respuestas.iterator(); it.hasNext();) {
                Respuesta r = it.next();
                try {
                    getEntityManager().persist(r);
                } catch (Exception e) {
                    return "Error. No puedo generar Transacción (respuestas).<br/><br/>Por Favor, informe al CallCenter de la Universidad Andina Simon Bolivar <br/>sobre este error.<br/><br/>Gracias !";
                }
            }
            EncuestaRealizada enc = new EncuestaRealizada();
            EncuestaRealizadaPK encPk = new EncuestaRealizadaPK(codigoAlumno, encuProgDispo.getEncuProgDispoPK().getAnio(),
                    encuProgDispo.getCiclo(), Long.parseLong("-1"), encuProgDispo.getEncuProgDispoPK().getCodigoEncuesta(),
                    encuProgDispo.getCodigoProfesor(), "A", encuProgDispo.getEncuProgDispoPK().getCodigoEsp(),
                    encuProgDispo.getEncuProgDispoPK().getCodigoNivel(), encuProgDispo.getEncuProgDispoPK().getCodigoParalelo());
            enc.setEncuestaRealizadaPK(encPk);
            enc.setFecha(new Date());
            enc.setRealizada('1');
            try {
                this.create(enc);
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

        Query query = em.createNativeQuery("select COD_ESTUDIANTE,CED_PAS_ESTUDIANTE,NOM_ESTUDIANTE,APELL_ESTUDIANTE,CLAVE,EMAIL from ESTUDIANTE  WITH (NOLOCK) where COD_ESTUDIANTE = ? ", Usuario.class);
        query.setParameter(1, codAlumno);
        Usuario user = (Usuario) query.getSingleResult();

        try {
            matr = matriculaFacade.getMatricula(codAlumno);
        } catch (MatriculaException ex) {
            Logger.getLogger(EncuestaRealizadaFacade.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        query = em.createNativeQuery(" select distinct TIPO_ENCUESTA from EVALUACION.ENCUESTA_CALENDARIO WITH (NOLOCK) where CODIGO_ENCUESTA= ?").setParameter(1, enc.getEncuestaRealizadaPK().getCodigoEncuesta());
        String tipoEncuesta = (String) query.getSingleResult();

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
        if (tipoEncuesta.equals("S")) {
            query.setParameter(12, "Encuesta de Calidad de Servicios");
        } else if (tipoEncuesta.equals("E")) {
            query.setParameter(12, "Participación Ceremonia Graduación");
        } else if (tipoEncuesta.equals("G")) {
            query.setParameter(12, "Encuesta Graduados");
        } else if (enc.getEncuestaRealizadaPK().getCodigoMateria() == -1 && enc.getEncuestaRealizadaPK().getCodigoProfesor() == -1) {
            query.setParameter(12, "Evaluacion del Programa");
        } else if (enc.getEncuestaRealizadaPK().getCodigoMateria() == -1 && enc.getEncuestaRealizadaPK().getCodigoProfesor() != -1) {
            query.setParameter(12, "Evaluacion de Tutoria");
        } else {
            query.setParameter(12, "Evaluación de la Asignatura");
        }
        query.setParameter(13, enc.getEncuestaRealizadaPK().getCodigoMateria());
        query.setParameter(14, enc.getEncuestaRealizadaPK().getCodigoNivel());
        query.executeUpdate();

        mensaje = this.genMensaje(matr, user, context, enc);

        sbDet.append("INSERT INTO PROGRAMA_RECORDATORIO ( CODIGO_RECORDATORIO,FECHA_PROGRAMA, MENSAJE_RECORDATORIO,");
        sbDet.append("USUARIO_CREA,USUARIO_ULTMODIFIC,FECHA_CREA,FECHA_ULTMODIFIC,ESTADO_PROGRAMA,FORMATO_MENSAJE_PROGRAMA, PERFIL_CORREO) ");
        sbDet.append("VALUES ( ?,convert (datetime,CONVERT(char, convert(datetime, getdate()),112) ),?,'iUASB','iUASB',getdate(),getdate(),'A','H','Eval')");
        query = em.createNativeQuery(sbDet.toString());
        query.setParameter(1, sec);
        query.setParameter(2, mensaje.toString());
        query.executeUpdate();

    }

    private StringBuilder genMensaje(Matricula matr, Usuario user, FacesContext fcontext, EncuestaRealizada enc) {
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE d 'de' MMMM 'de' yyyy", new Locale("es", "EC"));
        StringBuilder sbHtml = new StringBuilder();

        Query q = em.createNativeQuery(" select distinct TIPO_ENCUESTA from EVALUACION.ENCUESTA_CALENDARIO  WITH (NOLOCK)  where CODIGO_ENCUESTA = ?").setParameter(1, enc.getEncuestaRealizadaPK().getCodigoEncuesta());
        String tipoEncuesta = (String) q.getSingleResult();

        sbHtml.append("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'><html xmlns='http://www.w3.org/1999/xhtml'>");
        sbHtml.append("<head><meta http-equiv='Content-Type' content='text/html; charset=utf-8' /><title></title>");
        sbHtml.append("</head>");
        sbHtml.append("<body>");
        sbHtml.append(" Estimado(a) ").append(user.getUsuNombreUsuario().toUpperCase()).append(" ").append(user.getUsuApellUsuario().toUpperCase()).append("<br><br><br>");
        if (tipoEncuesta.equals("S")) {
            sbHtml.append(" El ").append(formatter.format(new Date())).append(" ha realizado la Encuesta de Calidad de Servicios. <br><br>");
        } else if (tipoEncuesta.equals("E")) {
            sbHtml.append(" El ").append(formatter.format(new Date())).append(" ha respondido sobre la Participación de Ceremonia Graduación. <br><br>");
        } else if (tipoEncuesta.equals("G")) {
            sbHtml.append(" El ").append(formatter.format(new Date())).append(" ha realizado la Encuesta de Seguimiento de Graduados. <br><br>");
        } else {
            sbHtml.append(" El ").append(formatter.format(new Date())).append(" ha realizado la evaluación de <br><br>");
            if (enc.getEncuestaRealizadaPK().getCodigoMateria() == -1 && enc.getEncuestaRealizadaPK().getCodigoProfesor() == -1) {
                sbHtml.append(" Programa: ").append(matr.getPrograma().toUpperCase()).append("<br><br>");
            } else if (enc.getEncuestaRealizadaPK().getCodigoMateria() == -1 && enc.getEncuestaRealizadaPK().getCodigoProfesor() != -1) {
                StringBuilder sbQuery = new StringBuilder("Select ");
                sbQuery.append(" 'Tutoria del '+");
                sbQuery.append(" 'Profesor: '+NOMBRE_PROFESOR +' '+APELLIDO_PROFESOR+'<br>' +");
                sbQuery.append("  CICLO_ACADEMICO.NOMBRE_CICLO  +'<br>' ");
                sbQuery.append(" from EVALUACION.ENCUESTA_REALIZADA WITH (NOLOCK) , PROFESOR WITH (NOLOCK) ,CICLO_ACADEMICO  WITH (NOLOCK) ");
                sbQuery.append(" where CICLO_ACADEMICO.ANIO = ENCUESTA_REALIZADA.ANIO and CICLO_ACADEMICO.CICLO = ENCUESTA_REALIZADA.CICLO ");
                sbQuery.append(" and PROFESOR.CODIGO_PROFESOR  = ENCUESTA_REALIZADA.CODIGO_PROFESOR ");
                sbQuery.append(" and ENCUESTA_REALIZADA.ANIO = ? and ENCUESTA_REALIZADA.CICLO = 1 and ENCUESTA_REALIZADA.CODIGO_MATERIA = ? and  ENCUESTA_REALIZADA.CODIGO_PROFESOR = ? ");
                sbQuery.append(" and ENCUESTA_REALIZADA.CODIGO_ALUMNO = ? and ENCUESTA_REALIZADA.CODIGO_ENCUESTA = ?  and TIPO_REGISTRO = 'A'  and ENCUESTA_REALIZADA.CODIGO_ESP=? ");
                sbQuery.append("  and ENCUESTA_REALIZADA.CODIGO_NIVEL = -1  and ENCUESTA_REALIZADA.CODIGO_PARALELO = -1 ");
                q = em.createNativeQuery(sbQuery.toString());
                q.setParameter(1, enc.getEncuestaRealizadaPK().getAnio());
                q.setParameter(2, -1);
                q.setParameter(3, enc.getEncuestaRealizadaPK().getCodigoProfesor());
                q.setParameter(4, enc.getEncuestaRealizadaPK().getCodigoAlumno());
                q.setParameter(5, enc.getEncuestaRealizadaPK().getCodigoEncuesta());
                q.setParameter(6, enc.getEncuestaRealizadaPK().getCodigoEsp());
                sbHtml.append(q.getSingleResult().toString().toUpperCase()).append("<br><br>");
            } else {
                StringBuilder sbQuery = new StringBuilder("Select distinct ");
//                sbQuery.append(" 'Asignatura: '+NOMBRE_MATERIA+'<br>' +");
//                sbQuery.append(" 'Trimestre: '+NOMBRE_NIVEL +'<br>' +");
//                sbQuery.append("  NOMBRE_CICLO +'<br>' +");
//                sbQuery.append(" 'Profesor: '+NOMBRE_PROFESOR +' '+APELLIDO_PROFESOR+'<br>' ");
//                sbQuery.append("  from CICLO_ACADEMICO WITH (NOLOCK) ,VISTA_ESTUDIANTES_MATERIA_PROFESOR WITH (NOLOCK) ,EVALUACION.ENCUESTA_REALIZADA WITH (NOLOCK) ");
//                sbQuery.append(" where CICLO_ACADEMICO.ANIO = VISTA_ESTUDIANTES_MATERIA_PROFESOR.ANIO AND CICLO_ACADEMICO.CICLO = VISTA_ESTUDIANTES_MATERIA_PROFESOR.CICLO ");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.ANIO = VISTA_ESTUDIANTES_MATERIA_PROFESOR.ANIO ");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.CICLO = VISTA_ESTUDIANTES_MATERIA_PROFESOR.CICLO ");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.CODIGO_MATERIA = VISTA_ESTUDIANTES_MATERIA_PROFESOR.CODIGO_MATERIA  ");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.CODIGO_ALUMNO = VISTA_ESTUDIANTES_MATERIA_PROFESOR.CODIGO_ESTUDIANTE  ");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.CODIGO_NIVEL = VISTA_ESTUDIANTES_MATERIA_PROFESOR.CODIGO_NIVEL  ");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.CODIGO_PARALELO = VISTA_ESTUDIANTES_MATERIA_PROFESOR.COD_PARALELO  ");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.CODIGO_PROFESOR = VISTA_ESTUDIANTES_MATERIA_PROFESOR.CODIGO_PROFESOR  ");
//                //sbQuery.append(" AND ENCUESTA_REALIZADA.CODIGO_ESP = VISTA_ESTUDIANTES_MATERIA_PROFESOR.CODIGO_ESP  ");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.ANIO = ? AND ENCUESTA_REALIZADA.CICLO = 1 AND ENCUESTA_REALIZADA.CODIGO_MATERIA = ? AND  ENCUESTA_REALIZADA.CODIGO_PROFESOR = ? ");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.CODIGO_ALUMNO = ? AND ENCUESTA_REALIZADA.CODIGO_ENCUESTA = ?   and TIPO_REGISTRO = 'A'");
//                sbQuery.append(" AND ENCUESTA_REALIZADA.CODIGO_ESP = ? and ENCUESTA_REALIZADA.CODIGO_NIVEL = ? and ENCUESTA_REALIZADA.CODIGO_PARALELO = ?");
                sbQuery.append(" 'Asignatura: '+MAT.NOMBRE_MATERIA+'<br>' +");
                sbQuery.append(" 'Trimestre: '+(CASE  ENCRE.CODIGO_NIVEL WHEN 1 THEN 'PRIMER TRIMESTRE' WHEN 2 THEN 'SEGUNDO TRIMESTRE' WHEN 3 THEN 'TERCER TRIMESTRE'WHEN 4 THEN 'CUARTO TRIMESTRE' WHEN 5 THEN 'QUINTO TRIMESTRE' WHEN 6 THEN 'SEXTO TRIMESTRE' WHEN 7 THEN 'SEPTIMO TRIMESTRE' WHEN 8 THEN 'OCTAVO TRIMESTRE' END) +'<br>' +");
                sbQuery.append("  NOMBRE_CICLO +'<br>' +");
                sbQuery.append(" 'Profesor: '+PROF.NOMBRE_PROFESOR +' '+PROF.APELLIDO_PROFESOR+'<br>' ");
                sbQuery.append("  FROM CICLO_ACADEMICO WITH (NOLOCK),EVALUACION.ENCUESTA_REALIZADA ENCRE WITH (NOLOCK), ");
                sbQuery.append(" MATERIA MAT WITH (NOLOCK),PROFESOR PROF WITH (NOLOCK) ");
                sbQuery.append(" where CICLO_ACADEMICO.ANIO = ENCRE.ANIO AND ENCRE.CODIGO_MATERIA = MAT.CODIGO_MATERIA  ");
                sbQuery.append(" AND ENCRE.CODIGO_PROFESOR = PROF.CODIGO_PROFESOR ");
                sbQuery.append(" AND ENCRE.ANIO = ? AND ENCRE.CICLO = 1 AND ENCRE.CODIGO_MATERIA = ? AND  ENCRE.CODIGO_PROFESOR = ? ");
                sbQuery.append(" AND ENCRE.CODIGO_ALUMNO = ? AND ENCRE.CODIGO_ENCUESTA = ?   and ENCRE.TIPO_REGISTRO = 'A' ");
                sbQuery.append(" AND ENCRE.CODIGO_ESP = ? and ENCRE.CODIGO_NIVEL = ? and ENCRE.CODIGO_PARALELO = ? ");
                q = em.createNativeQuery(sbQuery.toString());
                q.setParameter(1, enc.getEncuestaRealizadaPK().getAnio());
                q.setParameter(2, enc.getEncuestaRealizadaPK().getCodigoMateria());
                q.setParameter(3, enc.getEncuestaRealizadaPK().getCodigoProfesor());
                q.setParameter(4, enc.getEncuestaRealizadaPK().getCodigoAlumno());
                q.setParameter(5, enc.getEncuestaRealizadaPK().getCodigoEncuesta());
                q.setParameter(6, enc.getEncuestaRealizadaPK().getCodigoEsp());
                q.setParameter(7, enc.getEncuestaRealizadaPK().getCodigoNivel());
                q.setParameter(8, enc.getEncuestaRealizadaPK().getCodigoParalelo());
                sbHtml.append(q.getSingleResult().toString().toUpperCase()).append("<br><br>");

            }
        }
        sbHtml.append(" Gracias por su aporte. ").append("<br><br>");
        if (!tipoEncuesta.equals("E")) {
            sbHtml.append("Atentamente,<br><br><br>Dirección General Académica.<br>");
            sbHtml.append("Telef: (593 2) 299 3669 / (593 2) 299 3670<br>");
            sbHtml.append("Quito, Ecuador<br>");
        } else {
            sbHtml.append("Atentamente,<br><br><br>Secretaría General.<br>");
            sbHtml.append("Telef: (593 2) 3 228 085 ext. 1207<br>");
            sbHtml.append("Quito, Ecuador<br>");
        }

        HttpServletRequest o = (HttpServletRequest) fcontext.getExternalContext().getRequest();
        String s = o.getRequestURL().toString();
        sbHtml.append("<p align='left'><img src='").append(s.substring(0, s.indexOf("/", 15))).append(fcontext.getExternalContext().getRequestContextPath()).append("/resources/images/logocolor.png' alt='logouasb' width='225' height='75' /></p>");
        sbHtml.append("</body>");
        return sbHtml;
    }

    private List getParticipacionCeremoniaGrad(Long codEstud) {
        Query q;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT distinct E.CODIGO_ENCUESTA,E.TITULO,E.REFERENCIA,");
        sb.append(" C.ANIO,  C.CICLO,-1 CODIGO_NIVEL, -1 CODIGO_PARALELO,");
        sb.append(" C.FECHA_INICIO,C.FECHA_FIN,C.CODIGO_PROFESOR,G.CODIGO_ESP,");
        sb.append("(select count(*)  from EVALUACION.ENCUESTA_REALIZADA  AS R WITH (NOLOCK) where codigo_alumno = G.PCG_CODIGO_ESTUDIANTE ");
        sb.append(" and anio = C.ANIO and codigo_esp = G.CODIGO_ESP and   ");
        sb.append(" codigo_materia = C.CODIGO_MATERIA and codigo_encuesta = E.CODIGO_ENCUESTA   ");
        sb.append(" and codigo_profesor = C.CODIGO_PROFESOR and TIPO_REGISTRO = 'A'  AND CODIGO_NIVEL = -1 ) ESTADO, IIF(getdate() between C.fecha_inicio and C.fecha_fin + 1,'S','N') HABILITADO,   ");
        sb.append(" C.FECHA_GRUPO,C.FECHA_CEREMONIA, C.FECHA_ALQUILER,C.CORTE  ");
        sb.append(" FROM EVALUACION.ENCUESTA AS E WITH (NOLOCK) ,EVALUACION.ENCUESTA_CALENDARIO AS C WITH (NOLOCK) ,interfaseOcu.EVALUACION.PCG_ESTUDIANTES_GRADUACION AS G WITH (NOLOCK)   ");
        sb.append(" WHERE C.CODIGO_ENCUESTA = E.CODIGO_ENCUESTA    ");
        sb.append(" and C.ANIO =G.PCG_ANIO   ");
        sb.append(" and C.CODIGO_PARALELO = -1  ");
        sb.append(" and C.FECHA_INICIO <= getdate() and C.ESTADO_ENCUESTA = 'A'");
        sb.append(" and C.TIPO_ENCUESTA = 'E' and  G.PCG_CODIGO_ESTUDIANTE  = ? and E.USO='D' and G.PCG_VALIDADO=1");
        q = em.createNativeQuery(sb.toString(), ParticipacionCeremGradDispo.class);
        return q.setParameter(1, codEstud).getResultList();

    }

    @Override
    public String createEncuesta(Long codigoAlumno, ParticipacionCeremGradDispo partCeremGradDispo, List<Respuesta> respuestas, FacesContext context) {
        Query q;
        q = em.createNativeQuery("select count(*) from EVALUACION.ENCUESTA_REALIZADA  WITH (NOLOCK) where CODIGO_ALUMNO = ? and ANIO = ? and CICLO = ? and CODIGO_ENCUESTA = ? "
                + "and CODIGO_MATERIA = ? and  CODIGO_PROFESOR = ? and TIPO_REGISTRO = 'A' and CODIGO_ESP=? and CODIGO_NIVEL = ? and CODIGO_PARALELO = ?");
        q.setParameter(1, codigoAlumno);
        q.setParameter(2, partCeremGradDispo.getParticipacionCeremGradDispoPK().getAnio());
        q.setParameter(3, partCeremGradDispo.getCiclo());
        q.setParameter(4, partCeremGradDispo.getParticipacionCeremGradDispoPK().getCodigoEncuesta());
        q.setParameter(5, -1);
        q.setParameter(6, partCeremGradDispo.getCodigoProfesor());
        q.setParameter(7, partCeremGradDispo.getParticipacionCeremGradDispoPK().getCodigoEsp());
        q.setParameter(8, partCeremGradDispo.getParticipacionCeremGradDispoPK().getCodigoNivel());
        q.setParameter(9, partCeremGradDispo.getParticipacionCeremGradDispoPK().getCodigoParalelo());
        if ((Integer) q.getSingleResult() != 0) {
            return "<br/><br/>Encuesta ya fue realizada ! ";
        } else {
            for (Iterator<Respuesta> it = respuestas.iterator(); it.hasNext();) {
                Respuesta r = it.next();
                try {
                    getEntityManager().persist(r);
                } catch (Exception e) {
                    return "Error. No puedo generar Transacción (respuestas).<br/><br/>Por Favor, informe al CallCenter de la Universidad Andina Simon Bolivar <br/>sobre este error.<br/><br/>Gracias !";
                }
            }
            EncuestaRealizada enc = new EncuestaRealizada();
            EncuestaRealizadaPK encPk = new EncuestaRealizadaPK(codigoAlumno, partCeremGradDispo.getParticipacionCeremGradDispoPK().getAnio(),
                    partCeremGradDispo.getCiclo(), Long.parseLong("-1"), partCeremGradDispo.getParticipacionCeremGradDispoPK().getCodigoEncuesta(),
                    partCeremGradDispo.getCodigoProfesor(), "A", partCeremGradDispo.getParticipacionCeremGradDispoPK().getCodigoEsp(),
                    partCeremGradDispo.getParticipacionCeremGradDispoPK().getCodigoNivel(), partCeremGradDispo.getParticipacionCeremGradDispoPK().getCodigoParalelo());
            enc.setEncuestaRealizadaPK(encPk);
            enc.setFecha(new Date());
            enc.setRealizada('1');
            try {
                this.create(enc);
            } catch (Exception e) {
                return "Error. No puedo generar Transacción (EncuestaRealizada).<br/><br/>Por Favor, informe al CallCenter de la Universidad Andina Simon Bolivar <br/>sobre este error.<br/><br/>Gracias !";
            }
            registraRecordatorio(codigoAlumno, context, enc);
        }
        return null;
    }

}
