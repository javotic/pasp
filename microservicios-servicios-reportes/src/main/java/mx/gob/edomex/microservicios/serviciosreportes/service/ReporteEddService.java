package mx.gob.edomex.microservicios.serviciosreportes.service;

import mx.gob.edomex.microservicios.serviciosreportes.reponse.ReponsePreguntasEjecucionED;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseConsultarDatosPServidorPublico;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseConsultarInstruccionesLlenadoEDD;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseConsultarSeccionesEDD;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResponseResultadosEdd;
import mx.gob.edomex.microservicios.serviciosreportes.reponse.ResposeConsultarRespuestasCompetencias;

public interface ReporteEddService {

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    byte[] getReporteEdd(String Proceso, String servidorPublico);

    byte[] getReporteEddHT(String Proceso, String servidorPublico);

    ReponsePreguntasEjecucionED preguntasEjecucionED(String idProceso,
            String idSeccion);

    ResponseConsultarInstruccionesLlenadoEDD consultarInstruccionesLlenadoEDD(String proceso);

    ResponseConsultarDatosPServidorPublico consultarDatosPuestoServidorPublico(String servidorPublico, String proceso);

    ResposeConsultarRespuestasCompetencias consultarRespuestasCompetencias(String proceso);

    ResponseConsultarSeccionesEDD consultarSeccionesEDD(String proceso);

    ResponseResultadosEdd consultarResultadosCompetencias(String servidorPublico, String seccion, String proceso);

    /**
     * Generar reporte de evaluacion del desempeno con demeritos a traves del
     * nuevo formato solicitado por el cliente.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 03/02/2023.
     * @param proceso Clave del proceso del que se obtendra el formato
     * @param claveServidorPublico Clave de servidor publico que realiza la
     * evaluacion.
     * @return Arreglo de bytes con el reporte generado.
     */
    byte[] generarReporteEdddemeritos(String proceso, String claveServidorPublico);
    //</editor-fold>
}
