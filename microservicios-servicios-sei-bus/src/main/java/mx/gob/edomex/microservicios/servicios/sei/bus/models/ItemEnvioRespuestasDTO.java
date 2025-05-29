/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author smartinez
 */
public class ItemEnvioRespuestasDTO implements Serializable {
        private String IDPREGUNTA; 
    private String IDRESPUESTA;                              
    private String IDEVALUACION;
    private String IDSERVIDORPUBLICOEVALUADO;
    private String IDSERVIDORPUBLICOEVALUADOR;
    private String IDUNIDADADMINISTRAR;
    private String TIPOSECCION;
    private String PUNTAJEOBTENIDO;
    private String EVCONCLUIDA;

    @JsonProperty("IDPREGUNTA")
    public String getIDPREGUNTA() {
        return IDPREGUNTA;
    }

    public void setIDPREGUNTA(String IDPREGUNTA) {
        this.IDPREGUNTA = IDPREGUNTA;
    }

    @JsonProperty("IDRESPUESTA")
    public String getIDRESPUESTA() {
        return IDRESPUESTA;
    }

    public void setIDRESPUESTA(String IDRESPUESTA) {
        this.IDRESPUESTA = IDRESPUESTA;
    }

    @JsonProperty("IDEVALUACION")
    public String getIDEVALUACION() {
        return IDEVALUACION;
    }

    
    public void setIDEVALUACION(String IDEVALUACION) {
        this.IDEVALUACION = IDEVALUACION;
    }

    @JsonProperty("IDSERVIDORPUBLICOEVALUADO")
    public String getIDSERVIDORPUBLICOEVALUADO() {
        return IDSERVIDORPUBLICOEVALUADO;
    }

    public void setIDSERVIDORPUBLICOEVALUADO(String IDSERVIDORPUBLICOEVALUADO) {
        this.IDSERVIDORPUBLICOEVALUADO = IDSERVIDORPUBLICOEVALUADO;
    }

    @JsonProperty("IDSERVIDORPUBLICOEVALUADOR")
    public String getIDSERVIDORPUBLICOEVALUADOR() {
        return IDSERVIDORPUBLICOEVALUADOR;
    }

    public void setIDSERVIDORPUBLICOEVALUADOR(String IDSERVIDORPUBLICOEVALUADOR) {
        this.IDSERVIDORPUBLICOEVALUADOR = IDSERVIDORPUBLICOEVALUADOR;
    }

    @JsonProperty("IDUNIDADADMINISTRAR")
    public String getIDUNIDADADMINISTRAR() {
        return IDUNIDADADMINISTRAR;
    }

    public void setIDUNIDADADMINISTRAR(String IDUNIDADADMINISTRAR) {
        this.IDUNIDADADMINISTRAR = IDUNIDADADMINISTRAR;
    }

    @JsonProperty("TIPOSECCION")
    public String getTIPOSECCION() {
        return TIPOSECCION;
    }

    public void setTIPOSECCION(String TIPOSECCION) {
        this.TIPOSECCION = TIPOSECCION;
    }

    @JsonProperty("PUNTAJEOBTENIDO")
    public String getPUNTAJEOBTENIDO() {
        return PUNTAJEOBTENIDO;
    }

    public void setPUNTAJEOBTENIDO(String PUNTAJEOBTENIDO) {
        this.PUNTAJEOBTENIDO = PUNTAJEOBTENIDO;
    }

    @JsonProperty("EVCONCLUIDA")
    public String getEVCONCLUIDA() {
        return EVCONCLUIDA;
    }

    public void setEVCONCLUIDA(String EVCONCLUIDA) {
        this.EVCONCLUIDA = EVCONCLUIDA;
    }

        @Override
        public String toString() {
            return "ItemEnvioRespuestasDTO{" + "IDPREGUNTA=" + IDPREGUNTA + ", IDRESPUESTA=" + IDRESPUESTA + ", IDEVALUACION=" + IDEVALUACION + ", IDSERVIDORPUBLICOEVALUADO=" + IDSERVIDORPUBLICOEVALUADO + ", IDSERVIDORPUBLICOEVALUADOR=" + IDSERVIDORPUBLICOEVALUADOR + ", IDUNIDADADMINISTRAR=" + IDUNIDADADMINISTRAR + ", TIPOSECCION=" + TIPOSECCION + ", PUNTAJEOBTENIDO=" + PUNTAJEOBTENIDO + ", EVCONCLUIDA=" + EVCONCLUIDA + '}';
        }
    
}
