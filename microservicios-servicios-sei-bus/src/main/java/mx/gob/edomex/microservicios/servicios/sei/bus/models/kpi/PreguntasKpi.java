package mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi;

import java.io.Serializable;

public class PreguntasKpi implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idProcesovigente;
	private String idPregunta;
	private String descripcion;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
