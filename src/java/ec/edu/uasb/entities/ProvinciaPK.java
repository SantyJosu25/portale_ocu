/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author victor.barba
 */
@Embeddable
public class ProvinciaPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "CODIGO_PAIS")
    private String codigoPais;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "CODIGO_PROVINCIA")
    private String codigoProvincia;

    public ProvinciaPK() {
    }

    public ProvinciaPK(String codigoPais, String codigoProvincia) {
        this.codigoPais = codigoPais;
        this.codigoProvincia = codigoProvincia;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public String getCodigoProvincia() {
        return codigoProvincia;
    }

    public void setCodigoProvincia(String codigoProvincia) {
        this.codigoProvincia = codigoProvincia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPais != null ? codigoPais.hashCode() : 0);
        hash += (codigoProvincia != null ? codigoProvincia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProvinciaPK)) {
            return false;
        }
        ProvinciaPK other = (ProvinciaPK) object;
        if ((this.codigoPais == null && other.codigoPais != null) || (this.codigoPais != null && !this.codigoPais.equals(other.codigoPais))) {
            return false;
        }
        if ((this.codigoProvincia == null && other.codigoProvincia != null) || (this.codigoProvincia != null && !this.codigoProvincia.equals(other.codigoProvincia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.portaldoc.entity.ProvinciaPK[ codigoPais=" + codigoPais + ", codigoProvincia=" + codigoProvincia + " ]";
    }
    
}
