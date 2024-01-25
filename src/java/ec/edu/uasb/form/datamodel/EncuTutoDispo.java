/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.datamodel;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author marjorie.fiallos
 */
@Entity
public class EncuTutoDispo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private EncuTutoDispoPK EncuTutoDispoPK;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "CICLO")
    private Long ciclo;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "CODIGO_PROFESOR")
    private Long codigoProfesor;
    @Column(name = "PROGRAMA")
    private String programa;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "HABILITADO")
    private String habilitado;
    @Column(name = "CODIGO_INSTANCIA")
    private Long codigoInstancia;
    @Column(name = "TIPO_TUTORIA")
    private String tipoTutoria;
    @Column(name = "CODIGO_MATERIA")
    private Long codigoMateria;

    public EncuTutoDispo() {
    }

    public EncuTutoDispo(EncuTutoDispoPK EncuTutoDispoPK, String titulo, String referencia, Long ciclo, Date fechaInicio, Date fechaFin, Long codigoProfesor, String programa, String nombres, String estado, String habilitado, Long codigoInstancia, String tipoTutoria,Long codigoMateria) {
        this.EncuTutoDispoPK = EncuTutoDispoPK;
        this.titulo = titulo;
        this.referencia = referencia;
        this.ciclo = ciclo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.codigoProfesor = codigoProfesor;
        this.programa = programa;
        this.nombres = nombres;
        this.estado = estado;
        this.habilitado = habilitado;
        this.codigoInstancia = codigoInstancia;
        this.tipoTutoria = tipoTutoria;
        this.codigoMateria=codigoMateria;
    }

    /**
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * @return the referencia
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * @param referencia the referencia to set
     */
    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    /**
     * @return the ciclo
     */
    public long getCiclo() {
        return ciclo;
    }

    /**
     * @param ciclo the ciclo to set
     */
    public void setCiclo(long ciclo) {
        this.ciclo = ciclo;
    }

    /**
     * @return the fechaInicio
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * @param fechaInicio the fechaInicio to set
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * @return the fechaFin
     */
    public Date getFechaFin() {
        return fechaFin;
    }

    /**
     * @param fechaFin the fechaFin to set
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * @return the codigoProfesor
     */
    public long getCodigoProfesor() {
        return codigoProfesor;
    }

    /**
     * @param codigoProfesor the codigoProfesor to set
     */
    public void setCodigoProfesor(long codigoProfesor) {
        this.codigoProfesor = codigoProfesor;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the programa
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * @param programa the programa to set
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    /**
     * @return the nombres
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * @param nombres the nombres to set
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * @return the habilitado
     */
    public String getHabilitado() {
        return habilitado;
    }

    /**
     * @param habilitado the habilitado to set
     */
    public void setHabilitado(String habilitado) {
        this.habilitado = habilitado;
    }

    public Long getCodigoInstancia() {
        return codigoInstancia;
    }

    public void setCodigoInstancia(Long codigoInstancia) {
        this.codigoInstancia = codigoInstancia;
    }

    public String getTipoTutoria() {
        return tipoTutoria;
    }

    public void setTipoTutoria(String tipoTutoria) {
        this.tipoTutoria = tipoTutoria;
    }

    public EncuTutoDispoPK getEncuTutoDispoPK() {
        return EncuTutoDispoPK;
    }

    public void setEncuTutoDispoPK(EncuTutoDispoPK EncuTutoDispoPK) {
        this.EncuTutoDispoPK = EncuTutoDispoPK;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.getEncuTutoDispoPK() != null ? this.getEncuTutoDispoPK().hashCode() : 0);
        hash = 79 * hash + (this.titulo != null ? this.titulo.hashCode() : 0);
        hash = 79 * hash + (this.referencia != null ? this.referencia.hashCode() : 0);
        hash = 79 * hash + (this.ciclo != null ? this.ciclo.hashCode() : 0);
        hash = 79 * hash + (this.fechaInicio != null ? this.fechaInicio.hashCode() : 0);
        hash = 79 * hash + (this.fechaFin != null ? this.fechaFin.hashCode() : 0);
        hash = 79 * hash + (this.codigoProfesor != null ? this.codigoProfesor.hashCode() : 0);
        hash = 79 * hash + (this.programa != null ? this.programa.hashCode() : 0);
        hash = 79 * hash + (this.nombres != null ? this.nombres.hashCode() : 0);
        hash = 79 * hash + (this.estado != null ? this.estado.hashCode() : 0);
        hash = 79 * hash + (this.habilitado != null ? this.habilitado.hashCode() : 0);
        hash = 79 * hash + (this.codigoInstancia != null ? this.codigoInstancia.hashCode() : 0);
        hash = 79 * hash + (this.tipoTutoria != null ? this.tipoTutoria.hashCode() : 0);
        hash = 79 * hash + (this.codigoMateria != null ? this.codigoMateria.hashCode() : 0);
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
        final EncuTutoDispo other = (EncuTutoDispo) obj;
        if (this.getEncuTutoDispoPK() != other.getEncuTutoDispoPK() && (this.getEncuTutoDispoPK() == null || !this.EncuTutoDispoPK.equals(other.EncuTutoDispoPK))) {
            return false;
        }
        if ((this.titulo == null) ? (other.titulo != null) : !this.titulo.equals(other.titulo)) {
            return false;
        }
        if ((this.referencia == null) ? (other.referencia != null) : !this.referencia.equals(other.referencia)) {
            return false;
        }
        if (this.ciclo != other.ciclo && (this.ciclo == null || !this.ciclo.equals(other.ciclo))) {
            return false;
        }
        if (this.fechaInicio != other.fechaInicio && (this.fechaInicio == null || !this.fechaInicio.equals(other.fechaInicio))) {
            return false;
        }
        if (this.fechaFin != other.fechaFin && (this.fechaFin == null || !this.fechaFin.equals(other.fechaFin))) {
            return false;
        }
        if (this.codigoProfesor != other.codigoProfesor && (this.codigoProfesor == null || !this.codigoProfesor.equals(other.codigoProfesor))) {
            return false;
        }
        if ((this.programa == null) ? (other.programa != null) : !this.programa.equals(other.programa)) {
            return false;
        }
        if ((this.nombres == null) ? (other.nombres != null) : !this.nombres.equals(other.nombres)) {
            return false;
        }
        if ((this.estado == null) ? (other.estado != null) : !this.estado.equals(other.estado)) {
            return false;
        }
        if ((this.habilitado == null) ? (other.habilitado != null) : !this.habilitado.equals(other.habilitado)) {
            return false;
        }
        if (this.codigoInstancia != other.codigoInstancia && (this.codigoInstancia == null || !this.codigoInstancia.equals(other.codigoInstancia))) {
            return false;
        }
        if (this.tipoTutoria != other.tipoTutoria && (this.tipoTutoria == null || !this.tipoTutoria.equals(other.tipoTutoria))) {
            return false;
        }
         if (this.codigoMateria != other.codigoMateria && (this.codigoMateria == null || !this.codigoMateria.equals(other.codigoMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EncuTutoDispo{" + "getEncuTutoDispoPK=" + getEncuTutoDispoPK() + ", titulo=" + titulo + ", referencia=" + referencia + ", ciclo=" + ciclo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", codigoProfesor=" + codigoProfesor + ", programa=" + programa + ", nombres=" + nombres + ", estado=" + estado + ", habilitado=" + habilitado + ", codigoInstancia=" + codigoInstancia +", tipoTutoria=" + tipoTutoria + '}';
    }
}
