/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.notificaciones.models.repository;


import org.springframework.data.repository.CrudRepository;

import mx.gob.edomex.microservicios.notificaciones.models.entity.DtNotificaciones;

/**
 *
 * @author jolmv
 */
public interface DtNotificacionesRepository extends CrudRepository<DtNotificaciones, Long>{


}