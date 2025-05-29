package mx.gob.edomex.microservicios.servicios.sei.bus.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * Objeto de Transferencia de datos de servidor publico de incremento salarial.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 1.0, 18/07/2022.
 */
public class ServidorPublicoIncrementoSalarialDTO implements Serializable {

    //<editor-fold desc="Atributos de clase" defaultstate="collapsed">
    private String claveServidorPublico;
    private String nombreCompletoServidorPublico;
    private String nombrePuesto;
    private String codigoPuesto;
    private int tipoServidorPublico;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public ServidorPublicoIncrementoSalarialDTO() {
    }
    
    public ServidorPublicoIncrementoSalarialDTO(String claveServidorPublico) {
        this.claveServidorPublico = claveServidorPublico;
    }

    public ServidorPublicoIncrementoSalarialDTO(String claveServidorPublico,
            String nombreCompletoServidorPublico, String nombrePuesto,
            String codigoPuesto, int tipoServidorPublico) {
        this.claveServidorPublico = claveServidorPublico;
        this.nombreCompletoServidorPublico = nombreCompletoServidorPublico.replaceAll("\\s{2,}"," ");
        this.nombrePuesto = nombrePuesto.replaceAll("\\s{2,}"," ");
        this.codigoPuesto = codigoPuesto;
        this.tipoServidorPublico = tipoServidorPublico;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "ServidorPublicoIncrementoSalarialDTO{"
                + "claveServidorPublico=" + claveServidorPublico
                + ", nombreCompletoServidorPublico=" + nombreCompletoServidorPublico
                + ", nombrePuesto=" + nombrePuesto
                + ", codigoPuesto=" + codigoPuesto
                + ", tipoServidorPublico=" + tipoServidorPublico
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.claveServidorPublico);
        hash = 89 * hash + Objects.hashCode(this.nombreCompletoServidorPublico);
        hash = 89 * hash + Objects.hashCode(this.nombrePuesto);
        hash = 89 * hash + this.tipoServidorPublico;
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
        final ServidorPublicoIncrementoSalarialDTO other = (ServidorPublicoIncrementoSalarialDTO) obj;
        if (this.tipoServidorPublico != other.tipoServidorPublico) {
            return false;
        }
        if (!Objects.equals(this.claveServidorPublico, other.claveServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.nombreCompletoServidorPublico, other.nombreCompletoServidorPublico)) {
            return false;
        }
        if (!Objects.equals(this.nombrePuesto, other.nombrePuesto)) {
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

    public String getNombreCompletoServidorPublico() {
        return nombreCompletoServidorPublico;
    }

    public void setNombreCompletoServidorPublico(String nombreCompletoServidorPublico) {
        this.nombreCompletoServidorPublico = nombreCompletoServidorPublico;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public int getTipoServidorPublico() {
        return tipoServidorPublico;
    }

    public void setTipoServidorPublico(int tipoServidorPublico) {
        this.tipoServidorPublico = tipoServidorPublico;
    }

    public String getCodigoPuesto() {
        return codigoPuesto;
    }

    public void setCodigoPuesto(String codigoPuesto) {
        this.codigoPuesto = codigoPuesto;
    }
    //</editor-fold>
}
