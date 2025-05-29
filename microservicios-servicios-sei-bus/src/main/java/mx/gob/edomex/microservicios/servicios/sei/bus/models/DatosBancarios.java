/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author smartinez
 */
public class DatosBancarios {

private String SERVIDORPUBLICO;
private String NMSERVIDORPUBLICO;
private String NUMEROCUENTA;
private String IDBANCO;
private String NMBANCO;
private String IDTPPAGO;
private String NMTPPAGO;

    @JsonProperty("SERVIDORPUBLICO")
    public String getSERVIDORPUBLICO() {
        return SERVIDORPUBLICO;
    }

    public void setSERVIDORPUBLICO(String SERVIDORPUBLICO) {
        this.SERVIDORPUBLICO = SERVIDORPUBLICO;
    }

    @JsonProperty("NMSERVIDORPUBLICO")
    public String getNMSERVIDORPUBLICO() {
        return NMSERVIDORPUBLICO;
    }

    public void setNMSERVIDORPUBLICO(String NMSERVIDORPUBLICO) {
        this.NMSERVIDORPUBLICO = NMSERVIDORPUBLICO;
    }

    @JsonProperty("NUMEROCUENTA")
    public String getNUMEROCUENTA() {
        return NUMEROCUENTA;
    }

    public void setNUMEROCUENTA(String NUMEROCUENTA) {
        this.NUMEROCUENTA = NUMEROCUENTA;
    }

    @JsonProperty("IDBANCO")
    public String getIDBANCO() {
        return IDBANCO;
    }

    public void setIDBANCO(String IDBANCO) {
        this.IDBANCO = IDBANCO;
    }

    @JsonProperty("NMBANCO")
    public String getNMBANCO() {
        return NMBANCO;
    }

    public void setNMBANCO(String NMBANCO) {
        this.NMBANCO = NMBANCO;
    }

    @JsonProperty("IDTPPAGO")
    public String getIDTPPAGO() {
        return IDTPPAGO;
    }

    public void setIDTPPAGO(String IDTPPAGO) {
        this.IDTPPAGO = IDTPPAGO;
    }

    @JsonProperty("NMTPPAGO")
    public String getNMTPPAGO() {
        return NMTPPAGO;
    }

    public void setNMTPPAGO(String NMTPPAGO) {
        this.NMTPPAGO = NMTPPAGO;
    }
    
}
