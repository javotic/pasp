package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PreguntasKPI implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String IDPROCESOVIGENTE;
	private String IDPREGUNTA;
	private String DESCRIPCION;

	@JsonProperty("IDPROCESOVIGENTE")
	public String getIDPROCESOVIGENTE() {
		return IDPROCESOVIGENTE;
	}

	public void setIDPROCESOVIGENTE(String iDPROCESOVIGENTE) {
		IDPROCESOVIGENTE = iDPROCESOVIGENTE;
	}

	@JsonProperty("IDPREGUNTA")
	public String getIDPREGUNTA() {
		return IDPREGUNTA;
	}

	public void setIDPREGUNTA(String iDPREGUNTA) {
		IDPREGUNTA = iDPREGUNTA;
	}

	@JsonProperty("DESCRIPCION")
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}

	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}

}
