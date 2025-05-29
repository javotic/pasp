package mx.gob.edomex.microservicios.digital_vault.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource

@Configuration
@ConfigurationProperties("dv")
class DigitalVaultConfig {

    var home: String? = null
    var tmp: String? = null

    // getter
    get() = field

    set(value) {
        field = value
    }
}