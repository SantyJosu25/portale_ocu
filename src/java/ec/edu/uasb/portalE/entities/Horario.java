/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.uasb.portalE.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author victor.barba
 */
@Entity
public class Horario implements Serializable {

    @Id
    @Column(name = "ORDINAL")
    private Long ordinal;
    @Column(name = "CALEVENTO_FECHA")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date caleventoFecha;
    @Column(name = "CALEVENTO_HEMPIEZA")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date caleventoHempieza;
    @Column(name = "CALEVENTO_HTERMINA")
    @Temporal(javax.persistence.TemporalType.TIME)
    private Date caleventoHtermina;
    @Column(name = "INSTALACION_ASIGNADA")
    private String instalacionAsignada;
    @Column(name = "CURR")
    private int cursor;
    @Column(name = "SIG")
    private int sig;
    
    public Horario() {
    }

    public Long getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Long ordinal) {
        this.ordinal = ordinal;
    }

    public Date getCaleventoFecha() {
        return caleventoFecha;
    }

    public void setCaleventoFecha(Date caleventoFecha) {
        this.caleventoFecha = caleventoFecha;
    }

    public Date getCaleventoHempieza() {
        return caleventoHempieza;
    }

    public void setCaleventoHempieza(Date caleventoHempieza) {
        this.caleventoHempieza = caleventoHempieza;
    }

    public Date getCaleventoHtermina() {
        return caleventoHtermina;
    }

    public void setCaleventoHtermina(Date caleventoHtermina) {
        this.caleventoHtermina = caleventoHtermina;
    }

    public String getInstalacionAsignada() {
        return instalacionAsignada;
    }

    public void setInstalacionAsignada(String instalacionAsignada) {
        this.instalacionAsignada = instalacionAsignada;
    }

    public int getCursor() {
        return cursor;
    }

    public void setCursor(int cursor) {
        this.cursor = cursor;
    }

    public int getSig() {
        return sig;
    }

    public void setSig(int sig) {
        this.sig = sig;
    }

    @Override
    public String toString() {
        return "Horario{" + "ordinal=" + ordinal + ", caleventoFecha=" + caleventoFecha + ", caleventoHempieza=" + caleventoHempieza + ", caleventoHtermina=" + caleventoHtermina + ", instalacionAsignada=" + instalacionAsignada + '}';
    }

}
