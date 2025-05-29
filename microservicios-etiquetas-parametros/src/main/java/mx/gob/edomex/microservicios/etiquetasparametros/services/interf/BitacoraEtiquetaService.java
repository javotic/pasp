/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.etiquetasparametros.services.interf;

import java.util.List;
import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.BtEtiqueta;

/**
 *
 * @author jolmv
 */
public interface BitacoraEtiquetaService {
    
    /**
     * Guarda un registro de BtEtiqueta de eventos
     * @param info el evento a guardar
     * @throws java.lang.Exception
     */
    void saveBitacoraEtiquetaModel(BtEtiqueta info)throws Exception;;
    
    /**
     * Busca un registro de BtEtiqueta de eventos
     * @return 
     * @throws java.lang.Exception
     */
    List<BtEtiqueta> searchAllBitacoraEtiquetaModel()throws Exception;;
    
    /**
    * Elimina un registro de BtEtiqueta de eventos
    * @param info el evento a eliminar
     * @throws java.lang.Exception
    */
    void deleteByIdBitacoraEtiquetaModel(BtEtiqueta info)throws Exception;;
    
    /**
     * Actualiza un registro de BtEtiqueta de eventos
     * @param info el evento a actualizar
     * @throws java.lang.Exception
     */
    void updateBitacoraEtiquetaModel(BtEtiqueta info) throws Exception;
   
    
}
