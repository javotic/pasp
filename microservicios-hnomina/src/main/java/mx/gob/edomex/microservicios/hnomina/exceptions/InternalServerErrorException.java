package mx.gob.edomex.microservicios.hnomina.exceptions;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import mx.gob.edomex.microservicios.hnomina.dtos.ErrorDto;

public class InternalServerErrorException extends BusException{
	

	private static final long serialVersionUID = 1L;

	public InternalServerErrorException(String code, String message) {
		super(code,HttpStatus.INTERNAL_SERVER_ERROR.value(),message);
	}
	
	public InternalServerErrorException(String code, String message, ErrorDto data) {
		super(code,HttpStatus.INTERNAL_SERVER_ERROR.value(),message, Arrays.asList(data));
	}

}
