package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;

public class AsignacionPuntajeEscalafonario implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descripcion;
	private String puntos;

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getPuntos() {
		return puntos;
	}

	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}

}
