/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.etiquetasparametros.models.repository;



import org.springframework.data.repository.CrudRepository;

import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.CtIdioma;

/**
 *
 * @author jolmv
 */

public interface IdiomaRepository extends CrudRepository<CtIdioma, Long>{


    
}