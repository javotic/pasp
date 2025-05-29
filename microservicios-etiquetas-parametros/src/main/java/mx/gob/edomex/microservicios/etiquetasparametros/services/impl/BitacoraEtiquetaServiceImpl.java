/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.etiquetasparametros.services.impl;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.BtEtiqueta;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import mx.gob.edomex.microservicios.etiquetasparametros.models.repository.BitacoraEtiquetaRepository;
import mx.gob.edomex.microservicios.etiquetasparametros.services.interf.BitacoraEtiquetaService;


/**
 *
 * @author jolmv
 */

@Service
public class BitacoraEtiquetaServiceImpl implements BitacoraEtiquetaService{
    
   private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BitacoraEtiquetaServiceImpl.class);
   
   @Autowired
   private BitacoraEtiquetaRepository articleRepository;
   
   @PersistenceContext
   EntityManager em;
   

    @Override
    @Transactional
    public void saveBitacoraEtiquetaModel(BtEtiqueta info) throws Exception{
        try{
//            em.merge(info.getDtEtiqueta());
//            em.merge(info.getCtUsuariosistema());
            em.persist(info);
            em.flush();
            
        }catch(Exception e){
           logger.error("Problemas al Guardar (ImplementsBitacoraetiquetamodel) ");
           throw new Exception("Problemas al Guardar (ImplementsBitacoraetiquetamodel) " + e);
        }
    }

    
    @Override
    public List<BtEtiqueta> searchAllBitacoraEtiquetaModel() throws Exception{
        List<BtEtiqueta> infoList = new ArrayList<>();
        try{
            infoList = (List<BtEtiqueta>) articleRepository.findAll();
        }catch(Exception e){
            logger.error("Problemas al Buscar la informacion General (ImplementsBitacoraetiquetamodel) ");
            throw new Exception("Problemas al Buscar (ImplementsBitacoraetiquetamodel) " + e);  
        }
        return infoList;
    }
    
    @Override
    public void deleteByIdBitacoraEtiquetaModel(BtEtiqueta info) throws Exception{
        try{
            
            articleRepository.deleteById(Long.parseLong(String.valueOf(info.getDtEtiqueta().getDsvaloretiqueta())));
        }catch(NumberFormatException e){
            throw new Exception("Problemas al Eliminar (ImplementsBitacoraetiquetamodel) " + e);  
        }
    }

   @Override
    public void updateBitacoraEtiquetaModel(BtEtiqueta info) throws Exception{
        try{
            articleRepository.save(info);
        }catch(NumberFormatException e){
            throw new Exception("Problemas al Actualizar (ImplementsBitacoraetiquetamodel) " + e);   
        }
    }
   
    
}
