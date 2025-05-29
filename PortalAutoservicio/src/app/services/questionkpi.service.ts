import { Injectable } from '@angular/core';
import { QuestionsKPI } from '../models/questions';
import { QUESTIONSKPI } from '../mocks/mock-quiz-kpi';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { EnvioRespuestasKpi } from '../models/envio-respuestas-kpi';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { EnvioKpi } from '../models/envio-kpi';
import { NGXLogger } from 'ngx-logger';
import { ProcesoKpi } from '../models/proceso-kpi';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';

@Injectable({
  providedIn: 'root'
})
export class QuestionkpiService {

  constructor(private http: HttpClient,
    private logger: NGXLogger) { }

  getAllQuestion555(): QuestionsKPI[] {
    return QUESTIONSKPI;
  }

  getAllQuestion2(idServidorPublico: string, idProcesoVigente: string) {
    let url = `frwsr_AUT_JGEN${environment.HEDER_WS}.php`;
    //let url = '/historialEvaluacionKPI/preguntasRespuestasKpi';
    //return this.http.get<RespuestaApi<QuestionsKPI>>
    //  (`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}/${idProcesoVigente}`);
    let body = {
      funcion: "ejecucionEvaluacionKPI",
      IDPROCESO: idProcesoVigente,
      CLAVESERVIDORPUBLICO: idServidorPublico
    }

    return this.http.get<RespuestaServiceM4<ProcesoKpi>>(`${environment.BASEENPOINT_BUS_SEI}/evaluacionkpi/ejecucionkpi/idServidorPublico/${idServidorPublico}/idProceso/${idProcesoVigente}`)
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

  /**
   * Envia as respuestas de las preguntas de la encuesta de KPI
   * @param answers 
   */
  envioRespuestasKpi(answers: EnvioRespuestasKpi[]) {
    let body = JSON.stringify(answers);
    this.logger.debug('preguntas final=>', body);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });
    const url = '/evaluacionkpi/envioRespuestasEncuesta';
    return this.http.post<RespuestaApi<any>>(`${environment.BASEENPOINT_BUS_SEI}${url}`, body, { headers: headers })
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






  generarReporteKPI(fecha:string, servidor:string, proceso:string){
    this.logger.debug(`${environment.BASEENPOINT_BUS_SEI_REPORTES}reportes/generarReportekpi/${fecha}/${servidor}/${proceso}`);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json;' });
    return this.http.get(`${environment.BASEENPOINT_BUS_SEI_REPORTES}reportes/generarReportekpi/${fecha}/${servidor}/${proceso}`, { responseType: 'blob' })
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
