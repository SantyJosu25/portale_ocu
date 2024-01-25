/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.session;

import ec.edu.uasb.form.entities.SeguimientoEgresados;
import java.util.List;
import javax.ejb.Local;
import javax.faces.context.FacesContext;

/**
 *
 * @author johana.orozco
 */
@Local
public interface SeguimientoEgresadosFacadeLocal {

    void create(SeguimientoEgresados seguimientoEgresados);

    void edit(SeguimientoEgresados seguimientoEgresados);

    void remove(SeguimientoEgresados seguimientoEgresados);

    SeguimientoEgresados find(Object id);

    List<SeguimientoEgresados> findAll();

    List<SeguimientoEgresados> findRange(int[] range);

    int count();

    public java.lang.String titTerNivel(java.lang.Long codigoAlumno);

    public java.lang.String titCuarNivel(java.lang.Long codigoAlumno);

    public SeguimientoEgresados findbyEstAnio(Long codEstudiante, Long anio);

    public int veriEncRealizada(Long codigoAlumno, int codEncuesta, Long anio, int ciclo);

    public String createEncuesta(Long codigoAlumno, Long codEncuesta, SeguimientoEgresados respuestas, FacesContext context);
}
