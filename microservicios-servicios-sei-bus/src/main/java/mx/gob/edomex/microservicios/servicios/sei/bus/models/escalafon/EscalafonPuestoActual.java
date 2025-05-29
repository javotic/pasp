package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;

public class EscalafonPuestoActual implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idPuesto;
	private String nombrePuesto;
	private String idPlaza;
	private String idPuestoEdomex;

	public String getIdPuesto() {
		return idPuesto;
	}

	public void setIdPuesto(String idPuesto) {
		this.idPuesto = idPuesto;
	}

	public String getNombrePuesto() {
		return nombrePuesto;
	}

	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}

	public String getIdPlaza() {
		return idPlaza;
	}

	public void setIdPlaza(String idPlaza) {
		this.idPlaza = idPlaza;
	}

	public String getIdPuestoEdomex() {
		return idPuestoEdomex;
	}

	public void setIdPuestoEdomex(String idPuestoEdomex) {
		this.idPuestoEdomex = idPuestoEdomex;
	}

}
