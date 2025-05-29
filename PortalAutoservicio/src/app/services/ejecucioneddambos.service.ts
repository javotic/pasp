import { Injectable } from '@angular/core';
import { DetalleEjecucionEddambos } from '../models/detalle-ejecucion-edd-ambos';
import { SeccionesEDD } from '../models/seccionesEDD';
import { InstruccionesEDD } from '../models/instrucciones-edd';
import { RespuestasEDD } from '../models/respuestad-edd';
import { RespuestaApi } from '../repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
import { PorcesoVigente } from '../models/proceso-vigente';
import { NGXLogger } from 'ngx-logger';
import { PaquetePreguntasBus } from '../models/paquete-preguntas-bus';
import { SeccionesEddPR } from '../models/secciones-edd-p-r';
import { PreguntasBus } from '../models/preguntas-bus';
import { RespuestasPortal } from '../models/respuestas-portal';
import { Router } from '@angular/router';
import { EjecucionEdd } from '../models/ejecucion-edd';
import { MessageService } from 'primeng/api';
import { SeccionesEddPreg } from '../models/resultados-edd-preg';

@Injectable({
  providedIn: 'root'
})
export class EjecucionEddambosService {
  urlDat: string = `frwsr_LPSAUT_CONS_DAT${environment.HEDER_WS}.php`;
  urlDat2: string = `frwsr_AUT_INS${environment.HEDER_WS}.php`;
  constructor(private http: HttpClient,
    private logger: NGXLogger,
    private router: Router,
    private messageService: MessageService) { }

  getSeccionesEdd(idProceso: string) {
    let data = {
      "funcion": "seccionesEjecucionEvaluacionDesempenio",
      "IDPROCESO": idProceso
    };
    let body = JSON.stringify({ request: data });
    return this.http.post<RespuestaApi<SeccionesEDD>>(`${environment.BASEENPOINT_SERVICE_BUS}/frwsr_LPSAUT_CONS_DAT2${environment.HEDER_WS}.php`, data)
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

  getPreguntasApti(idProceso: string, idSeccion: string) {
    const url = '/ejecucionEDD/preguntasEjecucionED/';
    return this.http.get<RespuestaApi<DetalleEjecucionEddambos>>(`${environment.BASEENPOINT_BUS_SEI}${url}${idProceso}/${idSeccion}`)
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

  /* getPreguntasSociop(): DetalleEjecucionEddambos[] {
    
  } */

  /* getPreguntasDemeritos(): DetalleEjecucionEddambos[] {
    return DETALLEEJECUCIONEDDDEMERITOS;
  } */

  getinstrucciones(idProceso: string) {
    const url = '/ejecucionEDD/consultarInstruccionesllenadoEDD/';
    return this.http.get<RespuestaApi<InstruccionesEDD>>(`${environment.BASEENPOINT_BUS_SEI}${url}${idProceso}`)
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

  getRespuestasEDD(idProcesoActivo: string) {
    let data = {
      "funcion": "consultarRespuestasCompetencias",
      "IDPROCESOACTIVO": idProcesoActivo
    };
    let body = JSON.stringify({ request: data });
      return this.http.get<RespuestaApi<RespuestasEDD>>(`${environment.BASEENPOINT_BUS_SEI}/ejecucionEDD/consultarRespuestasCompetencias/${idProcesoActivo}`)
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

  public agregarAuthorizationHeader() {
    const httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': 'Basic ' + btoa(environment.TOKEN_AUTH_USERNAME + ':' + environment.TOKEN_AUTH_PASSWORD)
    });
    return httpHeaders;
  }

  guardarAvanece(data: any[]) {
    //`${environment.BASEENPOINT_AUTOSERVICIO}/obtenerPreguntas`
    const headers = new HttpHeaders({ 'Content-Type': 'application/json; charset=utf-8' });
    let v: string = '';
    data.forEach(element => {
      v += JSON.stringify(element) + ','
    });
    let b: string = '[' + v.substring(0, v.length - 1) + ']';
    this.logger.debug('V=>', b);
    let jsonObj: any = JSON.parse(b);
    let body2 = JSON.stringify({ seccionesEddPR: jsonObj });
    this.logger.debug('BODY"=>', body2);
    //let body = JSON.stringify({ seccionesEddPR: [jsonObj] });
    let body = JSON.stringify({ seccionesEddPR: [data[0], data[1], data[2]] })

    this.logger.debug('Guardar Avance =>', body.replace(',null', ''));
    return this.http.post<SeccionesEddPR[]>(`${environment.BASEENPOINT_AUTOSERVICIO}/guardarRespuestas`, body.replace(',null', '').replace(',null', ''), { headers: headers })
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

  guardarRespuestasFinal(secciones: SeccionesEddPR[], spevaluado: string, spevaluador: string, ua: string, tipoEval: string, tipoResp:string, estatusEval:string) {
    sessionStorage.removeItem('answarekpi');
    this.logger.debug('Iniciales =>', secciones)
    let preguntasb: PaquetePreguntasBus = new PaquetePreguntasBus();
    preguntasb.COMPETENCIASAPTITUDINALES = [];
    preguntasb.COMPETENCIASSOCIOPERSONALES = [];
    preguntasb.DEMERITOS = [];
    preguntasb.funcion = 'envioRespuestasEvaluacionDesempenio';
    for (let a of secciones) {
      // if (a.idseccion === 'CA') {
      for (let b of a.preguntas) {
        for (let c of b.respuestas) {
          if (c.selected === 'true') {
            let preg: PreguntasBus = new PreguntasBus();
            preg.IDEVALUACION = b.idprocesovigente;
            preg.IDPREGUNTA = b.idpregunta;
            preg.IDRESPUESTA = c.idrespuesta;
            preg.IDSERVIDORPUBLICOEVALUADO = spevaluado;
            preg.IDSERVIDORPUBLICOEVALUADOR = spevaluador;
            preg.IDUNIDADADMINISTRAR = ua;
            preg.PUNTAJEOBTENIDO = c.puntaje;
            preg.TIPOSECCION = b.idseccion;
            preguntasb.COMPETENCIASAPTITUDINALES.push(preg);
          }
        }
      }
      // }
      /*       if (a.idseccion === 'CS') {
              for (let b of a.preguntas) {
                for (let c of b.respuestas) {
                  if (c.selected === 'true') {
                    let preg: PreguntasBus = new PreguntasBus();
                    preg.IDEVALUACION = b.idprocesovigente;
                    preg.IDPREGUNTA = b.idpregunta;
                    preg.IDRESPUESTA = c.idrespuesta;
                    preg.IDSERVIDORPUBLICOEVALUADO = spevaluado;
                    preg.IDSERVIDORPUBLICOEVALUADOR = spevaluador;
                    preg.IDUNIDADADMINISTRAR = ua;
                    preg.PUNTAJEOBTENIDO = c.puntaje;
                    preg.TIPOSECCION = b.idseccion;
                    preguntasb.COMPETENCIASSOCIOPERSONALES.push(preg);
                  }
                }
              }
            } */
      /*       if (a.idseccion === 'D') {
              for (let b of a.preguntas) {
                for (let c of b.respuestas) {
                  if (c.selected === 'true') {
                    let preg: PreguntasBus = new PreguntasBus();
                    preg.IDEVALUACION = b.idprocesovigente;
                    preg.IDPREGUNTA = b.idpregunta;
                    preg.IDRESPUESTA = c.idrespuesta;
                    preg.IDSERVIDORPUBLICOEVALUADO = spevaluado;
                    preg.IDSERVIDORPUBLICOEVALUADOR = spevaluador;
                    preg.IDUNIDADADMINISTRAR = ua;
                    preg.PUNTAJEOBTENIDO = c.puntaje;
                    preg.TIPOSECCION = b.idseccion;
                    preguntasb.DEMERITOS.push(preg);
                  }
                }
              }
            } */
    }
    this.logger.debug('EnviarPreguntas=>', preguntasb);
    let body = JSON.stringify({ funcion: 'envioRespuestasEvaluacionDesempenio', COMPETENCIASAPTITUDINALES: preguntasb.COMPETENCIASAPTITUDINALES, COMPETENCIASSOCIOPERSONALES: preguntasb.COMPETENCIASSOCIOPERSONALES, DEMERITOS: preguntasb.DEMERITOS, EVCONCLUIDA:"0" });
    this.logger.debug('body final', body);
    sessionStorage.setItem('answarekpi', body);
    //this.router.navigate(['/ejecucionEdddemeritos'], { queryParams: { tipoEval: tipoEval, tipoResp: '1' } });
    this.guardarRespuestasEdd().subscribe(data => {
      this.router.navigate(['/ejecucionEdddemeritos'], { queryParams: { tipoEval: tipoEval, tipoResp: tipoResp, estatusEval: estatusEval} });
    })
  }

  guardarRespuestasEdd() {
    let body = JSON.parse(sessionStorage.getItem('answarekpi'));
    this.logger.debug('body final parse', body);

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

  obtenerAvance(claveproceso: string, spevaluador: string, spevaluado: string, claveunidadadmin: string) {
    //`${environment.BASEENPOINT_AUTOSERVICIO}/obtenerPreguntas`
    return this.http.get<RespuestasPortal[]>(`${environment.BASEENPOINT_AUTOSERVICIO}/obtenerPreguntas/${claveproceso}/${spevaluador}/${spevaluado}/${claveunidadadmin}`)
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

  obtenerEvaluacion(claveproceso: string, spevaluador: string, claveunidadadmin: string, tipoRep: string, idEvaluado: string ) {

    /*
    if (tipoRep === '2') {
      idEvaluado = '0';
    }
    */
    this.logger.debug('evaluado procesoVig=>', idEvaluado);

    return this.http.get<RespuestaApi<EjecucionEdd>>(`${environment.BASEENPOINT_BUS_SEI}/ejecucionEDD/procesoVig/${claveproceso}/evaluador/${spevaluador}/auxiliar/${claveunidadadmin}/tipoResp/${tipoRep}/idEvaluado/${idEvaluado}`)
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

  guardarRespuestasFinalDemeritos(secciones: SeccionesEddPR[], spevaluado: string, spevaluador: string, ua: string, tipoEval: string) {
    sessionStorage.removeItem('answarekpi');
    this.logger.debug('Iniciales =>', secciones)
    let preguntasb: PaquetePreguntasBus = new PaquetePreguntasBus();
    preguntasb.COMPETENCIASAPTITUDINALES = [];
    preguntasb.COMPETENCIASSOCIOPERSONALES = [];
    preguntasb.DEMERITOS = [];
    preguntasb.funcion = 'envioRespuestasEvaluacionDesempenio';
    for (let a of secciones) {
      // Solo enviamos demeritos
       if (a.idseccion === environment.ID_SEC_DEMERITOS) {
      for (let b of a.preguntas) {
        for (let c of b.respuestas) {
          if (c.selected === 'true') {
            let preg: PreguntasBus = new PreguntasBus();
            preg.IDEVALUACION = b.idprocesovigente;
            preg.IDPREGUNTA = b.idpregunta;
            preg.IDRESPUESTA = c.idrespuesta;
            preg.IDSERVIDORPUBLICOEVALUADO = spevaluado;
            preg.IDSERVIDORPUBLICOEVALUADOR = spevaluador;
            preg.IDUNIDADADMINISTRAR = ua;
            preg.PUNTAJEOBTENIDO = c.puntaje;
            preg.TIPOSECCION = b.idseccion;
            preguntasb.COMPETENCIASAPTITUDINALES.push(preg);
          }
        }
      }
       }
    }
    this.logger.debug('EnviarPreguntas=>', preguntasb);
    let body = JSON.stringify({ funcion: 'envioRespuestasEvaluacionDesempenio', COMPETENCIASAPTITUDINALES: preguntasb.COMPETENCIASAPTITUDINALES, COMPETENCIASSOCIOPERSONALES: preguntasb.COMPETENCIASSOCIOPERSONALES, DEMERITOS: preguntasb.DEMERITOS, EVCONCLUIDA:"0" });
    this.logger.debug('body final', body);
    sessionStorage.setItem('answarekpi', body);
    this.guardarRespuestasEdd().subscribe(data => {      
      this.logger.debug(data);
      this.router.navigate(['/evaluacionDesempeno'], { queryParams: { tipoEval: tipoEval, tipoResp: '1' } });
    })
  }


  getResultadosEdd(idServidorPublico: string, idSeccion: string, idProceso: string) {
    const url = `/resultadosedd/idServidorPublico/${idServidorPublico}/idSeccion/${idSeccion}/idProceso/${idProceso}`;
    return this.http.get<RespuestaApi<SeccionesEddPreg>>(`${environment.BASEENPOINT_BUS_SEI}${url}`)
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
