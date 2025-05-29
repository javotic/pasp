/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author smartinez
 */

public class Roles {
    
    private int IDROL;
    private String DSROL;
    private String BOACTIVO;
    private List<Menu> MENU;

    @JsonProperty("IDROL")
    public int getIDROL() {
        return IDROL;
    }

    public void setIDROL(int IDROL) {
        this.IDROL = IDROL;
    }

    @JsonProperty("DSROL")
    public String getDSROL() {
        return DSROL;
    }

    public void setDSROL(String DSROL) {
        this.DSROL = DSROL;
    }

    @JsonProperty("BOACTIVO")
    public String getBOACTIVO() {
        return BOACTIVO;
    }

    public void setBOACTIVO(String BOACTIVO) {
        this.BOACTIVO = BOACTIVO;
    }

    @JsonProperty("MENU")
    public List<Menu> getMENU() {
        return MENU;
    }

    public void setMENU(List<Menu> MENU) {
        this.MENU = MENU;
    }
    
   
}
