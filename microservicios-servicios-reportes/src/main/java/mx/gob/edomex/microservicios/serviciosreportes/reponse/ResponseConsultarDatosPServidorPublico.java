package mx.gob.edomex.microservicios.serviciosreportes.reponse;

import java.io.Serializable;
import java.util.List;

import mx.gob.edomex.microservicios.serviciosreportes.models.ConsultarDatosPuestoServidorPublico;

public class ResponseConsultarDatosPServidorPublico implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String status;
	private String message;
	private ConsultarDatosPuestoServidorPublico response;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
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

	public ConsultarDatosPuestoServidorPublico getResponse() {
		return response;
	}

	public void setResponse(ConsultarDatosPuestoServidorPublico response) {
		this.response = response;
	}

}
