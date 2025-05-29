package mx.gob.edomex.microservicios.digital_vault.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@EnableWebSecurity
@Configuration
class WebSecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
                .addFilterAfter(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login","/addUser").permitAll()
                .antMatchers(HttpMethod.GET, "/").permitAll()
                //.anyRequest().access("hasIpAddress('10.10.39.11') or hasIpAddress('10.8.130.1')")
    }
}