/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.dao;

import java.util.List;
import mx.gob.edomex.microservicios.servicios.sei.bus.models.DatosServidorPublico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

/**
 *
 * Objeto de acceso a datos del proceso de incremento salaria.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 19/07/2022.
 */
@Component
public interface IncrementoSalarialDAO extends JpaRepository<DatosServidorPublico, String> {

    //<editor-fold desc="Funciones SELECT" defaultstate="collapsed">
    /**
     * Obtener datos de servidor publico relacionado con el incremento salarial.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 19/07/2022.
     * @param claveServidor Clave del servidor publico del que se obtendra la
     * informacion.
     * @return Lista con los datos del servidor publico encontrado.
     */
    @Query(value = "SELECT"
            + " claveServidorPublico, nombreCompletoServidorPublico, nombrePuesto, codigoPuesto, tipoServidorPublico"
            + " FROM obtenerServPubIncrSal(:claveServidor)", nativeQuery = true)
    public List<Object[]> obtenerServPubIncrSal(
            @Param("claveServidor") String claveServidor);

    /**
     * Obtener categorias de incremento salarial.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 19/07/2022.
     * @return Lista con las diferentes categorias obtenidas.
     */
    @Query(value = "SELECT"
            + " categoria, porcentaje"
            + " FROM obtenerCategoriasIncrSal()", nativeQuery = true)
    public List<Object[]> obtenerCategoriasIncrSal();
    
    /**
     * Obtener tabulador docente de acuerdo a clave de servidor publico.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/07/2022.
     * @param claveServidor Clave del servidor publico del que se obtendra la
     * informacion.
     * @return Lista con los datos del tabulador docente encontrado.
     */
    @Query(value = "SELECT"
            + " salarioMensual, isr, salarioMensualDespuesImpuestos, porcentajeIncremento,"
            + " sueldoBase, incrementoPorcentual, sueldoBaseFinal"
            + " FROM obtenerTabuladorDocIncrSal(:claveServidor)", nativeQuery = true)
    public List<Object[]> obtenerTabuladorDocIncrSal(
            @Param("claveServidor") String claveServidor);
    
    /**
     * Obtener tabulador burocrata de acuerdo a un codigo de puesto determinado.
     *
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 20/07/2022.
     * @param codigoPuesto Codigo de puesto del que se obtendra el tabulador burocrata.
     * @return Lista con los datos del tabulador burocrata encontrado.
     */
    @Query(value = "SELECT"
            + " sueldoBase, gratificacion, fortalecimientoSalario, despensa,"
            + " totalBruto, isr, issemym, totalNeto, año"
            + " FROM obtenerTabuladorBurIncrSal(:codigoPuesto)", nativeQuery = true)
    public List<Object[]> obtenerTabuladorBurIncrSal(
            @Param("codigoPuesto") String codigoPuesto);
    //</editor-fold>

}
