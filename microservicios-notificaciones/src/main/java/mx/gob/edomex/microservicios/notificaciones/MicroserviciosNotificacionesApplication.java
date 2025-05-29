package mx.gob.edomex.microservicios.notificaciones;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosNotificacionesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosNotificacionesApplication.class, args);
	}

}
