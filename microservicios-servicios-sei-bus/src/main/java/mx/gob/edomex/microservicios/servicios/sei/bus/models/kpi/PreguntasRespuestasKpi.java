package mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi;

import java.io.Serializable;
import java.util.List;

public class PreguntasRespuestasKpi implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idProcesovigente;
	private String idPregunta;
	private String descripcion;
	List<RespuestasPreguntasKpi> respuestas;
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
	public List<RespuestasPreguntasKpi> getRespuestas() {
		return respuestas;
	}
	public void setRespuestas(List<RespuestasPreguntasKpi> respuestas) {
		this.respuestas = respuestas;
	}
	
	
	

}
