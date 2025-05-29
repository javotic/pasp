
package mx.gob.edomex.microservicios.servicios.sei.bus.service.impl;


import java.util.ArrayList;
import java.util.List;

import mx.gob.edomex.microservicios.servicios.sei.bus.dao.PagosDAO;
import mx.gob.edomex.microservicios.servicios.sei.bus.dto.PuntualidadAsistenciaDTO;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.BusException;
import mx.gob.edomex.microservicios.servicios.sei.bus.exceptions.InternalServerErrorException;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosBancarios;
import mx.gob.edomex.microservicios.servicios.sei.bus.service.PagosService;
import mx.gob.edomex.microservicios.servicios.sei.bus.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author smartinez
 */
@Service
public class PagosServiceImpl implements PagosService {
    private static final Logger LOG = LoggerFactory.getLogger(DatosServidorPublicoServiceImpl.class);
    
    @Autowired
    private PagosDAO pagosDAO;      

    
    @Override
    public List<DatosBancarios> consultarDatosBancarios(String idServidorPublico) throws BusException {
            Utils utils = new Utils();
            List<DatosBancarios> lstDatosBancarios = new ArrayList<>();
            try {
                    List<Object[]> lstResul = pagosDAO.consultarDatosBancarios(idServidorPublico);
                  
                    lstResul.forEach(x -> {                     
                            DatosBancarios itemBancario = new DatosBancarios();
                            itemBancario.setSERVIDORPUBLICO(utils.objectIsNULL(x[0]));
                            itemBancario.setNMSERVIDORPUBLICO(utils.objectIsNULL(x[1]));
                            itemBancario.setNUMEROCUENTA(utils.objectIsNULL(x[2]));
                            itemBancario.setIDBANCO(utils.objectIsNULL(x[3]));
                            itemBancario.setNMBANCO(utils.objectIsNULL(x[4]));
                            itemBancario.setIDTPPAGO(utils.objectIsNULL(x[5]));
                            itemBancario.setNMTPPAGO(utils.objectIsNULL(x[6]));

                            lstDatosBancarios.add(itemBancario);
                    });   
                    
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstDatosBancarios;
    }
    
    
    @Override
    public List<PuntualidadAsistenciaDTO> consultaDatosPuntyAsist(String claveServidor, String tpDato, String fecha1, String fecha2) throws BusException {
            Utils utils = new Utils();
            List<PuntualidadAsistenciaDTO> lstIncidencias = new ArrayList<>();
            try {
                    fecha1 = fecha1.equals("")?null: fecha1;
                    fecha2 = fecha2.equals("")?null: fecha2;
                    List<Object[]> lstResul = pagosDAO.consultaDatosPuntyAsist(claveServidor, tpDato, fecha1, fecha2);
                  
                    lstResul.forEach(x -> {                     
                            PuntualidadAsistenciaDTO itemIncidencia = new PuntualidadAsistenciaDTO();
                            
                            itemIncidencia.setIDINCIDENCIA(utils.objectIsNULL(x[0]));
                            itemIncidencia.setNMINCIDENCIA(utils.objectIsNULL(x[1]));
                            itemIncidencia.setFECHA(utils.objectIsNULL(x[2]));
                            itemIncidencia.setFECHAAPLICA(utils.objectIsNULL(x[3]));
                            itemIncidencia.setUNIDADES(utils.objectIsNULL(x[4]));
                            itemIncidencia.setTPUNIDADES(utils.objectIsNULL(x[5]));
                            itemIncidencia.setIMPORTE( utils.objectIsNULL(x[6]));
                            lstIncidencias.add(itemIncidencia);
                    });   
                    
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstIncidencias;
    }
    
    
    
     
    
    /*
    @Override
    public Roles getRolById(int idRol) throws BusException {
            Utils utils = new Utils();
            
            Roles rol = new Roles();
            return rol;
    }    
         
    @Override
    public  List<Menu> getMenuByIdRol(int idRol) throws BusException{
            Utils utils = new Utils();
            List<Menu> lstMenu = new ArrayList<>();
            try {
                    List<Object[]> lstResul = usuariosDAO.getMenuByIdRol(idRol);
                    lstResul.forEach(x -> {                     
                        Menu menu = new Menu();
                        menu.setIDMENU(Integer.valueOf(utils.objectIsNULL(x[0])));
                        menu.setDSMENU(utils.objectIsNULL(x[1]));
                        menu.setBOACTIVO(utils.objectIsNULL(x[2]));
                        menu.setNOORDEN(Integer.valueOf(utils.objectIsNULL(x[3])));
                        menu.setDSURL(utils.objectIsNULL(x[4]));
                        menu.setDSICONO(utils.objectIsNULL(x[5]));
                        menu.setDSTOOLTIP(utils.objectIsNULL(x[6]));

                        lstMenu.add(menu);
                    });   
                    
            } catch (Exception e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
            return lstMenu;
    }
  
    
    
    
    @Override
    public  List<Roles> getRoles() throws BusException{
            Utils utils = new Utils();
            List<Roles> lstUsuarios = new ArrayList<>();
            try {
                    List<Object[]> lstResul = usuariosDAO.getRoles();
                    
                    lstResul.forEach(x -> {                     
                            Roles rol = new Roles();
                            rol.setIDROL("".equals(utils.objectIsNULL(x[0]))? 0: Integer.valueOf(utils.objectIsNULL(x[0])));
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
    public Integer UpdInsRolUsuario(String idServidorPublico, Integer rolNew, String boActivo, String dependencias, String idAdmin) throws BusException{
		try {
                    SQLServerDataTable sourceDataTable = new SQLServerDataTable();
  
                    // Define metadata for the data table.  
                    sourceDataTable.addColumnMetadata("idServidorPublico" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("rolNew" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("boActivo" ,java.sql.Types.BIT);
                    sourceDataTable.addColumnMetadata("idUnidad" ,java.sql.Types.NVARCHAR);
                    sourceDataTable.addColumnMetadata("idAdmin" ,java.sql.Types.NVARCHAR);
      
                    String[] unidades = dependencias.split(",");
                    
                    if(dependencias.equals("")){
                        sourceDataTable.addRow(idServidorPublico, Integer.toString(rolNew) , boActivo.equals("1"), "", idAdmin);
                    }else{
                        for(String unidad : unidades) {                           
                            // Llevar tabla  
                            sourceDataTable.addRow(idServidorPublico, Integer.toString(rolNew) , boActivo.equals("1"), unidad, idAdmin);
                        }    
                    }
                    
                     System.out.println("database:" + databaseUser);
                     Connection conn =  DriverManager.getConnection(urlDataSource, databaseUser, databasePass);
                     SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.consultarRolSp ?, ?").unwrap(SQLServerCallableStatement.class);
                     pStmt.setStructured(1, "dbo.tableUsrRolUnidades", sourceDataTable); 
                     pStmt.registerOutParameter(2, java.sql.Types.VARCHAR);  

                     pStmt.execute();    
                     String SalidaMensaje = pStmt.getNString(2);

                     //El procedimiento almacenado manda error
                     if("0".equals(SalidaMensaje)){
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
    public ConfigProrrogaDTO getAdminProrroga(String claveParametro, Integer noDias, String dsusuario) throws BusException{
		ConfigProrrogaDTO prorroga = new ConfigProrrogaDTO();
        try {
                     System.out.println("database:" + databaseUser);
                     Connection conn =  DriverManager.getConnection(urlDataSource, databaseUser, databasePass);
                     SQLServerCallableStatement pStmt = conn.prepareCall("EXEC dbo.consultarConfigPortal ?, ?, ? ").unwrap(SQLServerCallableStatement.class);
                     pStmt.setString("idConfig", claveParametro); 
                     pStmt.setString("valConfig", (noDias==0?null:String.valueOf(noDias)));
                     pStmt.setString("idAdmin", (dsusuario.isEmpty()?null:dsusuario));

                     ResultSet resultData = pStmt.executeQuery();  
                    
                    // = pStmt.getResultSet();

                    while (resultData.next()) {
                        prorroga.setDSUSUARIO(resultData.getString("usuario"));
                        prorroga.setFCACTUALIZACION(resultData.getString("fecha"));
                        prorroga.setNODIAS(resultData.getString("valor"));
                    }
                             
                     conn.close();
            } catch (NumberFormatException | SQLException  e) {
                    LOG.error("Error {}", e);
                    throw new InternalServerErrorException("INTERNAL_SERVER_ERROR", "INTERNAL_SERVER_ERROR");
            }
        return prorroga;
    }    
    
    @Override
    public   List<ComboOrigen> getUnidades(String unidad, String seccion) throws BusException{
            Utils utils = new Utils();
            List<ComboOrigen> lstunidades = new ArrayList<>();
            try {
                
                String[] unidades = unidad.split(",");
                
                for(String und : unidades){
                    List<Object[]> lstResul = usuariosDAO.getUnidades(und, seccion);
                    
                    lstResul.forEach(x -> {                     
                            ComboOrigen item = new ComboOrigen();
                            item.setValue(utils.objectIsNULL(x[0]));
                            item.setLabel(utils.objectIsNULL(x[1]));
                            item.setOrigen(und);

                            lstunidades.add(item);
                    });  
                }
                
                if(unidades.length > 1){
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
    public List<Combo> consultarUsrUnidadesAsig(String idServidorPublico) throws BusException{
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
          
*/
}
