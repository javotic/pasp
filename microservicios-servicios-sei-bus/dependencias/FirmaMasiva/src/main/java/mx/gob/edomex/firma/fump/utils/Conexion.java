package mx.gob.edomex.firma.fump.utils;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingProvider;
import mx.gob.edomex.firma.fump.FirmarFump;
import mx.gob.edomex.ws.firmamasiva.CeroPapelWs;
import mx.gob.edomex.ws.firmamasiva.CeroPapelWsService;
import mx.gob.edomex.ws.firmamasiva.DocumentoVO;
import mx.gob.edomex.ws.firmamasiva.SolicitudVO;
import mx.gob.edomex.ws.firmamasiva.StringArray;
import mx.gob.edomex.ws.firmamasiva.WSException_Exception;

/**
 * Clase para establecer conexion con web service para firma
 *
 * @author adrian
 * @version 1.0
 */
public class Conexion {

    CeroPapelWsService service = new CeroPapelWsService();
    CeroPapelWs port = service.getCeroPapelWsPort();
    final String endPoint = "http://sistemas4.edomex.gob.mx/ceroPapel/webService?wsdl";
    String pathFile;
    String pathEvidencia;

    public Conexion(String pathFile, String pathEvidencia) {
        this.pathFile = pathFile;
        this.pathEvidencia = pathEvidencia;

        BindingProvider bp = (BindingProvider) port;
        bp.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "FmOGAf3m");
        bp.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "6%ftgRs#");
        bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endPoint);

    }

    public Conexion() {
        this("", "");
    }

    public String solicitudNueva(String nombreArchivo, String asunto, StringArray firmante, byte[] contenido) {
        try { // Call Web Service Operation
            //byte[] contenido = ArchivoUtils.copiarFicheroAMemoria(pathFile);//new byte[0];
            DocumentoVO documento = new DocumentoVO();
            documento.setContenido(contenido);
            documento.setNombre(nombreArchivo);
            XMLGregorianCalendar fecha = Fechas.getXMLCalendar(); // Retorna la fecha actual

            SolicitudVO solicitud = new SolicitudVO();
            solicitud.setAsunto(asunto);
            solicitud.setDocumento(documento);
            solicitud.setFechaVencimiento(fecha);//YYYY-MM-DD
            solicitud.setListaOrdenada(true);
            solicitud.getListaFirmantes().add(firmante);

            String result = port.nuevaSolicitudBatch(solicitud);
            return result;
        } catch (WSException_Exception ex) {
            System.out.println("CODE:" + ex.getFaultInfo().getErrorCode());
            System.out.println("MESG:" + ex.getFaultInfo().getMessage());
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Boolean existeSolicitudTerminada(String hashSolicitud) {

        try {
            boolean result = port.existeSolicitudTerminada(hashSolicitud);
            return result;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public byte[] getPdf(String hashSolicitud) throws Exception {

        try {
            DocumentoVO result = port.obtenerEvidenciaImprimible(hashSolicitud);
            ArchivoUtils.crearArchivoBinario(FirmarFump.repositorio + result.getNombre(), result.getContenido());
            return result.getContenido();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public byte[] getXml(String hashSolicitud) throws Exception {

        try {
            DocumentoVO result = port.obtenerEvidenciaXml(hashSolicitud);
            return result.getContenido();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    public void getCMS(String hashSolicitud) throws Exception {

        try {
            DocumentoVO result = port.obtenerDocumentoFirmado(hashSolicitud);
            ArchivoUtils.crearArchivoBinario(pathEvidencia + result.getNombre(), result.getContenido());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
}
