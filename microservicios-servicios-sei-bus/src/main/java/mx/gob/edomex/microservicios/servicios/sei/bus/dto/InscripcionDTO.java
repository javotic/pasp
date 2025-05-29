package mx.gob.edomex.microservicios.servicios.sei.bus.dto;

import java.io.Serializable;

public class InscripcionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idServidorPublico;
	private String idPlaza;
	private String idProcesoVigente;

	public String getIdServidorPublico() {
		return idServidorPublico;
	}

	public void setIdServidorPublico(String idServidorPublico) {
		this.idServidorPublico = idServidorPublico;
	}

	public String getIdPlaza() {
		return idPlaza;
	}

	public void setIdPlaza(String idPlaza) {
		this.idPlaza = idPlaza;
	}

	public String getIdProcesoVigente() {
		return idProcesoVigente;
	}

	public void setIdProcesoVigente(String idProcesoVigente) {
		this.idProcesoVigente = idProcesoVigente;
	}

}
