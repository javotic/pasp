package mx.gob.edomex.microservicios.serviciosbus.models;

import java.io.Serializable;

public class BusServiceEventos  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String IDCATEGORIA;
	private String IDEVENTO;
	private String NOMBREEVENTO;
	private String FECHAINICIO;
	private String FECHAFIN;
	private String TEXTOGENERAL;
	private String IMAGENPRE;
	private String IMAGENEXT;
	private String DOCUMENTO;
	private String ENLACEEXTERNO;

	public String getIDCATEGORIA() {
		return IDCATEGORIA;
	}

	public void setIDCATEGORIA(String iDCATEGORIA) {
		IDCATEGORIA = iDCATEGORIA;
	}

	public String getIDEVENTO() {
		return IDEVENTO;
	}

	public void setIDEVENTO(String iDEVENTO) {
		IDEVENTO = iDEVENTO;
	}

	public String getNOMBREEVENTO() {
		return NOMBREEVENTO;
	}

	public void setNOMBREEVENTO(String nOMBREEVENTO) {
		NOMBREEVENTO = nOMBREEVENTO;
	}

	public String getFECHAINICIO() {
		return FECHAINICIO;
	}

	public void setFECHAINICIO(String fECHAINICIO) {
		FECHAINICIO = fECHAINICIO;
	}

	public String getFECHAFIN() {
		return FECHAFIN;
	}

	public void setFECHAFIN(String fECHAFIN) {
		FECHAFIN = fECHAFIN;
	}

	public String getTEXTOGENERAL() {
		return TEXTOGENERAL;
	}

	public void setTEXTOGENERAL(String tEXTOGENERAL) {
		TEXTOGENERAL = tEXTOGENERAL;
	}

	public String getIMAGENPRE() {
		return IMAGENPRE;
	}

	public void setIMAGENPRE(String iMAGENPRE) {
		IMAGENPRE = iMAGENPRE;
	}

	public String getIMAGENEXT() {
		return IMAGENEXT;
	}

	public void setIMAGENEXT(String iMAGENEXT) {
		IMAGENEXT = iMAGENEXT;
	}

	public String getDOCUMENTO() {
		return DOCUMENTO;
	}

	public void setDOCUMENTO(String dOCUMENTO) {
		DOCUMENTO = dOCUMENTO;
	}

	public String getENLACEEXTERNO() {
		return ENLACEEXTERNO;
	}

	public void setENLACEEXTERNO(String eNLACEEXTERNO) {
		ENLACEEXTERNO = eNLACEEXTERNO;
	}

}
