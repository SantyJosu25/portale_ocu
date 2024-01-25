/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.tutoria.entities.EncuestaCalendarioTutacad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marjorie.fiallos
 */
@Local
public interface EncuestaCalendarioTutacadFacadeLocal {

    void create(EncuestaCalendarioTutacad encuestaCalendarioTutacad);

    void edit(EncuestaCalendarioTutacad encuestaCalendarioTutacad);

    void remove(EncuestaCalendarioTutacad encuestaCalendarioTutacad);

    EncuestaCalendarioTutacad find(Object id);

    List<EncuestaCalendarioTutacad> findAll();

    List<EncuestaCalendarioTutacad> findRange(int[] range);

    int count();
    
}
