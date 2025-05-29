package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ResultadosEddPreg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	

	private String totalpuntosobtenidos;	
	private String calificacionparcial;

	private List<ResultadosPreguntas> resultadosPreguntas;
	
	@JsonProperty("TOTALPUNTOSOBTENIDOS")
	public String getTotalpuntosobtenidos() {
		return totalpuntosobtenidos;
	}
	public void setTotalpuntosobtenidos(String totalpuntosobtenidos) {
		this.totalpuntosobtenidos = totalpuntosobtenidos;
	}
	
	@JsonProperty("CALIFICACIONPARCIAL")
	public String getCalificacionparcial() {
		return calificacionparcial;
	}
	public void setCalificacionparcial(String calificacionparcial) {
		this.calificacionparcial = calificacionparcial;
	}	

	@JsonProperty("RESULTADOSPREGUNTASEDDDTO")	
	public List<ResultadosPreguntas> getResultadosPreguntas() {
		return resultadosPreguntas;
	}
	public void setResultadosPreguntas(List<ResultadosPreguntas> resultadosPreguntas) {
		this.resultadosPreguntas = resultadosPreguntas;
	}
}
