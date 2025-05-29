package mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi;

import java.io.Serializable;

public class RespuestasPreguntasKpi implements Serializable {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idProcesovigente;
	private String idPregunta;
	private String idRespuesta;
	private String respuesta;
	private String siguientePregunta;
	private boolean selected;

	public String getIdProcesovigente() {
		return idProcesovigente;
	}

	public void setIdProcesovigente(String idProcesovigente) {
		this.idProcesovigente = idProcesovigente;
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

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}

	public String getSiguientePregunta() {
		return siguientePregunta;
	}

	public void setSiguientePregunta(String siguientePregunta) {
		this.siguientePregunta = siguientePregunta;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
