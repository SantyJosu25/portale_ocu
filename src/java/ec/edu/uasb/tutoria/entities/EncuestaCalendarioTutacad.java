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
@Table(name = "ENCUESTA_CALENDARIO_TUTACAD", schema = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EncuestaCalendarioTutacad.findAll", query = "SELECT e FROM EncuestaCalendarioTutacad e")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByAnio", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.anio = :anio")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByCiclo", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.ciclo = :ciclo")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByCodigoMateria", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.codigoMateria = :codigoMateria")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByCodigoEncuesta", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.codigoEncuesta = :codigoEncuesta")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByFechaInicio", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.fechaInicio = :fechaInicio")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByFechaFin", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.fechaFin = :fechaFin")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByCodigoProfesor", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.codigoProfesor = :codigoProfesor")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByCodigoEsp", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.codigoEsp = :codigoEsp")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByCodEstudiante", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.codEstudiante = :codEstudiante")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByTipoEncuesta", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.tipoEncuesta = :tipoEncuesta")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByFInicioCalificacion", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.fInicioCalificacion = :fInicioCalificacion")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByFFinCalificacion", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.fFinCalificacion = :fFinCalificacion")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByEstadoEncuesta", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.estadoEncuesta = :estadoEncuesta")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByCodigoNivel", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.codigoNivel = :codigoNivel")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByCodigoParalelo", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.codigoParalelo = :codigoParalelo")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByFReapertura", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.fReapertura = :fReapertura")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByNumRecordatorio", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.numRecordatorio = :numRecordatorio")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByNumRecordatorioReapertura", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.numRecordatorioReapertura = :numRecordatorioReapertura")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByCodigoInstancia", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.codigoInstancia = :codigoInstancia")
    , @NamedQuery(name = "EncuestaCalendarioTutacad.findByTstCodigo", query = "SELECT e FROM EncuestaCalendarioTutacad e WHERE e.encuestaCalendarioTutacadPK.tstCodigo = :tstCodigo")})
public class EncuestaCalendarioTutacad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EncuestaCalendarioTutacadPK encuestaCalendarioTutacadPK;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "TIPO_ENCUESTA")
    private Character tipoEncuesta;
    @Column(name = "F_INICIO_CALIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fInicioCalificacion;
    @Column(name = "F_FIN_CALIFICACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fFinCalificacion;
    @Column(name = "ESTADO_ENCUESTA")
    private Character estadoEncuesta;
    @Column(name = "F_REAPERTURA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fReapertura;
    @Column(name = "NUM_RECORDATORIO")
    private Integer numRecordatorio;
    @Column(name = "NUM_RECORDATORIO_REAPERTURA")
    private Integer numRecordatorioReapertura;
    @Column(name = "CODIGO_INSTANCIA")
    private Integer codigoInstancia;

    public EncuestaCalendarioTutacad() {
    }

    public EncuestaCalendarioTutacad(EncuestaCalendarioTutacadPK encuestaCalendarioTutacadPK) {
        this.encuestaCalendarioTutacadPK = encuestaCalendarioTutacadPK;
    }

    public EncuestaCalendarioTutacad(long anio, long ciclo, long codigoMateria, long codigoEncuesta, long codigoProfesor, long codigoEsp, long codEstudiante, long codigoNivel, long codigoParalelo, long tstCodigo) {
        this.encuestaCalendarioTutacadPK = new EncuestaCalendarioTutacadPK(anio, ciclo, codigoMateria, codigoEncuesta, codigoProfesor, codigoEsp, codEstudiante, codigoNivel, codigoParalelo, tstCodigo);
    }

    public EncuestaCalendarioTutacadPK getEncuestaCalendarioTutacadPK() {
        return encuestaCalendarioTutacadPK;
    }

    public void setEncuestaCalendarioTutacadPK(EncuestaCalendarioTutacadPK encuestaCalendarioTutacadPK) {
        this.encuestaCalendarioTutacadPK = encuestaCalendarioTutacadPK;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Character getTipoEncuesta() {
        return tipoEncuesta;
    }

    public void setTipoEncuesta(Character tipoEncuesta) {
        this.tipoEncuesta = tipoEncuesta;
    }

    public Date getFInicioCalificacion() {
        return fInicioCalificacion;
    }

    public void setFInicioCalificacion(Date fInicioCalificacion) {
        this.fInicioCalificacion = fInicioCalificacion;
    }

    public Date getFFinCalificacion() {
        return fFinCalificacion;
    }

    public void setFFinCalificacion(Date fFinCalificacion) {
        this.fFinCalificacion = fFinCalificacion;
    }

    public Character getEstadoEncuesta() {
        return estadoEncuesta;
    }

    public void setEstadoEncuesta(Character estadoEncuesta) {
        this.estadoEncuesta = estadoEncuesta;
    }

    public Date getFReapertura() {
        return fReapertura;
    }

    public void setFReapertura(Date fReapertura) {
        this.fReapertura = fReapertura;
    }

    public Integer getNumRecordatorio() {
        return numRecordatorio;
    }

    public void setNumRecordatorio(Integer numRecordatorio) {
        this.numRecordatorio = numRecordatorio;
    }

    public Integer getNumRecordatorioReapertura() {
        return numRecordatorioReapertura;
    }

    public void setNumRecordatorioReapertura(Integer numRecordatorioReapertura) {
        this.numRecordatorioReapertura = numRecordatorioReapertura;
    }

    public Integer getCodigoInstancia() {
        return codigoInstancia;
    }

    public void setCodigoInstancia(Integer codigoInstancia) {
        this.codigoInstancia = codigoInstancia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (encuestaCalendarioTutacadPK != null ? encuestaCalendarioTutacadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EncuestaCalendarioTutacad)) {
            return false;
        }
        EncuestaCalendarioTutacad other = (EncuestaCalendarioTutacad) object;
        if ((this.encuestaCalendarioTutacadPK == null && other.encuestaCalendarioTutacadPK != null) || (this.encuestaCalendarioTutacadPK != null && !this.encuestaCalendarioTutacadPK.equals(other.encuestaCalendarioTutacadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.tutoria.entities.EncuestaCalendarioTutacad[ encuestaCalendarioTutacadPK=" + encuestaCalendarioTutacadPK + " ]";
    }
    
}
