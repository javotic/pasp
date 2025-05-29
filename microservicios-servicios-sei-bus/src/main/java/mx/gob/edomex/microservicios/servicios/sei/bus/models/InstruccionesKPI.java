package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class InstruccionesKPI implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String INSTRUCCIONES;
	private String TIEMPOLIMITE;
	private String TIEMPO;

	@JsonProperty("INSTRUCCIONES")
	public String getINSTRUCCIONES() {
		return INSTRUCCIONES;
	}

	public void setINSTRUCCIONES(String iNSTRUCCIONES) {
		INSTRUCCIONES = iNSTRUCCIONES;
	}

	@JsonProperty("TIEMPOLIMITE")
	public String getTIEMPOLIMITE() {
		return TIEMPOLIMITE;
	}

	public void setTIEMPOLIMITE(String tIEMPOLIMITE) {
		TIEMPOLIMITE = tIEMPOLIMITE;
	}

	@JsonProperty("TIEMPO")
	public String getTIEMPO() {
		return TIEMPO;
	}

	public void setTIEMPO(String tIEMPO) {
		TIEMPO = tIEMPO;
	}

}
