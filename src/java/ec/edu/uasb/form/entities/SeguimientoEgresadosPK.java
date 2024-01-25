/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author johana.orozco
 */
@Embeddable
public class SeguimientoEgresadosPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESTUDIANTE")
    private long codigoEstudiante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO")
    private long anio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CICLO")
    private long ciclo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_REALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRealizacion;

    public SeguimientoEgresadosPK() {
    }

    public SeguimientoEgresadosPK(long codigoEstudiante, long anio, long ciclo, Date fechaRealizacion) {
        this.codigoEstudiante = codigoEstudiante;
        this.anio = anio;
        this.ciclo = ciclo;
        this.fechaRealizacion = fechaRealizacion;
    }

    public long getCodigoEstudiante() {
        return codigoEstudiante;
    }

    public void setCodigoEstudiante(long codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
    }

    public long getAnio() {
        return anio;
    }

    public void setAnio(long anio) {
        this.anio = anio;
    }

    public long getCiclo() {
        return ciclo;
    }

    public void setCiclo(long ciclo) {
        this.ciclo = ciclo;
    }

    public Date getFechaRealizacion() {
        return fechaRealizacion;
    }

    public void setFechaRealizacion(Date fechaRealizacion) {
        this.fechaRealizacion = fechaRealizacion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoEstudiante;
        hash += (int) anio;
        hash += (int) ciclo;
        hash += (fechaRealizacion != null ? fechaRealizacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoEgresadosPK)) {
            return false;
        }
        SeguimientoEgresadosPK other = (SeguimientoEgresadosPK) object;
        if (this.codigoEstudiante != other.codigoEstudiante) {
            return false;
        }
        if (this.anio != other.anio) {
            return false;
        }
        if (this.ciclo != other.ciclo) {
            return false;
        }
        if ((this.fechaRealizacion == null && other.fechaRealizacion != null) || (this.fechaRealizacion != null && !this.fechaRealizacion.equals(other.fechaRealizacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.entity.SeguimientoEgresadosPK[ codigoEstudiante=" + codigoEstudiante + ", anio=" + anio + ", ciclo=" + ciclo + ", fechaRealizacion=" + fechaRealizacion + " ]";
    }
    
}
