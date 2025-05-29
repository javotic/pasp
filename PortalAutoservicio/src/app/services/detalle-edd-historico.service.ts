import { Injectable } from '@angular/core';
import { DetalleProcesoedd } from '../models/detalle-proceso-edd';
import { DETALLEPROCESOEDD, DETALLECOMISIONESEDD } from '../mocks/mock-detalle-edd';
import { DetalleComisionesedd } from '../models/detalle-comisiones-edd';
import { HttpClient } from '@angular/common/http';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';
import { NGXLogger } from 'ngx-logger';
import { DetalleProcesoEddua } from 'src/app/models/detalle-proceso-edd-ua';

@Injectable({
  providedIn: 'root'
})
export class DetalleeddHistoricoService {

  constructor(private http: HttpClient,
    private logger: NGXLogger) { }

  DetalleProcesoedd(idServidorPublico: string,idProceso:string) {
    const url = '/evaluacionhistorico/getDetalleUnidades';
    this.logger.debug(`${environment.BASEENPOINT_BUS_SEI}${url}/idServidorPublico/${idServidorPublico}/idProceso/${idProceso}`);
    return this.http.get<RespuestaServiceM4<DetalleProcesoedd>>(`${environment.BASEENPOINT_BUS_SEI}${url}/idServidorPublico/${idServidorPublico}/idProceso/${idProceso}`)
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


  DetalleProcesoEdduaHistorico(idServidorPublico: string, idUnidadAdministrativa: string, idProcesoVigente: string) {
    const url = '/evaluacionhistorico/getServidoresedduaHt';
    this.logger.debug(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}/${idUnidadAdministrativa}/${idProcesoVigente}`);
    return this.http.get<RespuestaServiceM4<DetalleProcesoEddua>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}/${idUnidadAdministrativa}/${idProcesoVigente}`)
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


  /*
  DetalleComisionedd(idServidorPublico: string, idProceso:string) {
    this.logger.debug(`${environment.BASEENPOINT_BUS_SEI}/comisiones/idServidorPublico/${idServidorPublico}/idProceso/${idProceso}`);
    return this.http.get<RespuestaServiceM4<DetalleComisionesedd>>(`${environment.BASEENPOINT_BUS_SEI}/comisiones/idServidorPublico/${idServidorPublico}/idProceso/${idProceso}`)
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

}
