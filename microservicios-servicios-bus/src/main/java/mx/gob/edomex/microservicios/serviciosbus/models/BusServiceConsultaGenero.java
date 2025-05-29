package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BusServiceConsultaGenero implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("IDGENERO")
	private String idGenero;
	@SerializedName("NOMBREGENERO")
	private String nombreGenero;

	public String getIdGenero() {
		return idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

}
