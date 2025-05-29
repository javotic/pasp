package mx.gob.edomex.microservicios.servicios.sei.bus.dto;

import java.util.Objects;

/**
 *
 * Objeto de transporte de datos para insercion de solicitud de constancia de no
 * adeudo.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 16/12/2021.
 */
public class SolicitudConstanciaNoAdeudoDTO {
    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String claveServidorPublico;
    private String comentarios;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public SolicitudConstanciaNoAdeudoDTO() {
    }

    public SolicitudConstanciaNoAdeudoDTO(String claveServidorPublico, 
            String comentarios) {
        this.claveServidorPublico = claveServidorPublico;
        this.comentarios = comentarios;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "SolicitudHistorialLaboraDTO{"
                + "claveServidorPublico=" + claveServidorPublico
                + ", comentarios=" + comentarios
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.claveServidorPublico);
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
        final SolicitudConstanciaNoAdeudoDTO other = (SolicitudConstanciaNoAdeudoDTO) obj;
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    //</editor-fold>
}
