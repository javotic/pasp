package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EscalafonArbol implements Serializable {
	
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("LINEA")
	private String linea;
	@JsonProperty("GRUPO")
	private String grupo;
	@JsonProperty("RAMA")
	private String rama;
	@JsonProperty("CLAVE")
	private String clave;
	@JsonProperty("NOMBRE")
	private String nombre;

	public String getLinea() {
		return linea;
	}

	public void setLinea(String linea) {
		this.linea = linea;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getRama() {
		return rama;
	}

	public void setRama(String rama) {
		this.rama = rama;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
