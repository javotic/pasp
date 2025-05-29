/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.etiquetasparametros.services.impl;


import java.util.ArrayList;
import java.util.List;
import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.CtEtiqueta;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.gob.edomex.microservicios.etiquetasparametros.models.repository.EtiquetaRepository;
import mx.gob.edomex.microservicios.etiquetasparametros.services.interf.EtiquetaService;


/**
 *
 * @author jolmv
 */

@Component
public class EtiquetaServiceImpl implements EtiquetaService{
    
   private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EtiquetaServiceImpl.class);
   
   @Autowired
   private EtiquetaRepository articleRepository;
   
   

    @Override
    public void saveEtiquetaModel(CtEtiqueta info) throws Exception{
        try{   
            articleRepository.save(info);
        }catch(Exception e){
           logger.error("Problemas al Guardar (ImplementsEtiquetamodel) ");
           throw new Exception("Problemas al Guardar (ImplementsEtiquetamodel) " + e);
        }
    }

    @Override
    public List<CtEtiqueta> searchAllEtiquetaModel() throws Exception{
        List<CtEtiqueta> infoList = new ArrayList<>();
        try{
            infoList = (List<CtEtiqueta>) articleRepository.findAll();
        }catch(Exception e){
            logger.error("Problemas al Buscar la informacion General (ImplementsEtiquetamodel) ");
            throw new Exception("Problemas al Buscar (ImplementsEtiquetamodel) " + e);  
        }
        return infoList;
    }

    @Override
    public void deleteByIdEtiquetaModel(CtEtiqueta info) throws Exception{
        try{
            
            articleRepository.deleteById(Long.parseLong(String.valueOf(info.getLletiqueta())));
        }catch(NumberFormatException e){
            throw new Exception("Problemas al Eliminar (ImplementsEtiquetamodel) " + e);  
        }
    }

    @Override
    public void updateEtiquetaModel(CtEtiqueta info) throws Exception{
        try{
           articleRepository.save(info);
        }catch(NumberFormatException e){
            throw new Exception("Problemas al Actualizar (ImplementsEtiquetamodel) " + e);   
        }
    }
    
  
  

        
     
 
           
    
}
