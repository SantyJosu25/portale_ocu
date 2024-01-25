/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class MatriculaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "MATRICULA")
    private Long matricula;
    @Basic(optional = false)
    @NotNull    
    @Column(name = "ANIO")
    private Long anio;

    public MatriculaPK() {
    }

    public MatriculaPK(Long matricula, Long anio) {
        this.matricula = matricula;
        this.anio = anio;
    }

    /**
     * @return the matricula
     */
    public Long getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    /**
     * @return the anio
     */
    public Long getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Long anio) {
        this.anio = anio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.matricula != null ? this.matricula.hashCode() : 0);
        hash = 67 * hash + (this.anio != null ? this.anio.hashCode() : 0);
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
        final MatriculaPK other = (MatriculaPK) obj;
        if (this.matricula != other.matricula && (this.matricula == null || !this.matricula.equals(other.matricula))) {
            return false;
        }
        if (this.anio != other.anio && (this.anio == null || !this.anio.equals(other.anio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MatriculaPK{" + "matricula=" + matricula + ", anio=" + anio + '}';
    }
    
    
}
