/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.etiquetasparametros.services.interf;

import java.util.List;
import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.DtEtiqueta;

/**
 *
 * @author jolmv
 */
public interface EtiquetaValorService {
    
    /**
     * Guarda un registro de bitacora de eventos
     * @param info el evento a guardar
     * @throws java.lang.Exception
     */
    void saveEtiquetaValorModel(DtEtiqueta info)throws Exception;
    
    
    /**
     * Busca un registro de bitacora de eventos
     * @return 
     * @throws java.lang.Exception
     */
    List<DtEtiqueta> searchAllEtiquetaValorModel()throws Exception;
    
     /**
     * Busca todas la etiquetas Generales por URL y Idioma
     * @return 
     * @throws java.lang.Exception
     */
    List<DtEtiqueta> searchEtiquetasByLanguageURLGeneral(String URL, String Idioma)throws Exception;
    
    /**
    * Elimina un registro de bitacora de eventos
    * @param info el evento a eliminar
     * @throws java.lang.Exception
    */
    void deleteByIdEtiquetaValorModel(DtEtiqueta info)throws Exception;
    
    /**
     * Actualiza un registro de bitacora de eventos
     * @param info el evento a actualizar
     * @throws java.lang.Exception
     */
    void updateEtiquetaValorModel(DtEtiqueta info) throws Exception;
    
}
