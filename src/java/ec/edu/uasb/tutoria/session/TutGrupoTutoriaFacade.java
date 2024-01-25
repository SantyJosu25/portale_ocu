/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.tutoria.entities.TutGrupoTutoria;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marjorie.fiallos
 */
@Stateless
public class TutGrupoTutoriaFacade extends AbstractFacade<TutGrupoTutoria> implements TutGrupoTutoriaFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TutGrupoTutoriaFacade() {
        super(TutGrupoTutoria.class);
    }

    @Override
    public List<Object[]> obtenerGrupobySolicitud(Long cod) {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("select TGT_CODIGO,TGT_CODIGO_ESTUDIANTE from interfaseOcu.GESTIONACADEMICA.TUT_GRUPO_TUTORIA\n"
                + "where TST_CODIGO_SOLICITUD = ").append(cod);
        return em.createNativeQuery(sqlQuery.toString()).setParameter(1, sqlQuery).getResultList();

    }

}
