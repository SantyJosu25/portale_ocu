/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.entities;

import ec.edu.uasb.portalE.entities.Estudiante;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "APELATIVO")
@NamedQueries({
    @NamedQuery(name = "Apelativo.findAll", query = "SELECT a FROM Apelativo a order by a.nombreApelativo")})
public class Apelativo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CODIGO_APELATIVO")
    private Long codigoApelativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "NOMBRE_APELATIVO")
    private String nombreApelativo;
    @Size(max = 30)
    @Column(name = "NOMBRE_FEM_APELATIVO")
    private String nombreFemApelativo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "SIGLAS_APELATIVO")
    private String siglasApelativo;
    @Size(max = 30)
    @Column(name = "SIGLAS_FEM_APELATIVO")
    private String siglasFemApelativo;
    @OneToMany(mappedBy = "apelativo")
    private Collection<Estudiante> estudianteCollection;

    public Apelativo() {
    }

    public Apelativo(Long codigoApelativo) {
        this.codigoApelativo = codigoApelativo;
    }

    public Apelativo(Long codigoApelativo, String nombreApelativo, String siglasApelativo) {
        this.codigoApelativo = codigoApelativo;
        this.nombreApelativo = nombreApelativo;
        this.siglasApelativo = siglasApelativo;
    }

    public Long getCodigoApelativo() {
        return codigoApelativo;
    }

    public void setCodigoApelativo(Long codigoApelativo) {
        this.codigoApelativo = codigoApelativo;
    }

    public String getNombreApelativo() {
        return nombreApelativo;
    }

    public void setNombreApelativo(String nombreApelativo) {
        this.nombreApelativo = nombreApelativo;
    }

    public String getNombreFemApelativo() {
        return nombreFemApelativo;
    }

    public void setNombreFemApelativo(String nombreFemApelativo) {
        this.nombreFemApelativo = nombreFemApelativo;
    }

    public String getSiglasApelativo() {
        return siglasApelativo;
    }

    public void setSiglasApelativo(String siglasApelativo) {
        this.siglasApelativo = siglasApelativo;
    }

    public String getSiglasFemApelativo() {
        return siglasFemApelativo;
    }

    public void setSiglasFemApelativo(String siglasFemApelativo) {
        this.siglasFemApelativo = siglasFemApelativo;
    }

    public Collection<Estudiante> getEstudianteCollection() {
        return estudianteCollection;
    }

    public void setEstudianteCollection(Collection<Estudiante> estudianteCollection) {
        this.estudianteCollection = estudianteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoApelativo != null ? codigoApelativo.hashCode() : 0);
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
        final Apelativo other = (Apelativo) obj;
        if (this.codigoApelativo != other.codigoApelativo && (this.codigoApelativo == null || !this.codigoApelativo.equals(other.codigoApelativo))) {
            return false;
        }
        return true;
    }



    @Override
    public String toString() {
        return "Apelativo{" + "codigoApelativo=" + codigoApelativo + ", nombreApelativo=" + nombreApelativo + ", nombreFemApelativo=" + nombreFemApelativo + ", siglasApelativo=" + siglasApelativo + ", siglasFemApelativo=" + siglasFemApelativo + ", estudianteCollection=" + estudianteCollection + '}';
    }


}
