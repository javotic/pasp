
package mx.gob.edomex.microservicios.hnomina.response;

import java.io.Serializable;

/**
 *
 * @author smartinez
 */
public class BusRespuesta<T> implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private String estatus;
    private String codigo;
    private String mensaje;
    private T response; 


    public BusRespuesta(String estatus, String codigo, String mensaje, T response) {
        this.estatus = estatus;
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.response = response;
    }

    public BusRespuesta(String codigo, String mensaje, T response) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.response = response;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
        
        
}
