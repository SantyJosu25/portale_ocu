/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.session;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author milton.valencia
 */
@Local
public interface ResumenProgramaFacadeLocal {

    public List<Object[]> obtenerResumenPrograma(Long codigoEstudiante, Long anioAcademico);

}
