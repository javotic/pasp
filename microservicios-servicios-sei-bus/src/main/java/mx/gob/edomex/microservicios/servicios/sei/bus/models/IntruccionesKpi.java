package mx.gob.edomex.microservicios.servicios.sei.bus.models;


import com.fasterxml.jackson.annotation.JsonProperty;

public class IntruccionesKpi {

	@JsonProperty("INSTRUCCIONES")
	private String instrucciones;
	@JsonProperty("TIEMPOLIMITE")
	private String tiempoLimite;
	@JsonProperty("TIEMPO")
	private String tiempo;
	public String getInstrucciones() {
		return instrucciones;
	}
	public void setInstrucciones(String instrucciones) {
		this.instrucciones = instrucciones;
	}
	public String getTiempoLimite() {
		return tiempoLimite;
	}
	public void setTiempoLimite(String tiempoLimite) {
		this.tiempoLimite = tiempoLimite;
	}
	public String getTiempo() {
		return tiempo;
	}
	public void setTiempo(String tiempo) {
		this.tiempo = tiempo;
	}
	
	
}
