package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.ArrayList;
import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.ConfigProrrogaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ComboOrigen;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Menu;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Roles;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Usuarios;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.UsuariosService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author smartinez
 */
@RestController
@RequestMapping("/usuarios")
public class UsuariosController {
    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String estatus;
    private String mensaje;
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private UsuariosService usuariosService;
    //</editor-fold>

    //<editor-fold desc="Funciones GET" defaultstate="collapsed">
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getUsuariosByParam", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<Usuarios>>
            getUsuariosByParam(@RequestParam("clave") String clave,
                    @RequestParam("nombre") String nombre) throws BusException {
        return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
                usuariosService.getUsuariosByParam(clave, nombre));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getRolById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<Roles>
            getRolById(@PathVariable("idRol") int idRol) throws BusException {
        return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                usuariosService.getRolById(idRol));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getMenuByIdUsuario", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu>
            getMenuByIdUsuario(@RequestParam("idUsuario") int idUsuario) throws BusException {
        return usuariosService.getMenuByIdUsuario(idUsuario);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<Roles>>
            getRoles() throws BusException {
        return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                usuariosService.getRoles());
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getAdminProrroga", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<ConfigProrrogaDTO>
            getAdminProrroga(@RequestParam("claveParametro") String claveParametro,
                    @RequestParam("noDias") Integer noDias,
                    @RequestParam("dsusuario") String dsusuario) throws BusException {
        return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                usuariosService.getAdminProrroga(claveParametro, noDias, dsusuario));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getUnidades", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<ComboOrigen>>
            getUnidades(@RequestParam("unidad") String unidad,
                    @RequestParam("seccion") String seccion) throws BusException {
        return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                usuariosService.getUnidades(unidad, seccion));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarUsrUnidadesAsig", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<Combo>>
            getUnidades(@RequestParam("idServidorPublico") String idServidorPublico) throws BusException {
        return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                usuariosService.consultarUsrUnidadesAsig(idServidorPublico));
    }

    /**
     * Obtener listado de usuarios subordinados de un servidor público en
     * particular.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 14/01/2022.
     * @param claveSPBuscar Clave del servidor publico del que se desea buscar la informacion.
     * @param nombreBuscar Nombre del servidor publico del que se desea buscar la informacion.
     * @param claveSPSupervisor Clave del servidor publico que realiza la busqueda.
     * @return Lista de servidores publicos concordantes con los filtos de informacion.
     * @throws BusException de procesamiento de informacion
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "obtenerServidoresSubordinados",
            method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<Usuarios>> obtenerServidoresSubordinados(
            @RequestParam("claveSPBuscar") String claveSPBuscar,
            @RequestParam("nombreBuscar") String nombreBuscar,
            @RequestParam("claveSPSupervisor") String claveSPSupervisor) throws BusException {
        
        List<Usuarios> lsUsuarios = new ArrayList<>();
        
        try {
            lsUsuarios = this.usuariosService.obtenerServidoresSubordinados(
                    claveSPBuscar, 
                    nombreBuscar, 
                    claveSPSupervisor
            );
            estatus = Constantes.STATUS_SUCCESS;
            mensaje = Constantes.MESSAGE_SUCCESS;
        } catch (Exception e) {
            estatus = Constantes.STATUS_FAILURE;
            mensaje = e.getMessage();
        }
        
        return new BusResponse<>(
                estatus,
                String.valueOf(HttpStatus.OK).split(" ")[0],
                mensaje,
                lsUsuarios
        );
    }
    //</editor-fold>

    //<editor-fold desc="Funciones POST" defaultstate="collapsed">
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "UpdInsRolUsuario", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<Integer>
            UpdInsRolUsuario(@RequestParam("idServidorPublico") String idServidorPublico,
                    @RequestParam("rolNew") Integer rolNew,
                    @RequestParam("boActivo") String boActivo,
                    @RequestParam("dependencias") String dependencias,
                    @RequestParam("idAdmin") String idAdmin) throws BusException {
        return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                usuariosService.UpdInsRolUsuario(idServidorPublico, rolNew, boActivo, dependencias, idAdmin));
    }
    //</editor-fold>             
}
