package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.ConfigProrrogaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ComboOrigen;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Menu;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Roles;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Usuarios;

/**
 *
 * @author smartinez
 */
public interface UsuariosService {
    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    List<Usuarios> getUsuariosByParam(String clave, String nombre) throws BusException;
    Roles getRolById(int idRol) throws BusException;
    List<Menu> getMenuByIdUsuario(int idUsuario) throws BusException;
    List<Roles> getRoles() throws BusException;
    Integer UpdInsRolUsuario(String idServidorPublico, Integer rolNew, String boActivo, String dependencias, String idAdmin) throws BusException;
    ConfigProrrogaDTO getAdminProrroga(String claveParametro, Integer noDias, String dsusuario) throws BusException;
    List<ComboOrigen> getUnidades(String unidad, String seccion) throws BusException;
    List<Combo> consultarUsrUnidadesAsig(String idServidorPublico) throws BusException;
    
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
    List<Usuarios> obtenerServidoresSubordinados(String claveSPBuscar, 
            String nombreBuscar, String claveSPSupervisor) throws BusException;
    //</editor-fold>
    
}
