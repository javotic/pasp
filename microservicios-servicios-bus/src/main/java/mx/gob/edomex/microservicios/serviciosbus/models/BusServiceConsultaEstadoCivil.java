package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class BusServiceConsultaEstadoCivil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("IDESTADOCIVIL")
	private String idEstadoCivil;
	@SerializedName("NOMBREESTADOCIVIL")
	private String nombreEstadoCivil;

	public String getIdEstadoCivil() {
		return idEstadoCivil;
	}

	public void setIdEstadoCivil(String idEstadoCivil) {
		this.idEstadoCivil = idEstadoCivil;
	}

	public String getNombreEstadoCivil() {
		return nombreEstadoCivil;
	}

	public void setNombreEstadoCivil(String nombreEstadoCivil) {
		this.nombreEstadoCivil = nombreEstadoCivil;
	}

}
