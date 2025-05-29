/*
 * Copyright (c) 2019. DIRECCIÓN GENERAL DE PERSONAL.
 * Sistema de nómina y recursos humanos DGP.
 * Elaborado en consorcio por C&A Systems.
 * Se prohibe la reproducción total y/o parcial.
 */
package mx.gob.edomex.ms.simp.fump.transferenciafumps.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.Paths.get;
import java.util.ArrayList;
import java.util.List;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador de servicio de transferencia de FUMPS.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 26/10/2021.
 */
@RestController
@RequestMapping("/fump")
public class TransferenciaFumpController {
    
    //<editor-fold desc="Atributos de clase" defaultstate="collapsed">
//    public static final String REPOSITORIO = "C:\\tmp\\SIGAP\\REPOSITORIO";
    public static final String REPOSITORIO = "/Users/jalvarado/Repositorio";
    //</editor-fold>
    
    //<editor-fold desc="Metodos GET" defaultstate="collapsed">
    
    @GetMapping("/")
    public void descargarArchivo() throws IOException {
        System.out.println("Servicio levantando correctamente");
    }
    
    
    /**
     * Descargar archivo a traves de su nombre.
     * @author Javier Alvarado Rodriguez.
     * @version 1.0, 27/10/2021.
     * @param nombreArchivo Nombre de archivo a descargar.
     * @return Recurso encontrado
     * @throws java.io.IOException de busqueda de documentos 
     */
    @GetMapping("descargar/{nombreArchivo}")
    public ResponseEntity<Resource> descargarArchivo(
            @PathVariable("nombreArchivo") String nombreArchivo) throws IOException {
        Path rutaArchivo = get(REPOSITORIO).toAbsolutePath().normalize().resolve(nombreArchivo);
        
        if(!Files.exists(rutaArchivo)){
            throw new FileNotFoundException(nombreArchivo + " no se encontró en el servidor.");
        }

        List<MediaType> mediaTypeList = new ArrayList<>();
        mediaTypeList.add(MediaType.APPLICATION_PDF);
        
        Resource recurso = new UrlResource(rutaArchivo.toUri());
        
        HttpHeaders encabezadosHttp = new HttpHeaders();
        encabezadosHttp.add("File-Name", nombreArchivo);
        encabezadosHttp.setAccept(mediaTypeList);
        encabezadosHttp.add(CONTENT_DISPOSITION, "attachment;File-Name="+recurso.getFilename());
        
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF)
                .headers(encabezadosHttp).body(recurso);
    }
    //</editor-fold>
}
