import { Injectable } from '@angular/core';
import { ProcesoKPIVigente } from '../models/proceso-kpi-vigente';
import { PROCESOKPIVIGENTEMOCKS } from '../mocks/mock-proceso-kpi-vigente';
import { HistorialKPI } from '../models/historial-KPI';
import { HttpClient } from '@angular/common/http';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { InstruccionesEvaluacionKPI } from '../models/instrucciones-evaluacion-kpi';
import { HistorialKpiVIgente } from '../models/historial-kpi-vigente';
import { throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { NGXLogger } from 'ngx-logger';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';

@Injectable({
  providedIn: 'root'
})
export class ProcesoKPIVigenteService {
  url = `frwsr_LPSAUT_CONS_CAT${environment.HEDER_WS}.php`;
  constructor(private http: HttpClient,
    private logger: NGXLogger) { }

  getProcesoKPIVigente(idProceso: string) {
    let body = {
      funcion: 'consultarProcesoKPIVigente',
      IDSERVIDORPUBLICO: idProceso
    }

    return this.http.get<RespuestaServiceM4<ProcesoKPIVigente>>(`${environment.BASEENPOINT_BUS_SEI}/evaluacionkpi/procesoVigente/idServidorPublico/${idProceso}`)
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



  getHistoricoKPI(idEmpleado: string) {
    let body = {
      funcion: 'consultarHistorialEvaluacionesKPI',
      IDSERVIDORPUBLICO: idEmpleado
    }

    return this.http.get<RespuestaServiceM4<HistorialKpiVIgente>>(`${environment.BASEENPOINT_BUS_SEI}/evaluacionkpi/historicokpi/idServidorPublico/${idEmpleado}`)
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

  getinstruccionesEvaluacionKPI(idProceso: string) {
    let body = {
      funcion: "instruccionesEvaluacionKPI",
      IDPROCESOKIP: idProceso
    }

    return this.http.get<RespuestaServiceM4<InstruccionesEvaluacionKPI>>(`${environment.BASEENPOINT_BUS_SEI}/evaluacionkpi/instruccioneskpi/idProceso/${idProceso}`)
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
