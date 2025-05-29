/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.edomex.microservicios.servicios.sei.bus.models;

import java.io.Serializable;

/**
 *
 * @author smartinez
 */
public class EnvioRespuestasTableDTO implements Serializable{
    private String IDPREGUNTA;
    private String IDRESPUESTA;
    private String IDEVALUACION;
    private String IDSEVIDORPUBLICOEVALUADO;
    private String IDSEVIDORPUBLICOEVALUADOR;
    private String IDUNIDADADMINISTRATIVA;
    private String TIPOSECCION;
    private int PUNTAJEOBTENIDO;
    private int EVCONCLUIDA;

    public String getIDPREGUNTA() {
        return IDPREGUNTA;
    }

    public void setIDPREGUNTA(String IDPREGUNTA) {
        this.IDPREGUNTA = IDPREGUNTA;
    }

    public String getIDRESPUESTA() {
        return IDRESPUESTA;
    }

    public void setIDRESPUESTA(String IDRESPUESTA) {
        this.IDRESPUESTA = IDRESPUESTA;
    }

    public String getIDEVALUACION() {
        return IDEVALUACION;
    }

    public void setIDEVALUACION(String IDEVALUACION) {
        this.IDEVALUACION = IDEVALUACION;
    }

    public String getIDSEVIDORPUBLICOEVALUADO() {
        return IDSEVIDORPUBLICOEVALUADO;
    }

    public void setIDSEVIDORPUBLICOEVALUADO(String IDSEVIDORPUBLICOEVALUADO) {
        this.IDSEVIDORPUBLICOEVALUADO = IDSEVIDORPUBLICOEVALUADO;
    }

    public String getIDSEVIDORPUBLICOEVALUADOR() {
        return IDSEVIDORPUBLICOEVALUADOR;
    }

    public void setIDSEVIDORPUBLICOEVALUADOR(String IDSEVIDORPUBLICOEVALUADOR) {
        this.IDSEVIDORPUBLICOEVALUADOR = IDSEVIDORPUBLICOEVALUADOR;
    }

    public String getIDUNIDADADMINISTRATIVA() {
        return IDUNIDADADMINISTRATIVA;
    }

    public void setIDUNIDADADMINISTRATIVA(String IDUNIDADADMINISTRATIVA) {
        this.IDUNIDADADMINISTRATIVA = IDUNIDADADMINISTRATIVA;
    }

    public String getTIPOSECCION() {
        return TIPOSECCION;
    }

    public void setTIPOSECCION(String TIPOSECCION) {
        this.TIPOSECCION = TIPOSECCION;
    }

    public int getPUNTAJEOBTENIDO() {
        return PUNTAJEOBTENIDO;
    }

    public void setPUNTAJEOBTENIDO(int PUNTAJEOBTENIDO) {
        this.PUNTAJEOBTENIDO = PUNTAJEOBTENIDO;
    }

    public int getEVCONCLUIDA() {
        return EVCONCLUIDA;
    }

    public void setEVCONCLUIDA(int EVCONCLUIDA) {
        this.EVCONCLUIDA = EVCONCLUIDA;
    }
        
        
        
}
