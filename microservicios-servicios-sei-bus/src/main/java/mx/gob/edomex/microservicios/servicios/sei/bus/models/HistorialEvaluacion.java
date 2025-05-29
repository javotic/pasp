package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class HistorialEvaluacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IDPROCESOEVALUACIONKPI")
	@JsonProperty("CLAVEPROCESOEVALUACIONKPI")
	private String idProcesoEvaluacion;
	@JsonProperty("CLAVEPROCESOEKPI")
	private String claveProcesoKPI;
	@JsonProperty("NOMBREPROCESOEVALUACION")
	private String nombreProcesoEvaluacion;
	@JsonProperty("FECHAINICIOPROCESOKPI")
	private String fechaInicioProcesoKPI;
	@JsonProperty("FECHAFINPROCESOKPI")
	private String fechaFInProcesoKPI;
	@JsonProperty("IDESTATUS")
	private String idEstatus;

	public String getIdProcesoEvaluacion() {
		return idProcesoEvaluacion;
	}

	public void setIdProcesoEvaluacion(String idProcesoEvaluacion) {
		this.idProcesoEvaluacion = idProcesoEvaluacion;
	}

	public String getClaveProcesoKPI() {
		return claveProcesoKPI;
	}

	public void setClaveProcesoKPI(String claveProcesoKPI) {
		this.claveProcesoKPI = claveProcesoKPI;
	}

	public String getNombreProcesoEvaluacion() {
		return nombreProcesoEvaluacion;
	}

	public void setNombreProcesoEvaluacion(String nombreProcesoEvaluacion) {
		this.nombreProcesoEvaluacion = nombreProcesoEvaluacion;
	}

	public String getFechaInicioProcesoKPI() {
		return fechaInicioProcesoKPI;
	}

	public void setFechaInicioProcesoKPI(String fechaInicioProcesoKPI) {
		this.fechaInicioProcesoKPI = fechaInicioProcesoKPI;
	}

	public String getFechaFInProcesoKPI() {
		return fechaFInProcesoKPI;
	}

	public void setFechaFInProcesoKPI(String fechaFInProcesoKPI) {
		this.fechaFInProcesoKPI = fechaFInProcesoKPI;
	}

	public String getIdEstatus() {
		return idEstatus;
	}

	public void setIdEstatus(String idEstatus) {
		this.idEstatus = idEstatus;
	}

}
