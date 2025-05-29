package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class DatosPersonales implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String CLAVESERVIDOR;
	private String APELLIDOPATERNO;
	private String APELLIDOMATERNO;
	private String MOMBRE;
	private String NOMBRECOMPLETO;
	private String IDGENERO;
	private String NOMBREGENERO;
	private String IDPAIS;
	private String NOMBREPAIS;
	private String IDTRATAMIENTO;
	private String NOMBRETRATAMIENTO;
	private String FECHANACIMIENTO;
	private String RFC;
	private String CURP;
	private String IDESCOLARIDAD;
	private String DESCRIESCOLARIDAD;
	private String IDESTADOCIVIL;
	private String NOMBREESTADOCIVIL;
	private String COMENTARIOS;
	private String DESEMPENO;
	private String KPI;
	private String ESCALAFON;
	private String CONSULTA;
        private Integer IDROL;
        private String BOACTIVO;
        private String BOPRORROGA;
        private String IDPUESTO;
        private String NPUESTO;

	@JsonProperty("CLAVESERVIDOR")
	public String getCLAVESERVIDOR() {
		return CLAVESERVIDOR;
	}

	public void setCLAVESERVIDOR(String cLAVESERVIDOR) {
		CLAVESERVIDOR = cLAVESERVIDOR;
	}

	@JsonProperty("APELLIDOPATERNO")
	public String getAPELLIDOPATERNO() {
		return APELLIDOPATERNO;
	}

	public void setAPELLIDOPATERNO(String aPELLIDOPATERNO) {
		APELLIDOPATERNO = aPELLIDOPATERNO;
	}

	@JsonProperty("APELLIDOMATERNO")
	public String getAPELLIDOMATERNO() {
		return APELLIDOMATERNO;
	}

	public void setAPELLIDOMATERNO(String aPELLIDOMATERNO) {
		APELLIDOMATERNO = aPELLIDOMATERNO;
	}

	@JsonProperty("MOMBRE")
	public String getMOMBRE() {
		return MOMBRE;
	}

	public void setMOMBRE(String mOMBRE) {
		MOMBRE = mOMBRE;
	}

	@JsonProperty("NOMBRECOMPLETO")
	public String getNOMBRECOMPLETO() {
		return NOMBRECOMPLETO;
	}

	public void setNOMBRECOMPLETO(String nOMBRECOMPLETO) {
		NOMBRECOMPLETO = nOMBRECOMPLETO;
	}

	@JsonProperty("IDGENERO")
	public String getIDGENERO() {
		return IDGENERO;
	}

	public void setIDGENERO(String iDGENERO) {
		IDGENERO = iDGENERO;
	}

	@JsonProperty("NOMBREGENERO")
	public String getNOMBREGENERO() {
		return NOMBREGENERO;
	}

	public void setNOMBREGENERO(String nOMBREGENERO) {
		NOMBREGENERO = nOMBREGENERO;
	}

	@JsonProperty("IDPAIS")
	public String getIDPAIS() {
		return IDPAIS;
	}

	public void setIDPAIS(String iDPAIS) {
		IDPAIS = iDPAIS;
	}

	@JsonProperty("NOMBREPAIS")
	public String getNOMBREPAIS() {
		return NOMBREPAIS;
	}

	public void setNOMBREPAIS(String nOMBREPAIS) {
		NOMBREPAIS = nOMBREPAIS;
	}

	@JsonProperty("IDTRATAMIENTO")
	public String getIDTRATAMIENTO() {
		return IDTRATAMIENTO;
	}

	public void setIDTRATAMIENTO(String iDTRATAMIENTO) {
		IDTRATAMIENTO = iDTRATAMIENTO;
	}

	@JsonProperty("NOMBRETRATAMIENTO")
	public String getNOMBRETRATAMIENTO() {
		return NOMBRETRATAMIENTO;
	}

	public void setNOMBRETRATAMIENTO(String nOMBRETRATAMIENTO) {
		NOMBRETRATAMIENTO = nOMBRETRATAMIENTO;
	}

	@JsonProperty("FECHANACIMIENTO")
	public String getFECHANACIMIENTO() {
		return FECHANACIMIENTO;
	}

	public void setFECHANACIMIENTO(String fECHANACIMIENTO) {
		FECHANACIMIENTO = fECHANACIMIENTO;
	}

	@JsonProperty("RFC")
	public String getRFC() {
		return RFC;
	}

	public void setRFC(String rFC) {
		RFC = rFC;
	}

	@JsonProperty("CURP")
	public String getCURP() {
		return CURP;
	}

	public void setCURP(String cURP) {
		CURP = cURP;
	}

	@JsonProperty("IDESCOLARIDAD")
	public String getIDESCOLARIDAD() {
		return IDESCOLARIDAD;
	}

	public void setIDESCOLARIDAD(String iDESCOLARIDAD) {
		IDESCOLARIDAD = iDESCOLARIDAD;
	}

	@JsonProperty("DESCRIESCOLARIDAD")
	public String getDESCRIESCOLARIDAD() {
		return DESCRIESCOLARIDAD;
	}

	public void setDESCRIESCOLARIDAD(String dESCRIESCOLARIDAD) {
		DESCRIESCOLARIDAD = dESCRIESCOLARIDAD;
	}

	@JsonProperty("IDESTADOCIVIL")
	public String getIDESTADOCIVIL() {
		return IDESTADOCIVIL;
	}

	public void setIDESTADOCIVIL(String iDESTADOCIVIL) {
		IDESTADOCIVIL = iDESTADOCIVIL;
	}

	@JsonProperty("NOMBREESTADOCIVIL")
	public String getNOMBREESTADOCIVIL() {
		return NOMBREESTADOCIVIL;
	}

	public void setNOMBREESTADOCIVIL(String nOMBREESTADOCIVIL) {
		NOMBREESTADOCIVIL = nOMBREESTADOCIVIL;
	}

	@JsonProperty("COMENTARIOS")
	public String getCOMENTARIOS() {
		return COMENTARIOS;
	}

	public void setCOMENTARIOS(String cOMENTARIOS) {
		COMENTARIOS = cOMENTARIOS;
	}

	@JsonProperty("DESEMPENO")
	public String getDESEMPENO() {
		return DESEMPENO;
	}

	public void setDESEMPENO(String dESEMPENO) {
		DESEMPENO = dESEMPENO;
	}

	@JsonProperty("KPI")
	public String getKPI() {
		return KPI;
	}

	public void setKPI(String kPI) {
		KPI = kPI;
	}

	@JsonProperty("ESCALAFON")
	public String getESCALAFON() {
		return ESCALAFON;
	}

	public void setESCALAFON(String eSCALAFON) {
		ESCALAFON = eSCALAFON;
	}

	@JsonProperty("CONSULTA")
	public String getCONSULTA() {
		return CONSULTA;
	}

	public void setCONSULTA(String cONSULTA) {
		CONSULTA = cONSULTA;
	}

        @JsonProperty("IDROL")
        public Integer getIDROL() {
            return IDROL;
        }

        public void setIDROL(Integer IDROL) {
            this.IDROL = IDROL;
        }

        @JsonProperty("BOACTIVO")
        public String getBOACTIVO() {
            return BOACTIVO;
        }

        public void setBOACTIVO(String BOACTIVO) {
            this.BOACTIVO = BOACTIVO;
        }

        @JsonProperty("BOPRORROGA")
        public String getBOPRORROGA() {
            return BOPRORROGA;
        }

        public void setBOPRORROGA(String BOPRORROGA) {
            this.BOPRORROGA = BOPRORROGA;
        }

        @JsonProperty("IDPUESTO")
        public String getIDPUESTO() {
            return IDPUESTO;
        }

        public void setIDPUESTO(String IDPUESTO) {
            this.IDPUESTO = IDPUESTO;
        }

        @JsonProperty("NPUESTO")
        public String getNPUESTO() {
            return NPUESTO;
        }

        public void setNPUESTO(String NPUESTO) {
            this.NPUESTO = NPUESTO;
        } 
        
}
