/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author smartinez
 */
@JsonPropertyOrder({ "IDSOLICITUDHISTORIALLABORAL", "RESPUESTA", "FECHASOLICITUD", "DETALLERESPUESTA", "ESTATUS", "GESTORADMINISTRATIVO", "FECHARESPUESTA", "IDDOCUMENTO" })
public class SolicitudHistorialLaboralModel {
    
    private String IDSOLICITUDHISTORIALLABORAL;
    private String RESPUESTA;
    private String FECHASOLICITUD;
    private String DETALLERESPUESTA;
    private String ESTATUS;
    private String GESTORADMINISTRATIVO;
    private String FECHARESPUESTA;
    private String IDDOCUMENTO;   

    @JsonProperty("IDSOLICITUDHISTORIALLABORAL")
    public String getIDSOLICITUDHISTORIALLABORAL() {
        return IDSOLICITUDHISTORIALLABORAL;
    }

    public void setIDSOLICITUDHISTORIALLABORAL(String IDSOLICITUDHISTORIALLABORAL) {
        this.IDSOLICITUDHISTORIALLABORAL = IDSOLICITUDHISTORIALLABORAL;
    }

    @JsonProperty("RESPUESTA")
    public String getRESPUESTA() {
        return RESPUESTA;
    }

    public void setRESPUESTA(String RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }

    @JsonProperty("FECHASOLICITUD")    
    public String getFECHASOLICITUD() {
        return FECHASOLICITUD;
    }

    public void setFECHASOLICITUD(String FECHASOLICITUD) {
        this.FECHASOLICITUD = FECHASOLICITUD;
    }

    @JsonProperty("DETALLERESPUESTA")  
    public String getDETALLERESPUESTA() {
        return DETALLERESPUESTA;
    }

    public void setDETALLERESPUESTA(String DETALLERESPUESTA) {
        this.DETALLERESPUESTA = DETALLERESPUESTA;
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

    @Override
    public String toString() {
        return "SolicitudHistorialLaboralModel{" + "IDSOLICITUDHISTORIALLABORAL=" + IDSOLICITUDHISTORIALLABORAL + ", RESPUESTA=" + RESPUESTA + ", FECHASOLICITUD=" + FECHASOLICITUD + ", DETALLERESPUESTA=" + DETALLERESPUESTA + ", ESTATUS=" + ESTATUS + ", GESTORADMINISTRATIVO=" + GESTORADMINISTRATIVO + ", FECHARESPUESTA=" + FECHARESPUESTA + ", IDDOCUMENTO=" + IDDOCUMENTO + '}';
    }
    
}
