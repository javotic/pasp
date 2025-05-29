/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.util.Objects;

/**
 *
 * @author smartinez
 */
public class Combo {
    //<editor-fold desc="Propiedades de clase" defaultstate="collapsed">
    private String value;
    private String label;
    //</editor-fold>

    //<editor-fold desc="Constructores" defaultstate="collapsed">
    public Combo() {
    }

    public Combo(String value, String label) {
        this.value = value;
        this.label = label;
    }
    //</editor-fold>
    
    //<editor-fold desc="Funciones publicas" defaultstate="collapsed">
    @Override    
    public String toString() {
        return "Combo{" 
                + "value=" + value 
                + ", label=" + label 
                + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.value);
        hash = 59 * hash + Objects.hashCode(this.label);
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
        final Combo other = (Combo) obj;
        if (!Objects.equals(this.value, other.value)) {
            return false;
        }
        if (!Objects.equals(this.label, other.label)) {
            return false;
        }
        return true;
    }
    //</editor-fold>
    
    //<editor-fold desc="Accesores" defaultstate="collapsed">
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    //</editor-fold>    
}
