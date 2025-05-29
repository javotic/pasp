/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.hnomina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author smartinez
 */
public class ServidorComprobanteDTO {

    private String CLAVESP;
    private String FECHAINICIO;
    private String FECHAFIN;

    @JsonProperty("CLAVESP")
    public String getCLAVESP() {
        return CLAVESP;
    }

    public void setCLAVESP(String CLAVESP) {
        this.CLAVESP = CLAVESP;
    }

    @JsonProperty("FECHAINICIO")
    public String getFECHAINICIO() {
        return FECHAINICIO;
    }

    public void setFECHAINICIO(String FECHAINICIO) {
        this.FECHAINICIO = FECHAINICIO;
    }

    @JsonProperty("FECHAFIN")
    public String getFECHAFIN() {
        return FECHAFIN;
    }

    public void setFECHAFIN(String FECHAFIN) {
        this.FECHAFIN = FECHAFIN;
    }


        
}