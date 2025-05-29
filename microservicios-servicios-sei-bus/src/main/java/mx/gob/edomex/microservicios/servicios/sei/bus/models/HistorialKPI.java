package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class HistorialKPI implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String IDPROCESOEVALUACIONKPI;
	private String CLAVEPROCESOKPI;
	private String NOMBREPROCESOEVALUACION;
	private String FECHAINICIOPROCESOKPI;
	private String FECHAFINPROCESOKPI;
	private String IDESTATUS;
	private String fechaEval;

	@JsonProperty("IDPROCESOEVALUACIONKPI")
	public String getIDPROCESOEVALUACIONKPI() {
		return IDPROCESOEVALUACIONKPI;
	}

	public void setIDPROCESOEVALUACIONKPI(String iDPROCESOEVALUACIONKPI) {
		IDPROCESOEVALUACIONKPI = iDPROCESOEVALUACIONKPI;
	}

	@JsonProperty("CLAVEPROCESOKPI")
	public String getCLAVEPROCESOKPI() {
		return CLAVEPROCESOKPI;
	}

	public void setCLAVEPROCESOKPI(String cLAVEPROCESOKPI) {
		CLAVEPROCESOKPI = cLAVEPROCESOKPI;
	}

	@JsonProperty("NOMBREPROCESOEVALUACION")
	public String getNOMBREPROCESOEVALUACION() {
		return NOMBREPROCESOEVALUACION;
	}

	public void setNOMBREPROCESOEVALUACION(String nOMBREPROCESOEVALUACION) {
		NOMBREPROCESOEVALUACION = nOMBREPROCESOEVALUACION;
	}

	@JsonProperty("FECHAINICIOPROCESOKPI")
	public String getFECHAINICIOPROCESOKPI() {
		return FECHAINICIOPROCESOKPI;
	}

	public void setFECHAINICIOPROCESOKPI(String fECHAINICIOPROCESOKPI) {
		FECHAINICIOPROCESOKPI = fECHAINICIOPROCESOKPI;
	}

	@JsonProperty("FECHAFINPROCESOKPI")
	public String getFECHAFINPROCESOKPI() {
		return FECHAFINPROCESOKPI;
	}

	public void setFECHAFINPROCESOKPI(String fECHAFINPROCESOKPI) {
		FECHAFINPROCESOKPI = fECHAFINPROCESOKPI;
	}

	@JsonProperty("IDESTATUS")
	public String getIDESTATUS() {
		return IDESTATUS;
	}

	public void setIDESTATUS(String iDESTATUS) {
		IDESTATUS = iDESTATUS;
	}

        @JsonProperty("FECHAEVAL")
        public String getFechaEval() {
            return fechaEval;
        }

        public void setFechaEval(String fechaEval) {
            this.fechaEval = fechaEval;
        }
 
}
