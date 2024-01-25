/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;

import ec.edu.uasb.entities.Apelativo;
import ec.edu.uasb.portalE.session.AbstractFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author victor.barba
 */
@Stateless
public class ApelativoFacade extends AbstractFacade<Apelativo> implements ApelativoFacadeLocal {
    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ApelativoFacade() {
        super(Apelativo.class);
    }
    

    @Override
    public List<Apelativo> findAllOrdenados() {
        Query q = em.createNamedQuery("Apelativo.findAll");
        return q.getResultList();
    }    
}
