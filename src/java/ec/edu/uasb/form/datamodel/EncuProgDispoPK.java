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
public class EncuProgDispoPK implements Serializable {

    @Column(name = "CODIGO_ENCUESTA")
    private Long codigoEncuesta;
    @Column(name = "ANIO")
    private Long anio;
    @Column(name = "CODIGO_ESP")
    private Long codigoEsp;
    @Column(name = "CODIGO_NIVEL")
    private Long codigoNivel;
    @Column(name = "CODIGO_PARALELO")
    private Long codigoParalelo;

    public Long getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(Long codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public long getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(Long codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public Long getCodigoEsp() {
        return codigoEsp;
    }

    public void setCodigoEsp(Long codigoEsp) {
        this.codigoEsp = codigoEsp;
    }

    public Long getCodigoParalelo() {
        return codigoParalelo;
    }

    public void setCodigoParalelo(Long codigoParalelo) {
        this.codigoParalelo = codigoParalelo;
    }

    public EncuProgDispoPK() {
    }

    public EncuProgDispoPK(Long codigoEncuesta, Long anio, Long codigoEsp, Long codigoNivel, Long codigoParalelo) {
        this.codigoEncuesta = codigoEncuesta;
        this.anio = anio;
        this.codigoEsp = codigoEsp;
        this.codigoNivel = codigoNivel;
        this.codigoParalelo = codigoParalelo;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (this.codigoEncuesta != null ? this.codigoEncuesta.hashCode() : 0);
        hash = 71 * hash + (this.anio != null ? this.anio.hashCode() : 0);
        hash = 71 * hash + (this.codigoEsp != null ? this.codigoEsp.hashCode() : 0);
        hash = 71 * hash + (this.codigoNivel != null ? this.codigoNivel.hashCode() : 0);
        hash = 71 * hash + (this.codigoParalelo != null ? this.codigoParalelo.hashCode() : 0);
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
        final EncuProgDispoPK other = (EncuProgDispoPK) obj;
        if (this.codigoEncuesta != other.codigoEncuesta && (this.codigoEncuesta == null || !this.codigoEncuesta.equals(other.codigoEncuesta))) {
            return false;
        }
        if (this.anio != other.anio && (this.anio == null || !this.anio.equals(other.anio))) {
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
        return codigoEncuesta + "-" + anio + ";" + codigoEsp + ":" + codigoNivel+ "_" + codigoParalelo;
    }
}
