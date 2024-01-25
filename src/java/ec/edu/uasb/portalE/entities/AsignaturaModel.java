/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author victor.barba
 */
@Entity
public class AsignaturaModel implements Serializable {

    private static final long serialVersionUID = 106L;
    @EmbeddedId
    private AsignaturaModelPk asignaturaModelPk;
    @Column(name = "IDENTIF_MATERIA")
    private String identifMateria;
    @Column(name = "NOMBRE_MATERIA")
    private String nombreMateria;
    @Column(name = "NOMBRE_NIVEL")
    private String nombreNivel;
    @Column(name = "ESTADO_SYLABUS")
    private String estadoSyllabus;
    @Column(name = "SIGLA_TMATERIA")
    private String siglaTmateria;
    @Column(name = "ESTADO_PAGESTAMPILLA")
    private Long estadoPagEstampilla;
    @Column(name = "NOTA_NTCICLO")
    private BigDecimal notaNtciclo;
    @Column(name = "NCRED_REGMAT")
    private Long ncredRegmat;
    @Column(name = "ASISTENCIA_NTCICLO")
    private BigDecimal asistenciaNtciclo;
    @Column(name = "EVALUACION_REALIZADA")
    private Long evaluacionRealizada;
    @Column(name = "ESTADO_REGMAT")
    private String estadoRegmat;
    @Column(name = "MATNIV_ESTADO")
    private String matnivEstado;
    @Column(name = "ESTADO_NTCICLO")
    private String estadoNtciclo;
    @Column(name = "CIC_ANIO")
    private Long anio;
    @Column(name = "NOMBRE_PARALELO")
    private String nombreParalelo;
    @Column(name = "FECHA_CREA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaCrea;
    @Transient
    private List<Horario> horarios;

    public AsignaturaModel() {
    }

    /**
     * @return the asignaturaModelPk
     */
    public AsignaturaModelPk getAsignaturaModelPk() {
        return asignaturaModelPk;
    }

    /**
     * @param asignaturaModelPk the asignaturaModelPk to set
     */
    public void setAsignaturaModelPk(AsignaturaModelPk asignaturaModelPk) {
        this.asignaturaModelPk = asignaturaModelPk;
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
     * @return the estadoSyllabus
     */
    public String getEstadoSyllabus() {
        return estadoSyllabus;
    }

    /**
     * @param estadoSyllabus the estadoSyllabus to set
     */
    public void setEstadoSyllabus(String estadoSyllabus) {
        this.estadoSyllabus = estadoSyllabus;
    }

    /**
     * @return the identifMateria
     */
    public String getIdentifMateria() {
        return identifMateria;
    }

    /**
     * @param identifMateria the identifMateria to set
     */
    public void setIdentifMateria(String identifMateria) {
        this.identifMateria = identifMateria;
    }

    /**
     * @return the siglaTmateria
     */
    public String getSiglaTmateria() {
        return siglaTmateria;
    }

    /**
     * @param siglaTmateria the siglaTmateria to set
     */
    public void setSiglaTmateria(String siglaTmateria) {
        this.siglaTmateria = siglaTmateria;
    }

    /**
     * @return the notaNtciclo
     */
    public BigDecimal getNotaNtciclo() {
        return notaNtciclo;
    }

    /**
     * @param notaNtciclo the notaNtciclo to set
     */
    public void setNotaNtciclo(BigDecimal notaNtciclo) {
        this.notaNtciclo = notaNtciclo;
    }

    /**
     * @return the asistenciaNtciclo
     */
    public BigDecimal getAsistenciaNtciclo() {
        return asistenciaNtciclo;
    }

    /**
     * @param asistenciaNtciclo the asistenciaNtciclo to set
     */
    public void setAsistenciaNtciclo(BigDecimal asistenciaNtciclo) {
        this.asistenciaNtciclo = asistenciaNtciclo;
    }

    /**
     * @return the evaluacionRealizada
     */
    public Long getEvaluacionRealizada() {
        return evaluacionRealizada;
    }

    /**
     * @param evaluacionRealizada the evaluacionRealizada to set
     */
    public void setEvaluacionRealizada(Long evaluacionRealizada) {
        this.evaluacionRealizada = evaluacionRealizada;
    }

    public Long getEstadoPagEstampilla() {
        return estadoPagEstampilla;
    }

    public void setEstadoPagEstampilla(Long estadoPagEstampilla) {
        this.estadoPagEstampilla = estadoPagEstampilla;
    }

    /**
     * @return the estadoRegmat
     */
    public String getEstadoRegmat() {
        return estadoRegmat;
    }

    /**
     * @param estadoRegmat the estadoRegmat to set
     */
    public void setEstadoRegmat(String estadoRegmat) {
        this.estadoRegmat = estadoRegmat;
    }

    /**
     * @return the matnivEstado
     */
    public String getMatnivEstado() {
        return matnivEstado;
    }

    /**
     * @param matnivEstado the matnivEstado to set
     */
    public void setMatnivEstado(String matnivEstado) {
        this.matnivEstado = matnivEstado;
    }

    /**
     * @return the estadoNtciclo
     */
    public String getEstadoNtciclo() {
        return estadoNtciclo;
    }

    /**
     * @param estadoNtciclo the estadoNtciclo to set
     */
    public void setEstadoNtciclo(String estadoNtciclo) {
        this.estadoNtciclo = estadoNtciclo;
    }

    /**
     * @return the anio
     */
    public Long getAnio() {
        return anio;
    }

    /**
     * @param anio the anio to set
     */
    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public String getNombreParalelo() {
        return nombreParalelo;
    }

    public void setNombreParalelo(String nombreParalelo) {
        this.nombreParalelo = nombreParalelo;
    }

    public Date getFechaCrea() {
        return fechaCrea;
    }

    public void setFechaCrea(Date fechaCrea) {
        this.fechaCrea = fechaCrea;
    }

    public Long getNcredRegmat() {
        return ncredRegmat;
    }

    public void setNcredRegmat(Long ncredRegmat) {
        this.ncredRegmat = ncredRegmat;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public AsignaturaModel(AsignaturaModelPk asignaturaModelPk, String identifMateria, String nombreNivel, String estadoSyllabus,
            String siglaTmateria, Long estadoPagEstampilla, BigDecimal notaNtciclo, BigDecimal asistenciaNtciclo, Long ncredRegmat,
            Long evaluacionRealizada, String estadoRegmat, String matnivEstado, String estadoNtciclo, Long anio) {
        this.asignaturaModelPk = asignaturaModelPk;
        this.identifMateria = identifMateria;
        this.nombreNivel = nombreNivel;
        this.estadoSyllabus = estadoSyllabus;
        this.siglaTmateria = siglaTmateria;
        this.estadoPagEstampilla = estadoPagEstampilla;
        this.notaNtciclo = notaNtciclo;
        this.asistenciaNtciclo = asistenciaNtciclo;
        this.evaluacionRealizada = evaluacionRealizada;
        this.estadoRegmat = estadoRegmat;
        this.matnivEstado = matnivEstado;
        this.estadoNtciclo = estadoNtciclo;
        this.anio = anio;
        this.ncredRegmat = ncredRegmat;
    }

    @Override
    public String toString() {
        return "AsignaturaModel{" + "asignaturaModelPk=" + getAsignaturaModelPk() + ", identifMateria=" + identifMateria + ", nombreMateria="
                + nombreMateria + ", nombreNivel=" + nombreNivel + ", estadoSyllabus=" + estadoSyllabus + ", fechaCrea=" + fechaCrea
                + ", siglaTmateria=" + siglaTmateria
                + ", estadoPagEstampilla=" + estadoPagEstampilla + ", notaNtciclo=" + notaNtciclo + ", asistenciaNtciclo=" + asistenciaNtciclo
                + ", evaluacionRealizada=" + evaluacionRealizada + ", estadoRegmat=" + estadoRegmat + ", matnivEstado=" + matnivEstado
                + ", estadoNtciclo=" + estadoNtciclo + ", anio=" + anio + ", horarios=" + horarios + '}';
    }

}
