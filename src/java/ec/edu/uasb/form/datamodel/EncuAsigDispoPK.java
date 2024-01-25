/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.datamodel;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class EncuAsigDispoPK implements Serializable {

    @Column(name = "CODIGO_MATERIA")
    private Long codigoMateria;
    @Column(name = "CODIGO_PROFESOR")
    private Long codigoProfesor;
    @Column(name = "CODIGO_ESP")
    private Long codigoEsp;
    @Column(name = "CODIGO_NIVEL")
    private Long codigoNivel;
    @Column(name = "COD_PARALELO")
    private Long codigoParalelo;

    public EncuAsigDispoPK() {
    }

    public EncuAsigDispoPK(Long codigoMateria, Long codigoProfesor, Long codigoEsp, Long codigoNivel,Long codigoParalelo) {
        this.codigoMateria = codigoMateria;
        this.codigoProfesor = codigoProfesor;
        this.codigoEsp = codigoEsp;
        this.codigoNivel = codigoNivel;
        this.codigoParalelo = codigoParalelo;
    }

    public Long getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Long codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public Long getCodigoParalelo() {
        return codigoParalelo;
    }

    public void setCodigoParalelo(Long codigoParalelo) {
        this.codigoParalelo = codigoParalelo;
    }

    public Long getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(Long codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public Long getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(Long codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public Long getCodigoEsp() {
        return codigoEsp;
    }

    public void setCodigoEsp(Long codigoEsp) {
        this.codigoEsp = codigoEsp;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + (this.codigoMateria != null ? this.codigoMateria.hashCode() : 0);
        hash = 73 * hash + (this.codigoProfesor != null ? this.codigoProfesor.hashCode() : 0);
        hash = 73 * hash + (this.codigoEsp != null ? this.codigoEsp.hashCode() : 0);
        hash = 73 * hash + (this.codigoNivel != null ? this.codigoNivel.hashCode() : 0);
        hash = 73 * hash + (this.codigoParalelo != null ? this.codigoParalelo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EncuAsigDispoPK other = (EncuAsigDispoPK) obj;
        if (this.codigoMateria != other.codigoMateria && (this.codigoMateria == null || !this.codigoMateria.equals(other.codigoMateria))) {
            return false;
        }
        if (this.codigoProfesor != other.codigoProfesor && (this.codigoProfesor == null || !this.codigoProfesor.equals(other.codigoProfesor))) {
            return false;
        }
        if (this.codigoEsp != other.codigoEsp && (this.codigoEsp == null || !this.codigoEsp.equals(other.codigoEsp))) {
            return false;
        }
        if (this.codigoNivel != other.codigoNivel && (this.codigoNivel == null || !this.codigoNivel.equals(other.codigoNivel))) {
            return false;
        }
        if (this.codigoParalelo != other.codigoParalelo && (this.codigoParalelo == null || !this.codigoParalelo.equals(other.codigoParalelo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return codigoProfesor + "-" + codigoMateria + ";" + codigoEsp + ":" + codigoNivel+ "_" + codigoParalelo;
    }

}
