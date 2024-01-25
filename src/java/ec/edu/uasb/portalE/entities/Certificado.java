/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.entities;

import ec.edu.uasb.seg.entities.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityResult;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author victor.barba
 */
@Entity
@Table(name = "CERTIFICADO", schema = "GESTIONACADEMICA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Certificado.findByCerNumero", query = "SELECT c FROM Certificado c WHERE c.cerNumero = :cerNumero")
    ,
    @NamedQuery(name = "Certificado.findAllByUser", query = "SELECT c FROM Certificado c WHERE c.codEstudiante = :codEstudiante order by c.cerFechaCrea desc")
})

@SqlResultSetMapping(name = "ResultadoCertificado",
        entities = {
            @EntityResult(entityClass = Certificado.class)
            ,
                @EntityResult(entityClass = Usuario.class)
        }
)
public class Certificado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CER_CODIGO")
    private Long cerCodigo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CER_TIPO_DOC")
    private String cerTipoDoc;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PER_CODIGO")
    private long perCodigo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "COD_ESTUDIANTE")
    private long codEstudiante;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MATRICULA")
    private long matricula;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ANIO")
    private short anio;
    @Size(max = 25)
    @Column(name = "CER_NUMERO")
    private String cerNumero;
    @Size(max = 25)
    @Column(name = "CER_TRIMESTRE")
    private String cerTrimestre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "CER_ESTADO")
    private String cerEstado;
    @Column(name = "CER_FECHA_FIRMA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cerFechaFirma;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CER_VALOR")
    private BigDecimal cerValor;
    @Column(name = "CER_FECHA_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cerFechaPago;
    @Size(max = 60)
    @Column(name = "CER_TRANSACCION")
    private String cerTransaccion;
    @Basic(optional = false)
    @Column(name = "CER_FECHA_DESCARGA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cerFechaDescarga;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 15)
    @Column(name = "CER_USU_CREA")
    private String cerUsuCrea;
    @Basic(optional = false)
    @NotNull
    @Column(name = "CER_FECHA_CREA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cerFechaCrea;
    @Transient
    private Usuario usuario;

    public Certificado() {
    }

    public Certificado(Long cerCodigo) {
        this.cerCodigo = cerCodigo;
    }

    public Certificado(Long cerCodigo, String cerTipoDoc, long perCodigo, long codEstudiante, long matricula, short anio, String cerEstado, BigDecimal cerValor, Date cerFechaDescarga, String cerUsuCrea, Date cerFechaCrea) {
        this.cerCodigo = cerCodigo;
        this.cerTipoDoc = cerTipoDoc;
        this.perCodigo = perCodigo;
        this.codEstudiante = codEstudiante;
        this.matricula = matricula;
        this.anio = anio;
        this.cerEstado = cerEstado;
        this.cerValor = cerValor;
        this.cerFechaDescarga = cerFechaDescarga;
        this.cerUsuCrea = cerUsuCrea;
        this.cerFechaCrea = cerFechaCrea;
    }

    public Long getCerCodigo() {
        return cerCodigo;
    }

    public void setCerCodigo(Long cerCodigo) {
        this.cerCodigo = cerCodigo;
    }

    public String getCerTipoDoc() {
        return cerTipoDoc;
    }

    public void setCerTipoDoc(String cerTipoDoc) {
        this.cerTipoDoc = cerTipoDoc;
    }

    public long getPerCodigo() {
        return perCodigo;
    }

    public void setPerCodigo(long perCodigo) {
        this.perCodigo = perCodigo;
    }

    public long getCodEstudiante() {
        return codEstudiante;
    }

    public void setCodEstudiante(long codEstudiante) {
        this.codEstudiante = codEstudiante;
    }

    public long getMatricula() {
        return matricula;
    }

    public void setMatricula(long matricula) {
        this.matricula = matricula;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }

    public String getCerNumero() {
        return cerNumero;
    }

    public void setCerNumero(String cerNumero) {
        this.cerNumero = cerNumero;
    }

    public String getCerTrimestre() {
        return cerTrimestre;
    }

    public void setCerTrimestre(String cerTrimestre) {
        this.cerTrimestre = cerTrimestre;
    }

    public String getCerEstado() {
        return cerEstado;
    }

    public void setCerEstado(String cerEstado) {
        this.cerEstado = cerEstado;
    }

    public Date getCerFechaFirma() {
        return cerFechaFirma;
    }

    public void setCerFechaFirma(Date cerFechaFirma) {
        this.cerFechaFirma = cerFechaFirma;
    }

    public BigDecimal getCerValor() {
        return cerValor;
    }

    public void setCerValor(BigDecimal cerValor) {
        this.cerValor = cerValor;
    }

    public Date getCerFechaPago() {
        return cerFechaPago;
    }

    public void setCerFechaPago(Date cerFechaPago) {
        this.cerFechaPago = cerFechaPago;
    }

    public String getCerTransaccion() {
        return cerTransaccion;
    }

    public void setCerTransaccion(String cerTransaccion) {
        this.cerTransaccion = cerTransaccion;
    }

    public Date getCerFechaDescarga() {
        return cerFechaDescarga;
    }

    public void setCerFechaDescarga(Date cerFechaDescarga) {
        this.cerFechaDescarga = cerFechaDescarga;
    }

    public String getCerUsuCrea() {
        return cerUsuCrea;
    }

    public void setCerUsuCrea(String cerUsuCrea) {
        this.cerUsuCrea = cerUsuCrea;
    }

    public Date getCerFechaCrea() {
        return cerFechaCrea;
    }

    public void setCerFechaCrea(Date cerFechaCrea) {
        this.cerFechaCrea = cerFechaCrea;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cerCodigo != null ? cerCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Certificado)) {
            return false;
        }
        Certificado other = (Certificado) object;
        if ((this.cerCodigo == null && other.cerCodigo != null) || (this.cerCodigo != null && !this.cerCodigo.equals(other.cerCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Certificado{" + "cerCodigo=" + cerCodigo
                + ", cerTipoDoc=" + cerTipoDoc
                + ", perCodigo=" + perCodigo
                + ", codEstudiante=" + codEstudiante
                + ", matricula=" + matricula
                + ", anio=" + anio
                + ", cerNumero=" + cerNumero
                //                + ", cerTrimestre=" + cerTrimestre 
                //                + ", cerEstado=" + cerEstado 
                //                + ", cerFechaFirma=" + cerFechaFirma 
                //                + ", cerValor=" + cerValor 
                //                + ", cerFechaPago=" + cerFechaPago 
                //                + ", cerTransaccion=" + cerTransaccion 
                + ", cerFechaDescarga=" + cerFechaDescarga
                + ", cerUsuCrea=" + cerUsuCrea
                + ", cerFechaCrea=" + cerFechaCrea
                + '}';
    }

}
