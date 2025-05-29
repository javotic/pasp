import { Injectable } from '@angular/core';
import { MiHistorico } from 'src/app/models/mi-historico';
import { HttpClient } from '@angular/common/http';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MihistoricoService {

  constructor(private http: HttpClient) { }

  getMiHistorico(idServidorPublico: string) {
      let url='/miHistorico/escalafonHistoricoProcesos';
      return this.http.get<RespuestaApi<MiHistorico>>(`${environment.BASEENPOINT_BUS_SEI}${url}/${idServidorPublico}`);
    /*
    let body = {
      funcion: 'consultaProcesoHistoricoEscalafonario',
      IdServidorPublico: idServidorPublico
    }
    let url = `frwsr_LPSAUT_CONS_CAT5${environment.HEDER_WS}.php`;
    return this.http.post<RespuestaApi<MiHistorico>>(`${environment.BASEENPOINT_SERVICE_BUS}/${url}`, body)
      .pipe(
        catchError(e => {
          if (e.status == 400) {
            return throwError(e);
          }
          console.error(e)
          return throwError(e);
        })
      );
*/
  }


  getDescargaConstancias(idServidorPublico: string, idPlaza: string, idProcesoVigente: string) {
    let url = `${environment.BASEENPOINT_BUS_SEI_REPORTES}/reportesConstancia/generarReporte`;

    return this.http.get(`${url}/${idServidorPublico}/${idPlaza}?idProcesoVigente=${idProcesoVigente}`, {
      responseType: 'blob'
    });

  }
}
