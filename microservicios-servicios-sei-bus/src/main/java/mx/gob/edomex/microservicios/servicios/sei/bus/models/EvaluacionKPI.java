package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class EvaluacionKPI implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String IDPROCESOVIGENTE;
	private String IDPROCESOKPI;
	private String NOMBREPROCESOKPI;
	private String IDSERVIDORPUBLICO;
	private String NOMBRESERVIDORPUBLICO;

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

}
