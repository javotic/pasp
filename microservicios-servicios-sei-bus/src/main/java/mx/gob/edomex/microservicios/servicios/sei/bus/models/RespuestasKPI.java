package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class RespuestasKPI implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String IDPROCESOVIGENTE;
	private String IDPREGUNTA;
	private String IDRESPUESTA;
	private String RESPUESTA;
	private String SIGUIENTEPREGUNTA;
	private String SIGUIENTERESPUESTA;

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

	@JsonProperty("IDRESPUESTA")
	public String getIDRESPUESTA() {
		return IDRESPUESTA;
	}

	public void setIDRESPUESTA(String iDRESPUESTA) {
		IDRESPUESTA = iDRESPUESTA;
	}

	@JsonProperty("RESPUESTA")
	public String getRESPUESTA() {
		return RESPUESTA;
	}

	public void setRESPUESTA(String rESPUESTA) {
		RESPUESTA = rESPUESTA;
	}

	@JsonProperty("SIGUIENTEPREGUNTA")
	public String getSIGUIENTEPREGUNTA() {
		return SIGUIENTEPREGUNTA;
	}

	public void setSIGUIENTEPREGUNTA(String sIGUIENTEPREGUNTA) {
		SIGUIENTEPREGUNTA = sIGUIENTEPREGUNTA;
	}

	@JsonProperty("SIGUIENTERESPUESTA")
	public String getSIGUIENTERESPUESTA() {
		return SIGUIENTERESPUESTA;
	}

	public void setSIGUIENTERESPUESTA(String sIGUIENTERESPUESTA) {
		SIGUIENTERESPUESTA = sIGUIENTERESPUESTA;
	}
}
