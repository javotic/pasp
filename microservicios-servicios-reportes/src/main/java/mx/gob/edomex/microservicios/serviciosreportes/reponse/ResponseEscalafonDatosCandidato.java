package mx.gob.edomex.microservicios.serviciosreportes.reponse;

import java.io.Serializable;
import java.util.List;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonDatosCandidato;

public class ResponseEscalafonDatosCandidato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String status;
	private String message;
	private List<EscalafonDatosCandidato> response;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<EscalafonDatosCandidato> getResponse() {
		return response;
	}

	public void setResponse(List<EscalafonDatosCandidato> response) {
		this.response = response;
	}

}
