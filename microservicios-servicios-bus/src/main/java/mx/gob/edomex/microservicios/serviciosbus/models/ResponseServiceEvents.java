package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;
import java.util.List;


public class ResponseServiceEvents implements Serializable {
	private static final long serialVersionUID = 1L;

	private int codigo;
	private String mensaje;
	private boolean status;
	private String registros;
	private boolean hayMas;
	private List<BusServiceEventos> response;

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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getRegistros() {
		return registros;
	}

	public void setRegistros(String registros) {
		this.registros = registros;
	}

	public boolean isHayMas() {
		return hayMas;
	}

	public void setHayMas(boolean hayMas) {
		this.hayMas = hayMas;
	}

	public List<BusServiceEventos> getResponse() {
		return response;
	}

	public void setResponse(List<BusServiceEventos> response) {
		this.response = response;
	}
}
