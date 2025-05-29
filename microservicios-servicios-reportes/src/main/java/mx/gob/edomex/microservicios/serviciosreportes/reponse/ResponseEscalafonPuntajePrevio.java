package mx.gob.edomex.microservicios.serviciosreportes.reponse;

import java.io.Serializable;
import java.util.List;
import mx.gob.edomex.microservicios.serviciosreportes.models.EscalafonPuntajePrevio;

public class ResponseEscalafonPuntajePrevio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;
	private String status;
	private String message;
	private List<EscalafonPuntajePrevio> response;

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

	public List<EscalafonPuntajePrevio> getResponse() {
		return response;
	}

	public void setResponse(List<EscalafonPuntajePrevio> response) {
		this.response = response;
	}

}
