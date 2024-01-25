/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.session;

import ec.edu.uasb.portalE.entities.Certificado;
import ec.edu.uasb.seg.entities.Usuario;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author victor.barba
 */
@Stateless
public class CertificadoFacade extends AbstractFacade<Certificado> implements CertificadoFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CertificadoFacade() {
        super(Certificado.class);
    }

    @Override
    public Certificado findByNroCertif(String nroCertif) {
        Certificado certificado = null;
        Query q = em.createNamedQuery("Certificado.findByCerNumero").setParameter("cerNumero", nroCertif);
        try {
            certificado = (Certificado) q.getSingleResult();
        } catch (NoResultException ex) {
        }
        return certificado;
    }

    @Override
    public List<Certificado> findAllBy(Usuario user) {
        Query q = em.createNamedQuery("Certificado.findAllByUser").setParameter("codEstudiante", user.getUsuCodigo());
        return q.setHint("eclipselink.refresh", true).getResultList();
    }

    @Override
    public BigDecimal findPersonaBy(String id) {
        Query q = em.createNativeQuery("select PER_CODIGO from PRIN_PERSONA where PER_ID_DOC = ?").setParameter(1, id);
        return (BigDecimal) q.getSingleResult();
    }

    @Override
    public void genDocuParaFirma(String rep, String docuXfirmar, String URLVerif, Map<String, Object> params, Certificado certificado) throws Exception {
        Connection conn = null;
        try {
            Query q = em.createNativeQuery("select PRM_VALOR_PARAM from PRIN_PARAMETRIZACION with(NOLOCK) where PRM_SISTEMA = ? and PRM_NOMBRE_PARAM = ?");
            BigDecimal sec = (BigDecimal) q.setParameter(1, "PORTAL_ESTUDIANTES").setParameter(2, "SECUENCIAL_CERTIF_ESTUD").getSingleResult();

            conn = em.unwrap(java.sql.Connection.class);
            params.put("pCodigoQR", URLVerif + "CERT-" + StringUtils.leftPad(sec.toString(), 6, "0"));
            params.put(JRParameter.REPORT_LOCALE, new Locale("es_EC"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(rep, params, conn);
            JasperExportManager.exportReportToPdfFile(jasperPrint, docuXfirmar);

            certificado.setCerFechaCrea(new Date());
            certificado.setCerNumero("CERT-" + StringUtils.leftPad(sec.toString(), 6, "0"));
            edit(certificado);

            q = em.createNativeQuery("UPDATE PRIN_PARAMETRIZACION SET PRM_VALOR_PARAM = PRM_VALOR_PARAM + 1 where PRM_SISTEMA = ? and PRM_NOMBRE_PARAM = ?");
            q.setParameter(1, "PORTAL_ESTUDIANTES").setParameter(2, "SECUENCIAL_CERTIF_ESTUD").executeUpdate();

        } catch (JRException ex) {
            throw new Exception(ex);
        } finally {
            closeConnection(conn);
        }
    }

    private void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            //  System.out.println("Developer Msg : Exception in printReport1Servlet.closeConnection()");  
        }
    }

    private Long getSecuencial(String sistema, String campo) {
        Query q = em.createNativeQuery("select PRM_VALOR_PARAM from PRIN_PARAMETRIZACION with(NOLOCK) "
                + " where PRM_SISTEMA = ? and PRM_NOMBRE_PARAM = ?");
        BigDecimal sec = (BigDecimal) q.setParameter(1, sistema).setParameter(2, campo).getSingleResult();
        q = em.createNativeQuery("UPDATE PRIN_PARAMETRIZACION SET PRM_VALOR_PARAM = PRM_VALOR_PARAM + 1 "
                + " where PRM_SISTEMA = ? and PRM_NOMBRE_PARAM = ?");
        q.setParameter(1, sistema).setParameter(2, campo).executeUpdate();
        return sec.longValue();
    }

    @Override
    public void edit(Certificado entity) {
        if (entity.getCerFechaCrea() != null) {
            if (entity.getCerNumero() == null) {
                Long sec = getSecuencial("PORTAL_ESTUDIANTES", "SECUENCIAL_CERTIF_ESTUD");
                entity.setCerNumero("CERT-" + StringUtils.leftPad(sec.toString(), 6, "0"));
            }
        }
        super.edit(entity);
        em.flush();
    }

    @Override
    public Certificado findByCerNumero(String cerNumero) {
        Certificado certificado = null;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * ,CERTIF.COD_ESTUDIANTE,CED_PAS_ESTUDIANTE,NOM_ESTUDIANTE,APELL_ESTUDIANTE,CLAVE,EMAIL,USULDAP,DN ")
                .append("FROM GESTIONACADEMICA.CERTIFICADO CERTIF ")
                .append("INNER JOIN interfaseOcu.dbo.ESTUDIANTE ESTUD on (CERTIF.COD_ESTUDIANTE = ESTUD.COD_ESTUDIANTE) ")
                .append("WHERE CERTIF.CER_NUMERO = ? ");

        Query q = em.createNativeQuery(sb.toString(), "ResultadoCertificado").setParameter(1, cerNumero);
        List temp = q.getResultList();

        for (Object record : temp) {
            Object[] reg = (Object[]) record;
            Certificado certif = (Certificado) reg[0];
            Usuario usuario = (Usuario) reg[1];
            certif.setUsuario(usuario);
            certificado = certif;
        }
        return certificado;
    }

}
