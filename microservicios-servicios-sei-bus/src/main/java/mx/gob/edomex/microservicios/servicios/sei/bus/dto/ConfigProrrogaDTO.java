package mx.gob.edomex.microservicios.servicios.sei.bus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

public class ConfigProrrogaDTO implements Serializable {

    private String NODIAS;
    private String FCACTUALIZACION;
    private String DSUSUARIO;

    @JsonProperty("NODIAS")
    public String getNODIAS() {
        return NODIAS;
    }

    public void setNODIAS(String NODIAS) {
        this.NODIAS = NODIAS;
    }

    @JsonProperty("FCACTUALIZACION")
    public String getFCACTUALIZACION() {
        return FCACTUALIZACION;
    }

    public void setFCACTUALIZACION(String FCACTUALIZACION) {
        this.FCACTUALIZACION = FCACTUALIZACION;
    }

    @JsonProperty("DSUSUARIO")
    public String getDSUSUARIO() {
        return DSUSUARIO;
    }

    public void setDSUSUARIO(String DSUSUARIO) {
        this.DSUSUARIO = DSUSUARIO;
    }
        
        

}
