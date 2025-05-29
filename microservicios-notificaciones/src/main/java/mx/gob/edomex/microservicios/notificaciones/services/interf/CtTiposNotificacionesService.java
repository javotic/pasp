/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.notificaciones.services.interf;

import java.util.List;

import mx.gob.edomex.microservicios.notificaciones.models.entity.CtTiposNotificaciones;

/**
 *
 * @author jolmv
 */
public interface CtTiposNotificacionesService {
    
    /**
     * Guarda un registro de CtTiposnotificaciones de eventos
     * @param info el evento a guardar
     * @throws java.lang.Exception
     */
	CtTiposNotificaciones saveTiposNotificacionesModel(CtTiposNotificaciones info)throws Exception;
    
    /**
     * Busca un registro de CtTiposnotificaciones de eventos
     * @return 
     * @throws java.lang.Exception
     */
    List<CtTiposNotificaciones> searchAllTiposNotificacionesModel()throws Exception;
    
    
    /**
     * Busca un registro de CtTiposnotificaciones de eventos
     * @return 
     * @throws java.lang.Exception
     */
    //List<CtTiposNotificaciones> searchByCveServPubTiposNotificaciones(String cveservidor)throws Exception;
    List<Object[]> searchByCveServPubTiposNotificaciones(String cveservidor)throws Exception;
    
    
    /**
    * Elimina un registro de CtTiposnotificaciones de eventos
    * @param info el evento a eliminar
     * @throws java.lang.Exception
    */
    void deleteByIdTiposNotificacionesModel(CtTiposNotificaciones info)throws Exception;
    
    /**
     * Actualiza un registro de CtTiposnotificaciones de eventos
     * @param info el evento a actualizar
     * @throws java.lang.Exception
     */
    CtTiposNotificaciones updateTiposNotificacionesModel(CtTiposNotificaciones info) throws Exception;
   
    
}
