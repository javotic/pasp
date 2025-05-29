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
public class ConsultarInstruccionesLlenadoDTO {
    	private static final long serialVersionUID = 1L;

        
        private String IDSECCION;
        
        private String VALOR;
       
        private String DESCRIPCIONVALOR;
       
        
	private String puntajeCriterioEvaluacion;
	private String descripcionCriterioEvaluacion;

    @JsonProperty("IDSECCION")
    public String getIDSECCION() {
        return IDSECCION;
    }

    public void setIDSECCION(String IDSECCION) {
        this.IDSECCION = IDSECCION;
    }

    @JsonProperty("VALOR")
    public String getVALOR() {
        return VALOR;
    }

    public void setVALOR(String VALOR) {
        this.VALOR = VALOR;
    }

     @JsonProperty("DESCRIPCIONVALOR")
    public String getDESCRIPCIONVALOR() {
        return DESCRIPCIONVALOR;
    }

    public void setDESCRIPCIONVALOR(String DESCRIPCIONVALOR) {
        this.DESCRIPCIONVALOR = DESCRIPCIONVALOR;
    }

        
        

	public String getPuntajeCriterioEvaluacion() {
		return puntajeCriterioEvaluacion;
	}

	public void setPuntajeCriterioEvaluacion(String puntajeCriterioEvaluacion) {
		this.puntajeCriterioEvaluacion = puntajeCriterioEvaluacion;
	}

	public String getDescripcionCriterioEvaluacion() {
		return descripcionCriterioEvaluacion;
	}

	public void setDescripcionCriterioEvaluacion(String descripcionCriterioEvaluacion) {
		this.descripcionCriterioEvaluacion = descripcionCriterioEvaluacion;
	}

}

