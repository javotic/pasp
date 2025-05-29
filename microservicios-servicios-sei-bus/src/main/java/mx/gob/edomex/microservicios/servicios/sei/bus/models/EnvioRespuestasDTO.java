/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author smartinez
 */
public class EnvioRespuestasDTO implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private List<ItemEnvioRespuestasDTO> COMPETENCIASAPTITUDINALES;
    private List<ItemEnvioRespuestasDTO> COMPETENCIASSOCIOPERSONALES;
    private List<ItemEnvioRespuestasDTO> DEMERITOS;
    private String EVCONCLUIDA;
    
   /* 
    private ItemEnvioRespuestasDTO COMPETENCIASAPTITUDINALES;

    
    @JsonProperty("COMPETENCIASAPTITUDINALES")
    public ItemEnvioRespuestasDTO getCOMPETENCIASAPTITUDINALES() {
        return COMPETENCIASAPTITUDINALES;
    }

    public void setCOMPETENCIASAPTITUDINALES(ItemEnvioRespuestasDTO COMPETENCIASAPTITUDINALES) {
        this.COMPETENCIASAPTITUDINALES = COMPETENCIASAPTITUDINALES;
    }
    
    */

     @JsonProperty("COMPETENCIASAPTITUDINALES")
    public List<ItemEnvioRespuestasDTO> getCOMPETENCIASAPTITUDINALES() {
        return COMPETENCIASAPTITUDINALES;
    }

    public void setCOMPETENCIASAPTITUDINALES(List<ItemEnvioRespuestasDTO> COMPETENCIASAPTITUDINALES) {
        this.COMPETENCIASAPTITUDINALES = COMPETENCIASAPTITUDINALES;
    }

    @JsonProperty("COMPETENCIASSOCIOPERSONALES")
    public List<ItemEnvioRespuestasDTO> getCOMPETENCIASSOCIOPERSONALES() {
        return COMPETENCIASSOCIOPERSONALES;
    }

    public void setCOMPETENCIASSOCIOPERSONALES(List<ItemEnvioRespuestasDTO> COMPETENCIASSOCIOPERSONALES) {
        this.COMPETENCIASSOCIOPERSONALES = COMPETENCIASSOCIOPERSONALES;
    }

    @JsonProperty("DEMERITOS")
    public List<ItemEnvioRespuestasDTO> getDEMERITOS() {
        return DEMERITOS;
    }

    public void setDEMERITOS(List<ItemEnvioRespuestasDTO> DEMERITOS) {
        this.DEMERITOS = DEMERITOS;
    }


    @JsonProperty("EVCONCLUIDA")
    public String getEVCONCLUIDA() {
        return EVCONCLUIDA;
    }
    
    public void setEVCONCLUIDA(String EVCONCLUIDA) {
        this.EVCONCLUIDA = EVCONCLUIDA;
    }
/*
    @Override
    public String toString() {
        return "EnvioRespuestasDTO{" + "COMPETENCIASAPTITUDINALES=" + COMPETENCIASAPTITUDINALES + ", EVCONCLUIDA=" + EVCONCLUIDA + '}';
    }
    
    */
    
 
    
}


