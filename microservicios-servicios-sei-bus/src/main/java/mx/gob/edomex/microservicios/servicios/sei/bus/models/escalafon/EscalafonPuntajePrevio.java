package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class EscalafonPuntajePrevio implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	@Id
	@JsonProperty("CAPACITACION")
	private String puntajeCapacitacion;
	@JsonProperty("ESCOLARIDAD")
	private String puntajeEscolaridad;
	@JsonProperty("EFICIENCIA")
	private String puntajeEficiencia;
	@JsonProperty("ANTIGUEDAD")
	private String puntajeAntiguedad;
	@JsonProperty("DEMERITOS")
	private String puntajeDemeritos;
	@JsonProperty("TOTAL")
	private String total;
        private String puntajeSubTotal;

	public String getPuntajeCapacitacion() {
		return puntajeCapacitacion;
	}

	public void setPuntajeCapacitacion(String puntajeCapacitacion) {
		this.puntajeCapacitacion = puntajeCapacitacion;
	}

	public String getPuntajeEscolaridad() {
		return puntajeEscolaridad;
	}

	public void setPuntajeEscolaridad(String puntajeEscolaridad) {
		this.puntajeEscolaridad = puntajeEscolaridad;
	}

	public String getPuntajeEficiencia() {
		return puntajeEficiencia;
	}

	public void setPuntajeEficiencia(String puntajeEficiencia) {
		this.puntajeEficiencia = puntajeEficiencia;
	}

	public String getPuntajeAntiguedad() {
		return puntajeAntiguedad;
	}

	public void setPuntajeAntiguedad(String puntajeAntiguedad) {
		this.puntajeAntiguedad = puntajeAntiguedad;
	}

	public String getPuntajeDemeritos() {
		return puntajeDemeritos;
	}

	public void setPuntajeDemeritos(String puntajeDemeritos) {
		this.puntajeDemeritos = puntajeDemeritos;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

        public String getPuntajeSubTotal() {
            return puntajeSubTotal;
        }

        public void setPuntajeSubTotal(String puntajeSubTotal) {
            this.puntajeSubTotal = puntajeSubTotal;
        }    
}
