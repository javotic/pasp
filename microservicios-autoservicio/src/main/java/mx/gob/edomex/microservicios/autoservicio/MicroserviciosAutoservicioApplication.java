package mx.gob.edomex.microservicios.autoservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosAutoservicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosAutoservicioApplication.class, args);
	}

}
