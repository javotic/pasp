package mx.gob.edomex.microservicios.autoservicio.models;

import java.io.Serializable;
import java.util.List;

public class PreguntasJson implements Serializable {

	private static final long serialVersionUID = 1L;

	private String IDSECCION;
	private String IDPROCESOVIGENTE;
	private String IDPREGUNTA;
	private String DESCRIPCIONPREGUNTA;
	private String backgroundPregunta;
	private String idPreguntaResp;
	private String tipoCampo;
	private List<RespuestasJson> respuestas;

	public String getIDSECCION() {
		return IDSECCION;
	}

	public void setIDSECCION(String iDSECCION) {
		IDSECCION = iDSECCION;
	}

	public String getIDPROCESOVIGENTE() {
		return IDPROCESOVIGENTE;
	}

	public void setIDPROCESOVIGENTE(String iDPROCESOVIGENTE) {
		IDPROCESOVIGENTE = iDPROCESOVIGENTE;
	}

	public String getIDPREGUNTA() {
		return IDPREGUNTA;
	}

	public void setIDPREGUNTA(String iDPREGUNTA) {
		IDPREGUNTA = iDPREGUNTA;
	}

	public String getDESCRIPCIONPREGUNTA() {
		return DESCRIPCIONPREGUNTA;
	}

	public void setDESCRIPCIONPREGUNTA(String dESCRIPCIONPREGUNTA) {
		DESCRIPCIONPREGUNTA = dESCRIPCIONPREGUNTA;
	}

	public String getBackgroundPregunta() {
		return backgroundPregunta;
	}

	public void setBackgroundPregunta(String backgroundPregunta) {
		this.backgroundPregunta = backgroundPregunta;
	}

	public List<RespuestasJson> getRespuestas() {
		return respuestas;
	}

	public void setRespuestas(List<RespuestasJson> respuestas) {
		this.respuestas = respuestas;
	}

	public String getIdPreguntaResp() {
		return idPreguntaResp;
	}

	public void setIdPreguntaResp(String idPreguntaResp) {
		this.idPreguntaResp = idPreguntaResp;
	}

	public String getTipoCampo() {
		return tipoCampo;
	}

	public void setTipoCampo(String tipoCampo) {
		this.tipoCampo = tipoCampo;
	}

	@Override
	public String toString() {
		return "PreguntasJson [IDSECCION=" + IDSECCION + ", IDPROCESOVIGENTE=" + IDPROCESOVIGENTE + ", IDPREGUNTA="
				+ IDPREGUNTA + ", DESCRIPCIONPREGUNTA=" + DESCRIPCIONPREGUNTA + ", backgroundPregunta="
				+ backgroundPregunta + ", idPreguntaResp=" + idPreguntaResp + ", tipoCampo=" + tipoCampo
				+ ", respuestas=" + respuestas + "]";
	}

}
