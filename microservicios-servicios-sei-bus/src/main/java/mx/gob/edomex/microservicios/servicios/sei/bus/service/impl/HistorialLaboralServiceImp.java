/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.HistorialLaboralDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ConfiguracionHistorialLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.HistorialLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SolicitudHistorialLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.SolicitudHistoricoLaboralModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.GenerarPDFService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.HistorialLaboralService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author smartinez
 */
@Service
public class HistorialLaboralServiceImp implements HistorialLaboralService {

    //<editor-fold desc="Constantes de clase" defaultstate="collapsed">
    private static final Logger LOG = LoggerFactory.getLogger(DatosServidorPublicoServiceImpl.class);
    public final String DOCUMENTO_FORMATO_FECHA = "dd 'de' MMMM 'de' YYYY";
    public final String DOCUMENTO_TITUTLO = "A QUIEN CORRESPONDA:";
    public final String DOCUMENTO_CIERRE_PARRAFO = ".";
    public final String DOCUMENTO_CONTINUACION_PARRAFO = "; ";
    public final String DOCUMENTO_COMA = ", ";
    public final String DOCUMENTO_EN_LA = ", en la ";
    public final String DOCUMENTO_COMO = " como ";
    public final String DOCUMENTO_INTRODUCCION_CONSTANCIA = "La Dirección de Remuneraciones al Personal, hace constar que ";
    public final String DOCUMENTO_INTRODUCCION_INGRESOGEM = ", ingresó al Gobierno del Estado de México Sector Central, el ";
    public final String DOCUMENTO_SUELDO_INTEGRADO_OSTENTANDO = "Ostentando a la fecha la misma categoría en la misma dependencia, con un sueldo mensual integrado de $";
    public final String DOCUMENTO_EXPEDICION_LUGAR = "Se expide la presente en la Ciudad de Toluca, Estado de México, a los ";
    public final String DOCUMENTO_EXPEDICION_FORMATO_FECHA = "dd 'días del mes de' MMMM 'de' YYYY";
    public final String DOCUMENTO_PIE_PAGINA_DIRECTOR = "DIRECTOR";
    public final String DOCUMENTO_CONTENIDO_TIPOHISTORIAL_PROMOVIO = "se promovió";
    public final String DOCUMENTO_CONTENIDO_TIPOHISTORIAL_REGULARIZACION = "por regularización de puesto";
    public final String DOCUMENTO_CONTENIDO_TIPOHISTORIAL_REINGRESO = "reingresó";
    public final String DOCUMENTO_CONTENIDO_TIPOHISTORIAL_BAJA = "causando baja";
    public final String DOCUMENTO_CONTENIDO_APARTIR = "a partir del ";
    public final String DOCUMENTO_CONTENIDO_EL = "el ";
    public final String DOCUMENTO_CONTENIDO_SE_LE_ASIGNO_CATEGORIA = " se le asignó la categoría de ";
    public final String DOCUMENTO_CONTENIDO_REINGRESANDO_EL = "reingresando el ";
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private HistorialLaboralDAO historialLaboralDAO;
    @Autowired
    private GenerarPDFService generarPDFService;
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public List<SolicitudHistorialLaboralModel> getSolicitudesHistoricoLaboral(String idservidorpublico) throws BusException {
        Utils utils = new Utils();
        List<SolicitudHistorialLaboralModel> historialLaboral = new ArrayList();
        try {

            List<Object[]> lstResul = historialLaboralDAO.getSolicitudesHistoricoLaboral(idservidorpublico);

            lstResul.forEach(x -> {
                SolicitudHistorialLaboralModel historial = new SolicitudHistorialLaboralModel();

                historial.setIDSOLICITUDHISTORIALLABORAL(utils.objectIsNULL(x[0]));
                historial.setRESPUESTA(utils.objectIsNULL(x[1]));
                historial.setFECHASOLICITUD(utils.objectIsNULL(x[2]));
                historial.setDETALLERESPUESTA(utils.objectIsNULL(x[3]));
                historial.setESTATUS(utils.objectIsNULL(x[4]));
                historial.setGESTORADMINISTRATIVO(utils.objectIsNULL(x[5]));
                historial.setFECHARESPUESTA(utils.objectIsNULL(x[6]));
                historial.setIDDOCUMENTO(utils.objectIsNULL(x[7]));

                historialLaboral.add(historial);

            });
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return historialLaboral;
    }

    @Override
    public List<Combo> obtenerCatalogoMotivosSolicitud() throws BusException {
        List<Combo> lsMotivos = new ArrayList<>();
        Utils utils = new Utils();
        try {
            List<Object[]> lsResultado = historialLaboralDAO.obtenerCatalogoMotivosSolicitud();

            lsResultado.forEach(motivo -> {
                lsMotivos.add(new Combo(
                        utils.objectIsNULL(motivo[0]),
                        utils.objectIsNULL(motivo[1])
                ));
            });
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }
        return lsMotivos;
    }

    @Override
    public List<SolicitudHistoricoLaboralModel> obtenerSolicitudes(String claveServidor,
            String nombreServidor, String claveServidorBusqueda, int banderaBusqueda) throws BusException {
        List<SolicitudHistoricoLaboralModel> lsSolicitudesHistorialLaboral = new ArrayList<>();
        Utils utils = new Utils();
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");  
        List<String> excepciones = new ArrayList<>();

        try {
            List<Object[]> lsResultado = historialLaboralDAO.obtenerSolicitudes(
                    claveServidor,
                    nombreServidor,
                    claveServidorBusqueda,
                    banderaBusqueda
            );

            lsResultado.forEach(solicitud -> {
                try {
                    lsSolicitudesHistorialLaboral.add(new SolicitudHistoricoLaboralModel(
                            Integer.valueOf(utils.objectIsNULL(solicitud[0])),//Folio
                            utils.objectIsNULL(solicitud[1]),//Clave de servidor publico
                            utils.objectIsNULL(solicitud[2]),//Nombre de servidor publico
                            parseador.parse(utils.objectIsNULL(solicitud[3])),//Fecha de solicitud
                            utils.objectIsNULL(solicitud[4]),//Motivo
                            utils.objectIsNULL(solicitud[5]),//Estatus
                            dateFormat.format(parseador.parse(utils.objectIsNULL(solicitud[3])))//Fecha de solicitud cadena
                    ));
                } catch (ParseException ex) {
                    excepciones.add("Error en solicitud con folio "
                            + utils.objectIsNULL(solicitud[0])
                            + ". Error: "
                            + ex.getMessage()
                    );
                }
            });

            //Si existe algun error, se notifica.
            if (excepciones.size() > 0) {

                StringBuilder builder = new StringBuilder();
                excepciones.forEach(e -> builder.append(e));

                throw new InternalServerErrorException(
                        Constantes.STATUS_FAILURE_SERVICE,
                        "Se han presentando errores al momento de "
                        + "obtener la información, favor de validar."
                        + builder.toString());
            }
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }

        return lsSolicitudesHistorialLaboral;
    }

    @Override
    public List<HistorialLaboralModel> obtenerHistorialLaboral(
            String claveServidor) throws BusException {
        List<HistorialLaboralModel> lsHistorialLaboral = new ArrayList<>();
        Utils utils = new Utils();
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        List<String> excepciones = new ArrayList<>();

        try {
            List<Object[]> lsResultado = historialLaboralDAO.obtenerHistorialLaboral(claveServidor);

            lsResultado.forEach(solicitud -> {
                try {
                    lsHistorialLaboral.add(new HistorialLaboralModel(
                            utils.objectIsNULL(solicitud[0]),//Clave de servidor publico
                            utils.objectIsNULL(solicitud[1]),//Nombre de servidor publico
                            parseador.parse(utils.objectIsNULL(solicitud[2])),//Fecha de inicio
                            parseador.parse(utils.objectIsNULL(solicitud[3])),//Fecha de termino
                            utils.objectIsNULL(solicitud[4]),//Movimiento
                            utils.objectIsNULL(solicitud[5]),//Puesto
                            utils.objectIsNULL(solicitud[6])//Secretaria
                    ));
                } catch (ParseException ex) {
                    excepciones.add("Error en al intentar convertir el historial: "
                            + utils.objectIsNULL(solicitud.toString())
                            + ". Error: "
                            + ex.getMessage()
                    );
                }
            });

            //Si existe algun error, se notifica.
            if (excepciones.size() > 0) {

                StringBuilder builder = new StringBuilder();
                excepciones.forEach(e -> builder.append(e));

                throw new InternalServerErrorException(
                        Constantes.STATUS_FAILURE_SERVICE,
                        "Se han presentando errores al momento de "
                        + "obtener la información, favor de validar."
                        + builder.toString());
            }
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }

        return lsHistorialLaboral;
    }

    @Override
    public boolean insertarSolicitud(String claveServidor, int idMotivo, String comentarios) throws BusException {
        int resultado = historialLaboralDAO.insertarSolicitud(
                claveServidor,
                idMotivo,
                comentarios
        );
        return resultado == 1;
    }

    @Override
    public byte[] generarDocumentoHistorialLaboral(String claveServidorPublico) throws BusException {
        byte[] documento = null;
        try {
            //Obtener parametros configurbles de solicitud
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel
                    = this.generarPDFService.obtenerConfiguracionDocumento();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            //Generar documento
            Document document = new Document(PageSize.LETTER, 30, 30, 10, 20);
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            //Iniciar con la creacion del documento
            document.open();
            //Generar encabezado de documento
            this.generarPDFService.generarEncabezado(
                    document,
                    configuracionHistorialLaboralModel
            );
            //Generar titulo de documento
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14, BaseColor.BLACK);
            document.add(new Chunk(this.DOCUMENTO_TITUTLO, font));
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            //Generar cuerpo de documento
            this.generarParrafos(
                    document,
                    this.obtenerHistorialLaboral(claveServidorPublico),
                    this.obtenerSueldoIntegrado(claveServidorPublico)
            );
            //Generar fecha de expedicion
            this.generarExpedicion(document);

            //Generar seccion de usuario firmante.
            this.generarFirmante(document, configuracionHistorialLaboralModel);

            //Generar pie de pagina
            this.generarPDFService.generarPiePagina(
                    document,
                    configuracionHistorialLaboralModel
            );
            //Cerrar documento y finalizar
            document.close();
            writer.close();

            documento = baos.toByteArray();
            IOUtils.closeQuietly(baos);

        } catch (IOException | DocumentException ex) {
            java.util.logging.Logger.getLogger(HistorialLaboralServiceImp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return documento;
    }

    @Override
    public HistorialLaboralModel obtenerSueldoIntegrado(String claveServidor) throws BusException {
        HistorialLaboralModel historialLaboral = new HistorialLaboralModel();
        Utils utils = new Utils();
        SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd");
        List<String> excepciones = new ArrayList<>();

        try {
            List<Object[]> lsResultado = historialLaboralDAO.obtenerSueldoIntegrado(claveServidor);

            if (lsResultado != null && lsResultado.size() == 1) {
                Object[] solicitud = lsResultado.get(0);
                try {
                    historialLaboral = new HistorialLaboralModel(
                            utils.objectIsNULL(solicitud[0]),//Clave de servidor publico
                            utils.objectIsNULL(solicitud[1]),//Nombre de servidor publico
                            parseador.parse(utils.objectIsNULL(solicitud[2])),//Fecha de inicio
                            parseador.parse(utils.objectIsNULL(solicitud[3])),//Fecha de termino
                            utils.objectIsNULL(solicitud[4]),//Puesto
                            Double.parseDouble(utils.objectIsNULL(solicitud[5]).isEmpty() ? "0.0" : utils.objectIsNULL(solicitud[5]))//Sueldo
                    );
                } catch (ParseException ex) {
                    excepciones.add("Error en al intentar convertir el historial: "
                            + utils.objectIsNULL(solicitud.toString())
                            + ". Error: "
                            + ex.getMessage()
                    );
                }
            }

            //Si existe algun error, se notifica.
            if (excepciones.size() > 0) {

                StringBuilder builder = new StringBuilder();
                excepciones.forEach(e -> builder.append(e));

                throw new InternalServerErrorException(
                        Constantes.STATUS_FAILURE_SERVICE,
                        "Se han presentando errores al momento de "
                        + "obtener la información, favor de validar."
                        + builder.toString());
            }
        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }

        return historialLaboral;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones privadas" defaultstate="collapsed">
    /**
     * Generar parrafos de cuerpo de documento..
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/11/2021.
     * @param document Documentoo a procesar.
     * @param lsHistorial Lista de historial laboral del cual se generara el
     * contenido.
     * @param historialSueldo Historial con sueldo integrado.
     * @throws DocumentException de construccion de documento.
     */
    private void generarParrafos(Document document, List<HistorialLaboralModel> lsHistorial,
            HistorialLaboralModel historialSueldo) throws DocumentException {
        if (lsHistorial != null && lsHistorial.size() > 0) {
            this.generarIntroduccion(document, lsHistorial.get(0));
            lsHistorial.remove(0);

            this.generarHistorial(document, lsHistorial);

            this.generarSueldo(document, historialSueldo);
        }
    }

    /**
     * Generar introduccion de documento de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/11/2021.
     * @param document Documentoo a procesar.
     * @param HistorialLaboralModel Historial laboral de primer ingreso.
     * @throws DocumentException de construccion de documento.
     */
    private void generarIntroduccion(Document document,
            HistorialLaboralModel historialLaboralModel) throws DocumentException {
        StringBuilder sb = new StringBuilder(this.DOCUMENTO_INTRODUCCION_CONSTANCIA);
        sb.append(historialLaboralModel.getNombreServidorPublico().trim());
        sb.append(this.DOCUMENTO_INTRODUCCION_INGRESOGEM);
        sb.append(new SimpleDateFormat(this.DOCUMENTO_FORMATO_FECHA, new Locale("es", "ES"))
                .format(historialLaboralModel.getFechaInicio()));
        sb.append(this.DOCUMENTO_COMA + this.DOCUMENTO_COMO);
        sb.append(historialLaboralModel.getPuesto().trim());
        sb.append(this.DOCUMENTO_EN_LA);
        sb.append(historialLaboralModel.getSecretaria().trim());
        sb.append(this.DOCUMENTO_CIERRE_PARRAFO);

        Paragraph parrafo = new Paragraph(sb.toString());

        document.add(parrafo);
        document.add(Chunk.NEWLINE);
    }

    /**
     * Generar cuerpo de documento de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/11/2021.
     * @param document Documentoo a procesar.
     * @param lsHistorialLaboralModel Listado de historial laboral del cual se
     * generara el contenido del documento.
     * @throws DocumentException de construccion de documento.
     */
    private void generarHistorial(Document document,
            List<HistorialLaboralModel> lsHistorialLaboralModel) throws DocumentException {

        if (lsHistorialLaboralModel == null || !(lsHistorialLaboralModel.size() > 1)) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        String secretaria = lsHistorialLaboralModel.get(0).getSecretaria();
        boolean cambioSecretaria = true;
        Paragraph parrafo = new Paragraph();

        for (HistorialLaboralModel historialLaboralModel : lsHistorialLaboralModel) {

            //Si son diferentes secretarías
            if (!secretaria.equals(historialLaboralModel.getSecretaria())) {
                if (sb.toString().length() > 0) {
                    parrafo = new Paragraph(sb.toString().substring(0, 1).toUpperCase() + sb.toString().substring(1));
                    document.add(parrafo);
                    document.add(Chunk.NEWLINE);

                    secretaria = historialLaboralModel.getSecretaria();
                    sb = new StringBuilder();
                    cambioSecretaria = true;
                }
            }

            sb.append(this.genearTexto(historialLaboralModel));

            //Si no hay cambio de secretaria, se muestra por unica vez
            if (cambioSecretaria) {
                sb.append(this.DOCUMENTO_EN_LA);
                sb.append(historialLaboralModel.getSecretaria().trim());
                cambioSecretaria = false;
            }

            sb.append(this.DOCUMENTO_CONTINUACION_PARRAFO);
        }

        if (sb.toString().length() > 0) {
            parrafo = new Paragraph(sb.toString().substring(0, 1).toUpperCase() + sb.toString().substring(1));
            document.add(parrafo);
            document.add(Chunk.NEWLINE);
        }
    }

    /**
     * Generar parrafo de fecha de expedicion en documento de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/11/2021.
     * @version 1.0, 18/01/2022, Javier Alvarado, Cambio en LOCALE de generacion
     * de fecha a idioma ES.
     * @param document Documentoo a procesar.
     * @throws BadElementException de procesamiento de elementos de creacion en
     * documento.
     * @throws IOException de generacion de elementos de entrada y salida.
     * @throws DocumentException de construccion de documento.
     */
    private void generarExpedicion(Document document) throws BadElementException,
            IOException, DocumentException {

        StringBuilder sb = new StringBuilder(this.DOCUMENTO_EXPEDICION_LUGAR);
        sb.append(new SimpleDateFormat(
                this.DOCUMENTO_EXPEDICION_FORMATO_FECHA, new Locale("es", "ES"))
                .format(new Date())
        );
        sb.append(this.DOCUMENTO_CIERRE_PARRAFO);

        document.add(new Paragraph(sb.toString()));
        document.add(Chunk.NEWLINE);
    }

    /**
     * Generar parrafo de sueldo integrado en documento de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/11/2021.
     * @param document Documentoo a procesar.
     * @param historialSueldo Historial laboral con el sueldo integrado.
     * @throws BadElementException de procesamiento de elementos de creacion en
     * documento.
     * @throws DocumentException de construccion de documento.
     */
    private void generarSueldo(Document document, HistorialLaboralModel historialSueldo)
            throws BadElementException, DocumentException {
        StringBuilder sb = new StringBuilder(this.DOCUMENTO_SUELDO_INTEGRADO_OSTENTANDO);
        sb.append(String.format("%,.2f", historialSueldo.getSueldo()));
        sb.append(this.DOCUMENTO_CIERRE_PARRAFO);

        document.add(new Paragraph(sb.toString()));
        document.add(Chunk.NEWLINE);
    }

    /**
     * Generar seccion de firmante de documento.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 17/12/2021.
     * @param document Documentoo a procesar.
     * @param configuracionHistorialLaboralModel Configuracion de historial
     * laboral para obtener parametros configurables.
     * @throws BadElementException de procesamiento de elementos de creacion en
     * documento.
     * @throws IOException de generacion de elementos de entrada y salida.
     * @throws DocumentException de construccion de documento.
     */
    private void generarFirmante(Document document,
            ConfiguracionHistorialLaboralModel configuracionHistorialLaboralModel) throws DocumentException {
        Paragraph parrafoCierre = new Paragraph(Constantes.DOCUMENTO_PIE_PAGINA_ATENTAMENTE);
        parrafoCierre.setAlignment(Element.ALIGN_CENTER);
        document.add(parrafoCierre);
        document.add(Chunk.NEWLINE);

        parrafoCierre = new Paragraph(configuracionHistorialLaboralModel.getNombreFirma());
        parrafoCierre.setAlignment(Element.ALIGN_CENTER);
        document.add(parrafoCierre);

        parrafoCierre = new Paragraph(Constantes.DOCUMENTO_PIE_PAGINA_DIRECTOR);
        parrafoCierre.setAlignment(Element.ALIGN_CENTER);
        document.add(parrafoCierre);
    }

    /**
     * Generar texto de acuerdo a tipo de movimiento de historial laboral.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 25/11/2021.
     * @param historialLaboralModel Historial del que se generara el contenido
     * del texto.
     */
    private String genearTexto(HistorialLaboralModel historialLaboralModel) {
        StringBuilder sb = new StringBuilder("");

        switch (historialLaboralModel.getMovimiento().trim().toLowerCase()) {
            case DOCUMENTO_CONTENIDO_TIPOHISTORIAL_PROMOVIO:
                sb.append(this.DOCUMENTO_CONTENIDO_APARTIR);
                sb.append(new SimpleDateFormat(this.DOCUMENTO_FORMATO_FECHA, new Locale("es", "ES"))
                        .format(historialLaboralModel.getFechaInicio()));
                sb.append(this.DOCUMENTO_COMA);
                sb.append(historialLaboralModel.getMovimiento().trim().toLowerCase());
                sb.append(this.DOCUMENTO_COMO);
                sb.append(historialLaboralModel.getPuesto().trim());
                break;
            case DOCUMENTO_CONTENIDO_TIPOHISTORIAL_REGULARIZACION:
                sb.append(this.DOCUMENTO_CONTENIDO_EL);
                sb.append(new SimpleDateFormat(this.DOCUMENTO_FORMATO_FECHA, new Locale("es", "ES"))
                        .format(historialLaboralModel.getFechaInicio()));
                sb.append(this.DOCUMENTO_COMA);
                sb.append(historialLaboralModel.getMovimiento().trim().toLowerCase());
                sb.append(this.DOCUMENTO_CONTENIDO_SE_LE_ASIGNO_CATEGORIA);
                sb.append(historialLaboralModel.getPuesto().trim());
                break;
            case DOCUMENTO_CONTENIDO_TIPOHISTORIAL_REINGRESO:
                sb.append(this.DOCUMENTO_CONTENIDO_REINGRESANDO_EL);
                sb.append(new SimpleDateFormat(this.DOCUMENTO_FORMATO_FECHA, new Locale("es", "ES"))
                        .format(historialLaboralModel.getFechaInicio()));
                sb.append(this.DOCUMENTO_COMO);
                sb.append(historialLaboralModel.getPuesto().trim());
                break;
            case DOCUMENTO_CONTENIDO_TIPOHISTORIAL_BAJA:
                sb.append(historialLaboralModel.getMovimiento().trim().toLowerCase());
                sb.append(" " + this.DOCUMENTO_CONTENIDO_EL);
                sb.append(new SimpleDateFormat(this.DOCUMENTO_FORMATO_FECHA, new Locale("es", "ES"))
                        .format(historialLaboralModel.getFechaInicio()));
                break;
        }

        return sb.toString();
    }
    //</editor-fold>
}
