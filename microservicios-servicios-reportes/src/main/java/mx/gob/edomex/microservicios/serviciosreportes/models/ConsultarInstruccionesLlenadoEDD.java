package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ConsultarInstruccionesLlenadoEDD implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SerializedName("IDSECCION")
	private String idSeccion;
	@SerializedName("VALOR")
	private String valor;
	@SerializedName("DESCRIPCIONVALOR")
	private String descripcionValor;

	private String puntajeCriterioEvaluacion;
	private String descripcionCriterioEvaluacion;

	public String getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(String idSeccion) {
		this.idSeccion = idSeccion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDescripcionValor() {
		return descripcionValor;
	}

	public void setDescripcionValor(String descripcionValor) {
		this.descripcionValor = descripcionValor;
	}

	public String getPuntajeCriterioEvaluacion() {
		return puntajeCriterioEvaluacion;
	}

	public void setPuntajeCriterioEvaluacion(String puntajeCriterioEvaluacion) {
		this.puntajeCriterioEvaluacion = puntajeCriterioEvaluacion;
	}

	public String getDescripcionCriterioEvaluacion() {
		return descripcionCriterioEvaluacion;
	}

	public void setDescripcionCriterioEvaluacion(String descripcionCriterioEvaluacion) {
		this.descripcionCriterioEvaluacion = descripcionCriterioEvaluacion;
	}

}
