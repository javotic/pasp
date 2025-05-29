package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class EjecucionPreguntasKPI implements Serializable {
	private static final long serialVersionUID = 1L;

	private String IDPROCESOVIGENTE;
	private String IDPREGUNTA;
	private String DESCRIPCION;
        private int MAXRESPUESTA;
	private List<RespuestasKPI> RESPUESTASKPI;

	@JsonProperty("IDPROCESOVIGENTE")
	public String getIDPROCESOVIGENTE() {
		return IDPROCESOVIGENTE;
	}

	public void setIDPROCESOVIGENTE(String iDPROCESOVIGENTE) {
		IDPROCESOVIGENTE = iDPROCESOVIGENTE;
	}

	@JsonProperty("IDPREGUNTA")
	public String getIDPREGUNTA() {
		return IDPREGUNTA;
	}

	public void setIDPREGUNTA(String iDPREGUNTA) {
		IDPREGUNTA = iDPREGUNTA;
	}

	@JsonProperty("DESCRIPCION")
	public String getDESCRIPCION() {
		return DESCRIPCION;
	}

	public void setDESCRIPCION(String dESCRIPCION) {
		DESCRIPCION = dESCRIPCION;
	}

        @JsonProperty("MAXRESPUESTA")
        public int getMAXRESPUESTA() {
            return MAXRESPUESTA;
        }

        public void setMAXRESPUESTA(int MAXRESPUESTA) {
            this.MAXRESPUESTA = MAXRESPUESTA;
        }
        
	@JsonProperty("RESPUESTASKPI")
	public List<RespuestasKPI> getRESPUESTASKPI() {
		return RESPUESTASKPI;
	}

	public void setRESPUESTASKPI(List<RespuestasKPI> rESPUESTASKPI) {
		RESPUESTASKPI = rESPUESTASKPI;
	}

}
