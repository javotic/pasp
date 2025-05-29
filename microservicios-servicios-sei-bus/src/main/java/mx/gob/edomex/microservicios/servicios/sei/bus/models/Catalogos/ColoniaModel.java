package mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author smartinez
 */
@JsonPropertyOrder({ "IDMUNICIPIOCIUDAD", "NOMBREMUNICIPIOCIIUDAD", "IDCOLONIA", "NOMBRECOLONIA"})
public class ColoniaModel {
    private String IDMUNICIPIOCIUDAD;
    private String NOMBREMUNICIPIOCIIUDAD;
    private String IDCOLONIA;
    private String NOMBRECOLONIA;

    @JsonProperty("IDMUNICIPIOCIUDAD")
    public String getIDMUNICIPIOCIUDAD() {
        return IDMUNICIPIOCIUDAD;
    }

    public void setIDMUNICIPIOCIUDAD(String IDMUNICIPIOCIUDAD) {
        this.IDMUNICIPIOCIUDAD = IDMUNICIPIOCIUDAD;
    }

    @JsonProperty("NOMBREMUNICIPIOCIIUDAD")
    public String getNOMBREMUNICIPIOCIIUDAD() {
        return NOMBREMUNICIPIOCIIUDAD;
    }

    public void setNOMBREMUNICIPIOCIIUDAD(String NOMBREMUNICIPIOCIIUDAD) {
        this.NOMBREMUNICIPIOCIIUDAD = NOMBREMUNICIPIOCIIUDAD;
    }

    @JsonProperty("IDCOLONIA")
    public String getIDCOLONIA() {
        return IDCOLONIA;
    }

    public void setIDCOLONIA(String IDCOLONIA) {
        this.IDCOLONIA = IDCOLONIA;
    }

    @JsonProperty("NOMBRECOLONIA")
    public String getNOMBRECOLONIA() {
        return NOMBRECOLONIA;
    }

    public void setNOMBRECOLONIA(String NOMBRECOLONIA) {
        this.NOMBRECOLONIA = NOMBRECOLONIA;
    }
    
    
}
