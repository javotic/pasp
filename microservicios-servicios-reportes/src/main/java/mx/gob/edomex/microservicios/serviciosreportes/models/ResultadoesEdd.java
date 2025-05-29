package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class ResultadoesEdd implements Serializable {

	private static final long serialVersionUID = 1L;
	@SerializedName("TOTALPUNTOSOBTENIDOS")
	private String TOTALPUNTOSOBTENIDOS;
	@SerializedName("CALIFICACIONPARCIAL")
	private String CALIFICACIONPARCIAL;

	private List<ResultadosPreguntasEdd> RESULTADOSPREGUNTASEDDDTO;

	public String getTOTALPUNTOSOBTENIDOS() {
		return TOTALPUNTOSOBTENIDOS;
	}

	public void setTOTALPUNTOSOBTENIDOS(String tOTALPUNTOSOBTENIDOS) {
		TOTALPUNTOSOBTENIDOS = tOTALPUNTOSOBTENIDOS;
	}

	public String getCALIFICACIONPARCIAL() {
		return CALIFICACIONPARCIAL;
	}

	public void setCALIFICACIONPARCIAL(String cALIFICACIONPARCIAL) {
		CALIFICACIONPARCIAL = cALIFICACIONPARCIAL;
	}

	public List<ResultadosPreguntasEdd> getRESULTADOSPREGUNTASEDDDTO() {
		return RESULTADOSPREGUNTASEDDDTO;
	}

	public void setRESULTADOSPREGUNTASEDDDTO(List<ResultadosPreguntasEdd> rESULTADOSPREGUNTASEDDDTO) {
		RESULTADOSPREGUNTASEDDDTO = rESULTADOSPREGUNTASEDDDTO;
	}

}
