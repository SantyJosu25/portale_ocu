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
public class EncuestaRealizadaTutacadPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ALUMNO")
    private long codigoAlumno;
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
    @Column(name = "TIPO_REGISTRO")
    private Character tipoRegistro;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_ESP")
    private long codigoEsp;
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
    @Column(name = "COD_INSTANCIA")
    private long codInstancia;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TST_CODIGO")
    private long tstCodigo;

    public EncuestaRealizadaTutacadPK() {
    }

    public EncuestaRealizadaTutacadPK(long codigoAlumno, long anio, long ciclo, long codigoMateria, long codigoEncuesta, long codigoProfesor, Character tipoRegistro, long codigoEsp, long codigoNivel, long codigoParalelo, long codInstancia, long tstCodigo) {
        this.codigoAlumno = codigoAlumno;
        this.anio = anio;
        this.ciclo = ciclo;
        this.codigoMateria = codigoMateria;
        this.codigoEncuesta = codigoEncuesta;
        this.codigoProfesor = codigoProfesor;
        this.tipoRegistro = tipoRegistro;
        this.codigoEsp = codigoEsp;
        this.codigoNivel = codigoNivel;
        this.codigoParalelo = codigoParalelo;
        this.codInstancia = codInstancia;
        this.tstCodigo = tstCodigo;
    }

    public long getCodigoAlumno() {
        return codigoAlumno;
    }

    public void setCodigoAlumno(long codigoAlumno) {
        this.codigoAlumno = codigoAlumno;
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

    public Character getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(Character tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public long getCodigoEsp() {
        return codigoEsp;
    }

    public void setCodigoEsp(long codigoEsp) {
        this.codigoEsp = codigoEsp;
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

    public long getCodInstancia() {
        return codInstancia;
    }

    public void setCodInstancia(long codInstancia) {
        this.codInstancia = codInstancia;
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
        hash += (int) codigoAlumno;
        hash += (int) anio;
        hash += (int) ciclo;
        hash += (int) codigoMateria;
        hash += (int) codigoEncuesta;
        hash += (int) codigoProfesor;
        hash += (tipoRegistro != null ? tipoRegistro.hashCode() : 0);
        hash += (int) codigoEsp;
        hash += (int) codigoNivel;
        hash += (int) codigoParalelo;
        hash += (int) codInstancia;
        hash += (int) tstCodigo;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaRealizadaTutacadPK)) {
            return false;
        }
        EncuestaRealizadaTutacadPK other = (EncuestaRealizadaTutacadPK) object;
        if (this.codigoAlumno != other.codigoAlumno) {
            return false;
        }
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
        if ((this.tipoRegistro == null && other.tipoRegistro != null) || (this.tipoRegistro != null && !this.tipoRegistro.equals(other.tipoRegistro))) {
            return false;
        }
        if (this.codigoEsp != other.codigoEsp) {
            return false;
        }
        if (this.codigoNivel != other.codigoNivel) {
            return false;
        }
        if (this.codigoParalelo != other.codigoParalelo) {
            return false;
        }
        if (this.codInstancia != other.codInstancia) {
            return false;
        }
        if (this.tstCodigo != other.tstCodigo) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.tutoria.entities.EncuestaRealizadaTutacadPK[ codigoAlumno=" + codigoAlumno + ", anio=" + anio + ", ciclo=" + ciclo + ", codigoMateria=" + codigoMateria + ", codigoEncuesta=" + codigoEncuesta + ", codigoProfesor=" + codigoProfesor + ", tipoRegistro=" + tipoRegistro + ", codigoEsp=" + codigoEsp + ", codigoNivel=" + codigoNivel + ", codigoParalelo=" + codigoParalelo + ", codInstancia=" + codInstancia + ", tstCodigo=" + tstCodigo + " ]";
    }
    
}
