package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
@Entity
public class ServidorPublicoEddUa  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "CLAVESERVIDORPUBLICO")	
	private String CLAVESERVIDORPUBLICO;   	
	private String NOMBRESERVIDORPUBLICO;	
	private String APELLIDOPATERNOSERVIDORPUBLIC;	
	private String APELLIDOMATERNOSERVIDORPUBLIC;	
	private String RFCSERVIDORPUBLICO;	
	private String PUESTOSERVIDORPUBLICO;	
	private String TIPOEVALUACION;	
	private String ESTATUSEVALUACION;	
	private String IDPROCESOVIGENTE;	
	private String IDEVALUACIONKP;   
	private String CORREOSERVIDORPUBLICO;
        private String ESPACIOORGANIZACIONAL;
        
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
	public String getAPELLIDOPATERNOSERVIDORPUBLIC() {
		return APELLIDOPATERNOSERVIDORPUBLIC;
	}
	public void setAPELLIDOPATERNOSERVIDORPUBLIC(String aPELLIDOPATERNOSERVIDORPUBLIC) {
		APELLIDOPATERNOSERVIDORPUBLIC = aPELLIDOPATERNOSERVIDORPUBLIC;
	}
	
	@JsonProperty("APELLIDOMATERNOSERVIDORPUBLICO")
	public String getAPELLIDOMATERNOSERVIDORPUBLIC() {
		return APELLIDOMATERNOSERVIDORPUBLIC;
	}
	public void setAPELLIDOMATERNOSERVIDORPUBLIC(String aPELLIDOMATERNOSERVIDORPUBLIC) {
		APELLIDOMATERNOSERVIDORPUBLIC = aPELLIDOMATERNOSERVIDORPUBLIC;
	}
	
	@JsonProperty("RFCSERVIDORPUBLICO")
	public String getRFCSERVIDORPUBLICO() {
		return RFCSERVIDORPUBLICO;
	}
	public void setRFCSERVIDORPUBLICO(String rFCSERVIDORPUBLICO) {
		RFCSERVIDORPUBLICO = rFCSERVIDORPUBLICO;
	}
	
	@JsonProperty("PUESTOSERVIDORPUBLICO")
	public String getPUESTOSERVIDORPUBLICO() {
		return PUESTOSERVIDORPUBLICO;
	}
	public void setPUESTOSERVIDORPUBLICO(String pUESTOSERVIDORPUBLICO) {
		PUESTOSERVIDORPUBLICO = pUESTOSERVIDORPUBLICO;
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
	
	@JsonProperty("IDPROCESOVIGENTE")
	public String getIDPROCESOVIGENTE() {
		return IDPROCESOVIGENTE;
	}
	public void setIDPROCESOVIGENTE(String iDPROCESOVIGENTE) {
		IDPROCESOVIGENTE = iDPROCESOVIGENTE;
	}
	
	@JsonProperty("IDEVALUACIONKP")
	public String getIDEVALUACIONKP() {
		return IDEVALUACIONKP;
	}
	public void setIDEVALUACIONKP(String iDEVALUACIONKP) {
		IDEVALUACIONKP = iDEVALUACIONKP;
	}
	@JsonProperty("CORREOSERVIDORPUBLICO")
	public String getCORREOSERVIDORPUBLICO() {
		return CORREOSERVIDORPUBLICO;
	}
	public void setCORREOSERVIDORPUBLICO(String cORREOSERVIDORPUBLICO) {
		CORREOSERVIDORPUBLICO = cORREOSERVIDORPUBLICO;
	}
        @JsonProperty("ESPACIOORGANIZACIONAL")
        public String getESPACIOORGANIZACIONAL() {
            return ESPACIOORGANIZACIONAL;
        }

        public void setESPACIOORGANIZACIONAL(String ESPACIOORGANIZACIONAL) {
            this.ESPACIOORGANIZACIONAL = ESPACIOORGANIZACIONAL;
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
