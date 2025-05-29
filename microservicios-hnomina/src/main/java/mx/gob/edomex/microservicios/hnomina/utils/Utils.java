package mx.gob.edomex.microservicios.hnomina.utils;

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
