package mx.gob.edomex.microservicios.etiquetasparametros;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosEtiquetasParametrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosEtiquetasParametrosApplication.class, args);
	}

}
