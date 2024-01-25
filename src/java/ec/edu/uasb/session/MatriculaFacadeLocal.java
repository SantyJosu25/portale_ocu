/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.session;

import ec.edu.uasb.bean.exceptions.MatriculaException;
import ec.edu.uasb.entities.Matricula;
import ec.edu.uasb.entities.MatriculaExt;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author victor.barba
 */
@Local
public interface MatriculaFacadeLocal {

    void create(Matricula matricula);

    void edit(Matricula matricula);

    void remove(Matricula matricula);

    Matricula find(Object id);

    List<Matricula> findAll();

    List<Matricula> findRange(int[] range);

    int count();

    public Matricula getMatricula(Long codEstud) throws MatriculaException;

    public List<MatriculaExt> getListaMatriculas(Long codEstud);


    
}
