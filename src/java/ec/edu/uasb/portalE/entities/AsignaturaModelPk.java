/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class AsignaturaModelPk implements Serializable {

    @Column(name = "CODIGO_MATERIA")
    private Long codigoMateria;
    @Column(name = "CODIGO_NIVEL")
    private Long codigoNivel;
    @Column(name = "VAC_CODNUM")
    private Long vacCodnum;
    @Column(name = "GAC_CODNUM")
    private Long gacCodnum;

    public AsignaturaModelPk(Long codigoMateria, Long codigoNivel, Long vacCodnum, Long gacCodnum) {
        this.codigoMateria = codigoMateria;
        this.codigoNivel = codigoNivel;
        this.vacCodnum = vacCodnum;
        this.gacCodnum = gacCodnum;
    }

    public AsignaturaModelPk() {
    }

    public Long getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(Long codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public Long getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Long codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public Long getVacCodnum() {
        return vacCodnum;
    }

    public void setVacCodnum(Long vacCodnum) {
        this.vacCodnum = vacCodnum;
    }

    public Long getGacCodnum() {
        return gacCodnum;
    }

    public void setGacCodnum(Long gacCodnum) {
        this.gacCodnum = gacCodnum;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.codigoMateria != null ? this.codigoMateria.hashCode() : 0);
        hash = 29 * hash + (this.codigoNivel != null ? this.codigoNivel.hashCode() : 0);
        hash = 29 * hash + (this.vacCodnum != null ? this.vacCodnum.hashCode() : 0);
        hash = 29 * hash + (this.gacCodnum != null ? this.gacCodnum.hashCode() : 0);
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
        final AsignaturaModelPk other = (AsignaturaModelPk) obj;
        if (this.codigoMateria != other.codigoMateria && (this.codigoMateria == null || !this.codigoMateria.equals(other.codigoMateria))) {
            return false;
        }
        if (this.codigoNivel != other.codigoNivel && (this.codigoNivel == null || !this.codigoNivel.equals(other.codigoNivel))) {
            return false;
        }
        if (this.vacCodnum != other.vacCodnum && (this.vacCodnum == null || !this.vacCodnum.equals(other.vacCodnum))) {
            return false;
        }
        if (this.gacCodnum != other.gacCodnum && (this.gacCodnum == null || !this.gacCodnum.equals(other.gacCodnum))) {
            return false;
        }
        return true;
    }

  
    @Override
    public String toString() {
        return codigoMateria + ":" + codigoNivel + ";" + vacCodnum + "=" + gacCodnum ;
    }
}
