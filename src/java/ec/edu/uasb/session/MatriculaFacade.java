/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;

import ec.edu.uasb.bean.exceptions.MatriculaException;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.entities.MatriculaExt;
import ec.edu.uasb.form.session.AbstractFacade;
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
public class MatriculaFacade extends AbstractFacade<Matricula> implements MatriculaFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MatriculaFacade() {
        super(Matricula.class);
    }

    @Override
    public Matricula getMatricula(Long codEstud) throws MatriculaException {
        Matricula m = null;
        try {
            Query q = em.createNativeQuery("select DISTINCT TOP(1) COD_ESTUDIANTE, MATRICULA,ANIO,ANIO_INICIAL,"
                    + " null NOMBRE_CICLO,PROGRAMA,AREA ,CODIGO_ESP,CODIGO_FACULTAD,CODIGO_NIVEACAD,-1 CODIGO_ESCUELA ,EJERCICIO"
                    + " from GESTIONACADEMICA.ESTUDIANTE_ULTIMAMATRICULA WITH (NOLOCK)"
                    + " where COD_ESTUDIANTE = ? ORDER BY ANIO DESC", Matricula.class);
            q.setParameter(1, codEstud);
            m = (Matricula) q.getSingleResult();
        } catch (NoResultException ex) {
            throw new MatriculaException("Usuario no registra matricula alguna");
        }
        return m;
    }


    @Override
    public List<MatriculaExt> getListaMatriculas(Long codEstud) {
        Query q = em.createNativeQuery("select DISTINCT COD_ESTUDIANTE, MATRICULA,ANIO,NOMBRE_CICLO,PROGRAMA,AREA ,CODIGO_ESP,CODIGO_FACULTAD,CODIGO_NIVEACAD,CODIGO_ESCUELA from V_ESTUDIANTE_MATMATERIA_CICLO  WITH (NOLOCK) where COD_ESTUDIANTE = ? ORDER BY ANIO DESC", MatriculaExt.class);
        q.setParameter(1, codEstud);
        return q.getResultList();

    }
}
