/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.hnomina.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author smartinez
 */
public class ServidorComprobanteListDTO {

    private List<ServidorComprobanteDTO> LSTSERVIDORCOMPROBANTE;

    @JsonProperty("LSTSERVIDORCOMPROBANTE")
    public List<ServidorComprobanteDTO> getLSTSERVIDORCOMPROBANTE() {
        return LSTSERVIDORCOMPROBANTE;
    }

    public void setLSTSERVIDORCOMPROBANTE(List<ServidorComprobanteDTO> LSTSERVIDORCOMPROBANTE) {
        this.LSTSERVIDORCOMPROBANTE = LSTSERVIDORCOMPROBANTE;
    }
    
}