/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.entities;

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
public class EstudiantePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "BASE_DIR")
    private char baseDir;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ESTUDIANTE")
    private long codEstudiante;

    public EstudiantePK() {
    }

    public EstudiantePK(char baseDir, long codEstudiante) {
        this.baseDir = baseDir;
        this.codEstudiante = codEstudiante;
    }

    public char getBaseDir() {
        return baseDir;
    }

    public void setBaseDir(char baseDir) {
        this.baseDir = baseDir;
    }

    public long getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(long codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) baseDir;
        hash += (int) codEstudiante;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstudiantePK)) {
            return false;
        }
        EstudiantePK other = (EstudiantePK) object;
        if (this.baseDir != other.baseDir) {
            return false;
        }
        if (this.codEstudiante != other.codEstudiante) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.EstudiantePK[ baseDir=" + baseDir + ", codEstudiante=" + codEstudiante + " ]";
    }
    
}
