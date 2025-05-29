package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class SeccionesEvaluacion  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private String IDPROCESOVIGENTE;
	private String IDSECCION;       
	private String NSECCION;        
	private String DESCSECCION;
	private String COLORSECCION;
	
	@JsonProperty("IDPROCESOVIGENTE")
	public String getIDPROCESOVIGENTE() {
		return IDPROCESOVIGENTE;
	}
	public void setIDPROCESOVIGENTE(String iDPROCESOVIGENTE) {
		IDPROCESOVIGENTE = iDPROCESOVIGENTE;
	}
	@JsonProperty("IDSECCION")
	public String getIDSECCION() {
		return IDSECCION;
	}
	public void setIDSECCION(String iDSECCION) {
		IDSECCION = iDSECCION;
	}
	@JsonProperty("NSECCION")
	public String getNSECCION() {
		return NSECCION;
	}
	public void setNSECCION(String nSECCION) {
		NSECCION = nSECCION;
	}
	@JsonProperty("DESCSECCION")
	public String getDESCSECCION() {
		return DESCSECCION;
	}
	public void setDESCSECCION(String dESCSECCION) {
		DESCSECCION = dESCSECCION;
	}
	@JsonProperty("COLORSECCION")
	public String getCOLORSECCION() {
		return COLORSECCION;
	}
	public void setCOLORSECCION(String cOLORSECCION) {
		COLORSECCION = cOLORSECCION;
	}     
	
	
	
}
