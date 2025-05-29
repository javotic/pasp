package mx.gob.edomex.microservicios.serviciosbus.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;

import mx.gob.edomex.microservicios.serviciosbus.models.BusServiceColonias;
import mx.gob.edomex.microservicios.serviciosbus.models.BusServiceConsultaDatosPersonales;
import mx.gob.edomex.microservicios.serviciosbus.models.BusServiceConsultaDatosServidorPublico;
import mx.gob.edomex.microservicios.serviciosbus.models.BusServiceConsultaEstadoCivil;
import mx.gob.edomex.microservicios.serviciosbus.models.BusServiceConsultaGenero;
import mx.gob.edomex.microservicios.serviciosbus.models.BusServiceEstados;
import mx.gob.edomex.microservicios.serviciosbus.models.BusServiceMunicipios;
import mx.gob.edomex.microservicios.serviciosbus.models.BusServiceNivelEstudios;
import mx.gob.edomex.microservicios.serviciosbus.models.Combo;
import mx.gob.edomex.microservicios.serviciosbus.models.ReponseConsultaEstadoCivil;
import mx.gob.edomex.microservicios.serviciosbus.models.ReponseConsultaGenero;
import mx.gob.edomex.microservicios.serviciosbus.models.ResponseConsultaColonias;
import mx.gob.edomex.microservicios.serviciosbus.models.ResponseConsultaDatosPersonales;
import mx.gob.edomex.microservicios.serviciosbus.models.ResponseConsultaDatosServidorPublico;
import mx.gob.edomex.microservicios.serviciosbus.models.ResponseConsultaEstados;
import mx.gob.edomex.microservicios.serviciosbus.models.ResponseConsultaMunicipios;
import mx.gob.edomex.microservicios.serviciosbus.models.ResponseConsultaNivelEstudios;
import mx.gob.edomex.microservicios.serviciosbus.models.ResponseDatosPersonales;

@RequestMapping("/datosPersonales")
@RestController
public class DatosPersonalesController {
	private static final Logger LOG = LoggerFactory.getLogger(DatosPersonalesController.class);
	@Autowired
	private RestTemplate restTemplate;

	@Value("${urldatospersonales}")
	private String urldatosPersonales;
	@Value("${urlconsultaservidorpublico}")
	private String urlConsultaservidorPublico;
	@Value("${urlestados}")
	private String urlEstados;
	@Value("${urlmunicipios}")
	private String urlMunicipios;
	@Value("${urlcolonias}")
	private String urlColonias;
	@Value("${urlnivelestudios}")
	private String urlNivelEstudios;
	@Value("${urlgenero}")
	private String urlGenero;
	@Value("${urlestadocivil}")
	private String urlEstadoCivil;

	@GetMapping("/{idServidorPublico}")
	public ResponseDatosPersonales obtenerEtiquetas(@RequestHeader HttpHeaders headers,
			@PathVariable String idServidorPublico) throws Exception {

		ResponseConsultaDatosPersonales personales = obtenerDatosPersonales(idServidorPublico);
		ResponseConsultaDatosServidorPublico servidorPublico = consultarDatosServidorPublico(idServidorPublico);

		ResponseConsultaEstados estados = null;
		ResponseConsultaMunicipios municipios = null;
		ResponseConsultaColonias colonias = null;

		if (!servidorPublico.getResponse().isEmpty()) {
			BusServiceConsultaDatosServidorPublico s = servidorPublico.getResponse().get(0);
			estados = consultaEstados();
			municipios = consultaMunicipios(s.getIdEstado());
			colonias = consultaColonias(s.getIdEstado(), s.getIdMunicipio());
		}

		ResponseConsultaNivelEstudios nivelEstudios = consultarNivelEstudios();
		ReponseConsultaGenero genero = consultarGenero();

		ReponseConsultaEstadoCivil estadoCivil = consultarEstadoCivil();

		boolean callPersonales = personales.getCodigo() == 200;
		boolean callServidorPublico = servidorPublico.getCodigo() == 200;
		boolean callEstados = estados!=null?estados.getCodigo() == 200:false;
		boolean callMunicipios = municipios!=null?municipios.getCodigo() == 200:false;
		boolean callColonias = colonias!=null?colonias.getCodigo() == 200:false;
		boolean callNivelEstudios = nivelEstudios!=null?nivelEstudios.getCodigo() == 200:false;
		boolean callGenero = genero!=null?genero.getCodigo() == 200:false;
		boolean callEstadoCivil = estadoCivil!=null?estadoCivil.getCodigo() == 200:false;

		if (callPersonales && callServidorPublico && callEstados && callMunicipios && callColonias && callNivelEstudios
				&& callGenero && callEstadoCivil) {

			List<Combo> lstEstados = new ArrayList<>();
			for (BusServiceEstados x : estados.getResponse()) {
				Combo c = new Combo(x.getIdEstado(), x.getNombreEstado());
				lstEstados.add(c);
			}

			List<Combo> lstMunicipios = new ArrayList<>();
			for (BusServiceMunicipios x : municipios.getResponse()) {
				Combo c = new Combo(x.getIdMunicipioCiudad(), x.getNombreMunicipioCiudad());
				lstMunicipios.add(c);
			}

			List<Combo> lstColonias = new ArrayList<>();
			for (BusServiceColonias x : colonias.getResponse()) {
				Combo c = new Combo(x.getIdColonia(), x.getNombreColonia());
				lstColonias.add(c);
			}

			List<Combo> lstNivelEstudios = new ArrayList<>();
			for (BusServiceNivelEstudios x : nivelEstudios.getResponse()) {
				Combo c = new Combo(x.getIdTituloCarrera(), x.getNombreTituloCarrera());
				lstNivelEstudios.add(c);
			}

			List<Combo> lstGenero = new ArrayList<>();
			for (BusServiceConsultaGenero x : genero.getResponse()) {
				Combo c = new Combo(x.getIdGenero(), x.getNombreGenero());
				lstGenero.add(c);
			}

			List<Combo> lstEstadoCivil = new ArrayList<>();
			for (BusServiceConsultaEstadoCivil x : estadoCivil.getResponse()) {
				Combo c = new Combo(x.getIdEstadoCivil(), x.getNombreEstadoCivil());
				lstEstadoCivil.add(c);
			}

			
			
			ResponseDatosPersonales dataPersonales = new ResponseDatosPersonales();
			dataPersonales.setCodigo(personales.getCodigo());
			dataPersonales.setMensaje(personales.getMensaje());
			BusServiceConsultaDatosPersonales s1 = personales.getResponse().get(0);
			BusServiceConsultaDatosServidorPublico s2 = servidorPublico.getResponse().get(0);
			
			String[] datos = null;
			if (!s2.getRfc().isEmpty()) {
				datos = s2.getRfc().split(Pattern.quote("|"));
			}
		
			
			dataPersonales.setNombre(s2.getNombre());
			dataPersonales.setApellidoPaterno(s1.getApellidoPaterno());
			dataPersonales.setApellidoMaterno(s1.getApellidoMaterno());
			dataPersonales.setFechaNacimiento(s2.getFechaNacimiento());
			dataPersonales.setCurp(s2.getCurp());
			dataPersonales.setRfc(datos!=null?datos[0]:"");
			dataPersonales.setIssemym(datos!=null&&datos.length==2?datos[1]:"");
			dataPersonales.setTelefono(s2.getTelefono());
			dataPersonales.setCorreoElectronico(s2.getCorreoElectronico());
			dataPersonales.setIdNivelEstudios(s2.getIdNivelEstudios());
			dataPersonales.setIdEstadoCivil(s1.getIdEstadoCivil());
			dataPersonales.setIdSexo(s1.getIdSexo());
			dataPersonales.setIdEstado(s2.getIdEstado());
			dataPersonales.setIdMunicipio(s2.getIdMunicipio());
			dataPersonales.setIdColonia(s2.getIdColonia());
			dataPersonales.setCalle(s2.getCalle());
			dataPersonales.setNumeroInterior(s2.getNumeroInterior());
			dataPersonales.setNumeroExterior(s2.getNumeroExterior());
			dataPersonales.setCodigoPostal(s2.getCodigoPostal());
			dataPersonales.setLstEstados(lstEstados);
			dataPersonales.setLstMunicipios(lstMunicipios);
			dataPersonales.setLstColonias(lstColonias);
			dataPersonales.setLstGenero(lstGenero);
			dataPersonales.setLstNivelEstudios(lstNivelEstudios);
			dataPersonales.setLstEstadoCivil(lstEstadoCivil);

			return dataPersonales;
		} else {
			ResponseDatosPersonales dataPersonales = new ResponseDatosPersonales();
			dataPersonales.setCodigo(500);
			dataPersonales.setMensaje("false");
			return dataPersonales;

		}
	}

	private ResponseConsultaDatosPersonales obtenerDatosPersonales(String idServidorPublico) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("funcion", "CONSULTADATOSPERSONALES");
		map.put("IDEMPLEADO", idServidorPublico);
		map.put("NOMBRE", "");
		map.put("APELLIDOPAT", "");
		map.put("APELLIDOMAT", "");
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("request", map);
		
		System.out.println(new Date() + " : " + map1.toString());
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());
		ResponseEntity<String> response = restTemplate.postForEntity(urldatosPersonales, entity, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		ResponseConsultaDatosPersonales data = gson.fromJson(account, ResponseConsultaDatosPersonales.class);

		return data;
	}

	private ResponseConsultaDatosServidorPublico consultarDatosServidorPublico(String idServidorPublico) {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("funcion", "consultarDatosServidorPublico");
		map.put("IDSERVIDORPUBLICO", idServidorPublico);

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("request", map);
		System.out.println(new Date() + " : " + map1.toString());
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());
		ResponseEntity<String> response = restTemplate.postForEntity(urlConsultaservidorPublico, entity, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		ResponseConsultaDatosServidorPublico data = gson.fromJson(account, ResponseConsultaDatosServidorPublico.class);

		return data;
	}

	private ResponseConsultaEstados consultaEstados() {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("funcion", "CONSULTACATESTADO");
		map.put("IDPAIS", "MEX");
		map.put("IDPESTADO", "");
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("request", map);
		System.out.println(new Date() + " : " + map1.toString());
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());
		ResponseEntity<String> response = restTemplate.postForEntity(urlEstados, entity, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		ResponseConsultaEstados data = gson.fromJson(account, ResponseConsultaEstados.class);

		return data;
	}

	private ResponseConsultaMunicipios consultaMunicipios(String idEstado) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("funcion", "CONSULTACATMUNICIPIO");
		map.put("IDPAIS", "MEX");
		map.put("IDPESTADO", idEstado);
		map.put("IDMUNICIPIO", "");
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("request", map);
		System.out.println(new Date() + " : " + map1.toString());
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());
		ResponseEntity<String> response = restTemplate.postForEntity(urlMunicipios, entity, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		ResponseConsultaMunicipios data = gson.fromJson(account, ResponseConsultaMunicipios.class);

		return data;
	}

	private ResponseConsultaColonias consultaColonias(String idEstado, String idMunicipio) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("funcion", "CONSULTACATCOLONIAS");
		map.put("IDPAIS", "MEX");
		map.put("IDPESTADO", idEstado);
		map.put("IDMUNICIPIO", idMunicipio);
		map.put("IDCOLONIA", "");

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("request", map);
		System.out.println(new Date() + " : " + map1.toString());
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());
		ResponseEntity<String> response = restTemplate.postForEntity(urlColonias, entity, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		ResponseConsultaColonias data = gson.fromJson(account, ResponseConsultaColonias.class);

		return data;
	}

	private ResponseConsultaNivelEstudios consultarNivelEstudios() {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("funcion", "CONSULTACATTITULOCARRERA");
		map.put("IDTICARRERA", "");

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("request", map);
		System.out.println(new Date() + " : " + map1.toString());
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());
		ResponseEntity<String> response = restTemplate.postForEntity(urlNivelEstudios, entity, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		ResponseConsultaNivelEstudios data = gson.fromJson(account, ResponseConsultaNivelEstudios.class);

		return data;
	}

	private ReponseConsultaGenero consultarGenero() {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("funcion", "CONSULTACATGENERO");

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("request", map);
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());
		ResponseEntity<String> response = restTemplate.postForEntity(urlGenero, entity, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		ReponseConsultaGenero data = gson.fromJson(account, ReponseConsultaGenero.class);

		return data;
	}

	private ReponseConsultaEstadoCivil consultarEstadoCivil() {

		HashMap<String, Object> map = new HashMap<String, Object>();

		map.put("funcion", "CONSULTACATESTADOCIVIL");

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("request", map);
		System.out.println(new Date() + " : " + map1.toString());
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map1, obtenerCabeceras());
		ResponseEntity<String> response = restTemplate.postForEntity(urlEstadoCivil, entity, String.class);
		String account = response.getBody();
		Gson gson = new Gson();
		ReponseConsultaEstadoCivil data = gson.fromJson(account, ReponseConsultaEstadoCivil.class);

		return data;
	}

	private HttpHeaders obtenerCabeceras() {
		String username = "seiusr";
		String password = "aguacate";
		HttpHeaders headers2 = new HttpHeaders();
		headers2.setBasicAuth(username, password);
		headers2.setContentType(MediaType.APPLICATION_JSON);
		return headers2;
	}

}
