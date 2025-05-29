package mx.gob.edomex.microservicios.digital_vault.service

import mx.gob.edomex.microservicios.digital_vault.model.UserToken
import mx.gob.edomex.microservicios.digital_vault.model.VaultUser
import mx.gob.edomex.microservicios.digital_vault.model.VaultUserRepository
import mx.gob.edomex.microservicios.digital_vault.utils.CryptoUtils
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service


@Service
class VaultUserService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var vaultUserRepository: VaultUserRepository

    fun findByUsername(username: String) = vaultUserRepository.findByUsername(username)


    fun findByUsernameAndPass(username: String, password: String) = vaultUserRepository.findByUsernameAndPass(username, password)
    fun existsUser(username: String) : Boolean = try { this.findByUsername(username)
        true } catch (err: EmptyResultDataAccessException) { false }

    fun saveUser(username: String, password: String) : Boolean {
        try {
            val cryptoUtils = CryptoUtils()
            val tk = JSONObject()
            tk.put("length", 32)
            tk.put("algorithm", "AES")
            val tkS = cryptoUtils.encodeString(tk.toString())
            val key = cryptoUtils.generateRandmonSymmetricKey(tk.get("length").toString().toInt(), tk.get("algorithm").toString())
            var u = VaultUser(0, username, cryptoUtils.bCryptPasswordEncoder(password), key.encoded, tkS)
            vaultUserRepository.save(u)
            return true;
        } catch (e: Exception){
            logger.error("Error", e)
            return false;
        }
    }
}