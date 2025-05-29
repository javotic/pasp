/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.notificaciones.services.interf;

import java.util.List;

import mx.gob.edomex.microservicios.notificaciones.models.entity.DtNotificaciones;

/**
 *
 * @author jolmv
 */
public interface DtNotificacionesService {
    
    /**
     * Guarda un registro de DtNotificaciones de eventos
     * @param info el evento a guardar
     * @throws java.lang.Exception
     */
	Iterable<DtNotificaciones> saveDtNotificacionesModel(List<DtNotificaciones> info)throws Exception;;
    
    
    /**
     * Busca un registro de DtNotificaciones de eventos
     * @param info el evento a buscar
     * @return 
     * @throws java.lang.Exception
     */
    List<DtNotificaciones> searchAllDtNotificacionesModel()throws Exception;;
    
    /**
    * Elimina un registro de DtNotificaciones de eventos
    * @param info el evento a eliminar
     * @throws java.lang.Exception
    */
    void deleteByIdDtNotificacionesModel(DtNotificaciones info)throws Exception;;
    
    /**
     * Actualiza un registro de DtNotificaciones de eventos
     * @param info el evento a actualizar
     * @throws java.lang.Exception
     */
    DtNotificaciones updateDtNotificacionesModel(DtNotificaciones info) throws Exception;
    
    
    
    /**
     * Actualiza un registro de DtNotificaciones de eventos por medio de la llave (llnotificaciones)
     * @param info el evento a actualizar
     * @throws java.lang.Exception
     */
    int updateDtNotificacionesByLlnotifi(String llnotifi) throws Exception;
    
    
}
