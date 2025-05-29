package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;

public class ConsultarDatosPlazaConcursa implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numeroPlaza;
	private String clavePLaza;
	private String descripcionPuesto;
	private String nivel;
	private String rango;
	private String direccionOficina;
	private String jefatura;
	private String puntajeMinimo;
	public String getNumeroPlaza() {
		return numeroPlaza;
	}
	public void setNumeroPlaza(String numeroPlaza) {
		this.numeroPlaza = numeroPlaza;
	}
	public String getClavePLaza() {
		return clavePLaza;
	}
	public void setClavePLaza(String clavePLaza) {
		this.clavePLaza = clavePLaza;
	}
	public String getDescripcionPuesto() {
		return descripcionPuesto;
	}
	public void setDescripcionPuesto(String descripcionPuesto) {
		this.descripcionPuesto = descripcionPuesto;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}
	public String getRango() {
		return rango;
	}
	public void setRango(String rango) {
		this.rango = rango;
	}
	public String getDireccionOficina() {
		return direccionOficina;
	}
	public void setDireccionOficina(String direccionOficina) {
		this.direccionOficina = direccionOficina;
	}
	public String getJefatura() {
		return jefatura;
	}
	public void setJefatura(String jefatura) {
		this.jefatura = jefatura;
	}
	public String getPuntajeMinimo() {
		return puntajeMinimo;
	}
	public void setPuntajeMinimo(String puntajeMinimo) {
		this.puntajeMinimo = puntajeMinimo;
	}
	
	

}
