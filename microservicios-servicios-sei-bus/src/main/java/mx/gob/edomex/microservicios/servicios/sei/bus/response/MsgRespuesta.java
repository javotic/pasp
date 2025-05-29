
package mx.gob.edomex.microservicios.servicios.sei.bus.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 *
 * @author smartinez
 */
public class MsgRespuesta  implements Serializable{
     private static final long serialVersionUID = 1L;
     
     
     private String RESPUESTA;

    @JsonProperty("RESPUESTA")
    public String getRESPUESTA() {
        return RESPUESTA;
    }

    public void setRESPUESTA(String RESPUESTA) {
        this.RESPUESTA = RESPUESTA;
    }
     
     
}
