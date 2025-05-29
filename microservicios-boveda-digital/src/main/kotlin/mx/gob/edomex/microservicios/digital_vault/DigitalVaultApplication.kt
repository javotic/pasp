package mx.gob.edomex.microservicios.digital_vault

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

import org.springframework.security.crypto.password.PasswordEncoder





@SpringBootApplication
class DigitalVaultApplication : CommandLineRunner {
	private val logger = LoggerFactory.getLogger(javaClass)

	override fun run(args: Array<String>) {
		logger.info("----------------------------------------------")
		logger.info("---------- Digital Vault Started -------------")
		logger.info("----------------------------------------------")
	}
}

fun main(args: Array<String>) {
	runApplication<DigitalVaultApplication>(*args)
}
