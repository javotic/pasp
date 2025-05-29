/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author smartinez
 */
public class SesionExamenDTO {
    private String idSession;
    private String dia;
    private String horaIni;
    private String horaFin;
    private String duracion;
    private String lugar;
    private String capMax;

    @JsonProperty("IDSESSION")
    public String getIdSession() {
        return idSession;
    }

    public void setIdSession(String idSession) {
        this.idSession = idSession;
    }

    @JsonProperty("DIA")
    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @JsonProperty("HORAINI")
    public String getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(String horaIni) {
        this.horaIni = horaIni;
    }

    @JsonProperty("HORAFIN")
    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    @JsonProperty("DURACION")
    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    @JsonProperty("LUGAR")
    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }



    @JsonProperty("CAPMAX")
    public String getCapMax() {
        return capMax;
    }

    public void setCapMax(String capMax) {
        this.capMax = capMax;
    }
    
    
    
}
