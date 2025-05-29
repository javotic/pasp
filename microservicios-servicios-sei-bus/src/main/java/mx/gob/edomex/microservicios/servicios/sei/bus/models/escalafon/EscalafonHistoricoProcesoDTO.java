package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author smartinez
 */
@JsonPropertyOrder({ "CLAVEPROCESOESCALAFONARIO", "NOMBREPROCESOESCALAFONARIO", "FECHAINICIOPROCESO", "FECHAFINPROCESO", "CLAVEPLAZA", "NOMBREPLAZA", 
                     "CLAVEPUESTO", "NOMBREPUESTO", "PUNTAJELOGRADO", "LUGARRANKING", "DICTAMEN", "DESCRIPCIONESTATUS" })
public class EscalafonHistoricoProcesoDTO {
    private String CLAVEPROCESOESCALAFONARIO;
    private String NOMBREPROCESOESCALAFONARIO;
    private String FECHAINICIOPROCESO;
    private String FECHAFINPROCESO;
    private String CLAVEPLAZA;
    private String NOMBREPLAZA;
    private String CLAVEPUESTO;
    private String NOMBREPUESTO;
    private String PUNTAJELOGRADO;
    private String LUGARRANKING;
    private String DICTAMEN;
    private String DESCRIPCIONESTATUS;

    @JsonProperty("CLAVEPROCESOESCALAFONARIO")
    public String getCLAVEPROCESOESCALAFONARIO() {
        return CLAVEPROCESOESCALAFONARIO;
    }

    public void setCLAVEPROCESOESCALAFONARIO(String CLAVEPROCESOESCALAFONARIO) {
        this.CLAVEPROCESOESCALAFONARIO = CLAVEPROCESOESCALAFONARIO;
    }

    @JsonProperty("NOMBREPROCESOESCALAFONARIO")
    public String getNOMBREPROCESOESCALAFONARIO() {
        return NOMBREPROCESOESCALAFONARIO;
    }

    public void setNOMBREPROCESOESCALAFONARIO(String NOMBREPROCESOESCALAFONARIO) {
        this.NOMBREPROCESOESCALAFONARIO = NOMBREPROCESOESCALAFONARIO;
    }

    @JsonProperty("FECHAINICIOPROCESO")
    public String getFECHAINICIOPROCESO() {
        return FECHAINICIOPROCESO;
    }

    public void setFECHAINICIOPROCESO(String FECHAINICIOPROCESO) {
        this.FECHAINICIOPROCESO = FECHAINICIOPROCESO;
    }

    @JsonProperty("FECHAFINPROCESO")
    public String getFECHAFINPROCESO() {
        return FECHAFINPROCESO;
    }

    public void setFECHAFINPROCESO(String FECHAFINPROCESO) {
        this.FECHAFINPROCESO = FECHAFINPROCESO;
    }

    @JsonProperty("CLAVEPLAZA")
    public String getCLAVEPLAZA() {
        return CLAVEPLAZA;
    }

    public void setCLAVEPLAZA(String CLAVEPLAZA) {
        this.CLAVEPLAZA = CLAVEPLAZA;
    }

    @JsonProperty("NOMBREPLAZA")
    public String getNOMBREPLAZA() {
        return NOMBREPLAZA;
    }

    public void setNOMBREPLAZA(String NOMBREPLAZA) {
        this.NOMBREPLAZA = NOMBREPLAZA;
    }

    @JsonProperty("CLAVEPUESTO")
    public String getCLAVEPUESTO() {
        return CLAVEPUESTO;
    }

    public void setCLAVEPUESTO(String CLAVEPUESTO) {
        this.CLAVEPUESTO = CLAVEPUESTO;
    }

    @JsonProperty("NOMBREPUESTO")
    public String getNOMBREPUESTO() {
        return NOMBREPUESTO;
    }

    public void setNOMBREPUESTO(String NOMBREPUESTO) {
        this.NOMBREPUESTO = NOMBREPUESTO;
    }

    @JsonProperty("PUNTAJELOGRADO")
    public String getPUNTAJELOGRADO() {
        return PUNTAJELOGRADO;
    }

    public void setPUNTAJELOGRADO(String PUNTAJELOGRADO) {
        this.PUNTAJELOGRADO = PUNTAJELOGRADO;
    }

    @JsonProperty("LUGARRANKING")
    public String getLUGARRANKING() {
        return LUGARRANKING;
    }

    public void setLUGARRANKING(String LUGARRANKING) {
        this.LUGARRANKING = LUGARRANKING;
    }

    @JsonProperty("DICTAMEN")
    public String getDICTAMEN() {
        return DICTAMEN;
    }

    public void setDICTAMEN(String DICTAMEN) {
        this.DICTAMEN = DICTAMEN;
    }

    @JsonProperty("DESCRIPCIONESTATUS")
    public String getDESCRIPCIONESTATUS() {
        return DESCRIPCIONESTATUS;
    }

    public void setDESCRIPCIONESTATUS(String DESCRIPCIONESTATUS) {
        this.DESCRIPCIONESTATUS = DESCRIPCIONESTATUS;
    }

}
