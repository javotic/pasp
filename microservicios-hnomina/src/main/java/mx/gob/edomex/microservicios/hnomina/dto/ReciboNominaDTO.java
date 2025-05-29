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
public class ReciboNominaDTO {

private String idServidor;
private String fechaI;
private String fechaF;
private String datoServidor;
private String percepciones;
private String deducciones;
private String totPercepciones;
private String totDeducciones;
private String totalNeto;
private String numRecibo;
private String numCuenta;
private String fechaAbono;
private String mensaje;

    public String getIdServidor() {
        return idServidor;
    }

    public void setIdServidor(String idServidor) {
        this.idServidor = idServidor;
    }

    public String getFechaI() {
        return fechaI;
    }

    public void setFechaI(String fechaI) {
        this.fechaI = fechaI;
    }

    public String getFechaF() {
        return fechaF;
    }

    public void setFechaF(String fechaF) {
        this.fechaF = fechaF;
    }

    public String getDatoServidor() {
        return datoServidor;
    }

    public void setDatoServidor(String datoServidor) {
        this.datoServidor = datoServidor;
    }

    public String getPercepciones() {
        return percepciones;
    }

    public void setPercepciones(String percepciones) {
        this.percepciones = percepciones;
    }

    public String getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(String deducciones) {
        this.deducciones = deducciones;
    }

    public String getTotPercepciones() {
        return totPercepciones;
    }

    public void setTotPercepciones(String totPercepciones) {
        this.totPercepciones = totPercepciones;
    }

    public String getTotDeducciones() {
        return totDeducciones;
    }

    public void setTotDeducciones(String totDeducciones) {
        this.totDeducciones = totDeducciones;
    }

    public String getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(String totalNeto) {
        this.totalNeto = totalNeto;
    }

    public String getNumRecibo() {
        return numRecibo;
    }

    public void setNumRecibo(String numRecibo) {
        this.numRecibo = numRecibo;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public String getFechaAbono() {
        return fechaAbono;
    }

    public void setFechaAbono(String fechaAbono) {
        this.fechaAbono = fechaAbono;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    

}
