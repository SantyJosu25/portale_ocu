/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.session;

import ec.edu.uasb.portalE.entities.CicloAcademico;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface CicloAcademicoFacadeLocal {

    void create(CicloAcademico cicloAcademico);

    void edit(CicloAcademico cicloAcademico);

    void remove(CicloAcademico cicloAcademico);

    CicloAcademico find(Object id);

    List<CicloAcademico> findAll();

    List<CicloAcademico> findRange(int[] range);

    int count();

    public List<CicloAcademico> findByEstado(char estado);
}
