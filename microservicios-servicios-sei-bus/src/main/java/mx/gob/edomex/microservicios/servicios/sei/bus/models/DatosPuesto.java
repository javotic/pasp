package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class DatosPuesto implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String CLAVESERVIDORPUBLICO;
	private String NOMBRE;
	private String PRIMERAPELLIDO;
	private String SEGUNDOAPELLIDO;
	private String NOMBREPUESTO;
	private String FECHAINGRESOPUESTO;
	private String NUMPLAZA;
	private String CODIGOPUESTO;
	private String FECHAINGRESOGEM;
	private String SUBSECRETARIAGRL;
	private String SUBSECRETARIA;
	private String DIRECCIONGRL;
	private String DIRECCIONAREA;
	private String SUBDIRECCION;
	private String DEPARTAMENTO;
	private String ESPACIOORGANIZACIONAL;
	private String CLAVEUNIDADADMINISTRATIVA;
	private String NOMBREUNIDADADMINISTRATIVA;
	private String CLAVESPEVALUADOR;
	private String NOMBRESPEVALUADOR;
	private String PRIMERAPELLIDOSPEVALUADOR;
	private String SEGUNDOAPELLIDOSPEVALUADOR;
	private String CALIFICACIONFINAL;
	private String FECHAUNO;
	private String FECHADOS;
	private String DEPENDENCIA;
        private String FECHATRES;
        private String ESTATUS;

	@JsonProperty("CLAVESERVIDORPUBLICO")
	public String getCLAVESERVIDORPUBLICO() {
		return CLAVESERVIDORPUBLICO;
	}

	public void setCLAVESERVIDORPUBLICO(String cLAVESERVIDORPUBLICO) {
		CLAVESERVIDORPUBLICO = cLAVESERVIDORPUBLICO;
	}

	@JsonProperty("NOMBRE")
	public String getNOMBRE() {
		return NOMBRE;
	}

	public void setNOMBRE(String nOMBRE) {
		NOMBRE = nOMBRE;
	}

	@JsonProperty("PRIMERAPELLIDO")
	public String getPRIMERAPELLIDO() {
		return PRIMERAPELLIDO;
	}

	public void setPRIMERAPELLIDO(String pRIMERAPELLIDO) {
		PRIMERAPELLIDO = pRIMERAPELLIDO;
	}

	@JsonProperty("SEGUNDOAPELLIDO")
	public String getSEGUNDOAPELLIDO() {
		return SEGUNDOAPELLIDO;
	}

	public void setSEGUNDOAPELLIDO(String sEGUNDOAPELLIDO) {
		SEGUNDOAPELLIDO = sEGUNDOAPELLIDO;
	}

	@JsonProperty("NOMBREPUESTO")
	public String getNOMBREPUESTO() {
		return NOMBREPUESTO;
	}

	public void setNOMBREPUESTO(String nOMBREPUESTO) {
		NOMBREPUESTO = nOMBREPUESTO;
	}

	@JsonProperty("FECHAINGRESOPUESTO")
	public String getFECHAINGRESOPUESTO() {
		return FECHAINGRESOPUESTO;
	}

	public void setFECHAINGRESOPUESTO(String fECHAINGRESOPUESTO) {
		FECHAINGRESOPUESTO = fECHAINGRESOPUESTO;
	}

	@JsonProperty("NUMPLAZA")
	public String getNUMPLAZA() {
		return NUMPLAZA;
	}

	public void setNUMPLAZA(String nUMPLAZA) {
		NUMPLAZA = nUMPLAZA;
	}

	@JsonProperty("CODIGOPUESTO")
	public String getCODIGOPUESTO() {
		return CODIGOPUESTO;
	}

	public void setCODIGOPUESTO(String cODIGOPUESTO) {
		CODIGOPUESTO = cODIGOPUESTO;
	}

	@JsonProperty("FECHAINGRESOGEM")
	public String getFECHAINGRESOGEM() {
		return FECHAINGRESOGEM;
	}

	public void setFECHAINGRESOGEM(String fECHAINGRESOGEM) {
		FECHAINGRESOGEM = fECHAINGRESOGEM;
	}

	@JsonProperty("SUBSECRETARIAGRL")
	public String getSUBSECRETARIAGRL() {
		return SUBSECRETARIAGRL;
	}

	public void setSUBSECRETARIAGRL(String sUBSECRETARIAGRL) {
		SUBSECRETARIAGRL = sUBSECRETARIAGRL;
	}

	@JsonProperty("SUBSECRETARIA")
	public String getSUBSECRETARIA() {
		return SUBSECRETARIA;
	}

	public void setSUBSECRETARIA(String sUBSECRETARIA) {
		SUBSECRETARIA = sUBSECRETARIA;
	}

	@JsonProperty("DIRECCIONGRL")
	public String getDIRECCIONGRL() {
		return DIRECCIONGRL;
	}

	public void setDIRECCIONGRL(String dIRECCIONGRL) {
		DIRECCIONGRL = dIRECCIONGRL;
	}

	@JsonProperty("DIRECCIONAREA")
	public String getDIRECCIONAREA() {
		return DIRECCIONAREA;
	}

	public void setDIRECCIONAREA(String dIRECCIONAREA) {
		DIRECCIONAREA = dIRECCIONAREA;
	}

	@JsonProperty("SUBDIRECCION")
	public String getSUBDIRECCION() {
		return SUBDIRECCION;
	}

	public void setSUBDIRECCION(String sUBDIRECCION) {
		SUBDIRECCION = sUBDIRECCION;
	}

	@JsonProperty("DEPARTAMENTO")
	public String getDEPARTAMENTO() {
		return DEPARTAMENTO;
	}

	public void setDEPARTAMENTO(String dEPARTAMENTO) {
		DEPARTAMENTO = dEPARTAMENTO;
	}

	@JsonProperty("ESPACIOORGANIZACIONAL")
	public String getESPACIOORGANIZACIONAL() {
		return ESPACIOORGANIZACIONAL;
	}

	public void setESPACIOORGANIZACIONAL(String eSPACIOORGANIZACIONAL) {
		ESPACIOORGANIZACIONAL = eSPACIOORGANIZACIONAL;
	}

	@JsonProperty("CLAVEUNIDADADMINISTRATIVA")
	public String getCLAVEUNIDADADMINISTRATIVA() {
		return CLAVEUNIDADADMINISTRATIVA;
	}

	public void setCLAVEUNIDADADMINISTRATIVA(String cLAVEUNIDADADMINISTRATIVA) {
		CLAVEUNIDADADMINISTRATIVA = cLAVEUNIDADADMINISTRATIVA;
	}

	@JsonProperty("NOMBREUNIDADADMINISTRATIVA")
	public String getNOMBREUNIDADADMINISTRATIVA() {
		return NOMBREUNIDADADMINISTRATIVA;
	}

	public void setNOMBREUNIDADADMINISTRATIVA(String nOMBREUNIDADADMINISTRATIVA) {
		NOMBREUNIDADADMINISTRATIVA = nOMBREUNIDADADMINISTRATIVA;
	}

	@JsonProperty("CLAVESPEVALUADOR")
	public String getCLAVESPEVALUADOR() {
		return CLAVESPEVALUADOR;
	}

	public void setCLAVESPEVALUADOR(String cLAVESPEVALUADOR) {
		CLAVESPEVALUADOR = cLAVESPEVALUADOR;
	}

	@JsonProperty("NOMBRESPEVALUADOR")
	public String getNOMBRESPEVALUADOR() {
		return NOMBRESPEVALUADOR;
	}

	public void setNOMBRESPEVALUADOR(String nOMBRESPEVALUADOR) {
		NOMBRESPEVALUADOR = nOMBRESPEVALUADOR;
	}

	@JsonProperty("PRIMERAPELLIDOSPEVALUADOR")
	public String getPRIMERAPELLIDOSPEVALUADOR() {
		return PRIMERAPELLIDOSPEVALUADOR;
	}

	public void setPRIMERAPELLIDOSPEVALUADOR(String pRIMERAPELLIDOSPEVALUADOR) {
		PRIMERAPELLIDOSPEVALUADOR = pRIMERAPELLIDOSPEVALUADOR;
	}

	@JsonProperty("SEGUNDOAPELLIDOSPEVALUADOR")
	public String getSEGUNDOAPELLIDOSPEVALUADOR() {
		return SEGUNDOAPELLIDOSPEVALUADOR;
	}

	public void setSEGUNDOAPELLIDOSPEVALUADOR(String sEGUNDOAPELLIDOSPEVALUADOR) {
		SEGUNDOAPELLIDOSPEVALUADOR = sEGUNDOAPELLIDOSPEVALUADOR;
	}

	@JsonProperty("CALIFICACIONFINAL")
	public String getCALIFICACIONFINAL() {
		return CALIFICACIONFINAL;
	}

	public void setCALIFICACIONFINAL(String cALIFICACIONFINAL) {
		CALIFICACIONFINAL = cALIFICACIONFINAL;
	}

	@JsonProperty("FECHAUNO")
	public String getFECHAUNO() {
		return FECHAUNO;
	}

	public void setFECHAUNO(String fECHAUNO) {
		FECHAUNO = fECHAUNO;
	}

	@JsonProperty("FECHADOS")
	public String getFECHADOS() {
		return FECHADOS;
	}

	public void setFECHADOS(String fECHADOS) {
		FECHADOS = fECHADOS;
	}
	@JsonProperty("DEPENDENCIA")
	public String getDEPENDENCIA() {
		return DEPENDENCIA;
	}

	public void setDEPENDENCIA(String dEPENDENCIA) {
		DEPENDENCIA = dEPENDENCIA;
	}
        
        @JsonProperty("FECHATRES")
        public String getFECHATRES() {
            return FECHATRES;
        }

        public void setFECHATRES(String fECHATRES) {
            this.FECHATRES = fECHATRES;
        }

        public String getESTATUS() {
            return ESTATUS;
        }
        
        @JsonProperty("ESTATUS")
        public void setESTATUS(String eSTATUS) {
            this.ESTATUS = eSTATUS;
        }
        
}
