package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BusServiceEstados implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("IDPAIS")
	private String idPais;
	@SerializedName("NOMBREPAIS")
	private String nombrePais;
	@SerializedName("IDESTADO")
	private String idEstado;
	@SerializedName("NOMBREESTADO")
	private String nombreEstado;

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	public String getNombrePais() {
		return nombrePais;
	}

	public void setNombrePais(String nombrePais) {
		this.nombrePais = nombrePais;
	}

	public String getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

}
