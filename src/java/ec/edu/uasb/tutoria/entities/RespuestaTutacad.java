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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author marjorie.fiallos
 */
@Entity
@Table(name = "RESPUESTA_TUTACAD", schema = "EVALUACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RespuestaTutacad.findAll", query = "SELECT r FROM RespuestaTutacad r")
    , @NamedQuery(name = "RespuestaTutacad.findByAnio", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.anio = :anio")
    , @NamedQuery(name = "RespuestaTutacad.findByCiclo", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.ciclo = :ciclo")
    , @NamedQuery(name = "RespuestaTutacad.findByCodigoMateria", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.codigoMateria = :codigoMateria")
    , @NamedQuery(name = "RespuestaTutacad.findByNumeroEncuesta", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.numeroEncuesta = :numeroEncuesta")
    , @NamedQuery(name = "RespuestaTutacad.findByCodigoPregunta", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.codigoPregunta = :codigoPregunta")
    , @NamedQuery(name = "RespuestaTutacad.findByCodigoEncuesta", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.codigoEncuesta = :codigoEncuesta")
    , @NamedQuery(name = "RespuestaTutacad.findByCodigoProfesor", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.codigoProfesor = :codigoProfesor")
    , @NamedQuery(name = "RespuestaTutacad.findByValor", query = "SELECT r FROM RespuestaTutacad r WHERE r.valor = :valor")
    , @NamedQuery(name = "RespuestaTutacad.findByTexto", query = "SELECT r FROM RespuestaTutacad r WHERE r.texto = :texto")
    , @NamedQuery(name = "RespuestaTutacad.findByFecha", query = "SELECT r FROM RespuestaTutacad r WHERE r.fecha = :fecha")
    , @NamedQuery(name = "RespuestaTutacad.findByCodigoEsp", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.codigoEsp = :codigoEsp")
    , @NamedQuery(name = "RespuestaTutacad.findByCodigoNivel", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.codigoNivel = :codigoNivel")
    , @NamedQuery(name = "RespuestaTutacad.findByCodigoParalelo", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.codigoParalelo = :codigoParalelo")
    , @NamedQuery(name = "RespuestaTutacad.findByCodInstancia", query = "SELECT r FROM RespuestaTutacad r WHERE r.respuestaTutacadPK.codInstancia = :codInstancia")})
public class RespuestaTutacad implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RespuestaTutacadPK respuestaTutacadPK;
    @Column(name = "VALOR")
    private Long valor;
    @Size(max = 8000)
    @Column(name = "TEXTO")
    private String texto;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    public RespuestaTutacad() {
    }

    public RespuestaTutacad(RespuestaTutacadPK respuestaTutacadPK) {
        this.respuestaTutacadPK = respuestaTutacadPK;
    }

    public RespuestaTutacad(long anio, long ciclo, long codigoMateria, long numeroEncuesta, long codigoPregunta, long codigoEncuesta, long codigoProfesor, long codigoEsp, long codigoNivel, long codigoParalelo, long codInstancia) {
        this.respuestaTutacadPK = new RespuestaTutacadPK(anio, ciclo, codigoMateria, numeroEncuesta, codigoPregunta, codigoEncuesta, codigoProfesor, codigoEsp, codigoNivel, codigoParalelo, codInstancia);
    }

    public RespuestaTutacadPK getRespuestaTutacadPK() {
        return respuestaTutacadPK;
    }

    public void setRespuestaTutacadPK(RespuestaTutacadPK respuestaTutacadPK) {
        this.respuestaTutacadPK = respuestaTutacadPK;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (respuestaTutacadPK != null ? respuestaTutacadPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RespuestaTutacad)) {
            return false;
        }
        RespuestaTutacad other = (RespuestaTutacad) object;
        if ((this.respuestaTutacadPK == null && other.respuestaTutacadPK != null) || (this.respuestaTutacadPK != null && !this.respuestaTutacadPK.equals(other.respuestaTutacadPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.tutoria.entities.RespuestaTutacad[ respuestaTutacadPK=" + respuestaTutacadPK + " ]";
    }
    
}
