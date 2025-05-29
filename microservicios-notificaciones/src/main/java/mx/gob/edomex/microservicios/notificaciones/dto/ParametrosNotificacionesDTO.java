package mx.gob.edomex.microservicios.notificaciones.dto;

public class ParametrosNotificacionesDTO {
	
	private String url;
	private String idioma;
	
	
	
	
	public ParametrosNotificacionesDTO(String url, String idioma) {
		super();
		this.url = url;
		this.idioma = idioma;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

}
