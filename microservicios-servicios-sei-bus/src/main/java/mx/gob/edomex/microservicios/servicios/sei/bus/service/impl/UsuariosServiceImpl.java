package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;

import com.microsoft.sqlserver.jdbc.SQLServerCallableStatement;
import com.microsoft.sqlserver.jdbc.SQLServerDataTable;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javax.transaction.Transactional;
import mx.gob.edomex.microservicios.servicios.sei.bus.dao.UsuariosDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.ConfigProrrogaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Combo;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.ComboOrigen;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Menu;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Roles;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.Usuarios;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.UsuariosService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Constantes;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

/**
 *
 * @author smartinez
 */
@Service
public class UsuariosServiceImpl implements UsuariosService {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private static final Logger LOG = LoggerFactory.getLogger(DatosServidorPublicoServiceImpl.class);
    //</editor-fold>

    //<editor-fold desc="Propiedades administradas" defaultstate="collapsed">
    @Autowired
    private UsuariosDAO usuariosDAO;
    @Value("${edomex.database.evaluacion.url}")
    private String urlDataSource;
    @Value("${edomex.database.evaluacion.user}")
    private String databaseUser;
    @Value("${edomex.database.evaluacion.pass}")
    private String databasePass;
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public List<Usuarios> getUsuariosByParam(String clave, String nombre) throws BusException {
        Utils utils = new Utils();
        List<Usuarios> lstUsuarios = new ArrayList<>();
        try {
            List<Object[]> lstResul = usuariosDAO.getUsuariosByParam(clave, nombre);

            lstResul.forEach(x -> {
                Usuarios usuario = new Usuarios();
                usuario.setID_PERSON(utils.objectIsNULL(x[0]));
                usuario.setNOMBRE(utils.objectIsNULL(x[1]));
                usuario.setAPELLIDO_P(utils.objectIsNULL(x[2]));
                usuario.setAPELLIDO_M(utils.objectIsNULL(x[3]));
                usuario.setBOACTIVO(utils.objectIsNULL(x[4]));

                Roles rol = new Roles();

                rol.setIDROL("".equals(utils.objectIsNULL(x[5])) ? 0 : Integer.valueOf(utils.objectIsNULL(x[5])));
                rol.setDSROL(utils.objectIsNULL(x[6]));
                rol.setBOACTIVO(utils.objectIsNULL(x[7]));
                usuario.setROL(rol);

                lstUsuarios.add(usuario);
            });

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return lstUsuarios;
    }

    @Override
    public Roles getRolById(int idRol) throws BusException {
        Utils utils = new Utils();

        Roles rol = new Roles();
        /*
            List<String> excepciones = new ArrayList<>();
            try {
                    
                    List<Object[]> lstResul = usuariosDAO.getRolById(idRol);
                    
                    lstResul.forEach(x -> {
                            PagosModel pago = new PagosModel();
                            pago.setIDSERVIDORPUBLICO(utils.objectIsNULL(x[0]));
                            pago.setIDPAGO(utils.objectIsNULL(x[1]));
                            pago.setESTATUSPAGO(utils.objectIsNULL(x[2]));
                            pago.setTIPOPAGO(utils.objectIsNULL(x[3]));
                            
                            SimpleDateFormat formateador = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            SimpleDateFormat parseador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            String fechaPago = utils.objectIsNULL(x[4]);
                            if(!"".equals(fechaPago)){
                                try {
                                    Date fechapagoDate = parseador.parse(fechaPago);
                                    fechaPago = formateador.format(fechapagoDate);
                                } catch (ParseException ex) {
                                    java.util.logging.Logger.getLogger(UsuariosServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
                                    excepciones.add("error");
                                }
                            }
                            
                            pago.setFECHAPAGO(fechaPago);
                            pago.setIMPORTEPAGOGRAVADO(utils.objectIsNULL(x[5]));
                            pago.setIMPORTEPAGOEXENTO(utils.objectIsNULL(x[6]));

                            lstPagos.add(pago);  
                    });   
                    
                    //Si no se pudo convertir la fecha manda error
                    if(excepciones.size() > 0){
                    throw new Exception("No se pudo convertir la fecha");
                    }
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
         */
        return rol;
    }

    @Override
    public List<Menu> getMenuByIdUsuario(int idUsuario) throws BusException {
        Utils utils = new Utils();
        List<Menu> lstMenu = new ArrayList<>();
        try {
            
            String claveServidorPublico = this.convertirClaveSP(idUsuario);
            
//            List<Object[]> lstResul = usuariosDAO.getMenuByIdUsuario(idUsuario);
            List<Object[]> lstResul = usuariosDAO.getMenuByIdUsuario(claveServidorPublico);
            lstResul.forEach(x -> {
                Menu menu = new Menu();
                menu.setIDMENU(Integer.valueOf(utils.objectIsNULL(x[0])));
                menu.setDSMENU(utils.objectIsNULL(x[1]));
                menu.setBOACTIVO(utils.objectIsNULL(x[2]));
                menu.setNOORDEN(Integer.valueOf(utils.objectIsNULL(x[3])));
                menu.setDSURL(utils.objectIsNULL(x[4]));
                menu.setDSICONO(utils.objectIsNULL(x[5]));
                menu.setDSTOOLTIP(utils.objectIsNULL(x[6]));
                menu.setDSCOLOR(utils.objectIsNULL(x[7]));
                lstMenu.add(menu);
            });

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return lstMenu;
    }

    @Override
    public List<Roles> getRoles() throws BusException {
        Utils utils = new Utils();
        List<Roles> lstUsuarios = new ArrayList<>();
        try {
            List<Object[]> lstResul = usuariosDAO.getRoles();

            lstResul.forEach(x -> {
                Roles rol = new Roles();
                rol.setIDROL("".equals(utils.objectIsNULL(x[0])) ? 0 : Integer.valueOf(utils.objectIsNULL(x[0])));
                rol.setDSROL(utils.objectIsNULL(x[1]));
                rol.setBOACTIVO(utils.objectIsNULL(x[2]));

                lstUsuarios.add(rol);
            });

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return lstUsuarios;
    }

    @Override
    public Integer UpdInsRolUsuario(String idServidorPublico, Integer rolNew, String boActivo, String dependencias, String idAdmin) throws BusException {
        try {
            SQLServerDataTable sourceDataTable = new SQLServerDataTable();

            // Define metadata for the data table.  
            sourceDataTable.addColumnMetadata("idServidorPublico", java.sql.Types.NVARCHAR);
            sourceDataTable.addColumnMetadata("rolNew", java.sql.Types.NVARCHAR);
            sourceDataTable.addColumnMetadata("boActivo", java.sql.Types.BIT);
            sourceDataTable.addColumnMetadata("idUnidad", java.sql.Types.NVARCHAR);
            sourceDataTable.addColumnMetadata("idAdmin", java.sql.Types.NVARCHAR);

            String[] unidades = dependencias.split(",");

            if (dependencias.equals("")) {
                sourceDataTable.addRow(idServidorPublico, Integer.toString(rolNew), boActivo.equals("1"), "", idAdmin);
            } else {
                for (String unidad : unidades) {
                    // Llevar tabla  
                    sourceDataTable.addRow(idServidorPublico, Integer.toString(rolNew), boActivo.equals("1"), unidad, idAdmin);
                }
            }

            System.out.println("database:" + databaseUser);
            Connection conn = DriverManager.getConnection(urlDataSource, databaseUser, databasePass);
            SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.consultarRolSp ?, ?").unwrap(SQLServerCallableStatement.class);
            pStmt.setStructured(1, "dbo.tableUsrRolUnidades", sourceDataTable);
            pStmt.registerOutParameter(2, java.sql.Types.VARCHAR);

            pStmt.execute();
            String SalidaMensaje = pStmt.getNString(2);

            //El procedimiento almacenado manda error
            if ("0".equals(SalidaMensaje)) {
                conn.close();
                throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            conn.close();
            return Integer.valueOf(SalidaMensaje);

        } catch (NumberFormatException | SQLException | InternalServerErrorException e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
    }

    @Modifying(clearAutomatically = true)
    @Transactional
    @Override
    public ConfigProrrogaDTO getAdminProrroga(String claveParametro, Integer noDias, String dsusuario) throws BusException {
        ConfigProrrogaDTO prorroga = new ConfigProrrogaDTO();
        try {
            System.out.println("database:" + databaseUser);
            Connection conn = DriverManager.getConnection(urlDataSource, databaseUser, databasePass);
            SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.consultarConfigPortal ?, ?, ? ").unwrap(SQLServerCallableStatement.class);
            pStmt.setString("idConfig", claveParametro);
            pStmt.setString("valConfig", (noDias == 0 ? null : String.valueOf(noDias)));
            pStmt.setString("idAdmin", (dsusuario.isEmpty() ? null : dsusuario));

            ResultSet resultData = pStmt.executeQuery();

            // = pStmt.getResultSet();
            while (resultData.next()) {
                prorroga.setDSUSUARIO(resultData.getString("usuario"));
                prorroga.setFCACTUALIZACION(resultData.getString("fecha"));
                prorroga.setNODIAS(resultData.getString("valor"));
            }

            conn.close();
        } catch (NumberFormatException | SQLException e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return prorroga;
    }

    @Override
    public List<ComboOrigen> getUnidades(String unidad, String seccion) throws BusException {
        Utils utils = new Utils();
        List<ComboOrigen> lstunidades = new ArrayList<>();
        try {

            String[] unidades = unidad.split(",");

            for (String und : unidades) {
                List<Object[]> lstResul = usuariosDAO.getUnidades(und, seccion);

                lstResul.forEach(x -> {
                    ComboOrigen item = new ComboOrigen();
                    item.setValue(utils.objectIsNULL(x[0]));
                    item.setLabel(utils.objectIsNULL(x[1]));
                    item.setOrigen(und);

                    lstunidades.add(item);
                });
            }

            if (unidades.length > 1) {
                Set<ComboOrigen> set = new HashSet<>(lstunidades);
                lstunidades.clear();
                lstunidades.addAll(set);
            }

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return lstunidades;
    }

    @Override
    public List<Combo> consultarUsrUnidadesAsig(String idServidorPublico) throws BusException {
        Utils utils = new Utils();
        List<Combo> lstunidades = new ArrayList<>();
        try {

            List<Object[]> lstResul = usuariosDAO.consultarUsrUnidadesAsig(idServidorPublico);

            lstResul.forEach(x -> {
                Combo item = new Combo();
                item.setValue(utils.objectIsNULL(x[0]));
                item.setLabel(utils.objectIsNULL(x[1]));

                lstunidades.add(item);
            });

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
        }
        return lstunidades;
    }

    @Override
    public List<Usuarios> obtenerServidoresSubordinados(String claveSPBuscar,
            String nombreBuscar, String claveSPSupervisor) throws BusException {
        Utils utils = new Utils();
        List<Usuarios> lsUsuarios = new ArrayList<>();
        try {
            List<Object[]> lsResultado = usuariosDAO.obtenerServidoresSubordinados(
                    claveSPBuscar, nombreBuscar, claveSPSupervisor
            );

            lsResultado.forEach(x -> {
                Usuarios usuario = new Usuarios();
                usuario.setID_PERSON(utils.objectIsNULL(x[0]));
                usuario.setNOMBRE(utils.objectIsNULL(x[1]));
                lsUsuarios.add(usuario);
            });

        } catch (Exception e) {
            LOG.error("Error {}", e);
            throw new InternalServerErrorException(
                    Constantes.STATUS_FAILURE_SERVICE,
                    e.getMessage()
            );
        }
        return lsUsuarios;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones privadas" defaultstate="collapsed">
    /**
     * Convertir una clave de SP de entero a cadena.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 13/07/2022.
     * @param claveSP Clave de servidor publico en tipo int.
     * @return clave de servidor publico convertido a cadena.
     */
    private String convertirClaveSP(int claveSP) {
        String claveServidor = String.valueOf(claveSP);

        if (claveServidor.length() == 9) {
            return claveServidor;
        }

        for (int i = claveServidor.length(); i < 9; i++) {
            claveServidor = "0" + claveServidor;
        }

        return claveServidor;
    }
    //</editor-fold>
}
