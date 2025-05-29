package mx.gob.edomex.microservicios.transversales.firmadocumentos.excepciones;

/**
 * Excepcion controlada de procesamiento general.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 01/12/2021.
 */
public class ServiceExcepcion extends Exception {

    // <editor-fold defaultstate="collapsed" desc="Propiedades de clase">
    private String mensaje = null;
    //</editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constructores">
    public ServiceExcepcion() {
        super();
    }

    public ServiceExcepcion(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    public ServiceExcepcion(Throwable cause) {
        super(cause);
        this.mensaje = cause.getMessage();
    }

    public ServiceExcepcion(String message, Throwable cause) {
        super(cause);
        this.mensaje = message;
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Funciones publicas">
    @Override
    public String toString() {
        return mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
    //</editor-fold>
}
