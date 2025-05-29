package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ResultadosPreguntas  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IDPREGUNTA")
	private String idpregunta;	
	private String descripcionpregunta;	
	private String idrespuesta;
	

	@JsonProperty("IDPREGUNTA")
	public String getIdpregunta() {
		return idpregunta;
	}
	public void setIdpregunta(String idpregunta) {
		this.idpregunta = idpregunta;
	}
	
	@JsonProperty("DESCRIPCIONPREGUNTA")
	public String getDescripcionpregunta() {
		return descripcionpregunta;
	}
	public void setDescripcionpregunta(String descripcionpregunta) {
		this.descripcionpregunta = descripcionpregunta;
	}
	
	@JsonProperty("IDRESPUESTA")
	public String getIdrespuesta() {
		return idrespuesta;
	}
	public void setIdrespuesta(String idrespuesta) {
		this.idrespuesta = idrespuesta;
	}
	
	
	
	
	
}
