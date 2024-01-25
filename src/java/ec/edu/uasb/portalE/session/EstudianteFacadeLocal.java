/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.session;

import ec.edu.uasb.portalE.entities.Estudiante;
import java.util.List;
import javax.ejb.Local;
import javax.faces.context.FacesContext;

/**
 *
 * @author victor.barba
 */
@Local
public interface EstudianteFacadeLocal {

    void create(Estudiante estudiante);

    void edit(Estudiante estudiante);

    void remove(Estudiante estudiante);

    Estudiante find(Object id);

    List<Estudiante> findAll();

    List<Estudiante> findRange(int[] range);

    int count();

    public Estudiante finbyCodigo(Long codEstud);

    public void enviaCorreo(String profile_name, String recipients, String copy_recipients, String blind_copy_recipients, String psubject, String pmensaje, String body_format);

    public void edit(Estudiante estud, FacesContext fcontext);
    
}
