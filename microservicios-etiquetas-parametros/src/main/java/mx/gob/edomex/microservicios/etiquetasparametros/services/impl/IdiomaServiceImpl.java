/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.etiquetasparametros.services.impl;


import java.util.ArrayList;
import java.util.List;
import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.CtIdioma;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mx.gob.edomex.microservicios.etiquetasparametros.models.repository.IdiomaRepository;
import mx.gob.edomex.microservicios.etiquetasparametros.services.interf.IdiomaService;


/**
 *
 * @author jolmv
 */

@Component
public class IdiomaServiceImpl implements IdiomaService{
    
   private static final org.slf4j.Logger logger = LoggerFactory.getLogger(IdiomaServiceImpl.class);
   
   @Autowired
   private IdiomaRepository articleRepository;
   
   

    @Override
    public void saveIdiomaModel(CtIdioma info) throws Exception{
        try{
            articleRepository.save(info);
        }catch(Exception e){
           logger.error("Problemas al Guardar (ImplementsIdiomamodel) ");
           throw new Exception("Problemas al Guardar (ImplementsIdiomamodel) " + e);
        }
    }

    @Override
    public List<CtIdioma> searchAllIdiomaModel() throws Exception{
        List<CtIdioma> infoList = new ArrayList<>();
        try{
            infoList = (List<CtIdioma>) articleRepository.findAll();
        }catch(Exception e){
            logger.error("Problemas al Buscar la informacion General (ImplementsIdiomamodel) ");
            throw new Exception("Problemas al Buscar (ImplementsIdiomamodel) " + e);  
        }
        return infoList;
    }

    @Override
    public void deleteByIdIdiomaModel(CtIdioma info) throws Exception{
        try{
            articleRepository.deleteById(Long.parseLong(String.valueOf(info.getLlidioma())));
        }catch(NumberFormatException e){
            throw new Exception("Problemas al Eliminar (ImplementsIdiomamodel) " + e);  
        }
    }

    @Override
    public void updateIdiomaModel(CtIdioma info) throws Exception{
        try{
            articleRepository.save(info);
        }catch(Exception e){
            throw new Exception("Problemas al Actualizar (ImplementsIdiomamodel) " + e);   
        }
    }
    
  
  

        
     
 
           
    
}
