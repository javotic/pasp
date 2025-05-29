package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
public class EvaluacionKpiVigente implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	@Id
	@JsonProperty("CLAVEPROCESOEVALUACIONKPI")
	private String claveProcesoEvaluacionKpi;

	@JsonProperty("NOMBREPROCESOEVALUACIONKPI")
	private String nombreProcesoEvaluacionKpi;

	@JsonProperty("FECHAINICIOPROCESOGENERAL")
	private String fechaInicioProcesoGeneral;

	@JsonProperty("FECHAFINPROCESOGENERAL")
	private String fechaFinProcesoGeneral;

	@JsonProperty("DESCRIPCIONPROCESOKPIVIGENTE")
	private String descripcionProcesoKpiVigente;
	
	
	@JsonProperty("IDPROCESOVIGENTE")
	private String idProcesoVigente;

	public String getClaveProcesoEvaluacionKpi() {
		return claveProcesoEvaluacionKpi;
	}

	public void setClaveProcesoEvaluacionKpi(String claveProcesoEvaluacionKpi) {
		this.claveProcesoEvaluacionKpi = claveProcesoEvaluacionKpi;
	}

	public String getNombreProcesoEvaluacionKpi() {
		return nombreProcesoEvaluacionKpi;
	}

	public void setNombreProcesoEvaluacionKpi(String nombreProcesoEvaluacionKpi) {
		this.nombreProcesoEvaluacionKpi = nombreProcesoEvaluacionKpi;
	}

	public String getFechaInicioProcesoGeneral() {
		return fechaInicioProcesoGeneral;
	}

	public void setFechaInicioProcesoGeneral(String fechaInicioProcesoGeneral) {
		this.fechaInicioProcesoGeneral = fechaInicioProcesoGeneral;
	}

	public String getFechaFinProcesoGeneral() {
		return fechaFinProcesoGeneral;
	}

	public void setFechaFinProcesoGeneral(String fechaFinProcesoGeneral) {
		this.fechaFinProcesoGeneral = fechaFinProcesoGeneral;
	}

	public String getDescripcionProcesoKpiVigente() {
		return descripcionProcesoKpiVigente;
	}

	public void setDescripcionProcesoKpiVigente(String descripcionProcesoKpiVigente) {
		this.descripcionProcesoKpiVigente = descripcionProcesoKpiVigente;
	}

	public String getIdProcesoVigente() {
		return idProcesoVigente;
	}

	public void setIdProcesoVigente(String idProcesoVigente) {
		this.idProcesoVigente = idProcesoVigente;
	}

}
