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
public class EscalafonCertificadosDTO {
    
    private String nombreCertificado;
    private String fechaCurso;
    private String validado;

    public String getNombreCertificado() {
        return nombreCertificado;
    }

    public void setNombreCertificado(String nombreCertificado) {
        this.nombreCertificado = nombreCertificado;
    }

    public String getFechaCurso() {
        return fechaCurso;
    }

    public void setFechaCurso(String fechaCurso) {
        this.fechaCurso = fechaCurso;
    }

    public String getValidado() {
        return validado;
    }

    public void setValidado(String validado) {
        this.validado = validado;
    }
    
}
