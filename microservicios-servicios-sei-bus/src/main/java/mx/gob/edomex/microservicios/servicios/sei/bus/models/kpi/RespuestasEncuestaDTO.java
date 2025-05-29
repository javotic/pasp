package mx.gob.edomex.microservicios.servicios.sei.bus.models.kpi;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class RespuestasEncuestaDTO implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private String tiempo;
    private String idPregunta;
    private String idRespuesta;
    private String idServidorPublico;
    private String idProcesoVigente;
    

    @JsonProperty("tiempo")
    public String getTiempo() {
        return tiempo;
    }

    public void setTiempo(String tiempo) {
        this.tiempo = tiempo;
    }

    @JsonProperty("idPregunta")
    public String getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(String idPregunta) {
        this.idPregunta = idPregunta;
    }

    @JsonProperty("idRespuesta")
    public String getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(String idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    @JsonProperty("idServidorPublico")
    public String getIdServidorPublico() {
        return idServidorPublico;
    }

    public void setIdServidorPublico(String idServidorPublico) {
        this.idServidorPublico = idServidorPublico;
    }

    @JsonProperty("idProcesoVigente")
    public String getIdProcesoVigente() {
        return idProcesoVigente;
    }

    public void setIdProcesoVigente(String idProcesoVigente) {
        this.idProcesoVigente = idProcesoVigente;
    }
	
    
    
}
