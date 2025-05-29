package mx.gob.edomex.microservicios.digital_vault.controller
import mx.gob.edomex.microservicios.digital_vault.model.Msg
import mx.gob.edomex.microservicios.digital_vault.model.UserToken
import mx.gob.edomex.microservicios.digital_vault.model.VaultUser
import mx.gob.edomex.microservicios.digital_vault.service.VaultUserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

@RestController
class VaultUserController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var vaultUserService: VaultUserService

    @GetMapping("/")
    fun index(): String {
        return "OK"
    }

    @PostMapping("/loginTest")
    fun loginTest(): String {
        logger.debug("LoginTest -----")
        return "OK"
    }

    @PostMapping("/login")
    fun login(@RequestParam("username") username: String, @RequestParam("password") pass: String) : UserToken {
        return try {
            val vaultUser = vaultUserService.findByUsername(username)
            val passwordEncoder = BCryptPasswordEncoder()
            if (passwordEncoder.matches(pass, vaultUser.pass)){
                val token = getJWTToken(vaultUser.username)
                logger.debug("username --> {}", vaultUser.username)
                logger.debug("token --> {}", token)
                UserToken(vaultUser.userId, username, token)
            } else {
                UserToken(0, username, "Login incorrect")
            }
        } catch (err: EmptyResultDataAccessException) {
            UserToken(0, username, "Login incorrect")
        }
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    fun getJWTToken(username: String): String {
        val secretKey = "mySecretKey"
        val grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")
        val token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.toByteArray()).compact();

        return "Bearer " + token
    }

    @PostMapping("/addUser")
    fun createUser(@RequestParam("username") username: String, @RequestParam("password") pass: String): Msg {
        logger.debug("username --> {}", username)
        logger.debug("password --> {}", pass)
        val user = VaultUser()
        return if (!username.isEmpty() && !pass.isEmpty()){
            if (!vaultUserService.existsUser(username)){
                if (vaultUserService.saveUser(username, pass)) {
                    Msg(true, "El usuario fue dato de alta y se le creo una llave")
                } else {
                    Msg(false, "Hubo un problema al dar de alta el usuario revisar el log.")
                }
            } else {
                //Si ya existe tambien mandamos true para no validarlo antes
                Msg(false, "El usuario ya existe")
            }
        } else {
            Msg(false, "El usuario no fue dado de alta")
        }
    }
}