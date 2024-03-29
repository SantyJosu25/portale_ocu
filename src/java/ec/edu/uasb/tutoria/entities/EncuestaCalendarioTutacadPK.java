/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author marjorie.fiallos
 */
@Embeddable
public class EncuestaCalendarioTutacadPK implements Serializable {

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
    @Column(name = "CODIGO_MATERIA")
    private long codigoMateria;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ENCUESTA")
    private long codigoEncuesta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PROFESOR")
    private long codigoProfesor;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESP")
    private long codigoEsp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ESTUDIANTE")
    private long codEstudiante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_NIVEL")
    private long codigoNivel;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_PARALELO")
    private long codigoParalelo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TST_CODIGO")
    private long tstCodigo;

    public EncuestaCalendarioTutacadPK() {
    }

    public EncuestaCalendarioTutacadPK(long anio, long ciclo, long codigoMateria, long codigoEncuesta, long codigoProfesor, long codigoEsp, long codEstudiante, long codigoNivel, long codigoParalelo, long tstCodigo) {
        this.anio = anio;
        this.ciclo = ciclo;
        this.codigoMateria = codigoMateria;
        this.codigoEncuesta = codigoEncuesta;
        this.codigoProfesor = codigoProfesor;
        this.codigoEsp = codigoEsp;
        this.codEstudiante = codEstudiante;
        this.codigoNivel = codigoNivel;
        this.codigoParalelo = codigoParalelo;
        this.tstCodigo = tstCodigo;
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

    public long getCodigoMateria() {
        return codigoMateria;
    }

    public void setCodigoMateria(long codigoMateria) {
        this.codigoMateria = codigoMateria;
    }

    public long getCodigoEncuesta() {
        return codigoEncuesta;
    }

    public void setCodigoEncuesta(long codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    public long getCodigoProfesor() {
        return codigoProfesor;
    }

    public void setCodigoProfesor(long codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    public long getCodigoEsp() {
        return codigoEsp;
    }

    public void setCodigoEsp(long codigoEsp) {
        this.codigoEsp = codigoEsp;
    }

    public long getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(long codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public long getCodigoNivel() {
        return codigoNivel;
    }

    public void setCodigoNivel(long codigoNivel) {
        this.codigoNivel = codigoNivel;
    }

    public long getCodigoParalelo() {
        return codigoParalelo;
    }

    public void setCodigoParalelo(long codigoParalelo) {
        this.codigoParalelo = codigoParalelo;
    }

    public long getTstCodigo() {
        return tstCodigo;
    }

    public void setTstCodigo(long tstCodigo) {
        this.tstCodigo = tstCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) anio;
        hash += (int) ciclo;
        hash += (int) codigoMateria;
        hash += (int) codigoEncuesta;
        hash += (int) codigoProfesor;
        hash += (int) codigoEsp;
        hash += (int) codEstudiante;
        hash += (int) codigoNivel;
        hash += (int) codigoParalelo;
        hash += (int) tstCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaCalendarioTutacadPK)) {
            return false;
        }
        EncuestaCalendarioTutacadPK other = (EncuestaCalendarioTutacadPK) object;
        if (this.anio != other.anio) {
            return false;
        }
        if (this.ciclo != other.ciclo) {
            return false;
        }
        if (this.codigoMateria != other.codigoMateria) {
            return false;
        }
        if (this.codigoEncuesta != other.codigoEncuesta) {
            return false;
        }
        if (this.codigoProfesor != other.codigoProfesor) {
            return false;
        }
        if (this.codigoEsp != other.codigoEsp) {
            return false;
        }
        if (this.codEstudiante != other.codEstudiante) {
            return false;
        }
        if (this.codigoNivel != other.codigoNivel) {
            return false;
        }
        if (this.codigoParalelo != other.codigoParalelo) {
            return false;
        }
        if (this.tstCodigo != other.tstCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.tutoria.entities.EncuestaCalendarioTutacadPK[ anio=" + anio + ", ciclo=" + ciclo + ", codigoMateria=" + codigoMateria + ", codigoEncuesta=" + codigoEncuesta + ", codigoProfesor=" + codigoProfesor + ", codigoEsp=" + codigoEsp + ", codEstudiante=" + codEstudiante + ", codigoNivel=" + codigoNivel + ", codigoParalelo=" + codigoParalelo + ", tstCodigo=" + tstCodigo + " ]";
    }
    
}
