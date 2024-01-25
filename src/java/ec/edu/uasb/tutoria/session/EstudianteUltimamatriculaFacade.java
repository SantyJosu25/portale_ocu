/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.tutoria.entities.EstudianteUltimamatricula;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author marjorie.fiallos
 */
@Stateless
public class EstudianteUltimamatriculaFacade extends AbstractFacade<EstudianteUltimamatricula> implements EstudianteUltimamatriculaFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EstudianteUltimamatriculaFacade() {
        super(EstudianteUltimamatricula.class);
    }

    @Override
    public EstudianteUltimamatricula findStudent(Long cod) {
         Query q = em.createNativeQuery("select DISTINCT TOP(1) COD_ESTUDIANTE, CED_PAS_ESTUDIANTE, NOM_ESTUDIANTE, APELL_ESTUDIANTE, MATRICULA, ANIO, CICLO, CODIGO_ESP, PROGRAMA, INICIO, FIN, CODIGO_FACULTAD, CODIGO_NIVEACAD, AREA, EJERCICIO, ANIO_INICIAL"
                 + " from GESTIONACADEMICA.ESTUDIANTE_ULTIMAMATRICULA WITH (NOLOCK)"
                 + " where COD_ESTUDIANTE = ? ORDER BY ANIO DESC", EstudianteUltimamatricula.class).setParameter(1, cod);
        return (EstudianteUltimamatricula) q.getSingleResult();
    }

    @Override
    public List<String[]> obtenerListaCompañeros(int ejercicio, String codPrograma, Integer codEst, Integer anio) {
        Query q = em.createNativeQuery("select e.cod_Estudiante, UPPER(e.apell_Estudiante +' '+ e.nom_Estudiante) from GESTIONACADEMICA.ESTUDIANTE_ULTIMAMATRICULA as e where e.EJERCICIO= ? and e.codigo_Esp  = ? and e.cod_Estudiante <> ? and e.anio_inicial = ?  ORDER BY e.apell_Estudiante ASC ").setParameter(1, ejercicio).setParameter(2, codPrograma).setParameter(3, codEst).setParameter(4, anio);
        return q.getResultList();
    }

    @Override
    public List<String[]> obtenerListaCompañerosbycod(Integer codEst) {
        Query q = em.createNativeQuery("select distinct e.cod_Estudiante,UPPER(e.apell_Estudiante +' '+ e.nom_Estudiante) from GESTIONACADEMICA.ESTUDIANTE_ULTIMAMATRICULA as e where e.cod_Estudiante = ?  ORDER BY e.apell_Estudiante ASC").setParameter(3, codEst);
        return q.getResultList();
    }

}
