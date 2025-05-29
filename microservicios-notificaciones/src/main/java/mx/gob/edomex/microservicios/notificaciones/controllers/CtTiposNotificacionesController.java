package mx.gob.edomex.microservicios.notificaciones.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.notificaciones.models.entity.CtTiposNotificaciones;
import mx.gob.edomex.microservicios.notificaciones.models.entity.DtNotificaciones;
import mx.gob.edomex.microservicios.notificaciones.services.interf.CtTiposNotificacionesService;



/**
 * Controlador de CtTiposNotificaciones
 * @author jolmv
 * @version 1.0 17/04/2020
 * @since JDK 1.8
 */

@RestController
public class CtTiposNotificacionesController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CtTiposNotificacionesService cttiposnotifiservices;

    
    private  List<CtTiposNotificaciones> listaDtNotificaciones;
    private  List<Object[]> listaDtNotificacioness;
    
    private List<CtTiposNotificaciones> filteredDtNotificaciones;
    
        
    private CtTiposNotificaciones dtnotificaciones;
    

    
    //<editor-fold desc="Metodo searchAllCtTiposNotificaciones" defaultstate="collapsed">
    @GetMapping(path = "/searchAllCtTiposNotificaciones", produces = "application/json")
    public ResponseEntity<?> searchAllCtTiposNotificaciones() throws Exception {
        
        try {
            logger.info("ENTRO AL METODO searchAllCtTiposNotificaciones()============> ");


            this.listaDtNotificaciones = this.cttiposnotifiservices.searchAllTiposNotificacionesModel();
            
            

        } catch (IOException ex) {
            logger.info("ERROR AL OBTENER LOS DATOS DE  searchAllCtTiposNotificaciones()");
            System.out.println("STACKTRACE" + Arrays.toString(ex.getSuppressed()));
            System.out.println("CAUSE" + ex.getCause());
            System.out.println("LOCALIZEDMESSAGE" + ex.getLocalizedMessage());
            System.out.println("MESSAGE" + ex.getMessage());

        }
        return ResponseEntity.ok(listaDtNotificaciones);
    }
    //</editor-fold>
    
    
    
    
    //<editor-fold desc="Metodo searchByCveServPubCtTiposNotificaciones" defaultstate="collapsed">
    @GetMapping("/clave/{cveservidor}")
    public ResponseEntity<?> searchByCveServPubCtTiposNotificaciones(@PathVariable String cveservidor) throws Exception {
        
        try {
            logger.info("ENTRO AL METODO searchByCveServPubCtTiposNotificaciones()============> ");
            logger.info("cveservidor============> " +  cveservidor);

            this.listaDtNotificacioness = this.cttiposnotifiservices.searchByCveServPubTiposNotificaciones(cveservidor);
            //tipnot.descnotificacion, notif.llnotificaciones,  notif.fechanotificacion, notif.idnotificacionbus, notif.lltiponotificacion, notif.estatusnotificacion

            
            

        } catch (IOException ex) {
            logger.info("ERROR AL OBTENER LOS DATOS DE  searchAllCtTiposNotificaciones()");
            System.out.println("STACKTRACE" + Arrays.toString(ex.getSuppressed()));
            System.out.println("CAUSE" + ex.getCause());
            System.out.println("LOCALIZEDMESSAGE" + ex.getLocalizedMessage());
            System.out.println("MESSAGE" + ex.getMessage());

        }
        return ResponseEntity.ok(listaDtNotificacioness);
    }
    //</editor-fold>
    
    

    
    //<editor-fold desc="saveCtTiposNotificaciones" defaultstate="collapsed">
    @PostMapping(path = "/saveCtTiposNotificaciones", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> saveCtTiposNotificaciones(@RequestBody CtTiposNotificaciones info) throws Exception {
    	
        logger.info("ENTRO AL METODO saveCtTiposNotificaciones()============> ");


        CtTiposNotificaciones save = this.cttiposnotifiservices.saveTiposNotificacionesModel(info);
            
            

    	return new ResponseEntity<CtTiposNotificaciones>(save, HttpStatus.OK);
    	
    	
    	
    }
    //</editor-fold>
    
    
  //<editor-fold desc="updateCtTiposNotificaciones" defaultstate="collapsed">
    @PostMapping(path = "/updateCtTiposNotificaciones", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateCtTiposNotificaciones(@RequestBody CtTiposNotificaciones info) throws Exception {
    	
    	logger.info("ENTRO AL METODO updateCtTiposNotificaciones()============> ");


    	CtTiposNotificaciones update = this.cttiposnotifiservices.updateTiposNotificacionesModel(info);
            
            

        return new ResponseEntity<CtTiposNotificaciones>(update, HttpStatus.OK);
    	
    	
    	
    }
    //</editor-fold>
    

    //<editor-fold desc="deleteCtTiposNotificaciones" defaultstate="collapsed">
    @PostMapping(path = "/deleteCtTiposNotificaciones", consumes = "application/json", produces = "application/json")
    public void deleteCtTiposNotificaciones(@RequestBody CtTiposNotificaciones info) throws Exception {
    	
    	logger.info("ENTRO AL METODO deleteCtTiposNotificaciones()============> ");


    	this.cttiposnotifiservices.deleteByIdTiposNotificacionesModel(info);
            
     
    	
    	
    }
    //</editor-fold>

    
    
    //<editor-fold desc="Getters & Setters" defaultstate="collapsed">
  

	public CtTiposNotificacionesService getCttiposnotifiservices() {
		return cttiposnotifiservices;
	}


	public List<Object[]> getListaDtNotificacioness() {
		return listaDtNotificacioness;
	}

	public void setListaDtNotificacioness(List<Object[]> listaDtNotificacioness) {
		this.listaDtNotificacioness = listaDtNotificacioness;
	}




	public void setCttiposnotifiservices(CtTiposNotificacionesService cttiposnotifiservices) {
		this.cttiposnotifiservices = cttiposnotifiservices;
	}


	public List<CtTiposNotificaciones> getListaDtNotificaciones() {
		return listaDtNotificaciones;
	}


	public void setListaDtNotificaciones(List<CtTiposNotificaciones> listaDtNotificaciones) {
		this.listaDtNotificaciones = listaDtNotificaciones;
	}


	public List<CtTiposNotificaciones> getFilteredDtNotificaciones() {
		return filteredDtNotificaciones;
	}


	public void setFilteredDtNotificaciones(List<CtTiposNotificaciones> filteredDtNotificaciones) {
		this.filteredDtNotificaciones = filteredDtNotificaciones;
	}


	public CtTiposNotificaciones getDtnotificaciones() {
		return dtnotificaciones;
	}


	public void setDtnotificaciones(CtTiposNotificaciones dtnotificaciones) {
		this.dtnotificaciones = dtnotificaciones;
	}
    
   
    
    //</editor-fold>

    

    

  
    

    

}
