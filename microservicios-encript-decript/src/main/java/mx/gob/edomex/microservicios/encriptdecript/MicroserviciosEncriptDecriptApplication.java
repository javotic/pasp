package mx.gob.edomex.microservicios.encriptdecript;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MicroserviciosEncriptDecriptApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviciosEncriptDecriptApplication.class, args);
	}

}
