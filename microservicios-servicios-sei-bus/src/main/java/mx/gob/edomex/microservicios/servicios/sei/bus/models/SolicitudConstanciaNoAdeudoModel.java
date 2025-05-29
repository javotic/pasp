package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * Modelo de datos de solicitud de Constancia de No Adeudo.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 15/12/2021.
 */
public class SolicitudConstanciaNoAdeudoModel {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private int folio;
    private String claveServidorPublico;
    private String nombreServidorPublico;
    private Date fechaSolicitud;
    private String estatus;
    private String fechaSolicitudCadena;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public SolicitudConstanciaNoAdeudoModel() {
    }

    public SolicitudConstanciaNoAdeudoModel(int folio, String claveServidorPublico,
            String nombreServidorPublico, Date fechaSolicitud, String estatus,
            String fechaSolicitudCadena) {
        this.folio = folio;
        this.claveServidorPublico = claveServidorPublico;
        this.nombreServidorPublico = nombreServidorPublico;
        this.fechaSolicitud = fechaSolicitud;
        this.estatus = estatus;
        this.fechaSolicitudCadena = fechaSolicitudCadena;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "SolicitudConstanciaNoAdeudoModel{"
                + "folio=" + folio
                + ", claveServidorPublico=" + claveServidorPublico
                + ", nombreServidorPublico=" + nombreServidorPublico
                + ", fechaSolicitud=" + fechaSolicitud
                + ", estatus=" + estatus
                + ", fechaSolicitudCadena=" + fechaSolicitudCadena
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.folio;
        hash = 97 * hash + Objects.hashCode(this.claveServidorPublico);
        hash = 97 * hash + Objects.hashCode(this.nombreServidorPublico);
        hash = 97 * hash + Objects.hashCode(this.fechaSolicitud);
        hash = 97 * hash + Objects.hashCode(this.estatus);
        hash = 97 * hash + Objects.hashCode(this.fechaSolicitudCadena);
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
        final SolicitudConstanciaNoAdeudoModel other = (SolicitudConstanciaNoAdeudoModel) obj;
        if (this.folio != other.folio) {
            return false;
        }
        if (!Objects.equals(this.claveServidorPublico, other.claveServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.nombreServidorPublico, other.nombreServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.estatus, other.estatus)) {
            return false;
        }
        if (!Objects.equals(this.fechaSolicitud, other.fechaSolicitud)) {
            return false;
        }
        if (!Objects.equals(this.fechaSolicitudCadena, other.fechaSolicitudCadena)) {
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public String getClaveServidorPublico() {
        return claveServidorPublico;
    }

    public void setClaveServidorPublico(String claveServidorPublico) {
        this.claveServidorPublico = claveServidorPublico;
    }

    public String getNombreServidorPublico() {
        return nombreServidorPublico;
    }

    public void setNombreServidorPublico(String nombreServidorPublico) {
        this.nombreServidorPublico = nombreServidorPublico;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getFechaSolicitudCadena() {
        return fechaSolicitudCadena;
    }

    public void setFechaSolicitudCadena(String fechaSolicitudCadena) {
        this.fechaSolicitudCadena = fechaSolicitudCadena;
    }
    //</editor-fold>
}
