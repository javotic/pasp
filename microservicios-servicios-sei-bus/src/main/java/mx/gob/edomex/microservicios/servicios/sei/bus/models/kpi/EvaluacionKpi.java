package mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi;

import java.io.Serializable;

public class EvaluacionKpi implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String idProcesovigente;
	private String idProcesoKPI;
	private String nombreProcesoKPI;
	private String idServidorPublico;
	private String nombreServidorPublico;

	public String getIdProcesovigente() {
		return idProcesovigente;
	}

	public void setIdProcesovigente(String idProcesovigente) {
		this.idProcesovigente = idProcesovigente;
	}

	public String getIdProcesoKPI() {
		return idProcesoKPI;
	}

	public void setIdProcesoKPI(String idProcesoKPI) {
		this.idProcesoKPI = idProcesoKPI;
	}

	public String getNombreProcesoKPI() {
		return nombreProcesoKPI;
	}

	public void setNombreProcesoKPI(String nombreProcesoKPI) {
		this.nombreProcesoKPI = nombreProcesoKPI;
	}

	public String getIdServidorPublico() {
		return idServidorPublico;
	}

	public void setIdServidorPublico(String idServidorPublico) {
		this.idServidorPublico = idServidorPublico;
	}

	public String getNombreServidorPublico() {
		return nombreServidorPublico;
	}

	public void setNombreServidorPublico(String nombreServidorPublico) {
		this.nombreServidorPublico = nombreServidorPublico;
	}

}
