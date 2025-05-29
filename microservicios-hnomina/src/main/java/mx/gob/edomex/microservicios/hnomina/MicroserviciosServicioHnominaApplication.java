package mx.gob.edomex.microservicios.hnomina;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosServicioHnominaApplication {
	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosServicioHnominaApplication.class, args);
	}
}
