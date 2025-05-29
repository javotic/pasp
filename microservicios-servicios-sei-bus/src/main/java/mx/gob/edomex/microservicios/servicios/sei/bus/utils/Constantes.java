package mx.gob.edomex.microservicios.servicios.sei.bus.utils;

/**
 *
 * Constantes generales.
 * 
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 04/11/2021.
 */
public interface Constantes {
    // <editor-fold defaultstate="collapsed" desc="Respuesta de servicio de bus">
    public static final String STATUS_SUCCESS = "SUCCESS";
    public static final String STATUS_FAILURE = "FAILURE";
    public static final String MESSAGE_SUCCESS = "Servicio consumido correctamente.";
    public static final String MESSAGE_ERROR = "Error en consumo de servicio.";
    public static final String STATUS_FAILURE_SERVICE = "INTERNAL_SERVER_ERROR";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Armado de PDFs">
    public final String DOCUMENTO_IMAGEN_ENCABEZADO = "classpath:reportes/historial-laboral/historial-laboral-encabezado.jpg";
    public final String DOCUMENTO_IMAGEN_PIE_PAGINA = "classpath:reportes/historial-laboral/historial-laboral-piepagina.jpg";
    public final String DOCUMENTO_PIE_PAGINA_ATENTAMENTE = "A T E N T A M E N T E ";
    public final String DOCUMENTO_PIE_PAGINA_DIRECTOR = "DIRECTOR";
    public final String DOCUMENTO_PIE_PAGINA_SUBDIRECTOR_CONTROL_PAGOS = "SUBDIRECTOR DE CONTROL DE PAGOS";
    public final String DOCUMENTO_PIE_PAGINA_JESUS_ACEVEDO = "ING. JOSÉ DE JESÚS ACEVEDO RODRÍGUEZ";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Propiedades de Ambientes">
    public final String PROPIEDAD_AMBIENTE_TRABAJO_DEV = "dev";
    public final String PROPIEDAD_AMBIENTE_TRABAJO_PROD = "prod";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Rutas de imagenes">
    public final String PATH_IMAGEN_ENCABEZADO = "/home/images/historial-laboral-encabezado.jpg";
    public final String PATH_IMAGEN_PIE_PAGINA = "/home/images/historial-laboral-piepagina.jpg";
    public final String PATH_IMAGEN_ENCABEZADO_LOCAL = "/Users/jalvarado/images/historial-laboral-encabezado.jpg";
    public final String PATH_IMAGEN_PIE_PAGINA_LOCAL = "/Users/jalvarado/images/historial-laboral-piepagina.jpg";
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Constantes de firma">
    public final String CUTS_USUARIO = "LOVR890613HMCPDD00";
    public final String CUTS_CONTRASENIA = "R12D&mP3$v4";
    // </editor-fold>
    
}
