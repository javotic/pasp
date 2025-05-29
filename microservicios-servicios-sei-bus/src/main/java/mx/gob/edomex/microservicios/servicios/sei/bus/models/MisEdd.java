package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class MisEdd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id	
	private String NOMBREPROCESOEVALUACION;
	private String FECHAINICIOPROCESOEVALUACION;
	private String FECHAFINPROCESOEVALUACION;
	private String NOMBREUNIDADADMINISTRATIVA;
	private String NOMBREPUESTO;
	private String CALIFICACION;
	private String CONSTANCIA;
	private String IDPROCESOVIGENTE;



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

	@JsonProperty("NOMBREUNIDADADMINISTRATIVA")
	public String getNOMBREUNIDADADMINISTRATIVA() {
		return NOMBREUNIDADADMINISTRATIVA;
	}

	public void setNOMBREUNIDADADMINISTRATIVA(String nOMBREUNIDADADMINISTRATIVA) {
		NOMBREUNIDADADMINISTRATIVA = nOMBREUNIDADADMINISTRATIVA;
	}

	@JsonProperty("NOMBREPUESTO")
	public String getNOMBREPUESTO() {
		return NOMBREPUESTO;
	}

	public void setNOMBREPUESTO(String nOMBREPUESTO) {
		NOMBREPUESTO = nOMBREPUESTO;
	}

	@JsonProperty("CALIFICACION")
	public String getCALIFICACION() {
		return CALIFICACION;
	}

	public void setCALIFICACION(String cALIFICACION) {
		CALIFICACION = cALIFICACION;
	}

	@JsonProperty("CONSTANCIA")
	public String getCONSTANCIA() {
		return CONSTANCIA;
	}

	public void setCONSTANCIA(String cONSTANCIA) {
		CONSTANCIA = cONSTANCIA;
	}
	
	@JsonProperty("IDPROCESOVIGENTE")
	public String getIDPROCESOVIGENTE() {
		return IDPROCESOVIGENTE;
	}

	public void setIDPROCESOVIGENTE(String iDPROCESOVIGENTE) {
		IDPROCESOVIGENTE = iDPROCESOVIGENTE;
	}
	
	

}
