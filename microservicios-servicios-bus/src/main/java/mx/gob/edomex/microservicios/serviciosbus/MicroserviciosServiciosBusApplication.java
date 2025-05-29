package mx.gob.edomex.microservicios.serviciosbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosServiciosBusApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosServiciosBusApplication.class, args);
		ConfigurationTomcat s = new ConfigurationTomcat();
	}

}
