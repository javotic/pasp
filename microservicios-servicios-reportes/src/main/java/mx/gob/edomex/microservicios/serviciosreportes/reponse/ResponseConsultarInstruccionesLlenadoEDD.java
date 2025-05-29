package mx.gob.edomex.microservicios.serviciosreportes.reponse;

import java.io.Serializable;
import java.util.List;

import mx.gob.edomex.microservicios.serviciosreportes.models.ConsultarInstruccionesLlenadoEDD;

public class ResponseConsultarInstruccionesLlenadoEDD implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String mensaje;
	private List<ConsultarInstruccionesLlenadoEDD> response;

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

	public List<ConsultarInstruccionesLlenadoEDD> getResponse() {
		return response;
	}

	public void setResponse(List<ConsultarInstruccionesLlenadoEDD> response) {
		this.response = response;
	}

}
