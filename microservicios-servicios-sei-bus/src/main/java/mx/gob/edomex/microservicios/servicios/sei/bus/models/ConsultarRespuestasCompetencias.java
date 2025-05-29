/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author smartinez
 */
public class ConsultarRespuestasCompetencias {
    
    	private static final long serialVersionUID = 1L;
	@SerializedName("IDRESPUESTA")
	private String IDRESPUESTA;
	@SerializedName("RESPUESTA")
	private String RESPUESTA;
	@SerializedName("PUNTAJE")
	private String PUNTAJE;

    @JsonProperty("IDRESPUESTA")
    public String getIDRESPUESTA() {
        return IDRESPUESTA;
    }

    public void setIDRESPUESTA(String IDRESPUESTA) {
        this.IDRESPUESTA = IDRESPUESTA;
    }

    @JsonProperty("RESPUESTA")
    public String getRESPUESTA() {
        return RESPUESTA;
    }

    public void setRESPUESTA(String RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }

    @JsonProperty("PUNTAJE")
    public String getPUNTAJE() {
        return PUNTAJE;
    }

    public void setPUNTAJE(String PUNTAJE) {
        this.PUNTAJE = PUNTAJE;
    }


}
