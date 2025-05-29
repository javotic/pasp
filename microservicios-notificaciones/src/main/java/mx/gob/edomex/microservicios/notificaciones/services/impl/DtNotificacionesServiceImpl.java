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
import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import mx.gob.edomex.microservicios.notificaciones.models.entity.DtNotificaciones;
import mx.gob.edomex.microservicios.notificaciones.models.repository.DtNotificacionesRepository;
import mx.gob.edomex.microservicios.notificaciones.services.interf.DtNotificacionesService;

/**
 *
 * @author jolmv
 */

@Component
public class DtNotificacionesServiceImpl implements DtNotificacionesService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(DtNotificacionesServiceImpl.class);

	@Autowired
	private DtNotificacionesRepository articleRepository;

	@PersistenceContext
	EntityManager em;

	@Override
	public Iterable<DtNotificaciones> saveDtNotificacionesModel(List<DtNotificaciones> info) throws Exception {
		try {
			Iterable<DtNotificaciones> guardarNotificaciones = info;
			return articleRepository.saveAll(guardarNotificaciones);
		} catch (Exception e) {
			logger.error("Problemas al Guardar (DtNotificacionesServiceImpl) ");
			throw new Exception("Problemas al Guardar (DtNotificacionesServiceImpl) " + e);
		}
	}

	@Override
	public List<DtNotificaciones> searchAllDtNotificacionesModel() throws Exception {
		List<DtNotificaciones> infoList = new ArrayList<>();
		try {
			infoList = (List<DtNotificaciones>) articleRepository.findAll();
		} catch (Exception e) {
			logger.error("Problemas al Buscar la informacion General (DtNotificacionesServiceImpl) ");
			throw new Exception("Problemas al Buscar (DtNotificacionesServiceImpl) " + e);
		}
		return infoList;
	}

	@Override
	public void deleteByIdDtNotificacionesModel(DtNotificaciones info) throws Exception {
		try {

			articleRepository.deleteById(Long.parseLong(String.valueOf(info.getLlnotificaciones())));
		} catch (NumberFormatException e) {
			throw new Exception("Problemas al Eliminar (DtNotificacionesServiceImpl) " + e);
		}
	}

	@Override
	public DtNotificaciones updateDtNotificacionesModel(DtNotificaciones info) throws Exception {
		try {
			return articleRepository.save(info);
		} catch (NumberFormatException e) {
			throw new Exception("Problemas al Actualizar (DtNotificacionesServiceImpl) " + e);
		}
	}

	@Transactional
	@Modifying
	public int updateDtNotificacionesByLlnotifi(String llnotifi) throws Exception {
		try {
			System.out.println("Valor de llnotifi::::::::>  " + llnotifi);

			/*
			 * Query que actualiza la notificacion por la PK llnotificaciones una vez que se
			 * visualizo en el portal web de Autoservicio
			 */
			Query query = em.createQuery(
					"update DtNotificaciones n set n.estatusnotificacion = false  where n.llnotificaciones = "
							+ llnotifi);

			return query.executeUpdate();
		} catch (Exception e) {
			logger.error("Problemas al Actualizar la informacion en DtNotificaciones (DtNotificacionesServiceImpl) ");
			throw new Exception("Problemas al Aztualizar (updateDtNotificacionesByLlnotifi) " + e);
		}
	}

}
