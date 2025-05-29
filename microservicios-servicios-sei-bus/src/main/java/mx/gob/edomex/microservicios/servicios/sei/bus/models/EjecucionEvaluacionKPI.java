package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EjecucionEvaluacionKPI implements Serializable {
	private static final long serialVersionUID = 1L;
	private String IDPROCESOVIGENTE;
	private String IDPROCESOKPI;
	private String NOMBREPROCESOKPI;
	private String IDSERVIDORPUBLICO;
	private String NOMBRESERVIDORPUBLICO;
	private List<EjecucionPreguntasKPI> PREGUNTASKPI;

	@JsonProperty("IDPROCESOVIGENTE")
	public String getIDPROCESOVIGENTE() {
		return IDPROCESOVIGENTE;
	}

	public void setIDPROCESOVIGENTE(String iDPROCESOVIGENTE) {
		IDPROCESOVIGENTE = iDPROCESOVIGENTE;
	}

	@JsonProperty("IDPROCESOKPI")
	public String getIDPROCESOKPI() {
		return IDPROCESOKPI;
	}

	public void setIDPROCESOKPI(String iDPROCESOKPI) {
		IDPROCESOKPI = iDPROCESOKPI;
	}

	@JsonProperty("NOMBREPROCESOKPI")
	public String getNOMBREPROCESOKPI() {
		return NOMBREPROCESOKPI;
	}

	public void setNOMBREPROCESOKPI(String nOMBREPROCESOKPI) {
		NOMBREPROCESOKPI = nOMBREPROCESOKPI;
	}

	@JsonProperty("IDSERVIDORPUBLICO")
	public String getIDSERVIDORPUBLICO() {
		return IDSERVIDORPUBLICO;
	}

	public void setIDSERVIDORPUBLICO(String iDSERVIDORPUBLICO) {
		IDSERVIDORPUBLICO = iDSERVIDORPUBLICO;
	}

	@JsonProperty("NOMBRESERVIDORPUBLICO")
	public String getNOMBRESERVIDORPUBLICO() {
		return NOMBRESERVIDORPUBLICO;
	}

	public void setNOMBRESERVIDORPUBLICO(String nOMBRESERVIDORPUBLICO) {
		NOMBRESERVIDORPUBLICO = nOMBRESERVIDORPUBLICO;
	}

	@JsonProperty("PREGUNTASKPI")
	public List<EjecucionPreguntasKPI> getPREGUNTASKPI() {
		return PREGUNTASKPI;
	}

	public void setPREGUNTASKPI(List<EjecucionPreguntasKPI> pREGUNTASKPI) {
		PREGUNTASKPI = pREGUNTASKPI;
	}

}
