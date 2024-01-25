/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.session;

import ec.edu.uasb.form.datamodel.EncuAsigDispo;
import ec.edu.uasb.form.datamodel.EncuProgDispo;
import ec.edu.uasb.form.datamodel.ParticipacionCeremGradDispo;
import ec.edu.uasb.form.entities.EncuestaRealizada;
import ec.edu.uasb.form.entities.Respuesta;

import java.util.List;
import javax.ejb.Local;
import javax.faces.context.FacesContext;

/**
 *
 * @author victor.barba
 */
@Local
public interface EncuestaRealizadaFacadeLocal {

    void create(EncuestaRealizada encuestaRealizada);

    void edit(EncuestaRealizada encuestaRealizada);

    void remove(EncuestaRealizada encuestaRealizada);

    EncuestaRealizada find(Object id);

    List<EncuestaRealizada> findAll();

    List<EncuestaRealizada> findRange(int[] range);

    int count();

    public void enviaCorreo(String profile_name, String recipients, String copy_recipients, String blind_copy_recipients, String psubject, String pmensaje, String body_format);

    public String createEncuesta(Long codigoAlumno, EncuProgDispo encuProgDispo, List<Respuesta> respuestas, FacesContext context);

    public String createEncuesta(Long codigoAlumno, EncuAsigDispo asigEncuesta, List<Respuesta> respuestas, FacesContext context);

    public List encuestasDisponibles(Long codEstud, Long anio, String tipo);    

    public String createEncuesta(Long codigoAlumno, ParticipacionCeremGradDispo partCeremGradDispo, List<Respuesta> respuestas, FacesContext context);
}
