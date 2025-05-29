package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SesionesDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("IDSESION")
	private String idSesion;
	@JsonProperty("DIA")
	private String dia;
	@JsonProperty("DURACION")
	private String duracion;
	@JsonProperty("LUGAR")
	private String lugar;

	public String getIdSesion() {
		return idSesion;
	}

	public void setIdSesion(String idSesion) {
		this.idSesion = idSesion;
	}

	public String getDia() {
		return dia;
	}

	public void setDia(String dia) {
		this.dia = dia;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

}
