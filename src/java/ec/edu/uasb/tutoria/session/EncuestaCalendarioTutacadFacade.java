/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.tutoria.entities.EncuestaCalendarioTutacad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marjorie.fiallos
 */
@Stateless
public class EncuestaCalendarioTutacadFacade extends AbstractFacade<EncuestaCalendarioTutacad> implements EncuestaCalendarioTutacadFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EncuestaCalendarioTutacadFacade() {
        super(EncuestaCalendarioTutacad.class);
    }
    
}
