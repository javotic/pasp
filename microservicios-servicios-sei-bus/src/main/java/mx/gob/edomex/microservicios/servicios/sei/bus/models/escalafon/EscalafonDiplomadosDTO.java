/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon;

/**
 *
 * @author smartinez
 */
public class EscalafonDiplomadosDTO {
    private String nombreDiplomado;
    private String fechaDiplomado;
    private String duracion;
    private String tipoDuracion;
    private String validado;

    public String getNombreDiplomado() {
        return nombreDiplomado;
    }

    public void setNombreDiplomado(String nombreDiplomado) {
        this.nombreDiplomado = nombreDiplomado;
    }

    public String getFechaDiplomado() {
        return fechaDiplomado;
    }

    public void setFechaDiplomado(String fechaDiplomado) {
        this.fechaDiplomado = fechaDiplomado;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getTipoDuracion() {
        return tipoDuracion;
    }

    public void setTipoDuracion(String tipoDuracion) {
        this.tipoDuracion = tipoDuracion;
    }

    public String getValidado() {
        return validado;
    }

    public void setValidado(String validado) {
        this.validado = validado;
    }
    
    
}
