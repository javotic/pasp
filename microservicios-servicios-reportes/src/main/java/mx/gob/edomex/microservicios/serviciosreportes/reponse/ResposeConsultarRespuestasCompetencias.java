package mx.gob.edomex.microservicios.serviciosreportes.reponse;

import java.io.Serializable;
import java.util.List;

import mx.gob.edomex.microservicios.serviciosreportes.models.ConsultarRespuestasCompetencias;

public class ResposeConsultarRespuestasCompetencias implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String mensaje;
	private List<ConsultarRespuestasCompetencias> response;

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

	public List<ConsultarRespuestasCompetencias> getResponse() {
		return response;
	}

	public void setResponse(List<ConsultarRespuestasCompetencias> response) {
		this.response = response;
	}

}
