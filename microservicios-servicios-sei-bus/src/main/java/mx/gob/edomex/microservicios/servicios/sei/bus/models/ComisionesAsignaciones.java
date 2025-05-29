package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class ComisionesAsignaciones implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String CLAVESERVIDORPUBLICO;
	private String NOMBRESERVIDORPUBLICO;
	private String APELLIDOPATERNOSERVIDORPUBLICO;
	private String APELLIDOMATERNOSERVIDORPUBLICO;
	private String RFCSERVIDORPUBLICO;
	private String TIPOEVALUACION;
	private String ESTATUSEVALUACION;
	private String IDPROCESOACTUAL;
	private String CORREOSERVIDORPUBLICO;
	private String EVALUACIONKP;
        private String IDUNIDAD_ASIGNADA;
        private String UNIDAD_ASIGNADA;

	@JsonProperty("CLAVESERVIDORPUBLICO")
	public String getCLAVESERVIDORPUBLICO() {
		return CLAVESERVIDORPUBLICO;
	}

	public void setCLAVESERVIDORPUBLICO(String cLAVESERVIDORPUBLICO) {
		CLAVESERVIDORPUBLICO = cLAVESERVIDORPUBLICO;
	}

	@JsonProperty("NOMBRESERVIDORPUBLICO")
	public String getNOMBRESERVIDORPUBLICO() {
		return NOMBRESERVIDORPUBLICO;
	}

	public void setNOMBRESERVIDORPUBLICO(String nOMBRESERVIDORPUBLICO) {
		NOMBRESERVIDORPUBLICO = nOMBRESERVIDORPUBLICO;
	}

	@JsonProperty("APELLIDOPATERNOSERVIDORPUBLICO")
	public String getAPELLIDOPATERNOSERVIDORPUBLICO() {
		return APELLIDOPATERNOSERVIDORPUBLICO;
	}

	public void setAPELLIDOPATERNOSERVIDORPUBLICO(String aPELLIDOPATERNOSERVIDORPUBLICO) {
		APELLIDOPATERNOSERVIDORPUBLICO = aPELLIDOPATERNOSERVIDORPUBLICO;
	}

	@JsonProperty("APELLIDOMATERNOSERVIDORPUBLICO")
	public String getAPELLIDOMATERNOSERVIDORPUBLICO() {
		return APELLIDOMATERNOSERVIDORPUBLICO;
	}

	public void setAPELLIDOMATERNOSERVIDORPUBLICO(String aPELLIDOMATERNOSERVIDORPUBLICO) {
		APELLIDOMATERNOSERVIDORPUBLICO = aPELLIDOMATERNOSERVIDORPUBLICO;
	}

	@JsonProperty("RFCSERVIDORPUBLICO")
	public String getRFCSERVIDORPUBLICO() {
		return RFCSERVIDORPUBLICO;
	}

	public void setRFCSERVIDORPUBLICO(String rFCSERVIDORPUBLICO) {
		RFCSERVIDORPUBLICO = rFCSERVIDORPUBLICO;
	}

	@JsonProperty("TIPOEVALUACION")
	public String getTIPOEVALUACION() {
		return TIPOEVALUACION;
	}

	public void setTIPOEVALUACION(String tIPOEVALUACION) {
		TIPOEVALUACION = tIPOEVALUACION;
	}

	@JsonProperty("ESTATUSEVALUACION")
	public String getESTATUSEVALUACION() {
		return ESTATUSEVALUACION;
	}

	public void setESTATUSEVALUACION(String eSTATUSEVALUACION) {
		ESTATUSEVALUACION = eSTATUSEVALUACION;
	}

	@JsonProperty("IDPROCESOACTUAL")
	public String getIDPROCESOACTUAL() {
		return IDPROCESOACTUAL;
	}

	public void setIDPROCESOACTUAL(String iDPROCESOACTUAL) {
		IDPROCESOACTUAL = iDPROCESOACTUAL;
	}

	@JsonProperty("CORREOSERVIDORPUBLICO")
	public String getCORREOSERVIDORPUBLICO() {
		return CORREOSERVIDORPUBLICO;
	}

	public void setCORREOSERVIDORPUBLICO(String cORREOSERVIDORPUBLICO) {
		CORREOSERVIDORPUBLICO = cORREOSERVIDORPUBLICO;
	}

	@JsonProperty("EVALUACIONKP")
	public String getEVALUACIONKP() {
		return EVALUACIONKP;
	}

	public void setEVALUACIONKP(String eVALUACIONKP) {
		EVALUACIONKP = eVALUACIONKP;
	}
        
        @JsonProperty("IDUNIDAD_ASIGNADA")
        public String getIDUNIDAD_ASIGNADA() {
            return IDUNIDAD_ASIGNADA;
        }

        public void setIDUNIDAD_ASIGNADA(String IDUNIDAD_ASIGNADA) {
            this.IDUNIDAD_ASIGNADA = IDUNIDAD_ASIGNADA;
        }

        @JsonProperty("UNIDAD_ASIGNADA")
        public String getUNIDAD_ASIGNADA() {
            return UNIDAD_ASIGNADA;
        }

        public void setUNIDAD_ASIGNADA(String UNIDAD_ASIGNADA) {
            this.UNIDAD_ASIGNADA = UNIDAD_ASIGNADA;
        }        

}
