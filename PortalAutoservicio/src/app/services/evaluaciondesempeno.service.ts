import { Injectable } from '@angular/core';
import { Evaluaciondesempeno } from '../models/evaluaciondesempeno';
import { MOVIMIENTOSEVALUCIONDESEM } from '../mocks/mock-evaluaciondesempeno';
import { PorcesoVigente } from '../models/proceso-vigente';
import { PROCESOVIGENTEMOCKS } from '../mocks/mock-proceso-vigente';
import { HttpClient } from '@angular/common/http';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';

@Injectable({
  providedIn: 'root'
})
export class EvaluaciondesemService {

  constructor(private http: HttpClient) { }

  getMovimientosEvaliciondesem(): Evaluaciondesempeno[] {
    return MOVIMIENTOSEVALUCIONDESEM;
  }

  getProcesoVigente(idServidorPublico:string) {
    return this.http.get<RespuestaServiceM4<PorcesoVigente>>(`${environment.BASEENPOINT_BUS_SEI}/procesovigente/idServidorPublico/${idServidorPublico}`)
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

  getHistrialProcesoVig(idServidorPublico:string){
    return this.http.get<RespuestaServiceM4<Evaluaciondesempeno>>(`${environment.BASEENPOINT_BUS_SEI}/historialedd/idServidorPublico/${idServidorPublico}`)
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
