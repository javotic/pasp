package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class PreguntasEjecucion implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String IDPROCESOVIGENTE;   
	private String IDSECCION;          
	private String IDPREGUNTA;         
	private String DESCRIPCIONPREGUNTA;
	private String TIPOCAMPO;
	private String PUNTOS;
	
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
	@JsonProperty("IDPREGUNTA")
	public String getIDPREGUNTA() {
		return IDPREGUNTA;
	}
	public void setIDPREGUNTA(String iDPREGUNTA) {
		IDPREGUNTA = iDPREGUNTA;
	}
	@JsonProperty("DESCRIPCIONPREGUNTA")
	public String getDESCRIPCIONPREGUNTA() {
		return DESCRIPCIONPREGUNTA;
	}
	public void setDESCRIPCIONPREGUNTA(String dESCRIPCIONPREGUNTA) {
		DESCRIPCIONPREGUNTA = dESCRIPCIONPREGUNTA;
	}
	@JsonProperty("TIPOCAMPO")
	public String getTIPOCAMPO() {
		return TIPOCAMPO;
	}
	public void setTIPOCAMPO(String tIPOCAMPO) {
		TIPOCAMPO = tIPOCAMPO;
	}
	@JsonProperty("PUNTOS")
	public String getPUNTOS() {
		return PUNTOS;
	}
	public void setPUNTOS(String pUNTOS) {
		PUNTOS = pUNTOS;
	}        
	

}
