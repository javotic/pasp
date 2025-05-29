package mx.gob.edomex.microservicios.autoservicio.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EvaluacionDesempenioJson implements Serializable {
	private static final long serialVersionUID = 1L;
	private String IDPROCESOVIGENTE;	
	private String NOMBRESECCION;
	private String backgroundColor;
	private String claveEvaluador;
	private String claveEvaluado;
	private String claveUnidadAdmin;
	private String idseccion;
	private String VALOR;
	private String DESCRIPCIONVALOR;
	private String PUNTOS;
	private List<PreguntasJson> preguntas;

	@JsonProperty("idprocesovigente")
	public String getIDPROCESOVIGENTE() {
		return IDPROCESOVIGENTE;
	}

	public void setIDPROCESOVIGENTE(String iDPROCESOVIGENTE) {
		IDPROCESOVIGENTE = iDPROCESOVIGENTE;
	}
	@JsonProperty("nombreseccion")
	public String getNOMBRESECCION() {
		return NOMBRESECCION;
	}

	public void setNOMBRESECCION(String nOMBRESECCION) {
		NOMBRESECCION = nOMBRESECCION;
	}

	public String getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(String backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public List<PreguntasJson> getPreguntas() {
		return preguntas;
	}

	public void setPreguntas(List<PreguntasJson> preguntas) {
		this.preguntas = preguntas;
	}

	public String getClaveEvaluador() {
		return claveEvaluador;
	}

	public void setClaveEvaluador(String claveEvaluador) {
		this.claveEvaluador = claveEvaluador;
	}

	public String getClaveEvaluado() {
		return claveEvaluado;
	}

	public void setClaveEvaluado(String claveEvaluado) {
		this.claveEvaluado = claveEvaluado;
	}

	public String getClaveUnidadAdmin() {
		return claveUnidadAdmin;
	}

	public void setClaveUnidadAdmin(String claveUnidadAdmin) {
		this.claveUnidadAdmin = claveUnidadAdmin;
	}
	
	@JsonProperty("idseccion")
	public String getIdseccion() {
		return idseccion;
	}

	public void setIdseccion(String idseccion) {
		this.idseccion = idseccion;
	}

	@JsonProperty("VALOR")
	public String getVALOR() {
		return VALOR;
	}

	public void setVALOR(String vALOR) {
		VALOR = vALOR;
	}

	@JsonProperty("DESCRIPCIONVALOR")
	public String getDESCRIPCIONVALOR() {
		return DESCRIPCIONVALOR;
	}

	public void setDESCRIPCIONVALOR(String dESCRIPCIONVALOR) {
		DESCRIPCIONVALOR = dESCRIPCIONVALOR;
	}

	@JsonProperty("PUNTOS")
	public String getPUNTOS() {
		return PUNTOS;
	}

	public void setPUNTOS(String pUNTOS) {
		PUNTOS = pUNTOS;
	}

	@Override
	public String toString() {
		return "EvaluacionDesempenioJson [IDPROCESOVIGENTE=" + IDPROCESOVIGENTE + ", NOMBRESECCION=" + NOMBRESECCION
				+ ", backgroundColor=" + backgroundColor + ", claveEvaluador=" + claveEvaluador + ", claveEvaluado="
				+ claveEvaluado + ", claveUnidadAdmin=" + claveUnidadAdmin + ", idseccion=" + idseccion + ", VALOR="
				+ VALOR + ", DESCRIPCIONVALOR=" + DESCRIPCIONVALOR + ", preguntas=" + preguntas + "]";
	}

	

}
