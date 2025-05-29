package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;
import java.util.Objects;

/**
 * Modelo de categoria de incremento salarial.
 *
 * @author Javier Alvarado Rodriguez.
 * @version 18/07/2022.
 */
public class CategoriaIncrementoSalarialModel implements Serializable{

    //<editor-fold desc="Atributos de clase" defaultstate="collapsed">
    private static final long serialVersionUID = 1L;
    private String categoria;
    private double porcentaje;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public CategoriaIncrementoSalarialModel() {
    }

    public CategoriaIncrementoSalarialModel(String categoria, double porcentaje) {
        this.categoria = categoria;
        this.porcentaje = porcentaje;
    }
    //</editor-fold>

    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override
    public String toString() {
        return "CategoriaIncrementoSalarialModel{"
                + "categoria=" + categoria
                + ", porcentaje=" + porcentaje
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.categoria);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.porcentaje) ^ (Double.doubleToLongBits(this.porcentaje) >>> 32));
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
        final CategoriaIncrementoSalarialModel other = (CategoriaIncrementoSalarialModel) obj;
        if (Double.doubleToLongBits(this.porcentaje) != Double.doubleToLongBits(other.porcentaje)) {
            return false;
        }
        if (!Objects.equals(this.categoria, other.categoria)) {
            return false;
        }
        return true;
    }
    //</editor-fold>

    //<editor-fold desc="Acccesores" defaultstate="collapsed">
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }
    //</editor-fold>
}
