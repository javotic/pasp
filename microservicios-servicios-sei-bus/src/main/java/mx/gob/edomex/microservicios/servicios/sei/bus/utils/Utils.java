package mx.gob.edomex.microservicios.servicios.sei.bus.utils;

import java.util.Objects;

public class Utils {

    public String objectIsNULL(Object obj) {
        if (Objects.isNull(obj)) {
            return "";
        } else {
            return obj.toString();
        }

    }

}
