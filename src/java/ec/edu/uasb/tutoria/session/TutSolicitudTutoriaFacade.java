/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.tutoria.entities.TutSolicitudTutoria;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marjorie.fiallos
 */
@Stateless
public class TutSolicitudTutoriaFacade extends AbstractFacade<TutSolicitudTutoria> implements TutSolicitudTutoriaFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TutSolicitudTutoriaFacade() {
        super(TutSolicitudTutoria.class);
    }

    @Override
    public List ejecutaSqlList(String sql) {
        return em.createNativeQuery(sql).setParameter(1, sql).getResultList();
    }

    @Override
    public List<TutSolicitudTutoria> findRequest(long codEst, int anio) {
//        em.flush();
//        em.clear();
        try {
            Query q = em.createQuery("select s from TutSolicitudTutoria s where s.tstCodigoSolicitante = :codEstudiante and s.tstAnioSolicitud = :anio order by s.tstFechaCrea desc").setParameter("codEstudiante", codEst).setParameter("anio", anio);
            return q.setHint("eclipselink.refresh", true).getResultList();
        } catch (NoResultException ex) {
            return null;
        }

    }

    @Override
    public void solicitudTutoriaEnviaCorreo(Long codigoSolicitud) {
        Query query = em.createNativeQuery("{call interfaseOcu.GESTIONACADEMICA.sp_recordatorio_tutoria(?)}");
        query.setParameter(1, codigoSolicitud); // codigoSolicitud
        query.executeUpdate();
    }

}
