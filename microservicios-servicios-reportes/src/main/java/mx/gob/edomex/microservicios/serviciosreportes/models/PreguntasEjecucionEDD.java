package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class PreguntasEjecucionEDD implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("IDPROCESOVIGENTE")
	private String idProcesoVigente;
	@SerializedName("IDSECCION")
	private String idSecion;
	@SerializedName("IDPREGUNTA")
	private String idPregunta;
	@SerializedName("DESCRIPCIONPREGUNTA")
	private String descripcionPregunta;

	private String puntaje;
	private String puntajeSocioPersonal;
	private String puntajeDemeritos;

	public String getIdProcesoVigente() {
		return idProcesoVigente;
	}

	public void setIdProcesoVigente(String idProcesoVigente) {
		this.idProcesoVigente = idProcesoVigente;
	}

	public String getIdSecion() {
		return idSecion;
	}

	public void setIdSecion(String idSecion) {
		this.idSecion = idSecion;
	}

	public String getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(String idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getDescripcionPregunta() {
		return descripcionPregunta;
	}

	public void setDescripcionPregunta(String descripcionPregunta) {
		this.descripcionPregunta = descripcionPregunta;
	}

	public String getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(String puntaje) {
		this.puntaje = puntaje;
	}

	public String getPuntajeSocioPersonal() {
		return puntajeSocioPersonal;
	}

	public void setPuntajeSocioPersonal(String puntajeSocioPersonal) {
		this.puntajeSocioPersonal = puntajeSocioPersonal;
	}

	public String getPuntajeDemeritos() {
		return puntajeDemeritos;
	}

	public void setPuntajeDemeritos(String puntajeDemeritos) {
		this.puntajeDemeritos = puntajeDemeritos;
	}

}
