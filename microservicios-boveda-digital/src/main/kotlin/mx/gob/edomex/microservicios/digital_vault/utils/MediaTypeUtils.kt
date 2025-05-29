package mx.gob.edomex.microservicios.digital_vault.utils

import org.springframework.http.MediaType
import java.lang.Exception
import javax.servlet.ServletContext

/**
 * https://o7planning.org/en/11765/spring-boot-file-download-example
 */

class MediaTypeUtils {
    companion object {
        fun getMediaTypeForFileName(servletContext: ServletContext, fileName: String) : MediaType {
            val mineType = servletContext.getMimeType(fileName)
            return try {
                MediaType.parseMediaType(mineType);
            } catch (e: Exception) {
                MediaType.APPLICATION_OCTET_STREAM;
            }
        }
    }
}