import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ConstanciaNoAdeudoDTO } from '../dto/constanciaNoAdeudoDTO';
import { ConstanciaNoAdeudoModel } from '../models/constanciaNoAdeudoModel';
import { RespuestaApi } from '../repuesta/respuesta-api';

@Injectable({
  providedIn: 'root'
})
export class SolicitudConstanciaNoAdeudoService {
  //#region Propiedades de clase
  httpOptions = {
    headers: {
      'Content-type': 'application/json'
    }
  }
  //#endregion

  //#region Constructor
  constructor(
    private http: HttpClient
  ) { }
  //#endregion

  //#region Funciones publicas
  /**
   * Obtener listado de solicitudes realizadas por un 
   * servidor publico determinado.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 16/12/2021.
   * @param claveServidor Clave de servidor publico del que se obtendran 
   * los datos.
   * @param claveServidorBusqueda Clave de servidor publico que realiza 
   * la busqueda de la informacion.
   * @param banderaBusqueda Bandera de busqueda; 1 para busqueda de datos 
   * propios, y 2 para busqueda de coordinador.
   * @return Observable de respuesta principal de servicio de tipo HistorialLaboralModel.
  */
  obtenerSolicitudesRealizadas(claveServidor: String, claveServidorBusqueda: String,
    banderaBusqueda: number): Observable<RespuestaApi<ConstanciaNoAdeudoModel>> {
    let url = `/constanciaNoAdeudo/obtenerSolicitudes?claveServidor=${claveServidor}&nombreServidor&claveServidorBusqueda=${claveServidorBusqueda}&banderaBusqueda=${banderaBusqueda}`;
    return this.http.get<RespuestaApi<ConstanciaNoAdeudoModel>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  /**
   * Insertar nueva solicitud de constancia de no adeudo.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 16/12/2021.
   * @param claveServidorPublico Clave de servidor publico al que se realiza la solicitud.
   * @param comentarios Comentarios de solicitud de historial laboral.
   * @return Observable de respuesta principal de servicio de tipo String
  */
  insertarSolicitudHistorialLaboral(claveServidorPublico: String,
    comentarios: String): Observable<RespuestaApi<String>> {
    let url = `/constanciaNoAdeudo/insertarSolicitud`;
    let constanciaNoAdeudoDTO: ConstanciaNoAdeudoDTO = {
      claveServidorPublico: claveServidorPublico,
      comentarios: comentarios
    };

    return this.http.post<RespuestaApi<String>>(
      `${environment.BASEENPOINT_BUS_SEI}${url}`,
      constanciaNoAdeudoDTO,
      this.httpOptions
    );
  }

  /**
   * Descargar documento de constancia de no adeudo.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 05/01/2022.
   * @version 1.1, 13/01/2022, Javier Alvarado Rodriguez, Adicion de campo de folio para consumo de servicio.
   * @param claveServidor Clave de servidor publico del que se generara la constancia de no adeudo.
   * @returns Observable con el archivo de constancia de no adeudo generado.
  */
  obtenerConstanciaNoAdeudo(claveServidorPublico: String,
    folio: String) {
    let url = `/constanciaNoAdeudo/descargarSolicitud?claveServidor=${claveServidorPublico}&folio=${folio}`
    return this.http.post(
      `${environment.BASEENPOINT_BUS_SEI}${url}`,
      null,
      { responseType: 'blob' }
    );
  }
  //#endregion
}
