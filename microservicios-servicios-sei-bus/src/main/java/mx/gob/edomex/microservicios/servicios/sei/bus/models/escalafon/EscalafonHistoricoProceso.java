package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EscalafonHistoricoProceso implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String claveEscalafonProceso;
	private String nombreEscalafonProceso;
	private String fechaInicioProceso;
	private String fechaFinProceso;
	private String clavePlaza;
	private String nombrePlaza;
	private String clavePuesto;
	private String nombrePuesto;
	private String puntajeLogrado;
	private String lugarRanking;
	private String dictamen;
	private String descripcionEstatus;
	public String getClaveEscalafonProceso() {
		return claveEscalafonProceso;
	}
	public void setClaveEscalafonProceso(String claveEscalafonProceso) {
		this.claveEscalafonProceso = claveEscalafonProceso;
	}
	public String getNombreEscalafonProceso() {
		return nombreEscalafonProceso;
	}
	public void setNombreEscalafonProceso(String nombreEscalafonProceso) {
		this.nombreEscalafonProceso = nombreEscalafonProceso;
	}
	public String getFechaInicioProceso() {
		return fechaInicioProceso;
	}
	public void setFechaInicioProceso(String fechaInicioProceso) {
		this.fechaInicioProceso = fechaInicioProceso;
	}
	public String getFechaFinProceso() {
		return fechaFinProceso;
	}
	public void setFechaFinProceso(String fechaFinProceso) {
		this.fechaFinProceso = fechaFinProceso;
	}
	public String getClavePlaza() {
		return clavePlaza;
	}
	public void setClavePlaza(String clavePlaza) {
		this.clavePlaza = clavePlaza;
	}
	public String getNombrePlaza() {
		return nombrePlaza;
	}
	public void setNombrePlaza(String nombrePlaza) {
		this.nombrePlaza = nombrePlaza;
	}
	public String getClavePuesto() {
		return clavePuesto;
	}
	public void setClavePuesto(String clavePuesto) {
		this.clavePuesto = clavePuesto;
	}
	public String getNombrePuesto() {
		return nombrePuesto;
	}
	public void setNombrePuesto(String nombrePuesto) {
		this.nombrePuesto = nombrePuesto;
	}
	public String getPuntajeLogrado() {
		return puntajeLogrado;
	}
	public void setPuntajeLogrado(String puntajeLogrado) {
		this.puntajeLogrado = puntajeLogrado;
	}
	public String getLugarRanking() {
		return lugarRanking;
	}
	public void setLugarRanking(String lugarRanking) {
		this.lugarRanking = lugarRanking;
	}
	public String getDictamen() {
		return dictamen;
	}
	public void setDictamen(String dictamen) {
		this.dictamen = dictamen;
	}
	public String getDescripcionEstatus() {
		return descripcionEstatus;
	}
	public void setDescripcionEstatus(String descripcionEstatus) {
		this.descripcionEstatus = descripcionEstatus;
	}
	
	
	
	
}
