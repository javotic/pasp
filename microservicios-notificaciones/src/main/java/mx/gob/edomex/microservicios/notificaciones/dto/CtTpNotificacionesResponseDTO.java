package mx.gob.edomex.microservicios.notificaciones.dto;

public class CtTpNotificacionesResponseDTO {
	
	private String cvservpublico;
	private String descnotificacion;
	
	
	
	
	public CtTpNotificacionesResponseDTO(String cvservpublico, String descnotificacion) {
		super();
		this.cvservpublico = cvservpublico;
		this.descnotificacion = descnotificacion;
	}




	public String getCvservpublico() {
		return cvservpublico;
	}




	public void setCvservpublico(String cvservpublico) {
		this.cvservpublico = cvservpublico;
	}




	public String getDescnotificacion() {
		return descnotificacion;
	}




	public void setDescnotificacion(String descnotificacion) {
		this.descnotificacion = descnotificacion;
	}
	
	
	
	

}
