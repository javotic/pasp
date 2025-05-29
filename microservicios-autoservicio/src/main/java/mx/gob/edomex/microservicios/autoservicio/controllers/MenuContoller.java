package mx.gob.edomex.microservicios.autoservicio.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import mx.gob.edomex.microservicios.autoservicio.models.EvaluacionDesempenioJson;
import mx.gob.edomex.microservicios.autoservicio.models.EvaluacionJson;
import mx.gob.edomex.microservicios.autoservicio.models.MenuPortal;
import mx.gob.edomex.microservicios.autoservicio.models.PreguntasJson;
import mx.gob.edomex.microservicios.autoservicio.models.RespuestasJson;
import mx.gob.edomex.microservicios.autoservicio.models.entity.EvaluacionDesempenio;
import mx.gob.edomex.microservicios.autoservicio.models.entity.Menu;
import mx.gob.edomex.microservicios.autoservicio.models.entity.Menu_rol;
import mx.gob.edomex.microservicios.autoservicio.services.MenuService;

//@CrossOrigin({ "http://localhost:4200" })
@RestController
public class MenuContoller {
	@Autowired
	private MenuService menuService;

	@GetMapping
	public ResponseEntity<?> findAllMenu() {
		return ResponseEntity.ok().body(menuService.findAll());
	}
	
	@GetMapping("/idRol/{idRol}")
	public ResponseEntity<?> findByIdRol(@PathVariable Long idRol) {
		List<MenuPortal> lstMenu = new ArrayList<>();
		Iterable<Menu_rol> menuR = menuService.findByIdRol(idRol);
		menuR.forEach(e->{
			System.out.println("Menu=>>"+e.toString());
			MenuPortal menu = new MenuPortal();
			menu.setDescripcion(e.getMenu().getDescripcion());
			menu.setDsIcon(e.getMenu().getDsIcon());
			menu.setDsTooltip(e.getMenu().getDsTooltip());
			menu.setEstatus(e.getMenu().isEstatus());
			menu.setIdMenu(e.getMenu().getIdMenu());
			menu.setNombreMenu(e.getMenu().getNombreMenu());			
			menu.setNumOrden(e.getMenu().getNumOrden());
			menu.setUrl(e.getMenu().getUrl());			
			lstMenu.add(menu); 
		});
		
//		ArrayList<Menu> menu = new ArrayList<>();
//		for (Menu_rol m : menuR) {
//			menu.add(m.getMenu());
//		}
//		if (menu.isEmpty()) {
//			return ResponseEntity.notFound().build();
//		} else {
//			Iterable<Menu> mr = menu;
//			return ResponseEntity.ok().body(mr);
//		}
//
		return ResponseEntity.ok().body(lstMenu);
	}

	@RequestMapping(value = "/obtenerPreguntas/{claveproceso}/{spevaluador}/{spevaluado}/{claveunidadadmin}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findPreguntas(@PathVariable("claveproceso") String claveproceso,
			@PathVariable("spevaluador") String spevaluador, @PathVariable("spevaluado") String spevaluado,
			@PathVariable("claveunidadadmin") String claveunidadadmin) {		
		return ResponseEntity.ok().body(menuService.findById(claveproceso, spevaluador, spevaluado, claveunidadadmin));
	}

	// @PostMapping("/guardarRespuestas")
	@RequestMapping(value = "/guardarRespuestas", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> savePreguntas(@RequestBody EvaluacionJson evaluacion) {
		for (EvaluacionDesempenioJson eval : evaluacion.getSeccionesEddPR()) {
			for (PreguntasJson preguntasJson : eval.getPreguntas()) {
				Optional<RespuestasJson> OptResp = preguntasJson.getRespuestas().stream()
						.filter(e -> e.getSELECTED().equals("true")).findFirst();
				if (OptResp.isPresent()) {
					RespuestasJson rep = OptResp.get();
					EvaluacionDesempenio desempenio = new EvaluacionDesempenio();
					desempenio.setClaveproceso(eval.getIDPROCESOVIGENTE());
					desempenio.setClaveunidadadmin(eval.getClaveUnidadAdmin());
					desempenio.setIdDtEvaluacionDesempenio(preguntasJson.getIdPreguntaResp().isEmpty() ? null
							: Long.valueOf(preguntasJson.getIdPreguntaResp()));
					desempenio.setIdpregunta(preguntasJson.getIDPREGUNTA());
					desempenio.setIdrespuesta(rep.getIDRESPUESTA());
					if(preguntasJson.getTipoCampo().equals("2")) {
					desempenio.setIdrespuesta(rep.getPUNTAJE());
					}
					desempenio.setIdseccion(preguntasJson.getIDSECCION());
					desempenio.setSpevaluado(eval.getClaveEvaluado());
					desempenio.setSpevaluador(eval.getClaveEvaluador());
					EvaluacionDesempenio eva = menuService.savePreguntas(desempenio);
					preguntasJson.setIdPreguntaResp(eva.getIdDtEvaluacionDesempenio().toString());
				}
			}

		}
		return ResponseEntity.ok().body(evaluacion);
	}

}
