package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;


import com.google.gson.annotations.SerializedName;

public class ResultadosPreguntasEdd implements Serializable {
	private static final long serialVersionUID = 1L;

	@SerializedName("IDPREGUNTA")
	private String idpregunta;
	@SerializedName("DESCRIPCIONPREGUNTA")
	private String descripcionpregunta;
	@SerializedName("IDRESPUESTA")
	private String idrespuesta;

	public String getIdpregunta() {
		return idpregunta;
	}

	public void setIdpregunta(String idpregunta) {
		this.idpregunta = idpregunta;
	}

	public String getDescripcionpregunta() {
		return descripcionpregunta;
	}

	public void setDescripcionpregunta(String descripcionpregunta) {
		this.descripcionpregunta = descripcionpregunta;
	}

	public String getIdrespuesta() {
		return idrespuesta;
	}

	public void setIdrespuesta(String idrespuesta) {
		this.idrespuesta = idrespuesta;
	}

}
