package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConfiguracionHistorialLaboralModel;

/**
 * Definicion de interfaz que permite la generacion de PDFs de forma general.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 17/12/2021.
 */
public interface GenerarPDFService {

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    /**
     * Generar encabezado de documento de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/11/2021.
     * @param document Documentoo a procesar.
     * @param configuracionHistorialLaboralModel Configuracion de historial
     * laboral para indicar la frase del encabezado.
     * @throws BadElementException de procesamiento de elementos de creacion en
     * documento.
     * @throws IOException de generacion de elementos de entrada y salida.
     * @throws DocumentException de construccion de documento.
     */
    void generarEncabezado(Document document, 
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel)
            throws BadElementException, IOException, DocumentException;

    /**
     * Generar pie de pagina de documento de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/11/2021.
     * @param document Documentoo a procesar.
     * @param configuracionHistorialLaboralModel Configuracion de historial
     * laboral para obtener parametros configurables.
     * @throws BadElementException de procesamiento de elementos de creacion en
     * documento.
     * @throws IOException de generacion de elementos de entrada y salida.
     * @throws DocumentException de construccion de documento.
     */
    void generarPiePagina(Document document,
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel)
            throws BadElementException, IOException, DocumentException;
    
    /**
     * Obtener configuracion general de documento
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 17/12/2021.
     * @return Configuracion obtenida desde bae de datos.
     */
    ConfiguracionHistorialLaboralModel obtenerConfiguracionDocumento();
    //</editor-fold>
}
