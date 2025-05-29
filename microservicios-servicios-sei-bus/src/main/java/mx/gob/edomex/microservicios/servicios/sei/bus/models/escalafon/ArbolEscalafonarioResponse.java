package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ArbolEscalafonarioResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("CLAVEPUESTOACTUAL")
	private String clavePuestoActual;
	@JsonProperty("NOMBREPUESTOACTUAL")
	private String nombrePuestoActual;
	private List<EscalafonArbol> arbol;

	public String getClavePuestoActual() {
		return clavePuestoActual;
	}

	public void setClavePuestoActual(String clavePuestoActual) {
		this.clavePuestoActual = clavePuestoActual;
	}

	public String getNombrePuestoActual() {
		return nombrePuestoActual;
	}

	public void setNombrePuestoActual(String nombrePuestoActual) {
		this.nombrePuestoActual = nombrePuestoActual;
	}

	public List<EscalafonArbol> getArbol() {
		return arbol;
	}

	public void setArbol(List<EscalafonArbol> arbol) {
		this.arbol = arbol;
	}

}
