package mx.gob.edomex.microservicios.digital_vault.utils

import mx.gob.edomex.microservicios.digital_vault.config.DigitalVaultConfig
import org.apache.commons.codec.binary.Base64
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.security.InvalidKeyException
import java.security.SecureRandom
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class CryptoUtils {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var dvc: DigitalVaultConfig

    fun generateRandmonSymmetricKey(length: Int, algorithm: String): SecretKey {
        val rnd = SecureRandom()
        val key = ByteArray(length)
        rnd.nextBytes(key)
        return SecretKeySpec(key, algorithm)
    }

    fun generateSymetricKeyWithSecret(secret: String, length: Int, algorithm: String): SecretKey {
        val key = fixSecret(secret, length)
        return SecretKeySpec(key, algorithm)
    }

    fun fixSecret(s: String, length: Int): ByteArray {
        if (s.length < length) {
            val missingLength = length - s.length
            for (i in 0 downTo missingLength step 1) {
                s.plus(" ")
            }
        }
        return s.substring(0, length).toByteArray(Charsets.UTF_8)
    }

    fun encryptFileRandom(f: File, fout: File, algorithm: String, secretKey: SecretKey) {
        val cipher = Cipher.getInstance(algorithm);
        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            this.writeToFile(f, fout, cipher)
        } catch (ike: InvalidKeyException) {
            logger.error("InvalidKeyException", ike)
        }
    }

    fun decryptFileRandom(f: File, fout: File, algorithm: String, secretKey: SecretKey) {
        val cipher = Cipher.getInstance(algorithm);
        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            this.writeToFile(f, fout, cipher)
        } catch (ike: InvalidKeyException) {
            logger.error("InvalidKeyException", ike)
        }
    }

    fun writeToFile(f: File, fout: File, cipher: Cipher ) {
        try {
            val inFile = FileInputStream(f)
            val input = ByteArray(f.length().toInt())
            inFile.read(input)
            val outFile = FileOutputStream(fout)
            val output = cipher.doFinal(input)
            outFile.write(output)
            outFile.flush()
            outFile.close()
            inFile.close()
        } catch (err: IOException) {
            FileUtils.logger.error("IOException", err)
        }catch (ibse: IllegalBlockSizeException) {
            FileUtils.logger.error("IOException", ibse)
        }  catch (bpe: BadPaddingException) {
            FileUtils.logger.error("BadPaddingException", bpe)
        }
    }

    fun encodeString(s: String) = String(Base64.encodeBase64(s.toByteArray()))
    fun decodeString(s: String) = String(Base64.decodeBase64(s.toByteArray()))
    fun bCryptPasswordEncoder(s: String) = BCryptPasswordEncoder().encode(s)
}