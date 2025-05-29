package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;

public class ConsultarDatosPersonaCandidata implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String claveServidorPublico;
	private String desconocido;
	private String desconocido2;
	private String area;
	private String desconocido3;
	private String numeroRegistro;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getClaveServidorPublico() {
		return claveServidorPublico;
	}
	public void setClaveServidorPublico(String claveServidorPublico) {
		this.claveServidorPublico = claveServidorPublico;
	}
	public String getDesconocido() {
		return desconocido;
	}
	public void setDesconocido(String desconocido) {
		this.desconocido = desconocido;
	}
	public String getDesconocido2() {
		return desconocido2;
	}
	public void setDesconocido2(String desconocido2) {
		this.desconocido2 = desconocido2;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDesconocido3() {
		return desconocido3;
	}
	public void setDesconocido3(String desconocido3) {
		this.desconocido3 = desconocido3;
	}
	public String getNumeroRegistro() {
		return numeroRegistro;
	}
	public void setNumeroRegistro(String numeroRegistro) {
		this.numeroRegistro = numeroRegistro;
	}
	
	
	
	
	

}
