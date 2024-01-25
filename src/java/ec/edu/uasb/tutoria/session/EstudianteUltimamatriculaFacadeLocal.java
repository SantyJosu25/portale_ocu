/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.tutoria.entities.EstudianteUltimamatricula;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marjorie.fiallos
 */
@Local
public interface EstudianteUltimamatriculaFacadeLocal {

    void create(EstudianteUltimamatricula estudianteUltimamatricula);

    void edit(EstudianteUltimamatricula estudianteUltimamatricula);

    void remove(EstudianteUltimamatricula estudianteUltimamatricula);

    EstudianteUltimamatricula find(Object id);

    List<EstudianteUltimamatricula> findAll();

    List<EstudianteUltimamatricula> findRange(int[] range);

    EstudianteUltimamatricula findStudent(Long cod);

    public List<String[]> obtenerListaCompañeros(int ejercicio, String codPrograma, Integer codEst, Integer anio);

    public List<String[]> obtenerListaCompañerosbycod(Integer codEst);

    int count();

}
