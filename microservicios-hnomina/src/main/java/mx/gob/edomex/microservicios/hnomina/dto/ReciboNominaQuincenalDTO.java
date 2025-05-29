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
public class ReciboNominaQuincenalDTO {

private String idServidor;
private String fechaI;
private String fechaF;
private String datoServidor;
private String datosPlazaP;
private String totPercepciones;
private String totDeducciones;
private String totalNeto;
private String datosDesglosePlazas;

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

    public String getDatosPlazaP() {
        return datosPlazaP;
    }

    public void setDatosPlazaP(String datosPlazaP) {
        this.datosPlazaP = datosPlazaP;
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

    public String getDatosDesglosePlazas() {
        return datosDesglosePlazas;
    }

    public void setDatosDesglosePlazas(String datosDesglosePlazas) {
        this.datosDesglosePlazas = datosDesglosePlazas;
    }


}
