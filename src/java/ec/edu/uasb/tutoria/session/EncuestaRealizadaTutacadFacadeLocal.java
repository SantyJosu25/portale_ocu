/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.form.datamodel.EncuTutoDispo;
import ec.edu.uasb.tutoria.entities.EncuestaRealizadaTutacad;
import ec.edu.uasb.tutoria.entities.RespuestaTutacad;
import java.util.List;
import javax.ejb.Local;
import javax.faces.context.FacesContext;

/**
 *
 * @author marjorie.fiallos
 */
@Local
public interface EncuestaRealizadaTutacadFacadeLocal {

    void create(EncuestaRealizadaTutacad encuestaRealizadaTutacad);

    void edit(EncuestaRealizadaTutacad encuestaRealizadaTutacad);

    void remove(EncuestaRealizadaTutacad encuestaRealizadaTutacad);

    EncuestaRealizadaTutacad find(Object id);

    List<EncuestaRealizadaTutacad> findAll();

    List<EncuestaRealizadaTutacad> findRange(int[] range);

    int count();

    public String createEncuesta(Long codigoAlumno, EncuTutoDispo encuTutoDispo, List<RespuestaTutacad> respuestas, FacesContext context);

    public List evalTutoriaAcademica(Long codEstud, Long codigoesp);

}
