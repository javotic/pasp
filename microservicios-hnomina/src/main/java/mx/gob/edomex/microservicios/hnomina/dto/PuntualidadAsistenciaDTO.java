/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.hnomina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author smartinez
 */
public class PuntualidadAsistenciaDTO {

private String IDINCIDENCIA;
private String NMINCIDENCIA;
private String FECHA;
private String FECHAAPLICA;
private Integer UNIDADES;
private String TPUNIDADES;
private Integer IMPORTE;

    @JsonProperty("IDINCIDENCIA")
    public String getIDINCIDENCIA() {
        return IDINCIDENCIA;
    }

    public void setIDINCIDENCIA(String IDINCIDENCIA) {
        this.IDINCIDENCIA = IDINCIDENCIA;
    }

    @JsonProperty("NMINCIDENCIA")
    public String getNMINCIDENCIA() {
        return NMINCIDENCIA;
    }

    public void setNMINCIDENCIA(String NMINCIDENCIA) {
        this.NMINCIDENCIA = NMINCIDENCIA;
    }

    @JsonProperty("FECHA")
    public String getFECHA() {
        return FECHA;
    }

    public void setFECHA(String FECHA) {
        this.FECHA = FECHA;
    }

    @JsonProperty("FECHAAPLICA")
    public String getFECHAAPLICA() {
        return FECHAAPLICA;
    }

    public void setFECHAAPLICA(String FECHAAPLICA) {
        this.FECHAAPLICA = FECHAAPLICA;
    }

    @JsonProperty("UNIDADES")
    public Integer getUNIDADES() {
        return UNIDADES;
    }

    public void setUNIDADES(Integer UNIDADES) {
        this.UNIDADES = UNIDADES;
    }

    @JsonProperty("TPUNIDADES")
    public String getTPUNIDADES() {
        return TPUNIDADES;
    }

    public void setTPUNIDADES(String TPUNIDADES) {
        this.TPUNIDADES = TPUNIDADES;
    }

    @JsonProperty("IMPORTE")
    public Integer getIMPORTE() {
        return IMPORTE;
    }

    public void setIMPORTE(Integer IMPORTE) {
        this.IMPORTE = IMPORTE;
    }
    
    
    
}
