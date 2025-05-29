package mx.gob.edomex.microservicios.serviciosbus;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

public class ConfigurationTomcat implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

	@Override
	public void customize(TomcatServletWebServerFactory factory) {
		// TODO Auto-generated method stub
		factory.getTomcatConnectorCustomizers();
	}

}
