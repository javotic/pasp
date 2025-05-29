package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BusServiceColonias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("IDMUNICIPIOCIUDAD")
	private String idMunicipioCiudad;
	@SerializedName("NOMBREMUNICIPIOCIIUDAD")
	private String nombreMunicipioCiudad;

	@SerializedName("IDCOLONIA")
	private String idColonia;
	@SerializedName("NOMBRECOLONIA")
	private String nombreColonia;

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

	public String getIdColonia() {
		return idColonia;
	}

	public void setIdColonia(String idColonia) {
		this.idColonia = idColonia;
	}

	public String getNombreColonia() {
		return nombreColonia;
	}

	public void setNombreColonia(String nombreColonia) {
		this.nombreColonia = nombreColonia;
	}

}
