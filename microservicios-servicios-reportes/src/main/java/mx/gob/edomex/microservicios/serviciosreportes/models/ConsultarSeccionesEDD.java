package mx.gob.edomex.microservicios.serviciosreportes.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class ConsultarSeccionesEDD implements Serializable {
	private static final long serialVersionUID = 1L;
	@SerializedName("IDPROCESOVIGENTE")
	private String idProcesoVigente;
	@SerializedName("IDSECCION")
	private String idSeccion;
	@SerializedName("NOMBRESECCION")
	private String nombreSeccion;
	public String getIdProcesoVigente() {
		return idProcesoVigente;
	}
	public void setIdProcesoVigente(String idProcesoVigente) {
		this.idProcesoVigente = idProcesoVigente;
	}
	public String getIdSeccion() {
		return idSeccion;
	}
	public void setIdSeccion(String idSeccion) {
		this.idSeccion = idSeccion;
	}
	public String getNombreSeccion() {
		return nombreSeccion;
	}
	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}

	
	
}
