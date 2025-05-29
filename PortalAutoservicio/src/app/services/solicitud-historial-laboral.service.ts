import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Combo } from '../models/combo';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { HistorialLaboralModel } from '../models/historialLaboralModel';
import { SolicitudHistorialLaboralDTO } from '../dto/solicitudHistorialLaboralDTO';

@Injectable({
  providedIn: 'root'
})
export class SolicitudHistorialLaboralService {
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
   * Obtener catalogo de motivos de solicitud
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 18/11/2021.
   * @returns Observable de respuesta principal de servicio de tipo Combo
  */
  obtenerMotiviosSolicitud(): Observable<RespuestaApi<Combo>> {
    let url = `/historialLaboral/obtenerCatalogoMotivosSolicitud`;
    return this.http.get<RespuestaApi<Combo>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  /**
   * Obtener listado de solicitudes realizadas por un 
   * servidor publico determinado.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 18/11/2021.
   * @param claveServidor Clave de servidor publico del que se obtendran 
   * los datos.
   * @param claveServidorBusqueda Clave de servidor publico que realiza 
   * la busqueda de la informacion.
   * @param banderaBusqueda Bandera de busqueda; 1 para busqueda de datos 
   * propios, y 2 para busqueda de coordinador.
   * @returns Observable de respuesta principal de servicio de tipo HistorialLaboralModel.
  */
  obtenerSolicitudesRealizadas(claveServidor: String, claveServidorBusqueda: String,
    banderaBusqueda: number): Observable<RespuestaApi<HistorialLaboralModel>> {
    let url = `/historialLaboral/obtenerSolicitudes?claveServidor=${claveServidor}&nombreServidor&claveServidorBusqueda=${claveServidorBusqueda}&banderaBusqueda=${banderaBusqueda}`;
    return this.http.get<RespuestaApi<HistorialLaboralModel>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  /**
   * Insertar nueva solicitud de historial laboral.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 18/11/2021.
   * @param claveServidor Clave de servidor publico al que se realiza la solicitud.
   * @param idMotivo ID del motivo de solicitud.
   * @param comentarios Comentarios de solicitud de historial laboral.
   * @returns Observable de respuesta principal de servicio de tipo String
  */
  insertarSolicitudHistorialLaboral(claveServidorPublico: String, idMotivo: number,
    comentarios: String): Observable<RespuestaApi<String>> {
    let url = `/historialLaboral/insertarSolicitud`;
    let solicitudHistorialLaboralDTO: SolicitudHistorialLaboralDTO = {
      claveServidorPublico: claveServidorPublico,
      idMotivo: idMotivo,
      comentarios: comentarios
    };

    return this.http.post<RespuestaApi<String>>(
      `${environment.BASEENPOINT_BUS_SEI}${url}`,
      solicitudHistorialLaboralDTO,
      this.httpOptions
    );

  }

  /**
   * Descargar documento de historial laboral.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 25/11/2021.
   * @param claveServidor Clave de servidor publico del que se generara el historial laboral.
   * @returns Observable con el archivo de historial laboral
  */
  obtenerArchivoHistorialLaboral(claveServidorPublico: String) {
    let url = `/historialLaboral/descargarSolicitud?claveServidor=${claveServidorPublico}`
    return this.http.post(
      `${environment.BASEENPOINT_BUS_SEI}${url}`,
      null,
      { responseType: 'blob' }
    );
  }
  //#endregion

}
