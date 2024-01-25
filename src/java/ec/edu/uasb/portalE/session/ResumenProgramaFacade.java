/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author milton.valencia
 */
@Stateless
public class ResumenProgramaFacade implements ResumenProgramaFacadeLocal {

    @PersistenceContext(unitName = "academicoPU")
    private EntityManager entityManager;

    @Override
    public List<Object[]> obtenerResumenPrograma(Long codigoEstudiante, Long anioAcademico) {
        StringBuilder sqlQuery = new StringBuilder();
        sqlQuery.append("SELECT DISTINCT REPRO.*, GPRO.PROGRAMA FROM interfaseOcu.GESTIONACADEMICA.RESUM_PROGRAMA REPRO\n"
                + "INNER JOIN GESTIONACADEMICA.ESTUDIANTE_ULTIMAMATRICULA GPRO ON REPRO.RPRO_ANIO = GPRO.ANIO\n"
                + "AND REPRO.RPRO_CODIGO_ESP = GPRO.CODIGO_ESP\n"
                + "WHERE GPRO.ANIO = ").append(anioAcademico).append(" AND GPRO.COD_ESTUDIANTE = ").append(codigoEstudiante);
        return entityManager.createNativeQuery(sqlQuery.toString()).setParameter(1, sqlQuery).getResultList();
        
    }

}
