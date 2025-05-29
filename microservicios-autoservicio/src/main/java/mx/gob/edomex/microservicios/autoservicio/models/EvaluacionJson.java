package mx.gob.edomex.microservicios.autoservicio.models;

import java.io.Serializable;
import java.util.List;




public class EvaluacionJson  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private List<EvaluacionDesempenioJson> SeccionesEddPR;

	public List<EvaluacionDesempenioJson> getSeccionesEddPR() {
		return SeccionesEddPR;
	}

	public void setSeccionesEddPR(List<EvaluacionDesempenioJson> seccionesEddPR) {
		SeccionesEddPR = seccionesEddPR;
	}

	@Override
	public String toString() {
		return "EvaluacionJson [SeccionesEddPR=" + SeccionesEddPR + "]";
	}
	
}
