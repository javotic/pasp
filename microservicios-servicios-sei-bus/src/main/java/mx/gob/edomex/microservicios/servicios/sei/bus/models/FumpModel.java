package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.Objects;

/**
 *
 * Modelo de datos de FUMP.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 20/10/2021.
 */
public class FumpModel {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private long folio;
    private String claveServidorPublico;
    private String claveUnidadAdministrativa;
    private String numeroPlaza;
    private String CCT;
    private String tipoMovimiento;
    private Date fechaFirma;
    private String nombreArchivo;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public FumpModel() {
    }

    public FumpModel(long folio, String claveServidorPublico,
            String claveUnidadAdministrativa, String numeroPlaza,
            String CCT, String tipoMovimiento, Date fechaFirma,
            String nombreArchivo) {
        this.folio = folio;
        this.claveServidorPublico = claveServidorPublico;
        this.claveUnidadAdministrativa = claveUnidadAdministrativa;
        this.numeroPlaza = numeroPlaza;
        this.CCT = CCT;
        this.tipoMovimiento = tipoMovimiento;
        this.fechaFirma = fechaFirma;
        this.nombreArchivo = nombreArchivo;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicacs" defaultstate="collapsed">
    @Override()
    public String toString() {
        return "FumpModel{"
                + "folio=" + folio
                + ", claveServidorPublico=" + claveServidorPublico
                + ", claveUnidadAdministrativa=" + claveUnidadAdministrativa
                + ", numeroPlaza=" + numeroPlaza
                + ", CCT=" + CCT
                + ", tipoMovimiento=" + tipoMovimiento
                + ", fechaFirma=" + fechaFirma
                + ", nombreArchivo=" + nombreArchivo
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + (int) (this.folio ^ (this.folio >>> 32));
        hash = 83 * hash + Objects.hashCode(this.claveServidorPublico);
        hash = 83 * hash + Objects.hashCode(this.claveUnidadAdministrativa);
        hash = 83 * hash + Objects.hashCode(this.numeroPlaza);
        hash = 83 * hash + Objects.hashCode(this.CCT);
        hash = 83 * hash + Objects.hashCode(this.tipoMovimiento);
        hash = 83 * hash + Objects.hashCode(this.fechaFirma);
        hash = 83 * hash + Objects.hashCode(this.nombreArchivo);
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
        final FumpModel other = (FumpModel) obj;
        if (this.folio != other.folio) {
            return false;
        }
        if (!Objects.equals(this.claveServidorPublico, other.claveServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.claveUnidadAdministrativa, other.claveUnidadAdministrativa)) {
            return false;
        }
        if (!Objects.equals(this.numeroPlaza, other.numeroPlaza)) {
            return false;
        }
        if (!Objects.equals(this.CCT, other.CCT)) {
            return false;
        }
        if (!Objects.equals(this.tipoMovimiento, other.tipoMovimiento)) {
            return false;
        }
        if (!Objects.equals(this.nombreArchivo, other.nombreArchivo)) {
            return false;
        }
        if (!Objects.equals(this.fechaFirma, other.fechaFirma)) {
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Accesores" defaultstate="collapsed">
    @JsonProperty(value = "folio")
    public long getFolio() {
        return folio;
    }

    public void setFolio(long folio) {
        this.folio = folio;
    }

    @JsonProperty("claveServidorPublico")
    public String getClaveServidorPublico() {
        return claveServidorPublico;
    }

    public void setClaveServidorPublico(String claveServidorPublico) {
        this.claveServidorPublico = claveServidorPublico;
    }

    @JsonProperty("claveUnidadAdministrativa")
    public String getClaveUnidadAdministrativa() {
        return claveUnidadAdministrativa;
    }

    public void setClaveUnidadAdministrativa(String claveUnidadAdministrativa) {
        this.claveUnidadAdministrativa = claveUnidadAdministrativa;
    }

    @JsonProperty("numeroPlaza")
    public String getNumeroPlaza() {
        return numeroPlaza;
    }

    public void setNumeroPlaza(String numeroPlaza) {
        this.numeroPlaza = numeroPlaza;
    }

    @JsonProperty("CCT")
    public String getCCT() {
        return CCT;
    }

    public void setCCT(String CCT) {
        this.CCT = CCT;
    }

    @JsonProperty("tipoMovimiento")
    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    @JsonProperty("fechaFirma")
    public Date getFechaFirma() {
        return fechaFirma;
    }

    public void setFechaFirma(Date fechaFirma) {
        this.fechaFirma = fechaFirma;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    //</editor-fold>
}
