/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.hnomina.response;

import java.io.Serializable;

/**
 *
 * @author smartinez
 */
public class BusRespuestaSP<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int codigo;
    private String mensaje;
    private boolean status;
    private T response;

    public BusRespuestaSP(int codigo, String mensaje, boolean status, T response) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.status = status;
        this.response = response;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }


        
        
}
