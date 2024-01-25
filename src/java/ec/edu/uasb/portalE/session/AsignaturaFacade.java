/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.session;

import ec.edu.uasb.portalE.entities.AsignaturaModel;
import ec.edu.uasb.portalE.entities.Horario;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class AsignaturaFacade extends AbstractFacade<AsignaturaModel> implements AsignaturaFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AsignaturaFacade() {
        super(AsignaturaModel.class);
    }

    @Override
    public Long getSecuencial(String sistema, String campo) {
        Query q = em.createNativeQuery("select PRM_VALOR_PARAM from PRIN_PARAMETRIZACION with(NOLOCK) where PRM_SISTEMA = ? and PRM_NOMBRE_PARAM = ?");
        BigDecimal sec = (BigDecimal) q.setParameter(1, sistema).setParameter(2, campo).getSingleResult();
        return sec.longValue();
    }

    @Override
    public List<AsignaturaModel> getListaAsignaturasxAnio(Long codEstud, Long anio) {
        StringBuilder sb = new StringBuilder();
//        sb.append(" SELECT CODIGO_MATERIA,IDENTIF_MATERIA,NOMBRE_MATERIA,CODIGO_NIVEL,NOMBRE_NIVEL,ESTADO_SYLABUS, ");
//        sb.append(" SIGLA_TMATERIA,NOTA_NTCICLO, ASISTENCIA_NTCICLO, MATNIV_ESTADO,ESTADO_REGMAT,ESTADO_NTCICLO, ");
//        sb.append(" EVALUACION_REALIZADA,ESTADO_PAGESTAMPILLA,CIC_ANIO,VAC_CODNUM,GAC_CODNUM,NCRED_REGMAT ");
//        sb.append(" FROM V_NOTAS_ESTUDIANTE  WITH (NOLOCK) ");
//        sb.append(" WHERE  COD_ESTUDIANTE = ? And  CIC_ANIO = ? ");
//        sb.append(" ORDER BY CODIGO_NIVEL ASC,     NOMBRE_MATERIA ASC");

        sb.append(" SELECT DISTINCT VNE.CODIGO_MATERIA,VNE.IDENTIF_MATERIA,VNE.NOMBRE_MATERIA,VNE.CODIGO_NIVEL,VNE.NOMBRE_NIVEL,ESTADO_SYLABUS,VNE.FECHA_CREA,  SIGLA_TMATERIA, ")
                .append(" NOTA_NTCICLO,  ASISTENCIA_NTCICLO,  MATNIV_ESTADO, ESTADO_REGMAT, ESTADO_NTCICLO, EVALUACION_REALIZADA,")
                .append(" ESTADO_PAGESTAMPILLA,CIC_ANIO,VNE.VAC_CODNUM,GAC_CODNUM,NCRED_REGMAT,ICP.NOMBRE_PARALELO ")
                .append(" FROM V_NOTAS_ESTUDIANTE VNE WITH (NOLOCK)  ")
                .append(" INNER JOIN GESTIONACADEMICA.INFO_COORDINADOR_PROGRAMA ICP ")
                .append(" ON (VNE.CODIGO_MATERIA = ICP.CODIGO_MATERIA AND  VNE.CIC_ANIO = ICP.ANIO AND VNE.CODIGO_ESP = ICP.CODIGO_ESP AND VNE.CODIGO_NIVEL = ICP.CODIGO_NIVEL ")
                .append(" AND VNE.COD_PARALELO = ICP.COD_PARALELO AND VNE.VAC_CODNUM = ICP.VAC_CODNUM)  ")
                .append(" WHERE  VNE.COD_ESTUDIANTE = ? And  VNE.CIC_ANIO = ? ")
                .append("  ORDER BY VNE.CODIGO_NIVEL ASC,     VNE.NOMBRE_MATERIA ASC ");

        Query q = em.createNativeQuery(sb.toString(), AsignaturaModel.class);
        q.setParameter(1, codEstud).setParameter(2, anio);
        return q.getResultList();

    }

//    @Override
//    public List<AsignaturaModel> getListaAsignaturas(Long codEstud, Long codMatricula) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(" SELECT CODIGO_MATERIA,IDENTIF_MATERIA,NOMBRE_MATERIA,CODIGO_NIVEL,NOMBRE_NIVEL,ESTADO_SYLABUS, ");
//        sb.append(" SIGLA_TMATERIA,NOTA_NTCICLO, ASISTENCIA_NTCICLO,NCRED_REGMAT, EVALUACION_CALENDARIZADA,MATNIV_ESTADO,ESTADO_REGMAT,ESTADO_NTCICLO, ");
//        sb.append(" dbo.f_encu_eval_realizada(COD_ESTUDIANTE,CIC_ANIO,CIC_CICLO,CODIGO_MATERIA,CODIGO_NIVEL) EVALUACION_REALIZADA,ESTADO_PAGESTAMPILLA,CIC_ANIO ");
//        sb.append(" FROM V_NOTAS_ESTUDIANTE ");
//        sb.append(" WHERE  COD_ESTUDIANTE = ? And  NUM_MATRICULA = ? ");
//        sb.append(" ORDER BY CODIGO_NIVEL ASC,     NOMBRE_MATERIA ASC");
//        Query q = em.createNativeQuery(sb.toString(), AsignaturaModel.class);
//        q.setParameter(1, codEstud).setParameter(2, codMatricula);
//        return q.getResultList();
//    }
    @Override
    public List<AsignaturaModel> getListaAsignaturas(Long codEstud, Long codMatricula) {
        StringBuilder sb = new StringBuilder();
        sb.append(" SELECT CODIGO_MATERIA,IDENTIF_MATERIA,NOMBRE_MATERIA,CODIGO_NIVEL,NOMBRE_NIVEL,ESTADO_SYLABUS, ");
        sb.append(" SIGLA_TMATERIA,NOTA_NTCICLO, ASISTENCIA_NTCICLO,MATNIV_ESTADO,ESTADO_REGMAT,ESTADO_NTCICLO, ");
        sb.append(" 0 EVALUACION_REALIZADA,ESTADO_PAGESTAMPILLA,CIC_ANIO,VAC_CODNUM,GAC_CODNUM ");
        sb.append(" FROM V_NOTAS_ESTUDIANTE ");
        sb.append(" WHERE  COD_ESTUDIANTE = ? ");
        sb.append(" ORDER BY CODIGO_NIVEL ASC,     NOMBRE_MATERIA ASC");
        Query q = em.createNativeQuery(sb.toString(), AsignaturaModel.class);
        q.setParameter(1, codEstud).setParameter(2, codMatricula);
        return q.getResultList();
    }

    @Override
    public List<Horario> getHorarioPorAsig(Long ejercicio, Long asignatura, Long vacCodnum, Long gacCodnum) {
        StringBuilder sb = new StringBuilder();
        sb.append(" EXEC interfaseOcu.dbo.sp_getHorarios   @ejercicio = ?,  @asinatura = ? , @vacCodnum = ?, @codnum = ? ");
        Query q = em.createNativeQuery(sb.toString(), Horario.class);
        q.setParameter(1, ejercicio).setParameter(2, asignatura).setParameter(3, vacCodnum).setParameter(4, gacCodnum);
        return q.getResultList();

    }

    @Override
    public String getNombreParalelo(Long anio, Long codAsig, Long codEsp, Long codGac, Long codNivel) {
        String nomParalelo = null;
        StringBuilder sb = new StringBuilder("SELECT NOMBRE_PARALELO FROM interfaseOcu.GESTIONACADEMICA.INFO_COORDINADOR_PROGRAMA ");
        sb.append("WHERE RESPONSABLE_MATERIA = 'S' AND ANIO = ? AND CODIGO_MATERIA = ? AND CODIGO_ESP = ? AND COD_PARALELO = ? AND CODIGO_NIVEL = ?");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter(1, anio).setParameter(2, codAsig).setParameter(3, codEsp).setParameter(4, codGac).setParameter(5, codNivel);
        try {
            nomParalelo = (String) q.getSingleResult();
        } catch (NoResultException ex) {
        }
        return nomParalelo;
    }

//    @Override
//    public List<Horario> getHorarioPorAsig(Long ejercicio, Long asignatura, Long vacCodnum, Long gacCodnum, Long trimestre) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(" select DISTINCT CALEVENTO_FECHA,CALEVENTO_HEMPIEZA, CALEVENTO_HTERMINA,INSTALACION_ASIGNADA,  ")
//                .append(" ROW_NUMBER() Over (ORDER BY CALEVENTO_CODIGO) ORDINAL, ")
//                .append(" (Case  when CALEVENTO_FECHA < getdate() then -1 when CALEVENTO_FECHA = getdate() then 0 else 1 end) CURR ")
//                .append(" from gestionaulasSIU.dbo.VSIU_CALENDARIO_INSTALACIONES ")
//                .append(" WHERE CALEVENTO_ESTADO = 'A' AND EJERCICIO_CODIGO = ? and ASIGNATURA_CODIGO =?")
//                .append(" and substring(NIVEL_CODIGO,1,1) = ? AND ACTIVIDAD_CODIGO = ? AND GRUPO_ACTIVIDAD_CODIGO = ? ORDER BY ORDINAL");
//        Query q = em.createNativeQuery(sb.toString(), Horario.class);
//        q.setParameter(1, ejercicio).setParameter(2, asignatura).setParameter(3, trimestre).setParameter(4, vacCodnum).setParameter(5, gacCodnum);
//        return q.getResultList();
//
//    }
}
