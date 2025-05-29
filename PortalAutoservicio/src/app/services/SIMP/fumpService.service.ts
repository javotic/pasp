
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { fumpModel } from 'src/app/models/SIMP/fumpModel';
import { FUMPS } from 'src/app/mocks/mock-fump';
import { environment } from 'src/environments/environment';
import { RespuestaServiceM4 } from '../../repuesta/respuesta-service-m4';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FumpService {

  //#region Constructor
  constructor(
    private http: HttpClient
  ) { }
  //#endregion

  //#region Funciones publicas
  /**
   * Obtener listado mock de FUMPS
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 20/10/2021.
   */
  obtenerFumpsMock(): fumpModel[] {
    return FUMPS;
  }

  /**
   * Obtener listado de FUMPS de un servidor publico determinado 
   * en un periodo de fechas.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 26/10/2021.
   * 
   * @param claveServidor Clave de servidor publico a buscar.
   * @param fechaInicio Fecha de inicio para busqueda de informacion
   * @param fechaTermino Fecha de termino para busqueda de informacion
   * @returns Observable con arreglo de Fumps.
   */
  consultarFumps(claveServidor: string, fechaInicio: Date, fechaTermino: Date): Observable<fumpModel[]> {
    let url = `/fump/obtenerFumpPorFechaUsuario?claveServidorPublico=${claveServidor}&fechaInicio=${fechaInicio.toISOString().slice(0, 10)}&fechaTermino=${fechaTermino.toISOString().slice(0, 10)}`;
    return this.http.get<fumpModel[]>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }
  //#endregion
}
