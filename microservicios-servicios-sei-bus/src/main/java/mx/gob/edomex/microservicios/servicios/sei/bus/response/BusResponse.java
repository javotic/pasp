package mx.gob.edomex.microservicios.servicios.sei.bus.response;

import java.io.Serializable;

public class BusResponse<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	private String code;
	private String message;
	private T response;
	
	public BusResponse(String status, String code, String message) {
		this.status = status;
		this.code = code;
		this.message = message;
	}
	
	public BusResponse(String status, String code, String message, T response) {
		this.status = status;
		this.code = code;
		this.message = message;
		this.response = response;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getResponse() {
		return response;
	}

	public void setResponse(T response) {
		this.response = response;
	}

	
	
	
	

}
