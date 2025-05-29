package mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author smartinez
 */
@JsonPropertyOrder({ "NOMBRECERTIFICADO", "IDTIPOCERTIFICADO"})
public class TipoCertificadoModel {
    private String NOMBRECERTIFICADO;
    private String IDTIPOCERTIFICADO;

    @JsonProperty("NOMBRECERTIFICADO")
    public String getNOMBRECERTIFICADO() {
        return NOMBRECERTIFICADO;
    }

    public void setNOMBRECERTIFICADO(String NOMBRECERTIFICADO) {
        this.NOMBRECERTIFICADO = NOMBRECERTIFICADO;
    }

    @JsonProperty("IDTIPOCERTIFICADO")
    public String getIDTIPOCERTIFICADO() {
        return IDTIPOCERTIFICADO;
    }

    public void setIDTIPOCERTIFICADO(String IDTIPOCERTIFICADO) {
        this.IDTIPOCERTIFICADO = IDTIPOCERTIFICADO;
    }
    
    
}
