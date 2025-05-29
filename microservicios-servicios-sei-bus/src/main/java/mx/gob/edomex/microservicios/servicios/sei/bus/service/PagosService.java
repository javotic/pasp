package mx.gob.edomex.microservicios.servicios.sei.bus.service;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.PuntualidadAsistenciaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosBancarios;

/**
 *
 * @author smartinez
 */
public interface PagosService {
    
    List<DatosBancarios> consultarDatosBancarios(String idServidorPublico) throws BusException;
    List<PuntualidadAsistenciaDTO> consultaDatosPuntyAsist(String claveServidor, String tpDato, String fecha1, String fecha2) throws BusException;
    /*
    Roles getRolById(int idRol) throws BusException;
    List<Menu> getMenuByIdRol(int idRol) throws BusException;
    List<Roles> getRoles() throws BusException;
    Integer UpdInsRolUsuario(String idServidorPublico, Integer rolNew, String boActivo, String dependencias, String idAdmin) throws BusException;
    ConfigProrrogaDTO getAdminProrroga(String claveParametro, Integer noDias, String dsusuario) throws BusException;
    List<ComboOrigen> getUnidades(String unidad, String seccion) throws BusException;
    List<Combo> consultarUsrUnidadesAsig(String idServidorPublico) throws BusException;
    */
}
