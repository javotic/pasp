package mx.gob.edomex.microservicios.digital_vault.utils

import org.slf4j.LoggerFactory
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import javax.crypto.BadPaddingException
import javax.crypto.IllegalBlockSizeException

class FileUtils {

    private val logger = LoggerFactory.getLogger(javaClass)

    companion object {
        val logger = LoggerFactory.getLogger(javaClass)

        fun writeToFile(path: String, bytes: ByteArray) {
            try {
                val f = File(path)
                f.parentFile.mkdir()
                val fos = FileOutputStream(f)
                fos.write(bytes)
                fos.flush()
                fos.close()
            } catch (err: IOException) {
                logger.error("Problems to write file", err)
            }
        }
    }
}