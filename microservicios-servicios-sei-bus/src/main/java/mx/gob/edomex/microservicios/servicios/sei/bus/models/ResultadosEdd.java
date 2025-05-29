package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ResultadosEdd implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "TOTALPUNTOSOBTENIDOS")
	private String totalpuntosobtenidos;	
	private String calificacionparcial;
	
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
	
	
	
	
	

}
