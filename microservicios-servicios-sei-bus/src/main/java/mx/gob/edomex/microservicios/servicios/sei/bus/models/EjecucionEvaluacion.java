package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EjecucionEvaluacion  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	List<PreguntasEjecucion> preguntasEjecucion;
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
	@JsonProperty("PREGUNTASEJECUCION")
	public List<PreguntasEjecucion> getPreguntasEjecucion() {
		return preguntasEjecucion;
	}
	public void setPreguntasEjecucion(List<PreguntasEjecucion> preguntasEjecucion) {
		this.preguntasEjecucion = preguntasEjecucion;
	}
	@JsonProperty("COLORSECCION")
	public String getCOLORSECCION() {
		return COLORSECCION;
	}
	public void setCOLORSECCION(String cOLORSECCION) {
		COLORSECCION = cOLORSECCION;
	}     
	
	

}
