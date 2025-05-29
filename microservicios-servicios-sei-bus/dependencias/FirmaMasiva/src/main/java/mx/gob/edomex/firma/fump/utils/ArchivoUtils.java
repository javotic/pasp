package mx.gob.edomex.firma.fump.utils;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author adrian
 */

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.codec.binary.Base64;


public class ArchivoUtils {


//Crea un archivo a partir de un array de bytes
    public static void crearArchivoBinario(String rutaFichero, byte[] buffer) throws IOException {
        try {
            File file = new File(rutaFichero);
            file.createNewFile();
            FileInputStream fileInputStream = new FileInputStream(rutaFichero);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(buffer);
            OutputStream outputStream = new FileOutputStream(rutaFichero);
            int data;
            while ((data = byteArrayInputStream.read()) != -1) {
                outputStream.write(data);
            }
            fileInputStream.close();
            outputStream.close();
        } catch (IOException e) {
            throw e;
        }

    }

    public static byte[] copiarFicheroAMemoria(String ruta) throws IOException {
        byte[] contenidoDelFichero = null;
        File file = new File(ruta);
        FileInputStream fis;
        BufferedInputStream bis = null;
        long l = file.length();
        byte[] buffer = new byte[(int) l];
        int leido;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();


        try {
            fis = new FileInputStream(file);
            bis = new BufferedInputStream(fis);
            while ((leido = bis.read(buffer)) >= 0) {
                bos.write(buffer, 0, leido);
            }
            contenidoDelFichero = bos.toByteArray();
            bos.reset();
            bos.close();
        } catch (IOException e1) {
            throw e1;
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        return contenidoDelFichero;
    }

    public static boolean fileExist(String nombreFichero) throws Exception {
        File theFile = new File(nombreFichero);
        return theFile.exists();
    }

    public static String getHashFromFile(String path, String paramString) throws IOException, NoSuchAlgorithmException {        
        MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
        FileInputStream localFileInputStream = new FileInputStream(new File(path));
        byte[] arrayOfByte1 = new byte[100];
        int i;
        while ((i = localFileInputStream.read(arrayOfByte1)) != -1) {
            localMessageDigest.update(arrayOfByte1, 0, i);
        }
        byte[] arrayOfByte2 = localMessageDigest.digest();
        return getHexValue(arrayOfByte2);
    }
    
    public static String getHashFromBytes(byte[] content, String paramString) throws NoSuchAlgorithmException {        
        MessageDigest localMessageDigest = MessageDigest.getInstance(paramString); 
        byte[] arrayOfByte2 = localMessageDigest.digest(content);
        return getHexValue(arrayOfByte2);
    }

    public static String getHexValue(byte[] b) {
        
        StringBuilder sb = new StringBuilder(b.length);
        for (byte x : b) {          
            sb.append(String.format("%02x", x));
        }
        return sb.toString();
    }
    
    public static String convertirBytesToStringBase64(byte[] buf) {
        return Base64.encodeBase64String(buf);
    }

    public static byte[] convertirStringBase64ToBytes(String s) throws IOException {
        return Base64.decodeBase64(s);
    }
}

