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
public class Usuarios {

    private String ID_PERSON;
    private Roles ROL;
    private String BOACTIVO;
    private String NOMBRE;
    private String APELLIDO_P;
    private String APELLIDO_M;
    

    @JsonProperty("ID_PERSON")
    public String getID_PERSON() {
        return ID_PERSON;
    }

    public void setID_PERSON(String ID_PERSON) {
        this.ID_PERSON = ID_PERSON;
    }

    @JsonProperty("ROL")
    public Roles getROL() {
        return ROL;
    }

    public void setROL(Roles ROL) {
        this.ROL = ROL;
    }

    @JsonProperty("BOACTIVO")
    public String getBOACTIVO() {
        return BOACTIVO;
    }

    public void setBOACTIVO(String BOACTIVO) {
        this.BOACTIVO = BOACTIVO;
    }

    @JsonProperty("NOMBRE")
    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    @JsonProperty("APELLIDO_P")
    public String getAPELLIDO_P() {
        return APELLIDO_P;
    }

    public void setAPELLIDO_P(String APELLIDO_P) {
        this.APELLIDO_P = APELLIDO_P;
    }

    @JsonProperty("APELLIDO_M")
    public String getAPELLIDO_M() {
        return APELLIDO_M;
    }

    public void setAPELLIDO_M(String APELLIDO_M) {
        this.APELLIDO_M = APELLIDO_M;
    }
    
}
