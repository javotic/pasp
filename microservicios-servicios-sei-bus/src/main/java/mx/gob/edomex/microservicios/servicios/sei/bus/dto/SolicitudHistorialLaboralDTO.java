package mx.gob.edomex.microservicios.servicios.sei.bus.dto;

import java.util.Objects;

/**
 *
 * Objeto de transporte de datos para insercion de solicitud de historial
 * laboral.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 08/11/2021.
 */
public class SolicitudHistorialLaboralDTO {

    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String claveServidorPublico;
    private int idMotivo;
    private String comentarios;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public SolicitudHistorialLaboralDTO() {
    }

    public SolicitudHistorialLaboralDTO(String claveServidorPublico, int idMotivo, String comentarios) {
        this.claveServidorPublico = claveServidorPublico;
        this.idMotivo = idMotivo;
        this.comentarios = comentarios;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "SolicitudHistorialLaboraDTO{"
                + "claveServidorPublico=" + claveServidorPublico
                + ", idMotivo=" + idMotivo
                + ", comentarios=" + comentarios
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.claveServidorPublico);
        hash = 47 * hash + this.idMotivo;
        hash = 47 * hash + Objects.hashCode(this.comentarios);
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
        final SolicitudHistorialLaboralDTO other = (SolicitudHistorialLaboralDTO) obj;
        if (this.idMotivo != other.idMotivo) {
            return false;
        }
        if (!Objects.equals(this.claveServidorPublico, other.claveServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.comentarios, other.comentarios)) {
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public String getClaveServidorPublico() {
        return claveServidorPublico;
    }

    public void setClaveServidorPublico(String claveServidorPublico) {
        this.claveServidorPublico = claveServidorPublico;
    }

    public int getIdMotivo() {
        return idMotivo;
    }

    public void setIdMotivo(int idMotivo) {
        this.idMotivo = idMotivo;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    //</editor-fold>
}
