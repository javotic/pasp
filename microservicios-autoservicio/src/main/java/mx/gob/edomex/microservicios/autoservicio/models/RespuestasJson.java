package mx.gob.edomex.microservicios.autoservicio.models;

import java.io.Serializable;

public class RespuestasJson  implements Serializable {
	private static final long serialVersionUID = 1L;

	private String IDRESPUESTA;
	private String RESPUESTA;
	private String PUNTAJE;
	private String SELECTED;
	private String NAMEGROUP;

	public String getIDRESPUESTA() {
		return IDRESPUESTA;
	}

	public void setIDRESPUESTA(String iDRESPUESTA) {
		IDRESPUESTA = iDRESPUESTA;
	}

	public String getRESPUESTA() {
		return RESPUESTA;
	}

	public void setRESPUESTA(String rESPUESTA) {
		RESPUESTA = rESPUESTA;
	}

	public String getPUNTAJE() {
		return PUNTAJE;
	}

	public void setPUNTAJE(String pUNTAJE) {
		PUNTAJE = pUNTAJE;
	}

	public String getSELECTED() {
		return SELECTED;
	}

	public void setSELECTED(String sELECTED) {
		SELECTED = sELECTED;
	}

	public String getNAMEGROUP() {
		return NAMEGROUP;
	}

	public void setNAMEGROUP(String nAMEGROUP) {
		NAMEGROUP = nAMEGROUP;
	}

	@Override
	public String toString() {
		return "RespuestasJson [IDRESPUESTA=" + IDRESPUESTA + ", RESPUESTA=" + RESPUESTA + ", PUNTAJE=" + PUNTAJE
				+ ", SELECTED=" + SELECTED + ", NAMEGROUP=" + NAMEGROUP + "]";
	}

	
}
