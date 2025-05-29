package mx.gob.edomex.microservicios.digital_vault.service

import antlr.StringUtils
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import mx.gob.edomex.microservicios.digital_vault.config.DigitalVaultConfig
import mx.gob.edomex.microservicios.digital_vault.model.*
import mx.gob.edomex.microservicios.digital_vault.utils.CryptoUtils
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileNotFoundException
import java.lang.Exception
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.sql.Timestamp
import java.util.*
import java.util.stream.Collectors
import javax.annotation.Resource
import javax.crypto.spec.SecretKeySpec
import kotlin.collections.ArrayList

@Service
class VaultService {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var digitalVaultConfig: DigitalVaultConfig
    @Autowired
    lateinit var vaultUserRepository: VaultUserRepository
    @Autowired
    lateinit var vaultRepository: VaultRepository

    fun uploadFile(userId: Int, metadata: String, f: MultipartFile, idDocProfesional: Int) : FileIds? {
        val filePath: String
        return try {
            logger.debug("tmp path:: {}", digitalVaultConfig.tmp)
            logger.debug("file upload:: {}", f.originalFilename)
            val copyLocation = Paths.get(digitalVaultConfig.tmp + f.originalFilename)
            Files.copy(f.inputStream, copyLocation, StandardCopyOption.REPLACE_EXISTING)
            filePath = digitalVaultConfig.tmp + f.originalFilename
            logger.debug("filePath --> {}", filePath)
            val file = File(filePath)
            this.saveEncryptFile(userId, metadata, file, idDocProfesional)
        } catch (e: Exception) {
            logger.error("Error in upload file", e)
            FileIds (-1,-1)
        }
    }

    fun saveEncryptFile(userId: Int, metadata: String, f : File, idDocProfesional: Int) : FileIds {
        logger.debug("Encrypt File {}", f.name)
        val cryptoUtils = CryptoUtils()
        val u = vaultUserRepository.findById(userId).get()
       return try {
            logger.debug("Path --> {}", digitalVaultConfig.home)
            val current = Timestamp(Date().time)
            val vault = Vault()
            val typeKey = JSONObject(cryptoUtils.decodeString(u.typeKey))
            val a = typeKey.get("algorithm").toString()
            val secretKey = SecretKeySpec(u.privateKey, a)
            val encodeName = cryptoUtils.encodeString(f.name + userId.toString() + idDocProfesional.toString())
            logger.debug("OriginalName --> {}", f.name)
            logger.debug("encodeName --> {}", encodeName)
            val fout = File(digitalVaultConfig.home + encodeName)
            logger.debug("EncrypFile Output --> {}", fout.absoluteFile)
            val vaultExistente = vaultRepository.findByVaultIdUserAndIdDoc(userId, idDocProfesional)

           if(vaultExistente.count() > 0){
               vault.vaultId = vaultExistente[0].vaultId;
           }else {
               vault.vaultId = VaultId(u.userId, vaultRepository.getMaxId(u.userId) + 1)
           }
            vault.name = encodeName;
            vault.dateStorage = current
            vault.lastView = current
            vault.metadaData = metadata
            vault.idDocProfesional = idDocProfesional;
            logger.debug("-----------------------")
            logger.debug("---- Encrypt File -----")
            cryptoUtils.encryptFileRandom(f, fout, a, secretKey)
            logger.debug("-----------------------")
            vaultRepository.save(vault)
            if (f.delete()){
                FileIds (vault.vaultId!!.userId , vault.vaultId!!.fileId)
            } else {
                FileIds (-1,-1)
            }
        } catch (e: Exception){
            logger.error("Error, ", e)
            FileIds (-1,-1)
        }
    }

    fun listFilesUser(userId: Int) : List<DecryptFile> {
        val cryptoUtils = CryptoUtils()
        val vaultFiles = vaultRepository.findByVaultIdUserId(userId)
        val l = ArrayList<DecryptFile>()
        for (v in vaultFiles) {
            val d = DecryptFile(v.vaultId?.userId, v.vaultId?.fileId, v.user?.username,
                                cryptoUtils.decodeString(v.name).replace(userId.toString()+ v.idDocProfesional.toString(), ""),
                                v.idDocProfesional)
            l.add(d)
        }
        return l
    }

    fun decryptFileUser(userId: Int, fileId: Int) : String {
        return try {
            val cryptoUtils = CryptoUtils()
            val current = Timestamp(Date().time)
            val user = vaultUserRepository.getOne(userId)
            val typeKey = JSONObject(cryptoUtils.decodeString(user.typeKey))
            val a = typeKey.get("algorithm").toString()
            val vault = vaultRepository.findByVaultIdUserIdAndVaultIdFileId(userId, fileId)
            val f = File(digitalVaultConfig.home + vault.name)
            val fout = File(digitalVaultConfig.tmp + cryptoUtils.decodeString(vault.name))
            val secretKey = SecretKeySpec(user.privateKey, a)
            logger.debug("-----------------------")
            logger.debug("---- Encrypt File -----")
            cryptoUtils.decryptFileRandom(f, fout, a, secretKey)
            logger.debug("-----------------------")
            vault.lastView = current
            vaultRepository.save(vault)
            return fout.absolutePath
        } catch (e: Exception) {
            logger.error("Error with decrypt file" , e)
            throw FileNotFoundException()
        }
    }

    fun decryptFileUserPortal(userIdPortal: Int, iddocumentoportal: Int) : String {
        return try {
            logger.debug("--------- Validando usuario --------------")
            var vaultUser = vaultUserRepository.findByUsername(userIdPortal.toString())
            val passwordEncoder = BCryptPasswordEncoder()
            if (!passwordEncoder.matches(userIdPortal.toString() + "pas", vaultUser.pass)){
                throw Exception("No se ha obtenido el documento");
                //throw Exception("Login incorrect")
                logger.debug("--------- Login incorrect --------------")
            }
            logger.debug("--------- Usuario validado --------------")


            logger.debug("--------- Obtener documento --------------")
            var fileWork = vaultRepository.findByVaultIdUserAndIdDoc(vaultUser.userId, iddocumentoportal)
            if(fileWork.count() <= 0){
               throw Exception("No se ha obtenido el documento");
            }
            logger.debug("--------- Documento obtenido --------------")


            //Asignacion de variables
            var userId = vaultUser.userId
            var fileId = fileWork[0].vaultId?.fileId

            if (fileId == null) {
                throw Exception("No se ha obtenido el documento");
            }

            val cryptoUtils = CryptoUtils()
            val current = Timestamp(Date().time)
            val user = vaultUserRepository.getOne(userId)
            val typeKey = JSONObject(cryptoUtils.decodeString(user.typeKey))
            val a = typeKey.get("algorithm").toString()
            val vault = vaultRepository.findByVaultIdUserIdAndVaultIdFileId(userId, fileId)
            val f = File(digitalVaultConfig.home + vault.name)
            val fout = File(digitalVaultConfig.tmp + cryptoUtils.decodeString(vault.name))
            val secretKey = SecretKeySpec(user.privateKey, a)
            logger.debug("-----------------------")
            logger.debug("---- Encrypt File -----")
            cryptoUtils.decryptFileRandom(f, fout, a, secretKey)
            logger.debug("-----------------------")
            vault.lastView = current
            vaultRepository.save(vault)
            return fout.absolutePath
        } catch (e: Exception) {
            logger.error("Error with decrypt file" , e)
            return "0";
        }
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

}