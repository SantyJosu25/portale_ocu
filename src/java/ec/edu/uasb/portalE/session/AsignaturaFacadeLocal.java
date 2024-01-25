/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.session;

import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.portalE.entities.AsignaturaModel;
import ec.edu.uasb.portalE.entities.Horario;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface AsignaturaFacadeLocal {

    void create(AsignaturaModel asignatura);

    void edit(AsignaturaModel asignatura);

    void remove(AsignaturaModel asignatura);

    AsignaturaModel find(Object id);

    List<AsignaturaModel> findAll();

    List<AsignaturaModel> findRange(int[] range);

    int count();

    public List<AsignaturaModel> getListaAsignaturas(Long codEstud, Long codMatricula);

//    public List<Horario> getHorarioPorAsig(Long ejercicio, Long asignatura, Long vacCodnum, Long gacCodnum, Long trimestre);
    public List<AsignaturaModel> getListaAsignaturasxAnio(Long codEstud, Long anio);

    public List<Horario> getHorarioPorAsig(Long ejercicio, Long asignatura, Long vacCodnum, Long gacCodnum);

    public String getNombreParalelo(Long anio, Long codAsig, Long codEsp, Long codGac, Long codNivel);

    public Long getSecuencial(String sistema, String campo);

}
