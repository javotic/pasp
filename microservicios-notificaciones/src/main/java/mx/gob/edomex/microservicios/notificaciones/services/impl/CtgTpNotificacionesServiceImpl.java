/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.notificaciones.services.impl;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.edomex.microservicios.notificaciones.models.entity.CtTiposNotificaciones;
import mx.gob.edomex.microservicios.notificaciones.models.repository.CtgTpNotificacionesRepository;
import mx.gob.edomex.microservicios.notificaciones.services.interf.CtTiposNotificacionesService;


/**
 *
 * @author jolmv
 */

@Service
public class CtgTpNotificacionesServiceImpl implements CtTiposNotificacionesService{
    
   private static final org.slf4j.Logger logger = LoggerFactory.getLogger(CtgTpNotificacionesServiceImpl.class);
   
   @Autowired
   private CtgTpNotificacionesRepository articleRepository;
   
   @PersistenceContext
   EntityManager em;
   

    @Override
    @Transactional
    public CtTiposNotificaciones saveTiposNotificacionesModel(CtTiposNotificaciones info) throws Exception{
        try{

            
    		return articleRepository.save(info);
            
        }catch(Exception e){
           logger.error("Problemas al Guardar (CtgTpNotificacionesServiceImpl) ");
           throw new Exception("Problemas al Guardar (CtgTpNotificacionesServiceImpl) " + e);
        }
    }

    
    @Override
    public List<CtTiposNotificaciones> searchAllTiposNotificacionesModel() throws Exception{
        List<CtTiposNotificaciones> infoList = new ArrayList<>();
        try{
            infoList = (List<CtTiposNotificaciones>) articleRepository.findAll();
        }catch(Exception e){
            logger.error("Problemas al Buscar la informacion General (CtgTpNotificacionesServiceImpl) ");
            throw new Exception("Problemas al Buscar (CtgTpNotificacionesServiceImpl) " + e);  
        }
        return infoList;
    }
    
    
    @Override
    //public List<CtTiposNotificaciones> searchByCveServPubTiposNotificaciones(String cveservidor) throws Exception{
    public List<Object[]> searchByCveServPubTiposNotificaciones(String cveservidor) throws Exception{
    	 try{
             System.out.println("Valor de cveservidor::::::::>  " + cveservidor);

             /*Query que trae las notificaciones en base a la lltiponotificacion y al cveservpublico*/
         	Query query = em.createQuery("select tipnot.descnotificacion, notif.llnotificaciones,  notif.fechanotificacion, notif.idnotificacionbus, notif.lltiponotificacion, notif.estatusnotificacion  from CtTiposNotificaciones tipnot, DtNotificaciones notif  where tipnot.lltiponotificacion = notif.lltiponotificacion and notif.cveservidorpublico = '"+ cveservidor +"'");
         	
             
             return query.getResultList();
         }catch(Exception e){
             logger.error("Problemas al Buscar la informacion General (CtgTpNotificacionesServiceImpl) ");
             throw new Exception("Problemas al Buscar (searchByCveServPubTiposNotificaciones) " + e);  
         }
    }
    
    
    @Override
    public void deleteByIdTiposNotificacionesModel(CtTiposNotificaciones info) throws Exception{
        try{
            
            articleRepository.deleteById(Long.parseLong(String.valueOf(info.getLltiponotificacion())));
            
        }catch(NumberFormatException e){
            throw new Exception("Problemas al Eliminar (CtgTpNotificacionesServiceImpl) " + e);  
        }
    }

   @Override
    public CtTiposNotificaciones updateTiposNotificacionesModel(CtTiposNotificaciones info) throws Exception{
        try{
        	return articleRepository.save(info);
        }catch(NumberFormatException e){
            throw new Exception("Problemas al Actualizar (CtgTpNotificacionesServiceImpl) " + e);   
        }
    }
   
    
}
