/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author victor.barba
 */
public class AsigEncuestaModel {

    private String trimestre;
    private List asigEncuestaList = new ArrayList();

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public AsigEncuestaModel(String trimestre) {
        this.trimestre = trimestre;
    }

    public List getAsigEncuestaList() {
        return asigEncuestaList;
    }

    public void setAsigEncuestaList(List asigEncuestaList) {
        this.asigEncuestaList = asigEncuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.trimestre != null ? this.trimestre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AsigEncuestaModel other = (AsigEncuestaModel) obj;
        if ((this.trimestre == null) ? (other.trimestre != null) : !this.trimestre.equals(other.trimestre)) {
            return false;
        }
        return true;
    }
}
