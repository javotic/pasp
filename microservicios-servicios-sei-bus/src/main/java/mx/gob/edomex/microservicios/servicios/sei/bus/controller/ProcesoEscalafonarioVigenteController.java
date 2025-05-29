package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
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
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.ProcesoEscalafonarioVigenteService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/procesoEscalafonarioVigente")
public class ProcesoEscalafonarioVigenteController {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    @Autowired
    private ProcesoEscalafonarioVigenteService service;
    //</editor-fold>

    //<editor-fold desc="Funciones GET" defaultstate="collapsed">
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "escalafonPlazasDisponibles/{claveServidorPublico}/{todasPlazas}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<EscalafonPlazasDisponibles>>
            escalafonPlazasDisponibles(@PathVariable("claveServidorPublico") String claveServidorPublico,
                    @PathVariable("todasPlazas") String todasPlazas) throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                //                service.escalafonPlazasDisponibles(claveServidorPublico, todasPlazas));
                service.escalafonPlazasDisponiblesSemaforo(claveServidorPublico, todasPlazas));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "escalafonAplicaProcesoVigente/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<EscalafonAplicaProcesoVigente>>
            escalafonAplicaProcesoVigente(@PathVariable("claveServidorPublico") String claveServidorPublico) throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                service.escalafonAplicaProcesoVigente(claveServidorPublico));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "escalafonEstadoInscripcion/{claveServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<EscalafonEstadoInscripcion>>
            escalafonEstadoInscripcion(@PathVariable("claveServidorPublico") String claveServidorPublico, @RequestParam String idProcesoVigente) throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                service.escalafonEstadoInscripcion(claveServidorPublico, idProcesoVigente));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "escalafonProcesoVigente", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<EscalafonProcesoVigente>>
            escalafonProcesoVigente() throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                //                service.escalafonProcesoVigente());
                service.obtenerProcesoEscalafonarioVigente());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "escalafonSesionesAsesoria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<SesionesDTO>>
            escalafonSesionesAsesoria(@RequestParam String idProcesoVigente) throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                service.escalafonSesionesAsesoria(idProcesoVigente));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "escalafonSesionesExamen", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<SesionExamenDTO>>
            escalafonSesionesExamen(@RequestParam String idProcesoVigente) throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                service.escalafonSesionesExamen(idProcesoVigente));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarDatosProfesionales/{idServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<DatosProfecionalesDTO>>
            consultarDatosProfesionales(@PathVariable("idServidorPublico") String idServidorPublico) throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                service.consultarDatosProfesionales(idServidorPublico));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/descargaConvocatoria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<byte[]>
            descargaConvocatoria(@RequestParam String claveproceso, @RequestParam String idServidorPublico) throws BusException {
        return new ResponseEntity<byte[]>(service.descargaConvocatoria(claveproceso, idServidorPublico), HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/escalafonActualizaInfo", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<Integer>
            escalafonActualizaInfo() throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK", service.escalafonActualizaInfo());
    }
    //</editor-fold>

    //<editor-fold desc="Funciones POST" defaultstate="collapsed">
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "inscripcion", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<String> inscripcion(@RequestBody InscripcionDTO inscripcion)
            throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK), "OK",
                service.inscripcion(inscripcion).toString());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "seleccionarProceso",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<String> actualizarDatosSeleccion(
            @RequestBody SeleccionProcesoDTO seleccionProceso) throws BusException {
        return new BusResponse<>(
                Constantes.STATUS_SUCCESS,
                String.valueOf(HttpStatus.OK),
                Constantes.MESSAGE_SUCCESS,
                this.service.actualizarProfesionalesProceso(seleccionProceso)
        );
    }
    //</editor-fold>
}
