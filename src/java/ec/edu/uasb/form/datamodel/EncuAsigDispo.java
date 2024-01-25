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
 * @author victor.barba
 */
@Entity
public class EncuAsigDispo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected EncuAsigDispoPK encuAsigDispoPK;
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "REFERENCIA")
    private String referencia;
    @Column(name = "ANIO")
    private Long anio;
    @Column(name = "CICLO")
    private Long ciclo;
    @Column(name = "CODIGO_ENCUESTA")
    private long codigoEncuesta;
    @Column(name = "FECHA_INICIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaInicio;
    @Column(name = "FECHA_FIN")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "NOMBRES")
    private String nombres;
    @Column(name = "NOMBRE_MATERIA")
    private String nombreMateria;
    @Column(name = "NOMBRE_NIVEL")
    private String nombreNivel;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "HABILITADO")
    private String habilitado;


    public EncuAsigDispo() {
    }

    public EncuAsigDispo(EncuAsigDispoPK encuAsigDispoPK, String titulo, String referencia, Long anio, Long ciclo, long codigoEncuesta, Date fechaInicio, Date fechaFin, String nombres,
            String nombreMateria, String nombreNivel, String estado, String habilitado) {
        this.encuAsigDispoPK = encuAsigDispoPK;
        this.titulo = titulo;
        this.referencia = referencia;
        this.anio = anio;
        this.ciclo = ciclo;
        this.codigoEncuesta = codigoEncuesta;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nombres = nombres;
        this.nombreMateria = nombreMateria;
        this.nombreNivel = nombreNivel;
        this.estado = estado;
        this.habilitado = habilitado;

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
     * @return the codigoEncuesta
     */
    public long getCodigoEncuesta() {
        return codigoEncuesta;
    }

    /**
     * @param codigoEncuesta the codigoEncuesta to set
     */
    public void setCodigoEncuesta(long codigoEncuesta) {
        this.codigoEncuesta = codigoEncuesta;
    }

    /**
     * @return the anio
     */
    public long getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(long anio) {
        this.anio = anio;
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
     * @return the nombreMateria
     */
    public String getNombreMateria() {
        return nombreMateria;
    }

    /**
     * @param nombreMateria the nombreMateria to set
     */
    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
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
     * @return the nombreNivel
     */
    public String getNombreNivel() {
        return nombreNivel;
    }

    /**
     * @param nombreNivel the nombreNivel to set
     */
    public void setNombreNivel(String nombreNivel) {
        this.nombreNivel = nombreNivel;
    }

    /**
     * @return the encuAsigDispoPK
     */
    public EncuAsigDispoPK getEncuAsigDispoPK() {
        return encuAsigDispoPK;
    }

    /**
     * @param encuAsigDispoPK the encuAsigDispoPK to set
     */
    public void setEncuAsigDispoPK(EncuAsigDispoPK encuAsigDispoPK) {
        this.encuAsigDispoPK = encuAsigDispoPK;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (this.encuAsigDispoPK != null ? this.encuAsigDispoPK.hashCode() : 0);
        hash = 47 * hash + (this.titulo != null ? this.titulo.hashCode() : 0);
        hash = 47 * hash + (this.referencia != null ? this.referencia.hashCode() : 0);
        hash = 47 * hash + (this.anio != null ? this.anio.hashCode() : 0);
        hash = 47 * hash + (this.ciclo != null ? this.ciclo.hashCode() : 0);
        hash = 47 * hash + (int) (this.codigoEncuesta ^ (this.codigoEncuesta >>> 32));
        hash = 47 * hash + (this.fechaInicio != null ? this.fechaInicio.hashCode() : 0);
        hash = 47 * hash + (this.fechaFin != null ? this.fechaFin.hashCode() : 0);
        hash = 47 * hash + (this.nombres != null ? this.nombres.hashCode() : 0);
        hash = 47 * hash + (this.nombreMateria != null ? this.nombreMateria.hashCode() : 0);
        hash = 47 * hash + (this.nombreNivel != null ? this.nombreNivel.hashCode() : 0);
        hash = 47 * hash + (this.estado != null ? this.estado.hashCode() : 0);
        hash = 47 * hash + (this.habilitado != null ? this.habilitado.hashCode() : 0);
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
        final EncuAsigDispo other = (EncuAsigDispo) obj;
        if (this.encuAsigDispoPK != other.encuAsigDispoPK && (this.encuAsigDispoPK == null || !this.encuAsigDispoPK.equals(other.encuAsigDispoPK))) {
            return false;
        }
        if ((this.titulo == null) ? (other.titulo != null) : !this.titulo.equals(other.titulo)) {
            return false;
        }
        if ((this.referencia == null) ? (other.referencia != null) : !this.referencia.equals(other.referencia)) {
            return false;
        }
        if (this.anio != other.anio && (this.anio == null || !this.anio.equals(other.anio))) {
            return false;
        }
        if (this.ciclo != other.ciclo && (this.ciclo == null || !this.ciclo.equals(other.ciclo))) {
            return false;
        }
        if (this.codigoEncuesta != other.codigoEncuesta) {
            return false;
        }
        if (this.fechaInicio != other.fechaInicio && (this.fechaInicio == null || !this.fechaInicio.equals(other.fechaInicio))) {
            return false;
        }
        if (this.fechaFin != other.fechaFin && (this.fechaFin == null || !this.fechaFin.equals(other.fechaFin))) {
            return false;
        }
        if ((this.nombres == null) ? (other.nombres != null) : !this.nombres.equals(other.nombres)) {
            return false;
        }
        if ((this.nombreMateria == null) ? (other.nombreMateria != null) : !this.nombreMateria.equals(other.nombreMateria)) {
            return false;
        }
        if ((this.nombreNivel == null) ? (other.nombreNivel != null) : !this.nombreNivel.equals(other.nombreNivel)) {
            return false;
        }
        if ((this.estado == null) ? (other.estado != null) : !this.estado.equals(other.estado)) {
            return false;
        }
        if ((this.habilitado == null) ? (other.habilitado != null) : !this.habilitado.equals(other.habilitado)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "EncuAsigDispo{" + "encuAsigDispoPK=" + encuAsigDispoPK 
                + ", titulo=" + titulo
                + ", referencia=" + referencia 
                + ", anio=" + anio 
                + ", ciclo=" + ciclo
                + ", codigoEncuesta=" + codigoEncuesta
                + ", fechaInicio=" + fechaInicio 
                + ", fechaFin=" + fechaFin 
                + ", nombres=" + nombres 
                + ", nombreMateria=" + nombreMateria
                + ", nombreNivel=" + nombreNivel 
                + ", estado=" + estado 
                + ", habilitado=" + habilitado
                + '}';
    }

}
