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

public class Menu {
    private int IDMENU;
    private String DSMENU;
    private String BOACTIVO;
    private int NOORDEN;
    private String DSURL;
    private String DSICONO;
    private String DSTOOLTIP;
    private String DSCOLOR;

    @JsonProperty("IDMENU")
    public int getIDMENU() {
        return IDMENU;
    }

    public void setIDMENU(int IDMENU) {
        this.IDMENU = IDMENU;
    }

    @JsonProperty("DSMENU")
    public String getDSMENU() {
        return DSMENU;
    }

    public void setDSMENU(String DSMENU) {
        this.DSMENU = DSMENU;
    }

    @JsonProperty("BOACTIVO")
    public String getBOACTIVO() {
        return BOACTIVO;
    }

    public void setBOACTIVO(String BOACTIVO) {
        this.BOACTIVO = BOACTIVO;
    }

    @JsonProperty("NOORDEN")
    public int getNOORDEN() {
        return NOORDEN;
    }

    public void setNOORDEN(int NOORDEN) {
        this.NOORDEN = NOORDEN;
    }

    @JsonProperty("DSURL")
    public String getDSURL() {
        return DSURL;
    }

    public void setDSURL(String DSURL) {
        this.DSURL = DSURL;
    }

    @JsonProperty("DSICONO")
    public String getDSICONO() {
        return DSICONO;
    }

    public void setDSICONO(String DSICONO) {
        this.DSICONO = DSICONO;
    }

    @JsonProperty("DSTOOLTIP")
    public String getDSTOOLTIP() {
        return DSTOOLTIP;
    }

    public void setDSTOOLTIP(String DSTOOLTIP) {
        this.DSTOOLTIP = DSTOOLTIP;
    }
    
    @JsonProperty("DSCOLOR")
    public String getDSCOLOR() {
        return DSCOLOR;
    }
    
    public void setDSCOLOR(String DSCOLOR) {
        this.DSCOLOR = DSCOLOR;
    }
    
}
