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
public class EscalafonCursosDTO {
    
    private String nombreCurso;
    private String fechaCurso;
    private String horas;
    private String validado;

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getFechaCurso() {
        return fechaCurso;
    }

    public void setFechaCurso(String fechaCurso) {
        this.fechaCurso = fechaCurso;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getValidado() {
        return validado;
    }

    public void setValidado(String validado) {
        this.validado = validado;
    }
    
    
    
}
