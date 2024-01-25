/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author victor.barba
 */
@Entity
public class MatriculaExt implements Serializable {

    private static final long serialVersionUID = 105L;
    @EmbeddedId
    private MatriculaPK matriculaPK;
    @Column(name = "NOMBRE_CICLO")
    private String nombreAnio;
    @Column(name = "PROGRAMA")
    private String programa;
    @Column(name = "AREA")
    private String area;
    @Column(name = "CODIGO_ESP")
    private Long codPrograma;
    @Column(name = "CODIGO_FACULTAD")
    private Long codArea;
    @Column(name = "COD_ESTUDIANTE")
    private Long codAlumno;
    @Column(name = "CODIGO_NIVEACAD")
    private Long codNivelAcad;
    @Column(name = "CODIGO_ESCUELA")
    private Long codEscuela;

    public MatriculaExt() {
    }

    /**
     * @return the nombreAnio
     */
    public String getNombreAnio() {
        return nombreAnio;
    }

    /**
     * @param nombreAnio the nombreAnio to set
     */
    public void setNombreAnio(String nombreAnio) {
        this.nombreAnio = nombreAnio;
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
     * @return the area
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area the area to set
     */
    public void setArea(String area) {
        this.area = area;
    }

    /**
     * @return the codPrograma
     */
    public Long getCodPrograma() {
        return codPrograma;
    }

    /**
     * @param codPrograma the codPrograma to set
     */
    public void setCodPrograma(Long codPrograma) {
        this.codPrograma = codPrograma;
    }

    /**
     * @return the codArea
     */
    public Long getCodArea() {
        return codArea;
    }

    /**
     * @param codArea the codArea to set
     */
    public void setCodArea(Long codArea) {
        this.codArea = codArea;
    }

    /**
     * @return the codAlumno
     */
    public Long getCodAlumno() {
        return codAlumno;
    }

    /**
     * @param codAlumno the codAlumno to set
     */
    public void setCodAlumno(Long codAlumno) {
        this.codAlumno = codAlumno;
    }

    /**
     * @return the codNivelAcad
     */
    public Long getCodNivelAcad() {
        return codNivelAcad;
    }

    /**
     * @param codNivelAcad the codNivelAcad to set
     */
    public void setCodNivelAcad(Long codNivelAcad) {
        this.codNivelAcad = codNivelAcad;
    }

    /**
     * @return the codEscuela
     */
    public Long getCodEscuela() {
        return codEscuela;
    }

    /**
     * @param codEscuela the codEscuela to set
     */
    public void setCodEscuela(Long codEscuela) {
        this.codEscuela = codEscuela;
    }

    /**
     * @return the matriculaPK
     */
    public MatriculaPK getMatriculaPK() {
        return matriculaPK;
    }

    /**
     * @param matriculaPK the matriculaPK to set
     */
    public void setMatriculaPK(MatriculaPK matriculaPK) {
        this.matriculaPK = matriculaPK;
    }

    public MatriculaExt(MatriculaPK matriculaPK, String nombreAnio, String programa, String area, Long codPrograma, Long codArea, Long codAlumno, Long codNivelAcad, Long codEscuela) {
        this.matriculaPK = matriculaPK;
        this.nombreAnio = nombreAnio;
        this.programa = programa;
        this.area = area;
        this.codPrograma = codPrograma;
        this.codArea = codArea;
        this.codAlumno = codAlumno;
        this.codNivelAcad = codNivelAcad;
        this.codEscuela = codEscuela;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + (this.matriculaPK != null ? this.matriculaPK.hashCode() : 0);
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
        final MatriculaExt other = (MatriculaExt) obj;
        if (this.matriculaPK != other.matriculaPK && (this.matriculaPK == null || !this.matriculaPK.equals(other.matriculaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "MatriculaExt{" + "matriculaPK=" + matriculaPK + ", nombreAnio=" + nombreAnio + ", programa=" + programa + ", area=" + area + ", codPrograma=" + codPrograma + ", codArea=" + codArea + ", codAlumno=" + codAlumno + ", codNivelAcad=" + codNivelAcad + ", codEscuela=" + codEscuela + '}';
    }
    
    
}
