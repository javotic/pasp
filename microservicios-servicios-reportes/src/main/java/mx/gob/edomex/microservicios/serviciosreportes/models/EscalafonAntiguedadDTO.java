/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.serviciosreportes.models;

/**
 *
 * @author smartinez
 */
public class EscalafonAntiguedadDTO {
    private String antiguedad;
    private String rangoAntiguedad;
    private String puntajeAntiguedad;

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public String getRangoAntiguedad() {
        return rangoAntiguedad;
    }

    public void setRangoAntiguedad(String rangoAntiguedad) {
        this.rangoAntiguedad = rangoAntiguedad;
    }

    public String getPuntajeAntiguedad() {
        return puntajeAntiguedad;
    }

    public void setPuntajeAntiguedad(String puntajeAntiguedad) {
        this.puntajeAntiguedad = puntajeAntiguedad;
    }    
}
