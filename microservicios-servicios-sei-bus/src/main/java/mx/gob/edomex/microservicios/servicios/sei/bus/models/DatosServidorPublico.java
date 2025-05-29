package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class DatosServidorPublico implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String IDORDINAL;
	private String CLAVESERVIDORPUBLICO;
	private String NOMBRE;
	private String PRIMERAPELLIDO;
	private String GENERO;
	private String CURP;
	private String RFC;
	private String FECHANACIMIENTO;
	private String TELEFONO;
	private String CORREOELECTRONICO;
	private String CALLE;
	private String IDESTADO;
	private String IDMUNICIPIO;
	private String IDCOLONIA;
	private String NUMEXTERIOR;
	private String NUMINTERIOR;
	private String CODIGOPOSTAL;
	private String NOMBREESTADO;
	private String NOMBREPLAZA;
	private String IDNIVELESTUDIOS;
	private String IDSEXO;
	private String IDESTADOCIVIL;
	private String IDPLAZA;
	private String ISSEMYN;
	//SEGUNDOAPELLIDO

	@JsonProperty("IDORDINAL")
	public String getIDORDINAL() {
		return IDORDINAL;
	}

	public void setIDORDINAL(String iDORDINAL) {
		IDORDINAL = iDORDINAL;
	}

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

	@JsonProperty("GENERO")
	public String getGENERO() {
		return GENERO;
	}

	public void setGENERO(String gENERO) {
		GENERO = gENERO;
	}

	@JsonProperty("CURP")
	public String getCURP() {
		return CURP;
	}

	public void setCURP(String cURP) {
		CURP = cURP;
	}

	@JsonProperty("RFC")
	public String getRFC() {
		return RFC;
	}

	public void setRFC(String rFC) {
		RFC = rFC;
	}

	@JsonProperty("FECHANACIMIENTO")
	public String getFECHANACIMIENTO() {
		return FECHANACIMIENTO;
	}

	public void setFECHANACIMIENTO(String fECHANACIMIENTO) {
		FECHANACIMIENTO = fECHANACIMIENTO;
	}

	@JsonProperty("TELEFONO")
	public String getTELEFONO() {
		return TELEFONO;
	}

	public void setTELEFONO(String tELEFONO) {
		TELEFONO = tELEFONO;
	}

	@JsonProperty("CORREOELECTRONICO")
	public String getCORREOELECTRONICO() {
		return CORREOELECTRONICO;
	}

	public void setCORREOELECTRONICO(String cORREOELECTRONICO) {
		CORREOELECTRONICO = cORREOELECTRONICO;
	}

	@JsonProperty("CALLE")
	public String getCALLE() {
		return CALLE;
	}

	public void setCALLE(String cALLE) {
		CALLE = cALLE;
	}

	@JsonProperty("IDESTADO")
	public String getIDESTADO() {
		return IDESTADO;
	}

	public void setIDESTADO(String iDESTADO) {
		IDESTADO = iDESTADO;
	}

	@JsonProperty("IDMUNICIPIO")
	public String getIDMUNICIPIO() {
		return IDMUNICIPIO;
	}

	public void setIDMUNICIPIO(String iDMUNICIPIO) {
		IDMUNICIPIO = iDMUNICIPIO;
	}

	@JsonProperty("IDCOLONIA")
	public String getIDCOLONIA() {
		return IDCOLONIA;
	}

	public void setIDCOLONIA(String iDCOLONIA) {
		IDCOLONIA = iDCOLONIA;
	}

	@JsonProperty("NUMEXTERIOR")
	public String getNUMEXTERIOR() {
		return NUMEXTERIOR;
	}

	public void setNUMEXTERIOR(String nUMEXTERIOR) {
		NUMEXTERIOR = nUMEXTERIOR;
	}

	@JsonProperty("NUMINTERIOR")
	public String getNUMINTERIOR() {
		return NUMINTERIOR;
	}

	public void setNUMINTERIOR(String nUMINTERIOR) {
		NUMINTERIOR = nUMINTERIOR;
	}

	@JsonProperty("CODIGOPOSTAL")
	public String getCODIGOPOSTAL() {
		return CODIGOPOSTAL;
	}

	public void setCODIGOPOSTAL(String cODIGOPOSTAL) {
		CODIGOPOSTAL = cODIGOPOSTAL;
	}

	@JsonProperty("NOMBREESTADO")
	public String getNOMBREESTADO() {
		return NOMBREESTADO;
	}

	public void setNOMBREESTADO(String nOMBREESTADO) {
		NOMBREESTADO = nOMBREESTADO;
	}

	@JsonProperty("NOMBREPLAZA")
	public String getNOMBREPLAZA() {
		return NOMBREPLAZA;
	}

	public void setNOMBREPLAZA(String nOMBREPLAZA) {
		NOMBREPLAZA = nOMBREPLAZA;
	}

	@JsonProperty("IDNIVELESTUDIOS")
	public String getIDNIVELESTUDIOS() {
		return IDNIVELESTUDIOS;
	}

	public void setIDNIVELESTUDIOS(String iDNIVELESTUDIOS) {
		IDNIVELESTUDIOS = iDNIVELESTUDIOS;
	}

	@JsonProperty("IDSEXO")
	public String getIDSEXO() {
		return IDSEXO;
	}

	public void setIDSEXO(String iDSEXO) {
		IDSEXO = iDSEXO;
	}

	@JsonProperty("IDESTADOCIVIL")
	public String getIDESTADOCIVIL() {
		return IDESTADOCIVIL;
	}

	public void setIDESTADOCIVIL(String iDESTADOCIVIL) {
		IDESTADOCIVIL = iDESTADOCIVIL;
	}

	@JsonProperty("IDPLAZA")
	public String getIDPLAZA() {
		return IDPLAZA;
	}

	public void setIDPLAZA(String iDPLAZA) {
		IDPLAZA = iDPLAZA;
	}

	@JsonProperty("iSSEMYN")
	public String getISSEMYN() {
		return ISSEMYN;
	}

	public void setISSEMYN(String iSSEMYN) {
		ISSEMYN = iSSEMYN;
	}

}
