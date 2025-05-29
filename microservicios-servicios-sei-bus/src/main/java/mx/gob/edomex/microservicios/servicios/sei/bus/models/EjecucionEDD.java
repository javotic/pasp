package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EjecucionEDD implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<EjecucionEvaluacion> EjecucionEvaluacion;

	@JsonProperty("EJECUCIONEVALUACION")
	public List<EjecucionEvaluacion> getEjecucionEvaluacion() {
		return EjecucionEvaluacion;
	}

	public void setEjecucionEvaluacion(List<EjecucionEvaluacion> ejecucionEvaluacion) {
		EjecucionEvaluacion = ejecucionEvaluacion;
	}
	
	
}
