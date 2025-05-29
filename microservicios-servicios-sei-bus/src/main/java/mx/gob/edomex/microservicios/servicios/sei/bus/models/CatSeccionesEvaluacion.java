package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "IDPROCESOVIGENTE", "IDSECCION", "NOMBRESECCION" })
public class CatSeccionesEvaluacion implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private String IDPROCESOVIGENTE;
    private String IDSECCION;
    private String NOMBRESECCION;
    
    @JsonProperty("IDPROCESOVIGENTE")
    public String getIDPROCESOVIGENTE() {
        return IDPROCESOVIGENTE;
    }

    public void setIDPROCESOVIGENTE(String IDPROCESOVIGENTE) {
        this.IDPROCESOVIGENTE = IDPROCESOVIGENTE;
    }

    @JsonProperty("IDSECCION")
    public String getIDSECCION() {
        return IDSECCION;
    }

    public void setIDSECCION(String IDSECCION) {
        this.IDSECCION = IDSECCION;
    }

    @JsonProperty("NOMBRESECCION")
    public String getNOMBRESECCION() {
        return NOMBRESECCION;
    }

    public void setNOMBRESECCION(String NOMBRESECCION) {
        this.NOMBRESECCION = NOMBRESECCION;
    }
    
}
