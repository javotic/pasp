package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ProcesoEscalafonarioVigenteDAO {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    @PersistenceContext
    EntityManager entityManager;

    @Value("${edomex.database.evaluacion.schema}")
    private String schema;
    //</editor-fold>

    //<editor-fold desc="Funciones SELECT" defaultstate="collapsed">
    //@Query(value = "SELECT * FROM dbo.escalafonPlazasDisponibles(?,?) ORDER BY 1", nativeQuery = true)
    public List<Object[]> escalafonPlazasDisponibles(String claveServidorPublico, String tipo) {
        claveServidorPublico = claveServidorPublico == null ? null : "'" + claveServidorPublico + "'";
        tipo = tipo == null ? null : "'" + tipo + "'";

        String sql = "SELECT * FROM " + schema + ".dbo.escalafonPlazasDisponibles(" + claveServidorPublico + ", " + tipo + ") ORDER BY 1";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    //@Query(value = "SELECT * FROM dbo.escalafonAplicaProcesoVigente(?)", nativeQuery = true)
    public List<Object[]> escalafonAplicaProcesoVigente(String claveServidorPublico) {
        claveServidorPublico = claveServidorPublico == null ? null : "'" + claveServidorPublico + "'";

        String sql = "SELECT * FROM " + schema + ".dbo.escalafonAplicaProcesoVigente(" + claveServidorPublico + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    //@Query(value = "SELECT * FROM dbo.escalafonEstadoInscripcion(?,?)", nativeQuery = true)
    public List<Object[]> escalafonEstadoInscripcion(String claveServidorPublico, String idProceso) {
        claveServidorPublico = claveServidorPublico == null ? null : "'" + claveServidorPublico + "'";
        idProceso = idProceso == null ? null : "'" + idProceso + "'";

        String sql = "SELECT * FROM " + schema + ".dbo.escalafonEstadoInscripcion(" + claveServidorPublico + ", " + idProceso + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    /*
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "DECLARE @RESPUESTA BIT \n "
			+ "EXEC escalafonPreinscripcion ?, ?, ?, @RESPUESTA OUTPUT \n", nativeQuery = true)
	public Integer inscripcion(String idServidorPublico, String idPlaza, String idProcesoVigente);
     */
    //@Query(value = "SELECT * FROM dbo.escalafonProcesoActivo()", nativeQuery = true)
    public List<Object[]> escalafonProcesoVigente() {
        String sql = "SELECT * FROM " + schema + ".dbo.escalafonProcesoActivo()";
        return entityManager.createNativeQuery(sql).getResultList();
    } 
    
    //@Query(value = "SELECT * FROM dbo.escalafonSesionesAsesoria(?)", nativeQuery = true)
    public List<Object[]> escalafonSesionesAsesoria(String idProcesoVigente) {
        idProcesoVigente = idProcesoVigente == null ? null : "'" + idProcesoVigente + "'";

        String sql = "SELECT * FROM " + schema + ".dbo.escalafonSesionesAsesoria(" + idProcesoVigente + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    //@Query(value = "SELECT * FROM dbo.escalafonSesionesExamen(?)", nativeQuery = true)
    public List<Object[]> escalafonSesionesExamen(String idProcesoVigente) {
        idProcesoVigente = idProcesoVigente == null ? null : "'" + idProcesoVigente + "'";

        String sql = "SELECT * FROM " + schema + ".dbo.escalafonSesionesExamen(" + idProcesoVigente + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    //@Query(value = "SELECT * FROM dbo.CONSULTA_DATOS_PROFESIONALES(?)", nativeQuery = true)
    public List<Object[]> consultarDatosProfesionales(String idServidorPublico) {
        idServidorPublico = idServidorPublico == null ? null : "'" + idServidorPublico + "'";

        String sql = "SELECT * FROM dbo.CONSULTA_DATOS_PROFESIONALES(" + idServidorPublico + ") ORDER BY 3 DESC";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    //@Query(value = "SELECT dbo.escalafonConvocatoriaDoc(?,?)", nativeQuery = true)
    public byte[] descargaConvocatoria(String idServidorPublico, String claveProceso) {
        idServidorPublico = idServidorPublico == null ? null : "'" + idServidorPublico + "'";
        claveProceso = claveProceso == null ? null : "'" + claveProceso + "'";

        String sql = "SELECT " + schema + ".dbo.escalafonConvocatoriaDoc(" + idServidorPublico + ", " + claveProceso + ")";
        return (byte[]) entityManager.createNativeQuery(sql).getSingleResult();
    }

    public List<Integer> escalafonActualizaInfo() {

        String sql = "SELECT " + schema + ".dbo.escalafonActualizaInfo()";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    /**
     * Obtener listado de plazas disponibles con semaforo de ocupacion.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 04/08/2022.
     * @version 1.1, 09/05/2025, Javier Alvarado, Adicion de campo de inscritos.
     * @param claveServidorPublico Clave del servidor publico a buscar
     * @param tipo Tipo de plazas a obtener
     * @return Lista de Object[] con los datos obtenidos
     */
    public List<Object[]> escalafonPlazasDisponiblesSemaforo(String claveServidorPublico, String tipo) {
        claveServidorPublico = claveServidorPublico == null ? null : "'" + claveServidorPublico + "'";
        tipo = tipo == null ? null : "'" + tipo + "'";

        String sql = "SELECT "
                + " numero, nombrePuesto, jornadaLaboral, nivel, rango,"
                + " noPlaza, percepcionMensuales, adscripcion, ubicacionTrabajo, idEstatusPlaza,"
                + " puntajeEscalafonarioMinimo, escolaridadMinimaGen, escolaridadMinimaNat, experienciaMinima, conocimientos,"
                + " idPlaza, idEscolaridadMinimaGen, idEscolaridadMinimaNat, rutaGuiaEstudio, idPuesto, "
                + " puestoInferior, pVisible, semaforo, inscritos" 
                + " FROM " 
                + schema + ".dbo.escalafonPlazasDisponibles(" 
                + claveServidorPublico + ", " 
                + tipo + 
                ") ORDER BY 1";
        return entityManager.createNativeQuery(sql).getResultList();
    }
    
    /**
     * Obtener proceso escalafonario vigente.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 08/08/2022.
     * @return Listado con el proceso escalafonario vigentte
     */
    public List<Object[]> obtenerProcesoEscalafonarioVigente() {
        String sql = "SELECT"
                + " idProcesoVigente, nombreProcesoEvaluacion, fechaInicioProcesoGeneral,"
                + " fechaFinProcesoGeneral, descripcionProcesoVigente, fechaInicioAdmin,"
                + " fechaFinAdmin, tpProceso"
                + " FROM " + schema + ".dbo.escalafonProcesoActivo()";
        return entityManager.createNativeQuery(sql).getResultList();
    }   
    //</editor-fold>

    //<editor-fold desc="Funciones UPDATE" defaultstate="collapsed">
    public void actualizarProfesionalesProceso(String valoresInsert) {
        String sql
                = "DECLARE @CERTIF_LICEN as CERTIF_LICEN_TABLE \n"
                + "DECLARE @RESPUESTA VARCHAR(10) \n"
                + "INSERT @CERTIF_LICEN (ID_HR,OR_CERTIF_LIC,CHK_SEL) \n"
                + "VALUES \n"
                + valoresInsert
                + "\n EXEC ACTUALIZA_DATOS_PROFE_SEL @CERTIF_LICEN, @RESPUESTA = @RESPUESTA OUTPUT;";
        entityManager.createNativeQuery(sql).getResultList();
    }
    //</editor-fold>
}
