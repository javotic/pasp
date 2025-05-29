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
import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.DtEtiqueta;
import mx.gob.edomex.microservicios.etiquetasparametros.models.repository.EtiquetaValorRepository;
import mx.gob.edomex.microservicios.etiquetasparametros.services.interf.EtiquetaValorService;


/**
 *
 * @author jolmv
 */

@Component
public class EtiquetaValorServiceImpl implements EtiquetaValorService{
    
   private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EtiquetaValorServiceImpl.class);
   
   @Autowired
   private EtiquetaValorRepository articleRepository;
   
   @PersistenceContext
   EntityManager em;

    @Override
    public void saveEtiquetaValorModel(DtEtiqueta info) throws Exception{
        try{
           articleRepository.save(info);
        }catch(Exception e){
           logger.error("Problemas al Guardar (ImplementsEtiquetavalormodel) ");
           throw new Exception("Problemas al Guardar (saveEtiquetaValorModel) " + e);
        }
    }

    @Override
    public List<DtEtiqueta> searchAllEtiquetaValorModel() throws Exception{
        List<DtEtiqueta> infoList = new ArrayList<>();
        try{
            infoList = (List<DtEtiqueta>) articleRepository.findAll();
        }catch(Exception e){
            logger.error("Problemas al Buscar la informacion General (ImplementsEtiquetavalormodel) ");
            throw new Exception("Problemas al Buscar (searchAllEtiquetaValorModel) " + e);  
        }
        return infoList;
    }
    
   @Override
    public List<DtEtiqueta> searchEtiquetasByLanguageURLGeneral(String URL, String Idioma) throws Exception{
        
        try{
            

            /*Query que trae las etiquetas en base a la URL y al Idioma*/
            //Query query = em.createQuery("select ve from Rlopcionetiquetavalor oev, Envaloretiqueta ve, Ctopciones op, Ctetiqueta et, Ctidioma id  where ve.lletiqueta.lletiqueta = et.lletiqueta and ve.llidioma.llidioma = id.llidioma and oev.ctopcione.llopcion = op.llopcion  and oev.ctetiqueta.lletiqueta = ve.lletiqueta.lletiqueta and  oev.ctopcione.url='Vista/etiquetas' and ve.llidioma.dsidioma='español (México)' and ve.lletiqueta.boactivo=true");
            //Query query = em.createQuery("select ve from CrcEtiquetaspagina oev, DtEtiqueta ve, CtPagina op, CtEtiqueta et, CtIdioma id  where ve.ctEtiqueta.lletiqueta = et.lletiqueta and ve.ctIdioma.llidioma = id.llidioma and oev.ctPagina.llpagina = op.llpagina  and oev.ctEtiqueta.lletiqueta = ve.ctEtiqueta.lletiqueta and  op.dsurlpagina='Vista/etiquetas' and ve.ctIdioma.dsidioma='español (México)' and ve.ctEtiqueta.boactivo=true");
        	Query query = em.createQuery("select ve from CrcEtiquetaspagina oev, DtEtiqueta ve, CtPagina op, CtEtiqueta et, CtIdioma id  where ve.ctEtiqueta.lletiqueta = et.lletiqueta and ve.ctIdioma.llidioma = id.llidioma and oev.ctPagina.llpagina = op.llpagina  and oev.ctEtiqueta.lletiqueta = ve.ctEtiqueta.lletiqueta and  op.dsurlpagina='"+ URL +"' and ve.ctIdioma.dsidioma='"+Idioma+"' and ve.ctEtiqueta.boactivo=true");
            
            return query.getResultList();
        }catch(Exception e){
            logger.error("Problemas al Buscar la informacion General (ImplementsEtiquetavalormodel) ");
            throw new Exception("Problemas al Buscar (searchEtiquetaLanguajeURL) " + e);  
        }
    }
    

    @Override
    public void deleteByIdEtiquetaValorModel(DtEtiqueta info) throws Exception{
        try{
            articleRepository.deleteById(Long.parseLong(String.valueOf(info.getDsvaloretiqueta())));
        }catch(NumberFormatException e){
            logger.error("Problemas al Buscar la informacion General (ImplementsEtiquetavalormodel) ");
            throw new Exception("Problemas al Eliminar (deleteByIdEtiquetaValorModel) " + e);  
        }
    }

    @Override
    public void updateEtiquetaValorModel(DtEtiqueta info) throws Exception{
        try{
           articleRepository.save(info);
        }catch(NumberFormatException e){
            logger.error("Problemas al Buscar la informacion General (ImplementsEtiquetavalormodel) ");
            throw new Exception("Problemas al Actualizar (updateEtiquetaValorModel) " + e);   
        }
    }

  
  
  

        
     
 
           
    
}
