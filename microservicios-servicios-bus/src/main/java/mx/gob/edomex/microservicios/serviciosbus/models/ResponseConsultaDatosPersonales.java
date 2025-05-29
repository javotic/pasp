package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;
import java.util.List;

public class ResponseConsultaDatosPersonales  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo;
	private String mensaje;
	private List<BusServiceConsultaDatosPersonales> response;
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
	public List<BusServiceConsultaDatosPersonales> getResponse() {
		return response;
	}
	public void setResponse(List<BusServiceConsultaDatosPersonales> response) {
		this.response = response;
	}
	
	
	

}
