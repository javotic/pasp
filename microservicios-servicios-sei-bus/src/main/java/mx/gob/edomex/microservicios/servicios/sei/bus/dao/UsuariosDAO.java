/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 *
 * @author smartinez
 */
@Component
public class UsuariosDAO {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    @PersistenceContext
    EntityManager entityManager;

    @Value("${edomex.database.evaluacion.schema}")
    private String schema;
    //</editor-fold>

    //<editor-fold desc="Funciones SELECT" defaultstate="collapsed">
    //@Query(value = "SELECT * FROM M4CME_CT_USUARIOS", nativeQuery = true)
    public List<Object[]> getUsuariosByParam(String clave, String nombre) {
        String v_where = clave == null || "".equals(clave) ? "" : "P.STD_ID_PERSON = '" + clave + "'";
        String v_where_nombre = (nombre == null || "".equals(nombre) ? "" : "UPPER(P.STD_N_FAM_NAME_1 + ' ' + P.STD_N_MAIDEN_NAME + ' ' + P.STD_N_FIRST_NAME ) = '" + nombre.toUpperCase() + "'");

        if (!v_where.isEmpty() && v_where_nombre.isEmpty()) {
            v_where = " WHERE " + v_where;
        } else if (v_where.isEmpty() && !v_where_nombre.isEmpty()) {
            v_where = " WHERE " + v_where_nombre;
        } else if (!v_where.isEmpty() && !v_where_nombre.isEmpty()) {
            v_where = " WHERE " + v_where + " AND " + v_where_nombre;
        }

        String sql = "SELECT P.STD_ID_PERSON, P.STD_N_FIRST_NAME , P.STD_N_FAM_NAME_1, P.STD_N_MAIDEN_NAME, U.BOACTIVO, "
                + "R.IDROL, R.DSROL, R.BOACTIVO BOACTIVO_R"
                + " FROM " + schema + ".dbo.VW_STD_PERSON P LEFT JOIN  " + schema + ".dbo.M4CME_CT_USUARIOS U ON P.STD_ID_PERSON = U.STD_ID_PERSON "
                + " LEFT JOIN " + schema + ".dbo.M4CME_CT_ROLES R ON R.IDROL = U.IDROL "
                + v_where;
        return entityManager.createNativeQuery(sql).getResultList();
    }

    public List<Object[]> getRoles() {
        String sql = "SELECT IDROL, DSROL, BOACTIVO FROM " + schema + ".dbo.M4CME_CT_ROLES WHERE BOACTIVO = 1";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    /*
        public  List<Object[]> getMenuByIdRol(int idRol){
            String sql = "SELECT M.IDMENU, DSMENU, BOACTIVO, NOORDERN, DSURL, DSICONO, DSTOOLTIP FROM " + schema + ".dbo.M4CME_MENUS M\n" +
                         "INNER JOIN " + schema + ".dbo.M4CME_ROL_MENU RM ON RM.IDMENU = M.IDMENU  WHERE RM.IDROL = "+ idRol + " AND BOACTIVO = 1 ORDER BY NOORDERN";
            return entityManager.createNativeQuery(sql).getResultList();  
        }
     */
    public List<Object[]> getMenuByIdUsuario(int idUsuario) {
        String sql = "SELECT * FROM " + schema + ".dbo.consultarMenus('" + idUsuario + "') ORDER BY NOORDERN";

        return entityManager.createNativeQuery(sql).getResultList();
    }
    
    public List<Object[]> getMenuByIdUsuario(String claveServidorPublico) {
        String sql = "SELECT * FROM " + schema + ".dbo.consultarMenus('" + claveServidorPublico + "') ORDER BY NOORDERN";

        return entityManager.createNativeQuery(sql).getResultList();
    }

    public List<Object[]> getUnidades(String unidad, String seccion) {
        unidad = "".equals(unidad) ? "null" : "'" + unidad + "'";
        String sql = "SELECT  * FROM " + schema + ".dbo.consultarUsrUnidades(" + unidad + "," + seccion + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    public List<Object[]> consultarUsrUnidadesAsig(String idServidorPublico) {
        idServidorPublico = "".equals(idServidorPublico) ? "null" : "'" + idServidorPublico + "'";
        String sql = "SELECT  * FROM " + schema + ".dbo.consultarUsrUnidadesAsig(" + idServidorPublico + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    /*
        @Query(value = "SELECT * FROM dbo.CONSULTAR_LISTA_PAGOS(?,?,?)", nativeQuery = true)
	List<Object[]> getListadoPagos(String idServidorPublico, String fechaInicio, String fechaFin);
     */
 /*
        @Query(value = "SELECT * FROM dbo.CONSULTACATTIPOCERTIFICADO()", nativeQuery = true)
	List<Object[]> getTipoCertificados();
     */
    
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
     */
    public List<Object[]> obtenerServidoresSubordinados(String claveSPBuscar, 
            String nombreBuscar, String claveSPSupervisor) {
        
        //Si la clave del SP a buscar es la misma que la clave del SP que realiza la busqueda, se elimina la claveSPBuscar
        if(claveSPBuscar.equals(claveSPSupervisor)){
            claveSPBuscar = "";
        }

        String sql = "SELECT idServidor, nServidor "
                + " FROM " + schema + ".dbo.BuscarServidorLista('" 
                + claveSPBuscar + "', '"
                + nombreBuscar + "', '"
                + claveSPSupervisor + "' )";
        return entityManager.createNativeQuery(sql).getResultList();
    }
    //</editor-fold>
}
