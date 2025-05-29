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
public class DetalleReciboDTO {

private String CLAVE;
private String CONCEPTO;
private String IMPORTE;
//private String PLAZA;

    public String getCLAVE() {
        return CLAVE;
    }

    public void setCLAVE(String CLAVE) {
        this.CLAVE = CLAVE;
    }

    public String getCONCEPTO() {
        return CONCEPTO;
    }

    public void setCONCEPTO(String CONCEPTO) {
        this.CONCEPTO = CONCEPTO;
    }

    public String getIMPORTE() {
        return IMPORTE;
    }

    public void setIMPORTE(String IMPORTE) {
        this.IMPORTE = IMPORTE;
    }

    /*
    public String getPLAZA() {
        return PLAZA;
    }

    public void setPLAZA(String PLAZA) {
        this.PLAZA = PLAZA;
    }

*/
}
