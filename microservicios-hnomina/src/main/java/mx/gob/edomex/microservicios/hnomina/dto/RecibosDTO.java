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
public class RecibosDTO {

    private String idServidor;
    private String nServidor;
    private String idPaga;
    private String nPaga;
    private String fechaInicio;
    private String fechaFin;
    private boolean cancelado;

    @JsonProperty("idServidor")
    public String getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(String idServidor) {
        this.idServidor = idServidor;
    }

    @JsonProperty("nServidor")
    public String getnServidor() {
        return nServidor;
    }

    public void setnServidor(String nServidor) {
        this.nServidor = nServidor;
    }

    @JsonProperty("idPaga")
    public String getIdPaga() {
        return idPaga;
    }

    public void setIdPaga(String idPaga) {
        this.idPaga = idPaga;
    }

    @JsonProperty("nPaga")
    public String getnPaga() {
        return nPaga;
    }

    public void setnPaga(String nPaga) {
        this.nPaga = nPaga;
    }

    @JsonProperty("fechaInicio")
    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @JsonProperty("fechaFin")
    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    @JsonProperty("cancelado")
    public boolean isCancelado() {
        return cancelado;
    }

    public void setCancelado(boolean cancelado) {
        this.cancelado = cancelado;
    }
    
    

}
