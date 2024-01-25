/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.tutoria.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marjorie.fiallos
 */
@Entity
@Table(name = "ENCUESTA_REALIZADA_TUTACAD", schema = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncuestaRealizadaTutacad.findAll", query = "SELECT e FROM EncuestaRealizadaTutacad e")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByCodigoAlumno", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.codigoAlumno = :codigoAlumno")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByAnio", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.anio = :anio")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByCiclo", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.ciclo = :ciclo")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByCodigoMateria", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.codigoMateria = :codigoMateria")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByCodigoEncuesta", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.codigoEncuesta = :codigoEncuesta")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByCodigoProfesor", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.codigoProfesor = :codigoProfesor")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByFecha", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.fecha = :fecha")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByRealizada", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.realizada = :realizada")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByTipoRegistro", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.tipoRegistro = :tipoRegistro")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByCodigoEsp", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.codigoEsp = :codigoEsp")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByCodigoNivel", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.codigoNivel = :codigoNivel")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByCodigoParalelo", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.codigoParalelo = :codigoParalelo")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByCodInstancia", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.codInstancia = :codInstancia")
    , @NamedQuery(name = "EncuestaRealizadaTutacad.findByTstCodigo", query = "SELECT e FROM EncuestaRealizadaTutacad e WHERE e.encuestaRealizadaTutacadPK.tstCodigo = :tstCodigo")})
public class EncuestaRealizadaTutacad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EncuestaRealizadaTutacadPK encuestaRealizadaTutacadPK;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "REALIZADA")
    private Character realizada;

    public EncuestaRealizadaTutacad() {
    }

    public EncuestaRealizadaTutacad(EncuestaRealizadaTutacadPK encuestaRealizadaTutacadPK) {
        this.encuestaRealizadaTutacadPK = encuestaRealizadaTutacadPK;
    }

    public EncuestaRealizadaTutacad(long codigoAlumno, long anio, long ciclo, long codigoMateria, long codigoEncuesta, long codigoProfesor, Character tipoRegistro, long codigoEsp, long codigoNivel, long codigoParalelo, long codInstancia, long tstCodigo) {
        this.encuestaRealizadaTutacadPK = new EncuestaRealizadaTutacadPK(codigoAlumno, anio, ciclo, codigoMateria, codigoEncuesta, codigoProfesor, tipoRegistro, codigoEsp, codigoNivel, codigoParalelo, codInstancia, tstCodigo);
    }

    public EncuestaRealizadaTutacadPK getEncuestaRealizadaTutacadPK() {
        return encuestaRealizadaTutacadPK;
    }

    public void setEncuestaRealizadaTutacadPK(EncuestaRealizadaTutacadPK encuestaRealizadaTutacadPK) {
        this.encuestaRealizadaTutacadPK = encuestaRealizadaTutacadPK;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Character getRealizada() {
        return realizada;
    }

    public void setRealizada(Character realizada) {
        this.realizada = realizada;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (encuestaRealizadaTutacadPK != null ? encuestaRealizadaTutacadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaRealizadaTutacad)) {
            return false;
        }
        EncuestaRealizadaTutacad other = (EncuestaRealizadaTutacad) object;
        if ((this.encuestaRealizadaTutacadPK == null && other.encuestaRealizadaTutacadPK != null) || (this.encuestaRealizadaTutacadPK != null && !this.encuestaRealizadaTutacadPK.equals(other.encuestaRealizadaTutacadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.tutoria.entities.EncuestaRealizadaTutacad[ encuestaRealizadaTutacadPK=" + encuestaRealizadaTutacadPK + " ]";
    }
    
}
