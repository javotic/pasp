/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.etiquetasparametros.services.interf;

import java.util.List;
import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.CtEtiqueta;

/**
 *
 * @author jolmv
 */
public interface EtiquetaService {
    
    /**
     * Guarda un registro de CtEtiqueta de eventos
     * @param info el evento a guardar
     * @throws java.lang.Exception
     */
    void saveEtiquetaModel(CtEtiqueta info)throws Exception;;
    
    
    /**
     * Busca un registro de CtEtiqueta de eventos
     * @param info el evento a buscar
     * @return 
     * @throws java.lang.Exception
     */
    List<CtEtiqueta> searchAllEtiquetaModel()throws Exception;;
    
    /**
    * Elimina un registro de CtEtiqueta de eventos
    * @param info el evento a eliminar
     * @throws java.lang.Exception
    */
    void deleteByIdEtiquetaModel(CtEtiqueta info)throws Exception;;
    
    /**
     * Actualiza un registro de CtEtiqueta de eventos
     * @param info el evento a actualizar
     * @throws java.lang.Exception
     */
    void updateEtiquetaModel(CtEtiqueta info) throws Exception;
    
}
