package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ConsultarRespuestasCompetencias implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("IDRESPUESTA")
	private String idRespuesta;
	@SerializedName("RESPUESTA")
	private String respuesta;
	@SerializedName("PUNTAJE")
	private String puntaje;

	public String getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(String idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}

}
