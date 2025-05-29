package mx.gob.edomex.microservicios.digital_vault.controller

import mx.gob.edomex.microservicios.digital_vault.model.DecryptFile
import mx.gob.edomex.microservicios.digital_vault.model.Msg
import mx.gob.edomex.microservicios.digital_vault.service.VaultService
import mx.gob.edomex.microservicios.digital_vault.utils.MediaTypeUtils
import org.apache.commons.io.IOUtils
import org.json.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.io.FileInputStream
import javax.servlet.ServletContext
import javax.servlet.http.HttpServletResponse


@RestController
class VaulController {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    lateinit var vaultService: VaultService

    @Autowired
    lateinit var serlvetContext: ServletContext

    @Autowired
    lateinit var response: HttpServletResponse

    @PostMapping("addFile")
    fun uploadEncryptFile(@RequestParam("userId") userId: Int,
                          @RequestParam("metadata") metadata: String,
                          @RequestParam("idDocProfesional") idDocProfesional: Int,
                          @RequestParam("file") f: MultipartFile): Msg {
        return try {
            val fileIds = vaultService.uploadFile(userId, metadata, f, idDocProfesional)
            val s = JSONObject(fileIds).toString()
            Msg(true, s)
        } catch (e: Exception) {
            logger.error("Error", e)
            Msg(false, "Hubo un problema la registrar el fichero a la boveda")
        }
    }

    @PostMapping("files")
    fun listFilesUser(@RequestParam("userId") userId: Int): List<DecryptFile> {
        return vaultService.listFilesUser(userId)
    }

    @GetMapping("decryptFile")
    fun download(@RequestParam("userId") userId: Int, @RequestParam("fileId") fileId: Int, response: HttpServletResponse) {
        val fileName = vaultService.decryptFileUser(userId, fileId)
        logger.debug("Filename --> {}", fileName)
        val mediaType = MediaTypeUtils.getMediaTypeForFileName(this.serlvetContext, fileName)
        val file = File(fileName)
        logger.debug("mediaType --> {}", mediaType.type)
        response.setContentType(mediaType.type)
        response.setHeader("Content-disposition", "attachment; filename=" + file.getName())
        val fileOut = response.getOutputStream()
        val fileIn = FileInputStream(file)
        IOUtils.copy(fileIn, fileOut)
        fileOut.close()
        fileIn.close()
        file.delete()
    }


    @GetMapping("decryptFileByDocument")
    fun downloadFile(@RequestParam("useridportal") userIdPortal: Int,
                     @RequestParam("iddocumentoportal") iddocumentoportal: Int,
                     response: HttpServletResponse) {
        val fileName = vaultService.decryptFileUserPortal(userIdPortal, iddocumentoportal)
        if(fileName == "0"){
            response.setHeader("Custom-Header", "Sin Contenido");
            response.setStatus(200);
            response.getWriter().print("El documento no existe.");
    }else {
            logger.debug("Filename --> {}", fileName)
            val mediaType = MediaTypeUtils.getMediaTypeForFileName(this.serlvetContext, fileName)
            val file = File(fileName)
            logger.debug("mediaType --> {}", mediaType.type)
            response.setContentType(mediaType.type)

            response.setHeader("Content-disposition", "attachment; filename=" +
                    file.getName().replace("." + file.extension, "." + file.extension.substring(0, 3)))
            val fileOut = response.getOutputStream()
            val fileIn = FileInputStream(file)
            IOUtils.copy(fileIn, fileOut)
            fileOut.close()
            fileIn.close()
            file.delete()
        }
    }

}