package mx.gob.edomex.microservicios.servicios.sei.bus.models.Consultas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author smartinez
 */
@JsonPropertyOrder({ "IDSOLICITUDCONSTANCIANOADEUDO", "JUSTIFICACION", "FECHASOLICITUD", "RESPUESTA", "ESTATUS", "GESTORADMINISTRATIVO", "FECHARESPUESTA", "IDDOCUMENTO"})
public class ConstanciaNoAdeudoModel {
    private String IDSOLICITUDCONSTANCIANOADEUDO;
    private String JUSTIFICACION;
    private String FECHASOLICITUD;
    private String RESPUESTA;
    private String ESTATUS;
    private String GESTORADMINISTRATIVO;
    private String FECHARESPUESTA;
    private String IDDOCUMENTO;  

    @JsonProperty("IDSOLICITUDCONSTANCIANOADEUDO")
    public String getIDSOLICITUDCONSTANCIANOADEUDO() {
        return IDSOLICITUDCONSTANCIANOADEUDO;
    }

    public void setIDSOLICITUDCONSTANCIANOADEUDO(String IDSOLICITUDCONSTANCIANOADEUDO) {
        this.IDSOLICITUDCONSTANCIANOADEUDO = IDSOLICITUDCONSTANCIANOADEUDO;
    }

    @JsonProperty("JUSTIFICACION")
    public String getJUSTIFICACION() {
        return JUSTIFICACION;
    }

    public void setJUSTIFICACION(String JUSTIFICACION) {
        this.JUSTIFICACION = JUSTIFICACION;
    }

    @JsonProperty("FECHASOLICITUD")
    public String getFECHASOLICITUD() {
        return FECHASOLICITUD;
    }

    public void setFECHASOLICITUD(String FECHASOLICITUD) {
        this.FECHASOLICITUD = FECHASOLICITUD;
    }

    @JsonProperty("RESPUESTA")
    public String getRESPUESTA() {
        return RESPUESTA;
    }

    public void setRESPUESTA(String RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }

    @JsonProperty("ESTATUS")
    public String getESTATUS() {
        return ESTATUS;
    }

    public void setESTATUS(String ESTATUS) {
        this.ESTATUS = ESTATUS;
    }

    @JsonProperty("GESTORADMINISTRATIVO")
    public String getGESTORADMINISTRATIVO() {
        return GESTORADMINISTRATIVO;
    }

    public void setGESTORADMINISTRATIVO(String GESTORADMINISTRATIVO) {
        this.GESTORADMINISTRATIVO = GESTORADMINISTRATIVO;
    }

    @JsonProperty("FECHARESPUESTA")
    public String getFECHARESPUESTA() {
        return FECHARESPUESTA;
    }

    public void setFECHARESPUESTA(String FECHARESPUESTA) {
        this.FECHARESPUESTA = FECHARESPUESTA;
    }

    @JsonProperty("IDDOCUMENTO")
    public String getIDDOCUMENTO() {
        return IDDOCUMENTO;
    }

    public void setIDDOCUMENTO(String IDDOCUMENTO) {
        this.IDDOCUMENTO = IDDOCUMENTO;
    }
    
    
}
