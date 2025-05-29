package mx.gob.edomex.microservicios.serviciosbus.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/catalogos")
public class BusControllerCatalogos {

	@Autowired
	private RestTemplate restTemplate;
	private static final String URL_WS_DESA = "https://desabus.edomex.gob.mx/bussrv/sei/";
	private static final String URL_WS_PROD = "https://bus.edomex.gob.mx/bussrv/sei/";
	private static final Logger LOG = LoggerFactory.getLogger(BusControllerCatalogos.class);

	@RequestMapping(value = "/wsbus/{urlWS}", method = RequestMethod.POST)
	public ResponseEntity<String> findEvents(@PathVariable String urlWS, @RequestBody Object user) {

		ResponseEntity<String> responseService = null;

		try {
			// RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
			// System.out.println(urlWS);
			//String uri = URL_WS_PROD + urlWS;
			String uri = URL_WS_DESA + urlWS;

			String plainCreds = "seiusr:aguacate";
			byte[] plainCredsBytes = plainCreds.getBytes();
			byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
			String base64Creds = new String(base64CredsBytes);

			ObjectMapper mapper = new ObjectMapper();

			String jsonInString = mapper.writeValueAsString(user);

			// System.out.println(mapper);

			String json = "{ \"request\" : " + jsonInString + " }";
			// System.out.println(urlWS);
			// System.out.println(new Date() + " : " + json);
			LOG.info("Name service {}", urlWS);
			LOG.info("Request {}", json);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", "Basic " + base64Creds);

			HttpEntity<String> request = new HttpEntity<>(json, headers);
			ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, request, String.class);
			responseService = response;
			String jsonString = response.getBody();
			LOG.info("Status code {}", response.getStatusCode());
			LOG.info("Response body {}", jsonString);
			// System.out.println(response);
			// System.out.println(response.getBody());
			// System.out.println(response.getStatusCode().value());

		} catch (Exception e) {
			LOG.error("Error {}", e);
		}
		return responseService;
	}

}