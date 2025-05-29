package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BusServiceNivelEstudios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SerializedName("IDTITULOCARRERA")
	private String idTituloCarrera;
	@SerializedName("NOMBRECTITULOCARRERA")
	private String nombreTituloCarrera;

	public String getIdTituloCarrera() {
		return idTituloCarrera;
	}

	public void setIdTituloCarrera(String idTituloCarrera) {
		this.idTituloCarrera = idTituloCarrera;
	}

	public String getNombreTituloCarrera() {
		return nombreTituloCarrera;
	}

	public void setNombreTituloCarrera(String nombreTituloCarrera) {
		this.nombreTituloCarrera = nombreTituloCarrera;
	}

}
