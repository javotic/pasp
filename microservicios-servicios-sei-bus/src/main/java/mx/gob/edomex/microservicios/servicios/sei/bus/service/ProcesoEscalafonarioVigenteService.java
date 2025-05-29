package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.dto.InscripcionDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.SeleccionProcesoDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosProfecionalesDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonAplicaProcesoVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonEstadoInscripcion;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonPlazasDisponibles;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.EscalafonProcesoVigente;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.SesionExamenDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.escalafon.SesionesDTO;

public interface ProcesoEscalafonarioVigenteService {

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    List<EscalafonPlazasDisponibles> escalafonPlazasDisponibles(
            String claveServidorPublico, String tipo) throws BusException;

    List<EscalafonAplicaProcesoVigente> escalafonAplicaProcesoVigente(
            String claveServidorPublico) throws BusException;

    List<EscalafonEstadoInscripcion> escalafonEstadoInscripcion(
            String claveServidorPublico, String idProceso) throws BusException;

    Integer inscripcion(InscripcionDTO inscripcion) throws BusException;

    List<EscalafonProcesoVigente> escalafonProcesoVigente() throws BusException;

    List<SesionesDTO> escalafonSesionesAsesoria(String idProcesoVigente)
            throws BusException;

    List<SesionExamenDTO> escalafonSesionesExamen(String idProcesoVigente)
            throws BusException;

    List<DatosProfecionalesDTO> consultarDatosProfesionales(
            String idServidorPublico) throws BusException;

    byte[] descargaConvocatoria(String claveProceso, String idServidorPublico)
            throws BusException;

    Integer escalafonActualizaInfo() throws BusException;

    /**
     * Actualizar los datos profesionales de acuerdo con el proceso activo
     * escalafonario.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 16/03/2022.
     * @return Cadena indicando algun error en caso de presentarse
     * @param seleccionProceso Datos a actualizar del proceso escalafonario.
     * @throws BusException de procesamiento general.
     */
    String actualizarProfesionalesProceso(SeleccionProcesoDTO seleccionProceso)
            throws BusException;

    /**
     * Obtener listado de plazas disponibles con semaforo de ocupacion.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 04/08/2022.
     * @param claveServidorPublico Clave del servidor publico a buscar
     * @param tipo Tipo de plazas a obtener
     * @return Lista de EscalafonPlazasDisponibles con los datos obtenidos
     * @throws BusException de procesamiento general.
     */
    List<EscalafonPlazasDisponibles> escalafonPlazasDisponiblesSemaforo(
            String claveServidorPublico, String tipo) throws BusException;

    /**
     * Obtener proceso escalafonario vigente.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 08/08/2022.
     * @return Listado con el proceso escalafonario vigentte
     * @throws BusException de procesamiento general.
     */
    List<EscalafonProcesoVigente> obtenerProcesoEscalafonarioVigente() throws BusException;
    //</editor-fold>
}
