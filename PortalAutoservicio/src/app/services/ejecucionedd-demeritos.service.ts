import { Injectable } from '@angular/core';
import { DetalleEjecucionEddambos } from '../models/detalle-ejecucion-edd-ambos';
import { DetalleEjecucionEdddemeritos } from '../models/detalle-ejecucion-edd-demeritos';
import { DETALLEEJECUCIONEDDDEMERITOS } from '../mocks/mock-detalle-ejecucion-edd-demeritos';
import { DetalleEjecucionEddDemeritosPuntos } from '../models/detalle-ejecucion-edd-demeritos-puntos';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { NGXLogger } from 'ngx-logger';

@Injectable({
  providedIn: 'root'
})
export class EjecucionEdddemeritosService {

  constructor(private http: HttpClient,
    private logger: NGXLogger) { }

  getPreguntasEdddemeritos(): DetalleEjecucionEdddemeritos[] {
    return DETALLEEJECUCIONEDDDEMERITOS;
  }

  generarPDF(proceso: string, servidorPublico: string) {
    //return this.http.get(`${environment.BASEENPOINT_SERVICE_REPORTES}/generarReporte/${proceso}/${servidorPublico}`, { responseType: 'blob' })
    this.logger.debug('REPORTE=>>', `${environment.BASEENPOINT_BUS_SEI_REPORTES}reportes/generarReporte/${proceso}/${servidorPublico}`);
    return this.http.get(`${environment.BASEENPOINT_BUS_SEI_REPORTES}reportes/generarReporte/${proceso}/${servidorPublico}`, { responseType: 'blob' })
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

  generarReporteEdddemeritos(proceso: string, servidorPublico: string) {
    //return this.http.get(`${environment.BASEENPOINT_SERVICE_REPORTES}/generarReporte/${proceso}/${servidorPublico}`, { responseType: 'blob' })
    this.logger.debug('REPORTE=>>', `${environment.BASEENPOINT_BUS_SEI_REPORTES}reportes/generarReporteEdddemeritos/${proceso}/${servidorPublico}`);
    return this.http.get(`${environment.BASEENPOINT_BUS_SEI_REPORTES}reportes/generarReporteEdddemeritos/${proceso}/${servidorPublico}`, { responseType: 'blob' })
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

  generarPDFHT(proceso: string, servidorPublico: string) {
    //return this.http.get(`${environment.BASEENPOINT_SERVICE_REPORTES}/generarReporte/${proceso}/${servidorPublico}`, { responseType: 'blob' })
    this.logger.debug('REPORTE=>>', `${environment.BASEENPOINT_BUS_SEI_REPORTES}reportes/generarReporteHT/${proceso}/${servidorPublico}`);
    return this.http.get(`${environment.BASEENPOINT_BUS_SEI_REPORTES}reportes/generarReporteHT/${proceso}/${servidorPublico}`, { responseType: 'blob' })
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

  guardarRespuestasEdd() {
    let bodydata = JSON.parse(sessionStorage.getItem('answarekpi').replace('"EVCONCLUIDA":"0"', '"EVCONCLUIDA":"1"'));
    this.logger.debug('body final parse Finaliza', bodydata);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });

    return this.http.post<RespuestaApi<any>>(`${environment.BASEENPOINT_BUS_SEI}/enviarrespuestasedd/envioRespuestasEvaluacionDesempenio`, bodydata, { headers: headers })
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


}
