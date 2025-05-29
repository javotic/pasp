package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class EnvioRespuestas implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String IDPREGUNTA;
	private String IDRESPUESTA;
	private String IDEVALUACION;
	private String IDSEVIDORPUBLICOEVALUADO;
	private String IDSEVIDORPUBLICOEVALUADOR;
	private String IDUNIDADADMINISTRATIVA;
	private String TIPOSECCION;
	private String PUNTAJEOBTENIDO;

	@JsonProperty("IDPREGUNTA")
	public String getIDPREGUNTA() {
		return IDPREGUNTA;
	}

	public void setIDPREGUNTA(String iDPREGUNTA) {
		IDPREGUNTA = iDPREGUNTA;
	}
    
	@JsonProperty("IDRESPUESTA")
	public String getIDRESPUESTA() {
		return IDRESPUESTA;
	}

	public void setIDRESPUESTA(String iDRESPUESTA) {
		IDRESPUESTA = iDRESPUESTA;
	}

	@JsonProperty("IDEVALUACION")
	public String getIDEVALUACION() {
		return IDEVALUACION;
	}

	public void setIDEVALUACION(String iDEVALUACION) {
		IDEVALUACION = iDEVALUACION;
	}

	@JsonProperty("IDSEVIDORPUBLICOEVALUADO")
	public String getIDSEVIDORPUBLICOEVALUADO() {
		return IDSEVIDORPUBLICOEVALUADO;
	}

	public void setIDSEVIDORPUBLICOEVALUADO(String iDSEVIDORPUBLICOEVALUADO) {
		IDSEVIDORPUBLICOEVALUADO = iDSEVIDORPUBLICOEVALUADO;
	}

	@JsonProperty("IDSEVIDORPUBLICOEVALUADOR")
	public String getIDSEVIDORPUBLICOEVALUADOR() {
		return IDSEVIDORPUBLICOEVALUADOR;
	}

	public void setIDSEVIDORPUBLICOEVALUADOR(String iDSEVIDORPUBLICOEVALUADOR) {
		IDSEVIDORPUBLICOEVALUADOR = iDSEVIDORPUBLICOEVALUADOR;
	}

	@JsonProperty("IDUNIDADADMINISTRATIVA")
	public String getIDUNIDADADMINISTRATIVA() {
		return IDUNIDADADMINISTRATIVA;
	}

	public void setIDUNIDADADMINISTRATIVA(String iDUNIDADADMINISTRATIVA) {
		IDUNIDADADMINISTRATIVA = iDUNIDADADMINISTRATIVA;
	}

	@JsonProperty("TIPOSECCION")
	public String getTIPOSECCION() {
		return TIPOSECCION;
	}

	public void setTIPOSECCION(String tIPOSECCION) {
		TIPOSECCION = tIPOSECCION;
	}

	@JsonProperty("PUNTAJEOBTENIDO")
	public String getPUNTAJEOBTENIDO() {
		return PUNTAJEOBTENIDO;
	}

	public void setPUNTAJEOBTENIDO(String pUNTAJEOBTENIDO) {
		PUNTAJEOBTENIDO = pUNTAJEOBTENIDO;
	}

}
