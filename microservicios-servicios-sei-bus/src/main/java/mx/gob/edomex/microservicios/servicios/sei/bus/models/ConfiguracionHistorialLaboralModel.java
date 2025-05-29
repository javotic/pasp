
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.util.Objects;

/**
 * Modelo de datos de configuracion de historial laboral.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 25/11/2021.
 */
public class ConfiguracionHistorialLaboralModel {
    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String fraseEncabezado;
    private String nombreFirma;
    //</editor-fold>
    
    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public ConfiguracionHistorialLaboralModel() {    
        this.fraseEncabezado = "";
        this.nombreFirma = "";
    }

    public ConfiguracionHistorialLaboralModel(String fraseEncabezado, String nombreFirma) {
        this.fraseEncabezado = fraseEncabezado;
        this.nombreFirma = nombreFirma;
    }
    //</editor-fold>
    
    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "ConfiguracionHistorialLaboralModel{" + "fraseEncabezado=" + fraseEncabezado + ", nombreFirma=" + nombreFirma + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.fraseEncabezado);
        hash = 47 * hash + Objects.hashCode(this.nombreFirma);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConfiguracionHistorialLaboralModel other = (ConfiguracionHistorialLaboralModel) obj;
        if (!Objects.equals(this.fraseEncabezado, other.fraseEncabezado)) {
            return false;
        }
        if (!Objects.equals(this.nombreFirma, other.nombreFirma)) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public String getFraseEncabezado() {
        return fraseEncabezado;
    }

    public void setFraseEncabezado(String fraseEncabezado) {
        this.fraseEncabezado = fraseEncabezado;
    }

    public String getNombreFirma() {
        return nombreFirma;
    }

    public void setNombreFirma(String nombreFirma) {
        this.nombreFirma = nombreFirma;
    }
    //</editor-fold>
}
