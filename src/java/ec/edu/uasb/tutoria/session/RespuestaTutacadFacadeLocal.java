/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.session;

import ec.edu.uasb.tutoria.entities.RespuestaTutacad;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author marjorie.fiallos
 */
@Local
public interface RespuestaTutacadFacadeLocal {

    void create(RespuestaTutacad respuestaTutacad);

    void edit(RespuestaTutacad respuestaTutacad);

    void remove(RespuestaTutacad respuestaTutacad);

    RespuestaTutacad find(Object id);

    List<RespuestaTutacad> findAll();

    List<RespuestaTutacad> findRange(int[] range);

    int count();
    
}
