package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BusServiceMunicipios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("IDPAIS")
	private String idPais;
	@SerializedName("IDESTADO")
	private String idEstado;
	@SerializedName("NOMBREESTADO")
	private String nombreEstado;
	@SerializedName("IDMUNICIPIOCIUDAD")
	private String idMunicipioCiudad;
	@SerializedName("NOMBREMUNICIPIOCIIUDAD")
	private String nombreMunicipioCiudad;

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
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

	public String getIdMunicipioCiudad() {
		return idMunicipioCiudad;
	}

	public void setIdMunicipioCiudad(String idMunicipioCiudad) {
		this.idMunicipioCiudad = idMunicipioCiudad;
	}

	public String getNombreMunicipioCiudad() {
		return nombreMunicipioCiudad;
	}

	public void setNombreMunicipioCiudad(String nombreMunicipioCiudad) {
		this.nombreMunicipioCiudad = nombreMunicipioCiudad;
	}

}
