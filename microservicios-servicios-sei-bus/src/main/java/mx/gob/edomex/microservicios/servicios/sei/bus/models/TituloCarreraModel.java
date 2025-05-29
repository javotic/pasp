
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author smartinez
 */

@JsonPropertyOrder({ "IDTITULOCARRERA", "NOMBRECTITULOCARRERA"})
public class TituloCarreraModel {
    
    private String IDTITULOCARRERA;
    private String NOMBRECTITULOCARRERA;

    @JsonProperty("IDTITULOCARRERA")
    public String getIDTITULOCARRERA() {
        return IDTITULOCARRERA;
    }

    public void setIDTITULOCARRERA(String IDTITULOCARRERA) {
        this.IDTITULOCARRERA = IDTITULOCARRERA;
    }

    @JsonProperty("NOMBRECTITULOCARRERA")
    public String getNOMBRECTITULOCARRERA() {
        return NOMBRECTITULOCARRERA;
    }

    public void setNOMBRECTITULOCARRERA(String NOMBRECTITULOCARRERA) {
        this.NOMBRECTITULOCARRERA = NOMBRECTITULOCARRERA;
    }
    
    
}
