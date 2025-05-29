
package mx.gob.edomex.microservicios.servicios.sei.bus.models.Consultas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author smartinez
 */
@JsonPropertyOrder({ "CLAVEINCIDENCIA", "NOMBREINCIDENCIA", "NUMEROINCIDENCIA", "FECHAINICIO", "FECHAFIN", "NUMEROUNIDADES", "NOMBREPLAZA", "NUMEROPLAZA"})
public class IncidenciasTiempoModel {
    private String CLAVEINCIDENCIA;
    private String NOMBREINCIDENCIA;
    private String NUMEROINCIDENCIA;
    private String FECHAINICIO;
    private String FECHAFIN;
    private String NUMEROUNIDADES;
    private String NOMBREPLAZA;
    private String NUMEROPLAZA;

    @JsonProperty("CLAVEINCIDENCIA")
    public String getCLAVEINCIDENCIA() {
        return CLAVEINCIDENCIA;
    }

    public void setCLAVEINCIDENCIA(String CLAVEINCIDENCIA) {
        this.CLAVEINCIDENCIA = CLAVEINCIDENCIA;
    }

    @JsonProperty("NOMBREINCIDENCIA")
    public String getNOMBREINCIDENCIA() {
        return NOMBREINCIDENCIA;
    }

    public void setNOMBREINCIDENCIA(String NOMBREINCIDENCIA) {
        this.NOMBREINCIDENCIA = NOMBREINCIDENCIA;
    }

    @JsonProperty("NUMEROINCIDENCIA")
    public String getNUMEROINCIDENCIA() {
        return NUMEROINCIDENCIA;
    }

    public void setNUMEROINCIDENCIA(String NUMEROINCIDENCIA) {
        this.NUMEROINCIDENCIA = NUMEROINCIDENCIA;
    }

    @JsonProperty("FECHAINICIO")
    public String getFECHAINICIO() {
        return FECHAINICIO;
    }

    public void setFECHAINICIO(String FECHAINICIO) {
        this.FECHAINICIO = FECHAINICIO;
    }

    @JsonProperty("FECHAFIN")
    public String getFECHAFIN() {
        return FECHAFIN;
    }

    public void setFECHAFIN(String FECHAFIN) {
        this.FECHAFIN = FECHAFIN;
    }

    @JsonProperty("NUMEROUNIDADES")
    public String getNUMEROUNIDADES() {
        return NUMEROUNIDADES;
    }

    public void setNUMEROUNIDADES(String NUMEROUNIDADES) {
        this.NUMEROUNIDADES = NUMEROUNIDADES;
    }

    @JsonProperty("NOMBREPLAZA")
    public String getNOMBREPLAZA() {
        return NOMBREPLAZA;
    }

    public void setNOMBREPLAZA(String NOMBREPLAZA) {
        this.NOMBREPLAZA = NOMBREPLAZA;
    }

    @JsonProperty("NUMEROPLAZA")
    public String getNUMEROPLAZA() {
        return NUMEROPLAZA;
    }

    public void setNUMEROPLAZA(String NUMEROPLAZA) {
        this.NUMEROPLAZA = NUMEROPLAZA;
    }
    
    
}
