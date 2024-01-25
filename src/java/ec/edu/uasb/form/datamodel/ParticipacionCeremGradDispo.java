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
public class ParticipacionCeremGradDispo implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private ParticipacionCeremGradDispoPK ParticipacionCeremGradDispoPK;
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
    @Column(name = "FECHA_GRUPO")
    private String fechaGrupo;
    @Column(name = "FECHA_CEREMONIA")
    private String fechaCeremonia;
    @Column(name = "FECHA_ALQUILER")
    private String fechaAlquiler;
    @Column(name = "CORTE")
    private Long corte;

    public ParticipacionCeremGradDispo() {
    }

    public ParticipacionCeremGradDispo(ParticipacionCeremGradDispoPK ParticipacionCeremGradDispoPK, String titulo, String referencia, Long ciclo, Date fechaInicio, Date fechaFin, Long codigoProfesor, String programa, String nombres, String estado, String habilitado, String fechaGrupo, String fechaCeremonia, String fechaAlquiler, Long corte) {
        this.ParticipacionCeremGradDispoPK = ParticipacionCeremGradDispoPK;
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
        this.fechaGrupo = fechaGrupo;
        this.fechaCeremonia = fechaCeremonia;
        this.fechaAlquiler = fechaAlquiler;
        this.corte = corte;
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

    public ParticipacionCeremGradDispoPK getParticipacionCeremGradDispoPK() {
        return ParticipacionCeremGradDispoPK;
    }

    public void setParticipacionCeremGradDispoPK(ParticipacionCeremGradDispoPK ParticipacionCeremGradDispoPK) {
        this.ParticipacionCeremGradDispoPK = ParticipacionCeremGradDispoPK;
    }

    public String getFechaGrupo() {
        return fechaGrupo;
    }

    public void setFechaGrupo(String fechaGrupo) {
        this.fechaGrupo = fechaGrupo;
    }

    public String getFechaCeremonia() {
        return fechaCeremonia;
    }

    public void setFechaCeremonia(String fechaCeremonia) {
        this.fechaCeremonia = fechaCeremonia;
    }

    public String getFechaAlquiler() {
        return fechaAlquiler;
    }

    public void setFechaAlquiler(String fechaAlquiler) {
        this.fechaAlquiler = fechaAlquiler;
    }

    public Long getCorte() {
        return corte;
    }

    public void setCorte(Long corte) {
        this.corte = corte;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.getParticipacionCeremGradDispoPK() != null ? this.getParticipacionCeremGradDispoPK().hashCode() : 0);
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
        hash = 79 * hash + (this.fechaGrupo != null ? this.fechaGrupo.hashCode() : 0);
        hash = 79 * hash + (this.fechaCeremonia != null ? this.fechaCeremonia.hashCode() : 0);
        hash = 79 * hash + (this.fechaAlquiler != null ? this.fechaAlquiler.hashCode() : 0);
        hash = 79 * hash + (this.corte != null ? this.corte.hashCode() : 0);
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
        final ParticipacionCeremGradDispo other = (ParticipacionCeremGradDispo) obj;
        if (this.getParticipacionCeremGradDispoPK() != other.getParticipacionCeremGradDispoPK() && (this.getParticipacionCeremGradDispoPK() == null || !this.ParticipacionCeremGradDispoPK.equals(other.ParticipacionCeremGradDispoPK))) {
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
         if ((this.fechaGrupo == null) ? (other.fechaGrupo != null) : !this.fechaGrupo.equals(other.fechaGrupo)) {
            return false;
        }
          if ((this.fechaCeremonia == null) ? (other.fechaCeremonia != null) : !this.habilitado.equals(other.fechaCeremonia)) {
            return false;
        }
           if ((this.fechaAlquiler == null) ? (other.fechaAlquiler != null) : !this.fechaAlquiler.equals(other.fechaAlquiler)) {
            return false;
        }
            if ((this.corte == null) ? (other.corte != null) : !this.corte.equals(other.corte)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "ParticipacionCeremGradDispo{" + "getParticipacionCeremGradDispoPK=" + getParticipacionCeremGradDispoPK() + ", titulo=" + titulo + ", referencia=" + referencia + ", ciclo=" + ciclo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + ", codigoProfesor=" + codigoProfesor + ", programa=" + programa + ", nombres=" + nombres + ", estado=" + estado + ", habilitado=" + habilitado +", fechaGrupo=" + fechaGrupo + ", fechaCeremonia=" + fechaCeremonia +", fechaAlquiler=" + fechaAlquiler +", corte=" + corte +'}';
    }
}
