package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPersonales;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPersonalesCatalogosDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosPuesto;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ServidorPublicoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.TituloCarreraModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuestaSP;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.MsgRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DatosPuestoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DatosServidorPublicoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/servidorpublico")
public class DatosServidorPublicoController {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String estatus;
    private String mensaje;
    //</editor-fold>

    //<editor-fold desc="Propiedades autoadministradas" defaultstate="collapsed">
    @Autowired
    private DatosServidorPublicoService datosServidorPublicoService;
    @Autowired
    private DatosPuestoService datosPuestoService;
    //</editor-fold>

    //<editor-fold desc="Funciones GET" defaultstate="collapsed">
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "datosservidorpublico/idServidorPublico/{idServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<DatosServidorPublico> consultarDatosServidorPublico(
            @PathVariable("idServidorPublico") String idServidorPublico) throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
                datosServidorPublicoService.consultarDatosServidorPublico(idServidorPublico));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "datospersonales/idServidorPublico/{idServidorPublico}/nombre/{nombre}/apellidoPat/{apellidoPat}/apellidoMat/{apellidoMat}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<DatosPersonales> consultarDatosPersonales(
            @PathVariable("idServidorPublico") String idServidorPublico, @PathVariable("nombre") String nombre,
            @PathVariable("apellidoPat") String apellidoPat, @PathVariable("apellidoMat") String apellidoMat)
            throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
                datosServidorPublicoService.consultarDatosPersonales(idServidorPublico, nombre, apellidoPat,
                        apellidoMat));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "datosPuesto/IdServidorPublico/{IdServidorPublico}/proceso/{proceso}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<DatosPuesto> consultarDatosPuesto(@PathVariable("IdServidorPublico") String IdServidorPublico,
            @PathVariable("proceso") String proceso)
            throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
                datosPuestoService.consultarDatosPuesto(IdServidorPublico, proceso));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarDatosPuestoHT/IdServidorPublico/{IdServidorPublico}/proceso/{proceso}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<DatosPuesto> consultarDatosPuestoHT(@PathVariable("IdServidorPublico") String IdServidorPublico,
            @PathVariable("proceso") String proceso)
            throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
                datosPuestoService.consultarDatosPuestoHT(IdServidorPublico, proceso));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarDatosPersonalesConCatalogos/idServidorPublico/{idServidorPublico}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DatosPersonalesCatalogosDTO consultarDatosPersonalesConCatalogos(
            @PathVariable("idServidorPublico") String idServidorPublico) throws BusException {
        return datosServidorPublicoService.consultarDatosPersonalesConCatalogos(idServidorPublico);
    }

    /**
     * Obtener datos personales de un servidor publico determinado a traves de
     * su clave de servidor publico.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 24/01/2022.
     * @param claveServidor Clave del servidor publico del que se obtendran los
     * resultados.
     * @return Lista de motivos de solicitud de historial laboral.
     * @throws BusException de ejecución de BUS.
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerDatosGenerales",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<ServidorPublicoModel>> obtenerDatosGenerales(
            @RequestParam("claveServidor") String claveServidor
    ) throws BusException {
        List<ServidorPublicoModel> lsResultado = new ArrayList<>();
        try {
            lsResultado.add(this.datosServidorPublicoService.
                    obtenerDatosPersonalesPorClaveSP(claveServidor));

            estatus = Constantes.STATUS_SUCCESS;
            mensaje = Constantes.MESSAGE_SUCCESS;
        } catch (Exception e) {
            estatus = Constantes.STATUS_FAILURE;
            mensaje = e.getMessage();
        }

        return new BusRespuesta<>(
                estatus,
                String.valueOf(HttpStatus.OK).split(" ")[0],
                mensaje,
                lsResultado
        );
    }
    //</editor-fold>

    //<editor-fold desc="Funciones POST" defaultstate="collapsed">
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "CONSULTACATTITULOCARRERA", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<TituloCarreraModel>> getTituloCarrera(@RequestParam("IDTICARRERA") String idTipoCarrera) throws BusException {
        return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                datosServidorPublicoService.getTituloCarrera(idTipoCarrera));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "actualizarDatosPersonales", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuestaSP<List<MsgRespuesta>> updDatosPersonales(
            @RequestParam("CLAVESERVIDORPUBLICO") String CLAVESERVIDORPUBLICO,
            @RequestParam("IDDATOSPERSONALES") String IDDATOSPERSONALES,
            @RequestParam("TELEFONO") String TELEFONO,
            @RequestParam("CORREOELECTRONICO") String CORREOELECTRONICO,
            @RequestParam("IDESTADO") String IDESTADO,
            @RequestParam("IDMUNICIPIO") String IDMUNICIPIO,
            @RequestParam("IDCOLONIA") String IDCOLONIA,
            @RequestParam("CALLE") String CALLE,
            @RequestParam("NUMEXTERIOR") String NUMEXTERIOR,
            @RequestParam("NUMINTERIOR") String NUMINTERIOR,
            @RequestParam("CODIGOPOSTAL") String CODIGOPOSTAL,
            @RequestParam("IDNIVELESTUDIOS") String IDNIVELESTUDIOS,
            @RequestParam("IDESTADOCIVIL") String IDESTADOCIVIL
    ) throws BusException {
        return new BusRespuestaSP<>(Integer.parseInt(String.valueOf(HttpStatus.OK).split(" ")[0]), "success", true,
                datosServidorPublicoService.updDatosPersonales(CLAVESERVIDORPUBLICO, IDDATOSPERSONALES, TELEFONO, CORREOELECTRONICO,
                        IDESTADO, IDMUNICIPIO, IDCOLONIA, CALLE, NUMEXTERIOR, NUMINTERIOR,
                        CODIGOPOSTAL, IDNIVELESTUDIOS, IDESTADOCIVIL));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "gestionarDatosProfesionales", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuestaSP<List<MsgRespuesta>> gestionarDatosProfesionales(
            @RequestParam("IDDATOPROFESIONAL") String IDDATOPROFESIONAL,
            @RequestParam("IDSERVIDORPUBLICO") String IDSERVIDORPUBLICO,
            @RequestParam("IDNIVELESTUDIOS") String IDNIVELESTUDIOS,
            @RequestParam("FECHAEMISIONCERTIFICADO") String FECHAEMISIONCERTIFICADO,
            @RequestParam("FECHAVIGENCIACERTIFICADO") String FECHAVIGENCIACERTIFICADO,
            @RequestParam("NOMBRECERTIFICADO") String NOMBRECERTIFICADO,
            @RequestParam("IDTIPOCERTIFICADO") String IDTIPOCERTIFICADO,
            @RequestParam("NOCERTIFICADO") String NOCERTIFICADO,
            @RequestParam("IDEMISORCERTIFICADO") String IDEMISORCERTIFICADO,
            @RequestParam("DURACION") String DURACION,
            @RequestParam("TIPODURACION") String TIPODURACION
    ) throws BusException {
        return new BusRespuestaSP<>(Integer.parseInt(String.valueOf(HttpStatus.OK).split(" ")[0]), "success", true,
                datosServidorPublicoService.gestionarDatosProfesionales(IDDATOPROFESIONAL, IDSERVIDORPUBLICO, IDNIVELESTUDIOS, FECHAEMISIONCERTIFICADO,
                        FECHAVIGENCIACERTIFICADO, NOMBRECERTIFICADO, IDTIPOCERTIFICADO, NOCERTIFICADO,
                        IDEMISORCERTIFICADO, DURACION, TIPODURACION));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "eliminarDatosProfesionales", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuestaSP<Integer> eliminarDatosProfesionales(
            @RequestParam("IDSERVIDORPUBLICO") String IDSERVIDORPUBLICO,
            @RequestParam("IDREGISTRODATOPROFESIONAL") String IDREGISTRODATOPROFESIONAL
    ) throws BusException {
        return new BusRespuestaSP<>(Integer.parseInt(String.valueOf(HttpStatus.OK).split(" ")[0]), "success", true,
                datosServidorPublicoService.eliminarDatosProfesionales(IDSERVIDORPUBLICO, IDREGISTRODATOPROFESIONAL));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "actualizarDatosGenerales",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuestaSP<List<MsgRespuesta>> actualizarDatosGenerales(
            @RequestParam("claveServidor") String claveServidor,
            @RequestParam("telefono") String telefono,
            @RequestParam("mail") String mail,
            @RequestParam("idNivelEstudios") String idNivelEstudios,
            @RequestParam("idEstadoCivil") String idEstadoCivil,
            @RequestParam("idTipoDireccionPersonal") int idTipoDireccionPersonal,
            @RequestParam("idEstadoPersonal") String idEstadoPersonal,
            @RequestParam("idMunicipioPersonal") String idMunicipioPersonal,
            @RequestParam("idColoniaPersonal") String idColoniaPersonal,
            @RequestParam("callePersonal") String callePersonal,
            @RequestParam("numeroExteriorPersonal") String numeroExteriorPersonal,
            @RequestParam("numeroInteriorPersonal") String numeroInteriorPersonal,
            @RequestParam("codigoPostalPersonal") String codigoPostalPersonal,
            @RequestParam("idTipoDireccionFiscal") String idTipoDireccionFiscal,
            @RequestParam("idEstadoFiscal") String idEstadoFiscal,
            @RequestParam("idMunicipioFiscal") String idMunicipioFiscal,
            @RequestParam("idColoniaFiscal") String idColoniaFiscal,
            @RequestParam("calleFiscal") String calleFiscal,
            @RequestParam("numeroExteriorFiscal") String numeroExteriorFiscal,
            @RequestParam("numeroInteriorFiscal") String numeroInteriorFiscal,
            @RequestParam("codigoPostalFiscal") String codigoPostalFiscal
    ) throws BusException {
        boolean estado;
        List<MsgRespuesta> lsRespuesta = new ArrayList<>();

        try {
            Logger.getLogger(DatosServidorPublicoController.class.getName()).log(Level.INFO, "REV: Llamando servicio actualizarDatosGenerales");
            
            lsRespuesta = this.datosServidorPublicoService.actualizarDatosGenerales(
                    claveServidor, telefono, mail, idNivelEstudios, idEstadoCivil,
                    idTipoDireccionPersonal, idEstadoPersonal, idMunicipioPersonal,
                    idColoniaPersonal, callePersonal, numeroExteriorPersonal,
                    numeroInteriorPersonal, codigoPostalPersonal, idTipoDireccionFiscal,
                    idEstadoFiscal, idMunicipioFiscal, idColoniaFiscal, calleFiscal,
                    numeroExteriorFiscal, numeroInteriorFiscal, codigoPostalFiscal);

            mensaje = Constantes.MESSAGE_SUCCESS;
            estado = true;
            
            Logger.getLogger(DatosServidorPublicoController.class.getName()).log(Level.INFO, "REV: Servicio consumido correctamente");
        } catch (Exception e) {
            mensaje = e.getMessage();
            estado = false;
            Logger.getLogger(DatosServidorPublicoController.class.getName()).log(Level.SEVERE, e.getMessage(), e);
        }

        return new BusRespuestaSP<>(
                Integer.parseInt(String.valueOf(HttpStatus.OK).split(" ")[0]),
                mensaje,
                estado,
                lsRespuesta
        );
    }
    //</editor-fold>
}
