/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.etiquetasparametros.services.interf;

import java.util.List;
import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.CtIdioma;

/**
 *
 * @author jolmv
 */
public interface IdiomaService {
    
    /**
     * Guarda un registro de CtIdioma de eventos
     * @param info el evento a guardar
     * @throws java.lang.Exception
     */
    void saveIdiomaModel(CtIdioma info)throws Exception;;
    
    
    /**
     * Busca un registro de CtIdioma de eventos
     * @param info el evento a buscar
     * @return 
     * @throws java.lang.Exception
     */
    List<CtIdioma> searchAllIdiomaModel()throws Exception;;
    
    /**
    * Elimina un registro de CtIdioma de eventos
    * @param info el evento a eliminar
     * @throws java.lang.Exception
    */
    void deleteByIdIdiomaModel(CtIdioma info)throws Exception;;
    
    /**
     * Actualiza un registro de CtIdioma de eventos
     * @param info el evento a actualizar
     * @throws java.lang.Exception
     */
    void updateIdiomaModel(CtIdioma info) throws Exception;
    
}
