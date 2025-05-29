import { Injectable } from '@angular/core';
import { DetalleProcesoedd } from '../models/detalle-proceso-edd';
import { DetalleProcesoEddua } from '../models/detalle-proceso-edd-ua';
import { DETALLEPROCESOEDDUA } from '../mocks/mock-detalle-edd-ua';
import { environment } from 'src/environments/environment';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { catchError } from 'rxjs/operators';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
import { DetalleEjecucionEddambos } from '../models/detalle-ejecucion-edd-ambos';
import { forkJoin } from 'rxjs';
import { PreguntasBus } from '../models/preguntas-bus';
import { servidoresPublicosEddUa } from '../models/servidores-publicos-eddua';
import { NGXLogger } from 'ngx-logger';
import { RespuestaServiceM4 } from '../repuesta/respuesta-service-m4';



@Injectable({
  providedIn: 'root'
})
export class DetalleEdduaService {
  urlWSBUS: string = `frwsr_AUT_JGEN${environment.HEDER_WS}.php`;
  constructor(private http: HttpClient,
    private logger: NGXLogger) { }

  DetalleProcesoEddua(idServidorPublico: string, idUnidadAdministrativa: string, idProcesoVigente: string) {
    let dta = {
      "funcion": "consultarDetalleEDDUA",
      "IDPROCESOVIGENTE": idProcesoVigente,
      "IDSERVIDORPUBLICO": idServidorPublico,
      "IDUNIDADADMINISTRATIVA": idUnidadAdministrativa
    }

    this.logger.debug(`${environment.BASEENPOINT_SERVICE_SEI_BUS}/IdServidorPublico/${idServidorPublico}/IdUnidadAdministrativa/${idUnidadAdministrativa}/IdProcesoVigente/${idProcesoVigente}`);
    return this.http.get<RespuestaServiceM4<DetalleProcesoEddua>>(`${environment.BASEENPOINT_SERVICE_SEI_BUS}/IdServidorPublico/${idServidorPublico}/IdUnidadAdministrativa/${idUnidadAdministrativa}/IdProcesoVigente/${idProcesoVigente}`)
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

  finalizarDemeritos(demeritos: DetalleProcesoEddua, spFaltantes: servidoresPublicosEddUa[], idEvaluador: string) {
    //let demeritospreg = this.obtenerDemeritos(demeritos.IDEVALUACIONVIGENTE, 'D');
    let demeritosFinal: PreguntasBus[] = [];
    //  forkJoin([demeritospreg]).subscribe(results => {
    //let existenDemeritos = results[0].codigo === 200 ? true : false;
    // if (existenDemeritos) {
    //let vari = <RespuestaApi<DetalleEjecucionEddambos>>results[0];
    //let varAr = <DetalleEjecucionEddambos[]>vari.response;

    for (let b of spFaltantes) {
      //for (let a of varAr) {
      let dem: PreguntasBus = new PreguntasBus();
      dem.IDEVALUACION = demeritos.IDEVALUACIONVIGENTE;
      dem.IDPREGUNTA = '1';
      dem.IDRESPUESTA = '0';
      dem.IDSERVIDORPUBLICOEVALUADO = b.CLAVESERVIDORPUBLICO;
      dem.IDSERVIDORPUBLICOEVALUADOR = idEvaluador;
      dem.IDUNIDADADMINISTRAR = demeritos.IDUNIDADADMINISTRATIVA;
      dem.PUNTAJEOBTENIDO = '0';
      dem.TIPOSECCION = 'DEME_ALL';
      demeritosFinal.push(dem);
      // }
    }


    if (demeritosFinal.length > 0) {
      this.guardarDemeritos(demeritosFinal).subscribe(e => {
        this.logger.debug('guardarDemeritos -> ', e);
      })
    }


    //  }
    // });




  }

  guardarDemeritos(demeritosFinal: PreguntasBus[]) {
    let urlDat2: string = `frwsr_AUT_INS${environment.HEDER_WS}.php`;
    this.logger.debug('EnviarPreguntas -> ', demeritosFinal);
    let body = JSON.stringify({ funcion: 'envioRespuestasEvaluacionDesempenio', COMPETENCIASAPTITUDINALES: [], COMPETENCIASSOCIOPERSONALES: [], DEMERITOS: demeritosFinal, EVCONCLUIDA: "0" });
    this.logger.debug('body final -> ', body);
    const headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });

    return this.http.post<RespuestaApi<any>>(`${environment.BASEENPOINT_BUS_SEI}/enviarrespuestasedd/envioRespuestasEvaluacionDesempenio`, body, { headers: headers })
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
