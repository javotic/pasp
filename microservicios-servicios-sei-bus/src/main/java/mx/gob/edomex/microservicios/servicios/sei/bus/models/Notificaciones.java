package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Notificaciones implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String IDNOTIFICACION;
	private String TIPONOFICIACION;
	private String DESCRIPCION;

	@JsonProperty("IDNOTIFICACION")
	public String getIDNOTIFICACION() {
		return IDNOTIFICACION;
	}

	public void setIDNOTIFICACION(String iDNOTIFICACION) {
		IDNOTIFICACION = iDNOTIFICACION;
	}
	@JsonProperty("TIPONOFICIACION")
	public String getTIPONOFICIACION() {
		return TIPONOFICIACION;
	}

	public void setTIPONOFICIACION(String tIPONOFICIACION) {
		TIPONOFICIACION = tIPONOFICIACION;
	}
	@JsonProperty("DESCRIPCION")
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}

	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}

}
