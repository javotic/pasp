package mx.gob.edomex.microservicios.notificaciones.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.notificaciones.models.entity.DtNotificaciones;
import mx.gob.edomex.microservicios.notificaciones.models.entity.Notificaciones;
import mx.gob.edomex.microservicios.notificaciones.services.interf.DtNotificacionesService;

/**
 * Controlador de DTNotificaciones
 * 
 * @author jolmv
 * @version 1.0 17/04/2020
 * @since JDK 1.8
 */

@RequestMapping("/notificacionesPortal")
@RestController
public class DtNotificacionesController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private DtNotificacionesService dtnotifiservices;

	private List<DtNotificaciones> listaDtNotificaciones;

	private List<DtNotificaciones> filteredDtNotificaciones;

	private DtNotificaciones dtnotificaciones;

	// <editor-fold desc="Metodo searchAllDtNotificaciones"
	// defaultstate="collapsed">
	@GetMapping(path = "/searchAllDtNotificaciones", produces = "application/json")
	public ResponseEntity<?> searchAllDtNotificaciones() throws Exception {

		try {
			logger.info("ENTRO AL METODO searchAllDtNotificaciones()============> ");

			this.listaDtNotificaciones = this.dtnotifiservices.searchAllDtNotificacionesModel();

		} catch (IOException ex) {
			logger.info("ERROR AL OBTENER LOS DATOS DE  searchAllDtNotificaciones()");
			System.out.println("STACKTRACE" + Arrays.toString(ex.getSuppressed()));
			System.out.println("CAUSE" + ex.getCause());
			System.out.println("LOCALIZEDMESSAGE" + ex.getLocalizedMessage());
			System.out.println("MESSAGE" + ex.getMessage());

		}
		return ResponseEntity.ok(listaDtNotificaciones);
	}
	// </editor-fold>

	// <editor-fold desc="saveDtNotificaciones" defaultstate="collapsed">
	@PostMapping
	public ResponseEntity<Iterable<?>> saveDtNotificaciones(@RequestBody Notificaciones notificaciones)
			throws Exception {
		// Notificaciones
		// DtNotificaciones

		logger.info("ENTRO AL METODO saveDtNotificaciones()============> ");

		Iterable<DtNotificaciones> save = this.dtnotifiservices.saveDtNotificacionesModel(notificaciones.getLstNotificaciones());

		return new ResponseEntity<Iterable<?>>(save, HttpStatus.OK);

	}
	// </editor-fold>

	// <editor-fold desc="updateDtNotificaciones" defaultstate="collapsed">
	@PostMapping(path = "/updateDtNotificaciones", consumes = "application/json", produces = "application/json")
	public ResponseEntity<?> updateDtNotificaciones(@RequestBody DtNotificaciones info) throws Exception {

		logger.info("ENTRO AL METODO updateDtNotificaciones()============> ");

		DtNotificaciones update = this.dtnotifiservices.updateDtNotificacionesModel(info);

		return new ResponseEntity<DtNotificaciones>(update, HttpStatus.OK);

	}
	// </editor-fold>

	// <editor-fold desc="updateDtNotificacionesByLlnotifi"
	// defaultstate="collapsed">
	@PostMapping(path = "/updateDtNotificacionesByLlnotifi")
	public ResponseEntity<?> updateDtNotificacionesByLlnotifi(@RequestBody String llnotifi) throws Exception {

		logger.info("ENTRO AL METODO updateDtNotificacionesByLlnotifi()============> ");

		int update = this.dtnotifiservices.updateDtNotificacionesByLlnotifi(llnotifi);

		return new ResponseEntity<DtNotificaciones>(HttpStatus.OK);

		// return update;

	}
	// </editor-fold>

	// <editor-fold desc="deleteDtNotificaciones" defaultstate="collapsed">
	@PostMapping(path = "/deleteDtNotificaciones", consumes = "application/json", produces = "application/json")
	public void deleteDtNotificaciones(@RequestBody DtNotificaciones info) throws Exception {

		logger.info("ENTRO AL METODO deleteDtNotificaciones()============> ");

		this.dtnotifiservices.deleteByIdDtNotificacionesModel(info);

	}
	// </editor-fold>

	// <editor-fold desc="Getters & Setters" defaultstate="collapsed">

	public DtNotificacionesService getDtnotifiservices() {
		return dtnotifiservices;
	}

	public void setDtnotifiservices(DtNotificacionesService dtnotifiservices) {
		this.dtnotifiservices = dtnotifiservices;
	}

	public List<DtNotificaciones> getListaDtNotificaciones() {
		return listaDtNotificaciones;
	}

	public void setListaDtNotificaciones(List<DtNotificaciones> listaDtNotificaciones) {
		this.listaDtNotificaciones = listaDtNotificaciones;
	}

	public List<DtNotificaciones> getFilteredDtNotificaciones() {
		return filteredDtNotificaciones;
	}

	public void setFilteredDtNotificaciones(List<DtNotificaciones> filteredDtNotificaciones) {
		this.filteredDtNotificaciones = filteredDtNotificaciones;
	}

	public DtNotificaciones getDtnotificaciones() {
		return dtnotificaciones;
	}

	public void setDtnotificaciones(DtNotificaciones dtnotificaciones) {
		this.dtnotificaciones = dtnotificaciones;
	}

	// </editor-fold>

}
