package mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Entity;

/**
 *
 * @author smartinez
 */

public class EmisoresCertificadoModel {
    private String IDEMISOR;
    private String NOMBREEMISOR;

    @JsonProperty("IDEMISOR")
    public String getIDEMISOR() {
        return IDEMISOR;
    }

    public void setIDEMISOR(String IDEMISOR) {
        this.IDEMISOR = IDEMISOR;
    }

    @JsonProperty("NOMBREEMISOR")
    public String getNOMBREEMISOR() {
        return NOMBREEMISOR;
    }

    public void setNOMBREEMISOR(String NOMBREEMISOR) {
        this.NOMBREEMISOR = NOMBREEMISOR;
    }

    @Override
    public String toString() {
        return "EmisoresCertificadoModel{" + "IDEMISOR=" + IDEMISOR + ", NOMBREEMISOR=" + NOMBREEMISOR + '}';
    }
    
    
}
