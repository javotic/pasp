import { Injectable } from '@angular/core';
import { IncidenciasTiempo } from '../models/incidencias-tiempo';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { catchError, map } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { UtilsService } from './utils.service';
import { ConsultaIncidenciasEtiquetas } from '../etiquetas/consulta-incidencias-etiquetas';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';

@Injectable({
  providedIn: 'root'
})
export class IncidenciastiempoService {

  url: string = '/frwsr_LPSAUT_CONS.php';
  maxDate: Date;
  constructor(private http: HttpClient, private utilsService: UtilsService) { }

  /*
  obteneterIncidenciasTiempo(idEmpleado: string, fechaInicio: string, fechaFin: string) {
    const formData = new FormData();
    formData.append('IDSERVIDORPUBLICO', idEmpleado);
    formData.append('FECHAINICIO', fechaInicio);
    formData.append('FECHAFIN', fechaFin);

    const url = environment.BASEENPOINT_BUS_SEI + '/consultas/consultarIncidenciasTiempo';

    return this.http.post<RespuestaApi<IncidenciasTiempo>>(`${url}`, formData)
      .pipe(
        catchError(e => {
          if (e.status == 400) {
            return throwError(e);
          }
          console.error(e)
          return throwError(e);
        })
      );
  }
  */

  obteneterIncidenciasTiempo(claveServidor: string, tpDato: string, fecha1: string, fecha2: string ){
    const url = `/pagos/consultaDatosPuntyAsist?claveServidor=${claveServidor}&tpDato=${tpDato}&fecha1=${fecha1}&fecha2=${fecha2}`
    return this.http.get<RespuestaServiceM4<IncidenciasTiempo>>(`${environment.BASEENPOINT_BUS_SEI}${url}`);
  }

  getEtiquetasIncidencias(): Observable<any> {
    return this.utilsService.ObtenerEtiquetasPagina('/incidenciasTiempo', 'español (México)').pipe(

      map((response: any) => {
        let etiquetas = new ConsultaIncidenciasEtiquetas();
        Object.keys(response).map((key) => {

          switch (key) {
            case 'ttl.tituloincidenciasTiempo':
              etiquetas.titulo = response[key];
              break;
            case 'btn.buscar':
              etiquetas.botonBuscar = response[key];
              break;
            case 'lbl.tooltipfechas':
              etiquetas.tooltipfechas = response[key];
              break;
            case 'lbl.fechainicio':
              etiquetas.fechainicioL = response[key];
              break;
            case 'lbl.fechainicio':
              etiquetas.fechainicioL = response[key];
              break;
            case 'lbl.fechainicio':
              etiquetas.fechainicio = response[key];

              break;
            case 'lbl.fechafin':
              etiquetas.fechafin = response[key];

              break;
            case 'lbl.clave':
              etiquetas.clave = response[key];
              break;
            case 'lbl.nombre':
              etiquetas.nombre = response[key];
              break;

            case 'lbl.numero':
              etiquetas.numero = response[key];
              break;

            case 'lbl.fechainicio':
              etiquetas.fechainicio = response[key];
              break;
            case 'lbl.fechaFin':
              etiquetas.fechafin = response[key];
              break;
            case 'lbl.nounidades':
              etiquetas.noUnidades = response[key];
              break;

            case 'lbl.plaza':
              etiquetas.plaza = response[key];
              break;

            case 'lbl.noplaza':
              etiquetas.noPlaza = response[key];
              break;

            case 'lbl.noseencontraron':
              etiquetas.noseencontraron = response[key];
              break;

            case 'lbl.finicioffin':
              etiquetas.finicioffin = response[key];
              break;

            case 'lbl.finicioreq':
              etiquetas.finicioreq = response[key];
              break;
            case 'lbl.fechafinreq':
              etiquetas.fechafinreq = response[key];
              break;


            default:

              break;
          }
        });

        return etiquetas;
      })
    );
  }

  public agregarAuthorizationHeader() {
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD)
    });
    return httpHeaders;
  }

}
