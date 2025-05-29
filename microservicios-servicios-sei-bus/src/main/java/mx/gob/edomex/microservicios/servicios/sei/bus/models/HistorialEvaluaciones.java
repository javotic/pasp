package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class HistorialEvaluaciones implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String IDPROCESOEVALUACION;
	private String NOMBREPROCESOEVALUACION;
	private String FECHAINICIOPROCESOEVALUACION;
	private String FECHAFINPROCESOEVALUACION;
	private String TIPOEVALUACION;

	@JsonProperty("IDPROCESOEVALUACION")
	public String getIDPROCESOEVALUACION() {
		return IDPROCESOEVALUACION;
	}

	public void setIDPROCESOEVALUACION(String iDPROCESOEVALUACION) {
		IDPROCESOEVALUACION = iDPROCESOEVALUACION;
	}

	@JsonProperty("NOMBREPROCESOEVALUACION")
	public String getNOMBREPROCESOEVALUACION() {
		return NOMBREPROCESOEVALUACION;
	}

	public void setNOMBREPROCESOEVALUACION(String nOMBREPROCESOEVALUACION) {
		NOMBREPROCESOEVALUACION = nOMBREPROCESOEVALUACION;
	}

	@JsonProperty("FECHAINICIOPROCESOEVALUACION")
	public String getFECHAINICIOPROCESOEVALUACION() {
		return FECHAINICIOPROCESOEVALUACION;
	}

	public void setFECHAINICIOPROCESOEVALUACION(String fECHAINICIOPROCESOEVALUACION) {
		FECHAINICIOPROCESOEVALUACION = fECHAINICIOPROCESOEVALUACION;
	}

	@JsonProperty("FECHAFINPROCESOEVALUACION")
	public String getFECHAFINPROCESOEVALUACION() {
		return FECHAFINPROCESOEVALUACION;
	}

	public void setFECHAFINPROCESOEVALUACION(String fECHAFINPROCESOEVALUACION) {
		FECHAFINPROCESOEVALUACION = fECHAFINPROCESOEVALUACION;
	}

	@JsonProperty("TIPOEVALUACION")
	public String getTIPOEVALUACION() {
		return TIPOEVALUACION;
	}

	public void setTIPOEVALUACION(String tIPOEVALUACION) {
		TIPOEVALUACION = tIPOEVALUACION;
	}

}
