package mx.gob.edomex.microservicios.serviciosreportes.reponse;

import java.io.Serializable;
import java.util.List;

import mx.gob.edomex.microservicios.serviciosreportes.models.PreguntasEjecucionEDD;

public class ReponsePreguntasEjecucionED implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String mensaje;
	private List<PreguntasEjecucionEDD> response;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public List<PreguntasEjecucionEDD> getResponse() {
		return response;
	}

	public void setResponse(List<PreguntasEjecucionEDD> response) {
		this.response = response;
	}

	

}
