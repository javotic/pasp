package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

public class EnvioRespuestasKpi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tiempo;
	private String idPregunta;
	private String idRespuesta;
	private String idServidorPublico;
	private String idProcesoVigente;

	public String getTiempo() {
		return tiempo;
	}

	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}

	public String getIdPregunta() {
		return idPregunta;
	}

	public void setIdPregunta(String idPregunta) {
		this.idPregunta = idPregunta;
	}

	public String getIdRespuesta() {
		return idRespuesta;
	}

	public void setIdRespuesta(String idRespuesta) {
		this.idRespuesta = idRespuesta;
	}

	public String getIdServidorPublico() {
		return idServidorPublico;
	}

	public void setIdServidorPublico(String idServidorPublico) {
		this.idServidorPublico = idServidorPublico;
	}

	public String getIdProcesoVigente() {
		return idProcesoVigente;
	}

	public void setIdProcesoVigente(String idProcesoVigente) {
		this.idProcesoVigente = idProcesoVigente;
	}

}
