package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.gob.edomex.microservicios.servicios.sei.bus.models.SeccionesEvaluacion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SeccionEvaluacionDAO {

    @PersistenceContext
    EntityManager entityManager;

    @Value("${edomex.database.evaluacion.schema}")
    private String schema;

    //@Query(value = "SELECT * FROM pevaluacion.dbo.SECCIONESEVALUACION2(?,?,?,?,?)", nativeQuery = true)
    public List<Object[]> consultarSeccionesEDD(String procesoVig, String evaluador, String auxiliar, String tipoResp, String idEvaluado) {
        procesoVig = procesoVig == null ? null : "'" + procesoVig + "'";
        evaluador = evaluador == null ? null : "'" + evaluador + "'";
        auxiliar = auxiliar == null ? null : "'" + auxiliar + "'";
        tipoResp = tipoResp == null ? null : "'" + tipoResp + "'";
        idEvaluado = idEvaluado == null ? null : "'" + idEvaluado + "'";
        String sql = "SELECT * FROM " + schema + ".dbo.SECCIONESEVALUACION2(" + procesoVig + "," + evaluador + "," + auxiliar + "," + tipoResp + "," + idEvaluado + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    //@Query(value = "SELECT * FROM pevaluacion.dbo.RESPUESTAS (?)", nativeQuery = true)
    public List<Object[]> getconsultarRespuestasCompetencias(String idProcesoActivo) {
        idProcesoActivo = idProcesoActivo == null ? null : "'" + idProcesoActivo + "'";
        String sql = "SELECT * FROM " + schema + ".dbo.RESPUESTAS (" + idProcesoActivo + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    //@Query(value = "SELECT * FROM pevaluacion.dbo.INSTRUCCIONESEDD (?)", nativeQuery = true)
    public List<Object[]> consultarInstruccionesllenadoEDD(String idProcesoActivo) {
        idProcesoActivo = idProcesoActivo == null ? null : "'" + idProcesoActivo + "'";
        String sql = "SELECT * FROM " + schema + ".dbo.INSTRUCCIONESEDD (" + idProcesoActivo + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }

    //@Query(value = "SELECT * FROM dbo.SECCIONESEVALUACIONDESEMPENIO (?)", nativeQuery = true)
    public List<Object[]> getseccionesEjecucionEvaluacionDesempenio(String idProcesoActivo) {
        idProcesoActivo = idProcesoActivo == null ? null : "'" + idProcesoActivo + "'";
        String sql = "SELECT * FROM dbo.SECCIONESEVALUACIONDESEMPENIO (" + idProcesoActivo + ")";
        return entityManager.createNativeQuery(sql).getResultList();
    }
}
