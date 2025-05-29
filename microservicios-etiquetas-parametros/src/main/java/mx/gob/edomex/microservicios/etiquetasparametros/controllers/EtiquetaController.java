package mx.gob.edomex.microservicios.etiquetasparametros.controllers;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.gob.edomex.microservicios.etiquetasparametros.dto.ParametrosEtiquetasDTO;
import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.BtEtiqueta;
import mx.gob.edomex.microservicios.etiquetasparametros.models.entity.DtEtiqueta;
import mx.gob.edomex.microservicios.etiquetasparametros.services.interf.BitacoraEtiquetaService;
import mx.gob.edomex.microservicios.etiquetasparametros.services.interf.EtiquetaValorService;



/**
 * Controlador del acceso de etiqueta
 * @author jolmv
 * @version 1.0 31/01/2020
 * @since JDK 1.8
 */

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/etiquetaspagina")
@RestController
public class EtiquetaController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EtiquetaValorService etiquetavalor;

    @Autowired
    private BitacoraEtiquetaService bitacoravalor;
    
    //@Autowired
    //private UsuarioSistemaService ususistem;

   
    
    private List<DtEtiqueta> listaInfoEtiquetas;

    
    private List<DtEtiqueta> filteredEtiquetas;
    
    private Map<String, String> lsEtiquetasVista;
        
    private BtEtiqueta infoBitacora;
    
    
    //private Optional<CtUsuariosistema> infoUsuSis;

    private String valorPrueba;

    
    
    //<editor-fold desc="Metodo etiquetasPagina" defaultstate="collapsed">
	@PostMapping
    public ResponseEntity<?> etiquetasPagina(@RequestBody ParametrosEtiquetasDTO dto) throws Exception {
        this.lsEtiquetasVista = new HashMap<>();
        try {
            logger.info("ENTRO AL METODO etiquetasPagina()============> ");


            
            //Obtener URI
            /*HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().
                    getExternalContext().getRequest();*/
            //Buscar opcion en base a URL.
            /*String URL = request.getRequestURI().substring(request.getContextPath().length());
            Locale.setDefault(new Locale("es", "MX"));
            Locale idiomaActual = Locale.getDefault();*/                                                                                                                                                                                                                                                                                                                                                      
            
            System.out.println("URL + " + dto.getUrl());
            System.out.println("idiomaActual + " + dto.getIdioma());

            this.listaInfoEtiquetas = this.etiquetavalor.searchEtiquetasByLanguageURLGeneral(dto.getUrl(), dto.getIdioma());
            
            if (listaInfoEtiquetas.size() > 0) {
                filteredEtiquetas = listaInfoEtiquetas;
            }

            this.listaInfoEtiquetas.forEach((etiqueta) -> {
                System.out.println("Etiqueta + " + etiqueta.getCtEtiqueta().getDsllave());
                System.out.println("Valor Etiqueta + " + etiqueta.getDsvaloretiqueta());
                this.lsEtiquetasVista.put(etiqueta.getCtEtiqueta().getDsllave(), etiqueta.getDsvaloretiqueta());
            });


            

        } catch (IOException ex) {
            logger.info("ERROR AL MOSTRAR LA INFORMACION de etiquetasPagina()");
            System.out.println("STACKTRACE" + Arrays.toString(ex.getSuppressed()));
            System.out.println("CAUSE" + ex.getCause());
            System.out.println("LOCALIZEDMESSAGE" + ex.getLocalizedMessage());
            System.out.println("MESSAGE" + ex.getMessage());

        }
        return ResponseEntity.ok(lsEtiquetasVista);
    }
    //</editor-fold>

    
    //<editor-fold desc="actualizarEtiqueta" defaultstate="collapsed">
    /*Se quito el metodo de actualizar etiqueta*/
    //</editor-fold>
    
    

   

    
    
    //<editor-fold desc="Getters & Setters" defaultstate="collapsed">
    public String getValorPrueba() {
        return valorPrueba;
    }

    public void setValorPrueba(String valorPrueba) {
        this.valorPrueba = valorPrueba;
    }

    public List<DtEtiqueta> getListaInfoEtiquetas() {
        return listaInfoEtiquetas;
    }

    public void setListaInfoEtiquetas(List<DtEtiqueta> listaInfoEtiquetas) {
        this.listaInfoEtiquetas = listaInfoEtiquetas;
    }

    public List<DtEtiqueta> getFilteredEtiquetas() {
        return filteredEtiquetas;
    }

    public void setFilteredEtiquetas(List<DtEtiqueta> filteredEtiquetas) {
        this.filteredEtiquetas = filteredEtiquetas;
    }

    public BtEtiqueta getInfoBitacora() {
        return infoBitacora;
    }

    public void setInfoBitacora(BtEtiqueta infoBitacora) {
        this.infoBitacora = infoBitacora;
    }
    
      public Map<String, String> getLsEtiquetasVista() {
        return lsEtiquetasVista;
    }

    public void setLsEtiquetasVista(Map<String, String> lsEtiquetasVista) {
        this.lsEtiquetasVista = lsEtiquetasVista;
    }

    /*public Optional<CtUsuariosistema> getInfoUsuSis() {
        return infoUsuSis;
    }

    public void setInfoUsuSis(Optional<CtUsuariosistema> infoUsuSis) {
        this.infoUsuSis = infoUsuSis;
    }*/
    
    
    
    //</editor-fold>

    

    

  
    

    

}
