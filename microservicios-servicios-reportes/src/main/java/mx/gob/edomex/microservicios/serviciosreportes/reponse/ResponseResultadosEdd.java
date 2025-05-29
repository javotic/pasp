package mx.gob.edomex.microservicios.serviciosreportes.reponse;

import java.io.Serializable;
import java.util.List;

import mx.gob.edomex.microservicios.serviciosreportes.models.ResultadoesEdd;

public class ResponseResultadosEdd implements Serializable {

	private static final long serialVersionUID = 1L;
	private int code;
	private String status;
	private String message;
	private ResultadoesEdd response;

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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public ResultadoesEdd getResponse() {
		return response;
	}

	public void setResponse(ResultadoesEdd response) {
		this.response = response;
	}

	
}
