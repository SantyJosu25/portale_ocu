/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.tutoria.entities.TutParametroMensaje;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author marjorie.fiallos
 */
@Stateless
public class TutParametroMensajeFacade extends AbstractFacade<TutParametroMensaje> implements TutParametroMensajeFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TutParametroMensajeFacade() {
        super(TutParametroMensaje.class);
    }
    
}
