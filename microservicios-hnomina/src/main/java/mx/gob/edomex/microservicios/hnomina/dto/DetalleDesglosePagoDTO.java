/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.hnomina.dto;

import java.util.List;

/**
 *
 * @author smartinez
 */
public class DetalleDesglosePagoDTO {

    /*
    private String PLAZA;
    private String CLAVESP;

    private String NOMBRE;
    private String RFC_V;
    private String CODIGO;
    private String CODIGOPUESTO;
    private String PUESTO;
    private String CCT_V;
    private String LPAGO;

    private List<DetalleReciboDTO> DETALLEPERCEPCIONES;

*/

    private String ORDEN;
    private String COLUMN1;
    private String COLUMN2;
    private String COLUMN3;
    private String COLUMN4;
    private String COLUMN5;
    private String COLUMN6;

    public String getORDEN() {
        return ORDEN;
    }

    public void setORDEN(String ORDEN) {
        this.ORDEN = ORDEN;
    }

    public String getCOLUMN1() {
        return COLUMN1;
    }

    public void setCOLUMN1(String COLUMN1) {
        this.COLUMN1 = COLUMN1;
    }

    public String getCOLUMN2() {
        return COLUMN2;
    }

    public void setCOLUMN2(String COLUMN2) {
        this.COLUMN2 = COLUMN2;
    }

    public String getCOLUMN3() {
        return COLUMN3;
    }

    public void setCOLUMN3(String COLUMN3) {
        this.COLUMN3 = COLUMN3;
    }

    public String getCOLUMN4() {
        return COLUMN4;
    }

    public void setCOLUMN4(String COLUMN4) {
        this.COLUMN4 = COLUMN4;
    }

    public String getCOLUMN5() {
        return COLUMN5;
    }

    public void setCOLUMN5(String COLUMN5) {
        this.COLUMN5 = COLUMN5;
    }

    public String getCOLUMN6() {
        return COLUMN6;
    }

    public void setCOLUMN6(String COLUMN6) {
        this.COLUMN6 = COLUMN6;
    }
  
    
}
