package mx.gob.edomex.microservicios.servicios.sei.bus.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * Objto de acceso a datos para seleccion de datos profesionales para escalafon.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 16/03/2022.
 */
public class SeleccionProcesoDTO {
    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    @JsonProperty("CLAVESP")
    private String claveServidorPublico;;
    @JsonProperty("LSPROFESIONALES")
    private List<SeleccionDatoProfesionalDTO> lsSeleccionDatosProfesionales;
    //</editor-fold>
    
    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public String getClaveServidorPublico() {
        return claveServidorPublico;
    }

    public void setClaveServidorPublico(String claveServidorPublico) {
        this.claveServidorPublico = claveServidorPublico;
    }

    public List<SeleccionDatoProfesionalDTO> getLsSeleccionDatosProfesionales() {
        return lsSeleccionDatosProfesionales;
    }

    public void setLsSeleccionDatosProfesionales(List<SeleccionDatoProfesionalDTO> lsSeleccionDatosProfesionales) {
        this.lsSeleccionDatosProfesionales = lsSeleccionDatosProfesionales;
    }
    //</editor-fold>
}
