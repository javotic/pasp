package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.util.Date;
import java.util.Objects;

/**
 *
 * Modelo de datos de solicitud de historial laboral.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 08/11/2021.
 */
public class SolicitudHistoricoLaboralModel {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private int folio;
    private String claveServidorPublico;
    private String nombreServidorPublico;
    private Date fechaSolicitud;
    private String motivo;
    private String estatus;
    private String fechaSolicitudCadena;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public SolicitudHistoricoLaboralModel() {
    }

    public SolicitudHistoricoLaboralModel(int folio, String claveServidorPublico,
            String nombreServidorPublico, Date fechaSolicitud, String motivo,
            String estatus, String fechaSolicitudCadena) {
        this.folio = folio;
        this.claveServidorPublico = claveServidorPublico;
        this.nombreServidorPublico = nombreServidorPublico;
        this.fechaSolicitud = fechaSolicitud;
        this.motivo = motivo;
        this.estatus = estatus;
        this.fechaSolicitudCadena = fechaSolicitudCadena;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "SolicitudHistoricoLaboralModel{"
                + "folio=" + folio
                + ", claveServidorPublico=" + claveServidorPublico
                + ", nombreServidorPublico=" + nombreServidorPublico
                + ", fechaSolicitud=" + fechaSolicitud
                + ", motivo=" + motivo
                + ", estatus=" + estatus
                + ", fechaSolicitudCadena=" + fechaSolicitudCadena
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.folio;
        hash = 71 * hash + Objects.hashCode(this.claveServidorPublico);
        hash = 71 * hash + Objects.hashCode(this.nombreServidorPublico);
        hash = 71 * hash + Objects.hashCode(this.fechaSolicitud);
        hash = 71 * hash + Objects.hashCode(this.motivo);
        hash = 71 * hash + Objects.hashCode(this.estatus);
        hash = 71 * hash + Objects.hashCode(this.fechaSolicitudCadena);
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
        final SolicitudHistoricoLaboralModel other = (SolicitudHistoricoLaboralModel) obj;
        if (this.folio != other.folio) {
            return false;
        }
        if (!Objects.equals(this.claveServidorPublico, other.claveServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.nombreServidorPublico, other.nombreServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.motivo, other.motivo)) {
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

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
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
