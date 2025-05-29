
package mx.gob.edomex.microservicios.servicios.sei.bus.controller;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.ConfigProrrogaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.PuntualidadAsistenciaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.EmisoresCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.PagosModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.ColoniaModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Catalogos.TipoCertificadoModel;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ComboOrigen;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosBancarios;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Menu;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Roles;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Usuarios;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusResponse;
import mx.gob.edomex.microservicios.servicios.sei.bus.response.BusRespuesta;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.CatalogosService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.DatosPuestoService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.PagosService;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.UsuariosService;
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
@RequestMapping("/pagos")
public class PagosController {
    
    @Autowired
    private PagosService pagosService;
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultarDatosBancarios", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusResponse<List<DatosBancarios>> 
        consultarDatosBancarios(@RequestParam("idServidorPublico") String idServidorPublico) throws BusException {
            return new BusResponse<>("Succes", String.valueOf(HttpStatus.OK).split(" ")[0], "OK",
                            pagosService.consultarDatosBancarios(idServidorPublico));
    }
    
        
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "consultaDatosPuntyAsist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<PuntualidadAsistenciaDTO>> 
           consultaDatosPuntyAsist(@RequestParam("claveServidor") String claveServidor ,
                                   @RequestParam("tpDato") String tpDato ,
                                   @RequestParam("fecha1") String fecha1 ,
                                   @RequestParam("fecha2") String fecha2 ) throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            pagosService.consultaDatosPuntyAsist(claveServidor, tpDato, fecha1, fecha2));
    }
           
    /*     
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getMenuByIdRol", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu>
           getMenuByIdRol(@RequestParam("idRol") int idRol) throws BusException {
            return usuariosService.getMenuByIdRol(idRol);
    }
           
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "getRoles", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public BusRespuesta<List<Roles>> 
           getRoles() throws BusException {
            return new BusRespuesta<>("success", String.valueOf(HttpStatus.OK).split(" ")[0], "success",
                            usuariosService.getRoles());
    }       
    
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

*/
}
