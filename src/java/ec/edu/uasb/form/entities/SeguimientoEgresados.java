/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.form.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author johana.orozco
 */
@Entity
@Table(name = "SEGUIMIENTO_EGRESADOS")
@NamedQueries({
    @NamedQuery(name = "SeguimientoEgresados.findAll", query = "SELECT s FROM SeguimientoEgresados s")})
public class SeguimientoEgresados implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SeguimientoEgresadosPK seguimientoEgresadosPK;
    @Size(max = 120)
    @Column(name = "a1_1")
    private String a11;
    @Column(name = "a1_2")
    private Character a12;
    @Column(name = "a1_3")
    @Temporal(TemporalType.TIMESTAMP)
    private Date a13;
    @Size(max = 30)
    @Column(name = "a1_4")
    private String a14;
    @Size(max = 3)
    @Column(name = "a1_5")
    private String a15;
    @Column(name = "a1_6")
    private Long a16;
    @Column(name = "a1_7")
    private Character a17;
    @Size(max = 30)
    @Column(name = "a1_8")
    private String a18;
    @Size(max = 500)
    @Column(name = "a2_1")
    private String a21;
    @Size(max = 100)
    @Column(name = "a2_2")
    private String a22;
    @Size(max = 100)
    @Column(name = "a2_3")
    private String a23;
    @Size(max = 100)
    @Column(name = "a2_4")
    private String a24;
    @Column(name = "a3_1")
    private Long a31;
    @Size(max = 100)
    @Column(name = "a3_1_1")
    private String a311;
    @Column(name = "a3_2")
    private Character a32;
    @Column(name = "a3_3")
    private Long a33;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "a3_4")
    private BigDecimal a34;
    @Column(name = "a3_5_1")
    private Character a351;
    @Column(name = "a3_5_2")
    private Character a352;
    @Column(name = "a3_5_3")
    private Character a353;
    @Size(max = 100)
    @Column(name = "a3_5_4")
    private String a354;
    @Size(max = 200)
    @Column(name = "a3_6")
    private String a36;
    @Size(max = 20)
    @Column(name = "a3_7")
    private String a37;
    @Column(name = "a4_1")
    private Long a41;
    @Column(name = "a4_2")
    private Long a42;
    @Column(name = "a4_3")
    @Temporal(TemporalType.TIMESTAMP)
    private Date a43;
    @Column(name = "a4_4")
    @Temporal(TemporalType.TIMESTAMP)
    private Date a44;
    @Size(max = 500)
    @Column(name = "a4_5")
    private String a45;
    @Column(name = "a4_6")
    @Temporal(TemporalType.TIMESTAMP)
    private Date a46;
    @Size(max = 200)
    @Column(name = "a5_1_1")
    private String a511;
    @Column(name = "a5_1_2")
    private Long a512;
    @Column(name = "a5_1_3")
    private Long a513;
    @Size(max = 200)
    @Column(name = "a5_1_4")
    private String a514;
    @Column(name = "a5_1_5")
    private Long a515;
    @Column(name = "a5_1_6")
    private Long a516;
    @Size(max = 200)
    @Column(name = "a5_1_7")
    private String a517;
    @Column(name = "a5_1_8")
    private Long a518;
    @Column(name = "a5_1_9")
    private Long a519;
    @Size(max = 200)
    @Column(name = "a5_2_1")
    private String a521;
    @Column(name = "a5_2_2")
    private Long a522;
    @Column(name = "a5_2_3")
    private Long a523;
    @Size(max = 200)
    @Column(name = "a5_2_4")
    private String a524;
    @Column(name = "a5_2_5")
    private Long a525;
    @Column(name = "a5_2_6")
    private Long a526;
    @Size(max = 200)
    @Column(name = "a5_2_7")
    private String a527;
    @Column(name = "a5_2_8")
    private Long a528;
    @Column(name = "a5_2_9")
    private Long a529;
    @Size(max = 500)
    @Column(name = "a5_3_1")
    private String a531;
    @Column(name = "a6_1")
    private Long a61;
    @Column(name = "a6_2")
    private Character a62;
    @Column(name = "a6_3")
    private Character a63;
    @Column(name = "a6_4")
    private Long a64;
    @Column(name = "a6_5_1")
    private Character a651;
    @Column(name = "a6_5_2")
    private Character a652;
    @Column(name = "a6_5_3")
    private Character a653;
    @Column(name = "a6_5_4")
    private Character a654;
    @Column(name = "a6_5_5")
    private Character a655;
    @Column(name = "a6_5_6")
    private Character a656;
    @Column(name = "a6_6")
    private Long a66;
    @Column(name = "a6_7")
    private Long a67;
    @Column(name = "a6_8_1")
    private Character a681;
    @Column(name = "a6_8_2")
    private Character a682;
    @Column(name = "a6_8_3")
    private Character a683;
    @Column(name = "a6_8_4")
    private Character a684;
    @Column(name = "a7_0")
    private Character a70;
    @Column(name = "a7_1_1")
    private Character a711;
    @Column(name = "a7_1_2")
    private Character a712;
    @Column(name = "a7_1_3")
    private Character a713;
    @Size(max = 200)
    @Column(name = "a7_3")
    private String a73;
    @Column(name = "a7_4")
    private Long a74;
    @Size(max = 100)
    @Column(name = "a7_4_1")
    private String a741;
    @Column(name = "a7_5")
    private Character a75;
    @Size(max = 500)
    @Column(name = "a7_6")
    private String a76;
    @Size(max = 500)
    @Column(name = "a7_7")
    private String a77;
    @Column(name = "a7_8")
    private Long a78;
    @Size(max = 200)
    @Column(name = "a7_9")
    private String a79;
    @Column(name = "a7_10")
    private Long a710;
    @Size(max = 100)
    @Column(name = "a7_11")
    private String a7111;
    @Size(max = 100)
    @Column(name = "a7_12")
    private String a7121;
    @Size(max = 100)
    @Column(name = "a7_13")
    private String a7131;
    @Column(name = "a8_1_1")
    private Character a811;
    @Column(name = "a8_1_2")
    private Character a812;
    @Column(name = "a8_1_3")
    private Character a813;
    @Column(name = "a8_1_4")
    private Character a814;
    @Column(name = "a8_1_5")
    private Character a815;
    @Column(name = "a8_1_6")
    private Character a816;
    @Column(name = "a8_1_7")
    private Character a817;
    @Column(name = "a8_1_8")
    private Character a818;
    @Column(name = "a8_1_9")
    private Character a819;
    @Column(name = "a8_2")
    private Long a82;
    @Column(name = "a8_3")
    private Long a83;
    @Column(name = "a8_4")
    private Long a84;
    @Column(name = "a8_5")
    private Long a85;

    public SeguimientoEgresados() {
    }

    public SeguimientoEgresados(SeguimientoEgresadosPK seguimientoEgresadosPK) {
        this.seguimientoEgresadosPK = seguimientoEgresadosPK;
    }

    public SeguimientoEgresados(long codigoEstudiante, long anio, long ciclo, Date fechaRealizacion) {
        this.seguimientoEgresadosPK = new SeguimientoEgresadosPK(codigoEstudiante, anio, ciclo, fechaRealizacion);
    }

    public SeguimientoEgresadosPK getSeguimientoEgresadosPK() {
        return seguimientoEgresadosPK;
    }

    public void setSeguimientoEgresadosPK(SeguimientoEgresadosPK seguimientoEgresadosPK) {
        this.seguimientoEgresadosPK = seguimientoEgresadosPK;
    }

    public String getA11() {
        return a11;
    }

    public void setA11(String a11) {
        this.a11 = a11;
    }

    public Character getA12() {
        return a12;
    }

    public void setA12(Character a12) {
        this.a12 = a12;
    }

    public Date getA13() {
        return a13;
    }

    public void setA13(Date a13) {
        this.a13 = a13;
    }

    public String getA14() {
        return a14;
    }

    public void setA14(String a14) {
        this.a14 = a14;
    }

    public String getA15() {
        return a15;
    }

    public void setA15(String a15) {
        this.a15 = a15;
    }

    public Long getA16() {
        return a16;
    }

    public void setA16(Long a16) {
        this.a16 = a16;
    }

    public Character getA17() {
        return a17;
    }

    public void setA17(Character a17) {
        this.a17 = a17;
    }

    public String getA18() {
        return a18;
    }

    public void setA18(String a18) {
        this.a18 = a18;
    }

    public String getA21() {
        return a21;
    }

    public void setA21(String a21) {
        this.a21 = a21;
    }

    public String getA22() {
        return a22;
    }

    public void setA22(String a22) {
        this.a22 = a22;
    }

    public String getA23() {
        return a23;
    }

    public void setA23(String a23) {
        this.a23 = a23;
    }

    public String getA24() {
        return a24;
    }

    public void setA24(String a24) {
        this.a24 = a24;
    }

    public Long getA31() {
        return a31;
    }

    public void setA31(Long a31) {
        this.a31 = a31;
    }

    public String getA311() {
        return a311;
    }

    public void setA311(String a311) {
        this.a311 = a311;
    }

    public Character getA32() {
        return a32;
    }

    public void setA32(Character a32) {
        this.a32 = a32;
    }

    public Long getA33() {
        return a33;
    }

    public void setA33(Long a33) {
        this.a33 = a33;
    }

    public BigDecimal getA34() {
        return a34;
    }

    public void setA34(BigDecimal a34) {
        this.a34 = a34;
    }

    public Character getA351() {
        return a351;
    }

    public void setA351(Character a351) {
        this.a351 = a351;
    }

    public Character getA352() {
        return a352;
    }

    public void setA352(Character a352) {
        this.a352 = a352;
    }

    public Character getA353() {
        return a353;
    }

    public void setA353(Character a353) {
        this.a353 = a353;
    }

    public String getA354() {
        return a354;
    }

    public void setA354(String a354) {
        this.a354 = a354;
    }

    public String getA36() {
        return a36;
    }

    public void setA36(String a36) {
        this.a36 = a36;
    }

    public String getA37() {
        return a37;
    }

    public void setA37(String a37) {
        this.a37 = a37;
    }

    public Long getA41() {
        return a41;
    }

    public void setA41(Long a41) {
        this.a41 = a41;
    }

    public Long getA42() {
        return a42;
    }

    public void setA42(Long a42) {
        this.a42 = a42;
    }

    public Date getA43() {
        return a43;
    }

    public void setA43(Date a43) {
        this.a43 = a43;
    }

    public Date getA44() {
        return a44;
    }

    public void setA44(Date a44) {
        this.a44 = a44;
    }

    public String getA45() {
        return a45;
    }

    public void setA45(String a45) {
        this.a45 = a45;
    }

    public Date getA46() {
        return a46;
    }

    public void setA46(Date a46) {
        this.a46 = a46;
    }

    public String getA511() {
        return a511;
    }

    public void setA511(String a511) {
        this.a511 = a511;
    }

    public Long getA512() {
        return a512;
    }

    public void setA512(Long a512) {
        this.a512 = a512;
    }

    public Long getA513() {
        return a513;
    }

    public void setA513(Long a513) {
        this.a513 = a513;
    }

    public String getA514() {
        return a514;
    }

    public void setA514(String a514) {
        this.a514 = a514;
    }

    public Long getA515() {
        return a515;
    }

    public void setA515(Long a515) {
        this.a515 = a515;
    }

    public Long getA516() {
        return a516;
    }

    public void setA516(Long a516) {
        this.a516 = a516;
    }

    public String getA517() {
        return a517;
    }

    public void setA517(String a517) {
        this.a517 = a517;
    }

    public Long getA518() {
        return a518;
    }

    public void setA518(Long a518) {
        this.a518 = a518;
    }

    public Long getA519() {
        return a519;
    }

    public void setA519(Long a519) {
        this.a519 = a519;
    }

    public String getA521() {
        return a521;
    }

    public void setA521(String a521) {
        this.a521 = a521;
    }

    public Long getA522() {
        return a522;
    }

    public void setA522(Long a522) {
        this.a522 = a522;
    }

    public Long getA523() {
        return a523;
    }

    public void setA523(Long a523) {
        this.a523 = a523;
    }

    public String getA524() {
        return a524;
    }

    public void setA524(String a524) {
        this.a524 = a524;
    }

    public Long getA525() {
        return a525;
    }

    public void setA525(Long a525) {
        this.a525 = a525;
    }

    public Long getA526() {
        return a526;
    }

    public void setA526(Long a526) {
        this.a526 = a526;
    }

    public String getA527() {
        return a527;
    }

    public void setA527(String a527) {
        this.a527 = a527;
    }

    public Long getA528() {
        return a528;
    }

    public void setA528(Long a528) {
        this.a528 = a528;
    }

    public Long getA529() {
        return a529;
    }

    public void setA529(Long a529) {
        this.a529 = a529;
    }

    public String getA531() {
        return a531;
    }

    public void setA531(String a531) {
        this.a531 = a531;
    }

    public Long getA61() {
        return a61;
    }

    public void setA61(Long a61) {
        this.a61 = a61;
    }

    public Character getA62() {
        return a62;
    }

    public void setA62(Character a62) {
        this.a62 = a62;
    }

    public Character getA63() {
        return a63;
    }

    public void setA63(Character a63) {
        this.a63 = a63;
    }

    public Long getA64() {
        return a64;
    }

    public void setA64(Long a64) {
        this.a64 = a64;
    }

    public Character getA651() {
        return a651;
    }

    public void setA651(Character a651) {
        this.a651 = a651;
    }

    public Character getA652() {
        return a652;
    }

    public void setA652(Character a652) {
        this.a652 = a652;
    }

    public Character getA653() {
        return a653;
    }

    public void setA653(Character a653) {
        this.a653 = a653;
    }

    public Character getA654() {
        return a654;
    }

    public void setA654(Character a654) {
        this.a654 = a654;
    }

    public Character getA655() {
        return a655;
    }

    public void setA655(Character a655) {
        this.a655 = a655;
    }

    public Character getA656() {
        return a656;
    }

    public void setA656(Character a656) {
        this.a656 = a656;
    }

    public Long getA66() {
        return a66;
    }

    public void setA66(Long a66) {
        this.a66 = a66;
    }

    public Long getA67() {
        return a67;
    }

    public void setA67(Long a67) {
        this.a67 = a67;
    }

    public Character getA681() {
        return a681;
    }

    public void setA681(Character a681) {
        this.a681 = a681;
    }

    public Character getA682() {
        return a682;
    }

    public void setA682(Character a682) {
        this.a682 = a682;
    }

    public Character getA683() {
        return a683;
    }

    public void setA683(Character a683) {
        this.a683 = a683;
    }

    public Character getA684() {
        return a684;
    }

    public void setA684(Character a684) {
        this.a684 = a684;
    }

    public Character getA70() {
        return a70;
    }

    public void setA70(Character a70) {
        this.a70 = a70;
    }

    public Character getA711() {
        return a711;
    }

    public void setA711(Character a711) {
        this.a711 = a711;
    }

    public Character getA712() {
        return a712;
    }

    public void setA712(Character a712) {
        this.a712 = a712;
    }

    public Character getA713() {
        return a713;
    }

    public void setA713(Character a713) {
        this.a713 = a713;
    }

    public String getA73() {
        return a73;
    }

    public void setA73(String a73) {
        this.a73 = a73;
    }

    public Long getA74() {
        return a74;
    }

    public void setA74(Long a74) {
        this.a74 = a74;
    }

    public String getA741() {
        return a741;
    }

    public void setA741(String a741) {
        this.a741 = a741;
    }

    public Character getA75() {
        return a75;
    }

    public void setA75(Character a75) {
        this.a75 = a75;
    }

    public String getA76() {
        return a76;
    }

    public void setA76(String a76) {
        this.a76 = a76;
    }

    public String getA77() {
        return a77;
    }

    public void setA77(String a77) {
        this.a77 = a77;
    }

    public Long getA78() {
        return a78;
    }

    public void setA78(Long a78) {
        this.a78 = a78;
    }

    public String getA79() {
        return a79;
    }

    public void setA79(String a79) {
        this.a79 = a79;
    }

    public Long getA710() {
        return a710;
    }

    public void setA710(Long a710) {
        this.a710 = a710;
    }

    public String getA7111() {
        return a7111;
    }

    public void setA7111(String a7111) {
        this.a7111 = a7111;
    }

    public String getA7121() {
        return a7121;
    }

    public void setA7121(String a7121) {
        this.a7121 = a7121;
    }

    public String getA7131() {
        return a7131;
    }

    public void setA7131(String a7131) {
        this.a7131 = a7131;
    }

    public Character getA811() {
        return a811;
    }

    public void setA811(Character a811) {
        this.a811 = a811;
    }

    public Character getA812() {
        return a812;
    }

    public void setA812(Character a812) {
        this.a812 = a812;
    }

    public Character getA813() {
        return a813;
    }

    public void setA813(Character a813) {
        this.a813 = a813;
    }

    public Character getA814() {
        return a814;
    }

    public void setA814(Character a814) {
        this.a814 = a814;
    }

    public Character getA815() {
        return a815;
    }

    public void setA815(Character a815) {
        this.a815 = a815;
    }

    public Character getA816() {
        return a816;
    }

    public void setA816(Character a816) {
        this.a816 = a816;
    }

    public Character getA817() {
        return a817;
    }

    public void setA817(Character a817) {
        this.a817 = a817;
    }

    public Character getA818() {
        return a818;
    }

    public void setA818(Character a818) {
        this.a818 = a818;
    }

    public Character getA819() {
        return a819;
    }

    public void setA819(Character a819) {
        this.a819 = a819;
    }

    public Long getA82() {
        return a82;
    }

    public void setA82(Long a82) {
        this.a82 = a82;
    }

    public Long getA83() {
        return a83;
    }

    public void setA83(Long a83) {
        this.a83 = a83;
    }

    public Long getA84() {
        return a84;
    }

    public void setA84(Long a84) {
        this.a84 = a84;
    }

    public Long getA85() {
        return a85;
    }

    public void setA85(Long a85) {
        this.a85 = a85;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (seguimientoEgresadosPK != null ? seguimientoEgresadosPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SeguimientoEgresados)) {
            return false;
        }
        SeguimientoEgresados other = (SeguimientoEgresados) object;
        if ((this.seguimientoEgresadosPK == null && other.seguimientoEgresadosPK != null) || (this.seguimientoEgresadosPK != null && !this.seguimientoEgresadosPK.equals(other.seguimientoEgresadosPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ec.edu.uasb.entity.SeguimientoEgresados[ seguimientoEgresadosPK=" + seguimientoEgresadosPK + " ]";
    }
    
}
