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
public class ListStringDTO {

    private List<String> ItemString;

    @JsonProperty("ItemString")
    public List<String> getItemString() {
        return ItemString;
    }

    public void setItemString(List<String> ItemString) {
        this.ItemString = ItemString;
    }
    
    

}
