import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { UtilsService } from 'src/app/services/utils.service';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { NgxSpinnerService } from 'ngx-spinner';
import { Router, ActivatedRoute } from '@angular/router';
import { EjecucionEddambosService } from 'src/app/services/ejecucioneddambos.service';
import { DetalleEjecucionEddambos } from 'src/app/models/detalle-ejecucion-edd-ambos';
import { SeccionesEDD } from 'src/app/models/seccionesEDD';
import { InstruccionesEDD } from 'src/app/models/instrucciones-edd';
import { RespuestasEDD } from 'src/app/models/respuestad-edd';
import { TabView } from 'primeng/tabview/tabview';
import { environment } from 'src/environments/environment';
import { ServiceBus } from 'src/app/services/service-bus';
import { BusgenericserviceService } from 'src/app/services/busgenericservice.service';
import { forkJoin } from 'rxjs';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { SeccionesEddPR } from 'src/app/models/secciones-edd-p-r';
import { PreguntasEDD } from 'src/app/models/preguntasEDD';
import { RespuestasEDDB } from 'src/app/models/respuestas-edd-b';
import { RespuestasPortal } from 'src/app/models/respuestas-portal';
import { ProcesoEddSession } from 'src/app/models/proceso-edd-session';
import { EjecucionEdd } from 'src/app/models/ejecucion-edd';


@Component({
  selector: 'app-ejecucion-edd-ambos',
  templateUrl: './ejecucion-edd-ambos.component.html',
  styleUrls: ['./ejecucion-edd-ambos.component.css']
})
export class EjecucionEddAmbosComponent implements OnInit {

  // Variables Globales de la clase
  items3: MenuItem[];
  activeItem: MenuItem;
  preguntas: DetalleEjecucionEddambos[] = [];
  activeIndex: number = 1;
  selectedValue: string;
  val1: string;
  seccionesedd: SeccionesEDD[] = [];
  instrucciones: InstruccionesEDD[] = [];
  instruccionesObj: InstruccionesEDD = new InstruccionesEDD();
  index: number = 0;
  @ViewChild('tview') taview: TabView;
  urlDat: string = `frwsr_LPSAUT_CONS_DAT2${environment.HEDER_WS}.php`;
  catalogoRespuesta: RespuestasEDD[] = [];
  //procesoVigenteM: PorcesoVigente = new PorcesoVigente();
  currentUser: DatosPersonales = new DatosPersonales();
  idEvaluado: string = '';
  idProcesoVig: string = '';
  //usuarioEvaluado: ConsultaDatosPersonales = new ConsultaDatosPersonales();
  tipoEval: string = '';
  seccionFinal: SeccionesEddPR[] = [];
  progressBar: string = '0';
  precesoSesion: ProcesoEddSession = new ProcesoEddSession();
  unidadAdmin: string = '';
  tiposResp: string = '';
  existeMensaje: string ='';

  existeDemeritos: string ='';
  existeDesempenio: string ='';

  //Elementos para el boton siguiente
  showSiguiente: boolean = true;

  //Constructor de la clase
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: EjecucionEddambosService,
    private messageService: MessageService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
  ) {
  }

  //Metodo principal de la clase
  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
        this.idEvaluado = params.idEvaluado;
        this.idProcesoVig = params.idProcesoVig;
        this.tipoEval = params.tipoEval;
        this.unidadAdmin = params.unidadAdmin;
        this.tiposResp = params.tipoResp;
        this.logger.debug('url=>', params.idEvaluado);
      });
    this.precesoSesion = JSON.parse(sessionStorage.getItem('headerProceso'));
    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        this.currentUser = usr;
      });
    }

    let num: number = 0;
    //this.obtenerDatos();
    this.obtenerDatosFinal();
  }

  obteniendoInstrucciones(idSeccion: string) {
    let instruc: InstruccionesEDD = new InstruccionesEDD();
    this.instrucciones.forEach(element => {
      if (element.IDSECCION == idSeccion) {
        this.instruccionesObj = element;
      }
    });

  }

  obteniendoPreguntasBySection(seccion: string): DetalleEjecucionEddambos[] {
    this.logger.debug('obteniendoPreguntasBySection', seccion);
    let filPreg: DetalleEjecucionEddambos[] = [];
    this.preguntas.forEach(element => {
      this.logger.debug('compare=>', element.IDSECCION == seccion);
      if (element.IDSECCION == seccion) {
        filPreg.push(element);
      }

    });
    this.logger.debug('filtro=>', filPreg);
    this.obteniendoInstrucciones(seccion);
    return filPreg;
  }

  redirectEjecucionEddDesem() {
    this.router.navigate(['/ejecucionEdddesempeno'], { queryParams: { tipoEval: this.tipoEval } });
  }

  showRegresar() {
    if (this.tiposResp === '1') {
      this.router.navigate(['/detalleEddua'], { queryParams: { idAdmon: this.precesoSesion.claveUnidad, proVig: this.precesoSesion.claveProceso } });
    } else {
      this.router.navigate(['/detalleEdd'], { queryParams: { proVig: this.precesoSesion.claveProceso } });
    }

  }

  changeeTab(idSeccion: number) {
    this.logger.debug('Sección ==>>', idSeccion);

  }



  handleChange(e) {
    console.log('e.index=>' + e.index);
    if(Number(e.index) === (this.seccionFinal.length - 1)){
      console.log('ocultando');
      this.showSiguiente = false;
    }else{
      console.log('mostrarndo');
      this.showSiguiente = true;
    }

    this.index = e.index;
  }

  openNext() {
    //this.index = (this.index === 2) ? 0 : this.index + 1;
console.log('openNext');
    if((this.index + 1) === (this.seccionFinal.length - 1)){
      this.showSiguiente = false;
    }else{
      this.showSiguiente == true;
    }

    this.index +=1;
    //this.changeCSS();
  }

  finalizarQuiz() {
    this.router.navigate(['/ejecucionEdddemeritos']);
  }

  changeCSS() {
    this.taview.style = 'background-color: red ';
  }

  changeColorFont(color: string):string{
    
    if(color !== '#FFFFFF'){
      return '#f8f9fa';
    }
    return '#333333';
  }

  obtenerDatosFinal() {

    let pregP: RespuestasPortal[] = [];
    this.service.obtenerAvance(this.idProcesoVig, this.currentUser.CLAVESERVIDOR, this.idEvaluado, this.unidadAdmin).subscribe(e => {
      pregP = e;
      this.logger.debug('AVANCE=>>',pregP);
    });
    let respuestas = this.service.getRespuestasEDD(this.idProcesoVig);
    let evaluacion = this.service.obtenerEvaluacion(this.idProcesoVig, this.currentUser.CLAVESERVIDOR, this.unidadAdmin, this.tiposResp, this.idEvaluado);
    this.logger.debug('evaluacion ppp=>>',evaluacion);

    forkJoin([respuestas, evaluacion]).subscribe(results => {
      this.logger.debug('forkJoin respuestas', results[0].response);
      this.logger.debug('forkJoin evaluacion', results[1].response);
      this.logger.debug('forkJoin respuestas', results[0]);
      this.logger.debug('forkJoin evaluacion', results[1]);

      let existenRespuestas = true;
      let existenEvaluacion = true;
      let respuestas: RespuestasEDD[] = [];
      let evaluacionf: EjecucionEdd[] = [];
      if (existenEvaluacion && existenRespuestas) {
        respuestas = results[0].response;
        evaluacionf = results[1].response;

        let ser: SeccionesEddPR[] = [];
        evaluacionf.forEach(e => {
          let as: SeccionesEddPR = new SeccionesEddPR();
          as.DESCRIPCIONVALOR = e.DESCSECCION;
          //as.IDSECCION = e.IDSECCION;
          as.VALOR = '';
          as.backgroundColor = e.COLORSECCION;
          as.claveEvaluado = this.idEvaluado;
          as.claveEvaluador = this.currentUser.CLAVESERVIDOR;
          as.claveUnidadAdmin = this.unidadAdmin;
          as.idprocesovigente = this.idProcesoVig;
          as.idseccion = e.IDSECCION;
          as.nombreseccion = e.NSECCION;
          as.preguntas = [];
          e.PREGUNTASEJECUCION.forEach(i => {
            let ad: PreguntasEDD = new PreguntasEDD();
            ad.backgroundPregunta = '#FFFFFF';
            ad.descripcionpregunta = i.DESCRIPCIONPREGUNTA;
            ad.idPreguntaResp = '';
            ad.idpregunta = i.IDPREGUNTA;
            ad.idprocesovigente = i.IDPROCESOVIGENTE;
            ad.idseccion = i.IDSECCION;
            ad.tipoCampo = i.TIPOCAMPO
            ad.respuestas = [];
            if (i.TIPOCAMPO === '1') {
              respuestas.forEach(o => {
                let pr: RespuestasPortal;
                for (let g of pregP) {
                  if (g.idseccion === i.IDSECCION && g.idpregunta === i.IDPREGUNTA && g.idrespuesta === o.IDRESPUESTA) {
                    pr = new RespuestasPortal();
                    pr = g;
                  }
                }
                let af: RespuestasEDDB = new RespuestasEDDB();
                af.idrespuesta = o.IDRESPUESTA;
                af.namegroup = i.IDPREGUNTA + '-' + i.IDSECCION;
                af.puntos = i.PUNTOS;
                af.puntaje = o.PUNTAJE;
                af.respuesta = o.RESPUESTA;
                af.selected = '';
                if (pr != undefined) {
                  af.selected = 'true';
                  ad.idPreguntaResp = pr.idDtEvaluacionDesempenio;
                  ad.backgroundPregunta = e.COLORSECCION;
                }

                ad.respuestas.push(af);
              })
            } else {
              let pr: RespuestasPortal;
              for (let g of pregP) {
                if (g.idseccion === i.IDSECCION && g.idpregunta === i.IDPREGUNTA) {
                  pr = new RespuestasPortal();
                  pr = g;
                }
              }

              let af: RespuestasEDDB = new RespuestasEDDB();
              af.idrespuesta = i.IDPREGUNTA;
              af.namegroup = i.IDPREGUNTA + '-' + i.IDSECCION;
              af.puntos = i.PUNTOS;
              af.puntaje = '0';
              af.respuesta = '';
              af.selected = '';
              if (pr != undefined) {
                af.selected = 'true';
                ad.idPreguntaResp = pr.idDtEvaluacionDesempenio;
                af.puntaje = pr.idrespuesta;
                ad.backgroundPregunta = e.COLORSECCION;
              }
              ad.respuestas.push(af);
            }

            as.preguntas.push(ad);
          })
          this.logger.debug('seccion=>', as);
          ser.push(as);
          this.seccionFinal.push(as);
        });


      } else {
        this.logger.debug('existenEvaluacion=>', existenEvaluacion);
        this.logger.debug('existenRespuestas=>', existenRespuestas);
      }

      this.logger.debug('FINAL01 =>', this.seccionFinal);

      this.seccionFinal.forEach(seccion => {
        if (seccion.idseccion === 'X') {
          this.existeMensaje = '1';
        } else if (seccion.idseccion === environment.ID_SEC_DEMERITOS) {
          this.existeDemeritos = '1';
        } else if (seccion.idseccion === environment.ID_SEC_COMPETENCIA_APTITU || seccion.idseccion === environment.ID_SEC_COMPETENCIA_SOCIO){
          this.existeDesempenio = '1';
        }
      });

      this.logger.debug('existeMensaje =>', this.existeMensaje);


      this.calcularTotal();
    });

  }

  tipoEvaluacionTab(sec: SeccionesEDD): Boolean {
    if (sec.IDSECCION === 'CA') {
      if (this.tipoEval == '1') {
        return false;
      } else if (this.tipoEval == '2') {
        return false;
      } else if (this.tipoEval == '3') {
        return true;
      }
    }
    if (sec.IDSECCION === 'CS') {
      if (this.tipoEval == '1') {
        return false;
      } else if (this.tipoEval == '2') {
        return false;
      } else if (this.tipoEval == '3') {
        return true;
      }
    }
    if (sec.IDSECCION === 'D') {
      if (this.tipoEval == '1') {
        return true;
      } else if (this.tipoEval == '2') {
        return true;
      } else if (this.tipoEval == '3') {
        return false;
      }
    }

  }

  llenarPreguntas(se: SeccionesEDD[], resp: RespuestasEDD[], preg: DetalleEjecucionEddambos[], pregD: DetalleEjecucionEddambos[], pregT: DetalleEjecucionEddambos[], pregP: RespuestasPortal[]) {
    let seccionFinal1: SeccionesEddPR[] = [];
    for (let a of se) {
      let s: SeccionesEddPR = new SeccionesEddPR();
      s.idseccion = a.IDSECCION;
      s.nombreseccion = a.NOMBRESECCION;
      s.idprocesovigente = a.IDPROCESOVIGENTE;
      s.claveEvaluado = this.idEvaluado;
      s.claveEvaluador = this.currentUser.CLAVESERVIDOR;
      s.claveUnidadAdmin = '3';
      s.preguntas = [];
      //let respuestasP = this.service.obtenerAvance(this.idProcesoVig,this.currentUser.CLAVESERVIDOR,this.idEvaluado,'');
      if (a.IDSECCION === 'CA') {
        for (let instr of this.instrucciones) {
          if (instr.IDSECCION === 'CA') {
            s.DESCRIPCIONVALOR = instr.DESCRIPCIONVALOR;
            s.VALOR = instr.VALOR;
            //s.IDSECCION = instr.IDSECCION;
            s.idseccion = instr.IDSECCION;
          }
        }

        for (let b of preg) {
          this.logger.debug(b.IDSECCION + ' === ' + a.IDSECCION);

          let p: PreguntasEDD = new PreguntasEDD();
          p.descripcionpregunta = b.DESCRIPCIONPREGUNTA;
          p.idpregunta = b.IDPREGUNTA;
          p.idprocesovigente = b.IDPROCESOVIGENTE + '';
          p.idseccion = b.IDSECCION;
          p.backgroundPregunta = '#FFFFFF ';
          p.idPreguntaResp = '';
          s.backgroundColor = '#24cafa';

          p.respuestas = [];

          for (let c of resp) {
            let pr: RespuestasPortal;//pregP.filter(e => { e.idseccion == 'CA' && e.idpregunta == b.IDPREGUNTA && e.idrespuesta == c.IDRESPUESTA });
            for (let g of pregP) {
              if (g.idseccion === 'CA' && g.idpregunta === b.IDPREGUNTA && g.idrespuesta === c.IDRESPUESTA) {
                pr = new RespuestasPortal();
                pr = g;
              }
            }
            this.logger.debug('La pregunta existe en el portal => ', pr);
            let r: RespuestasEDDB = new RespuestasEDDB();
            r.idrespuesta = c.IDRESPUESTA;
            r.puntaje = c.PUNTAJE;
            if (pr != undefined) {
              r.selected = 'true';
              p.idPreguntaResp = pr.idDtEvaluacionDesempenio;
            } else {
              r.selected = '';
            }
            r.respuesta = c.RESPUESTA;

            r.namegroup = p.idpregunta + p.idseccion;
            //r.puntos = p.puntos
            p.respuestas.push(r);
          }
          s.preguntas.push(p);

        }
      }

      if (a.IDSECCION === 'CS') {
        for (let instr of this.instrucciones) {
          if (instr.IDSECCION === 'CS') {
            s.DESCRIPCIONVALOR = instr.DESCRIPCIONVALOR;
            s.VALOR = instr.VALOR;
            // s.IDSECCION = instr.IDSECCION;
            s.idseccion = instr.IDSECCION;
          }
        }
        for (let b of pregD) {
          this.logger.debug(b.IDSECCION + ' === ' + a.IDSECCION);

          let p: PreguntasEDD = new PreguntasEDD();
          p.descripcionpregunta = b.DESCRIPCIONPREGUNTA;
          p.idpregunta = b.IDPREGUNTA;
          p.idprocesovigente = b.IDPROCESOVIGENTE + '';
          p.idseccion = b.IDSECCION;
          p.backgroundPregunta = '#FFFFFF';
          p.idPreguntaResp = '';
          s.backgroundColor = '#790373';
          p.respuestas = [];

          for (let c of resp) {
            let pr: RespuestasPortal;//pregP.filter(e => { e.idseccion == 'CA' && e.idpregunta == b.IDPREGUNTA && e.idrespuesta == c.IDRESPUESTA });
            for (let g of pregP) {
              if (g.idseccion === 'CS' && g.idpregunta === b.IDPREGUNTA && g.idrespuesta === c.IDRESPUESTA) {
                pr = new RespuestasPortal();
                pr = g;
              }
            }
            this.logger.debug('La pregunta existe en el portal => ', pr);
            let r: RespuestasEDDB = new RespuestasEDDB();
            r.idrespuesta = c.IDRESPUESTA;
            r.puntaje = c.PUNTAJE;
            r.respuesta = c.RESPUESTA;
            if (pr != undefined) {
              r.selected = 'true';
              p.idPreguntaResp = pr.idDtEvaluacionDesempenio;
            } else {
              r.selected = '';
            }
            r.namegroup = p.idpregunta + p.idseccion;
            p.respuestas.push(r);
          }
          s.preguntas.push(p);

        }
      }

      if (a.IDSECCION === 'D') {
        for (let instr of this.instrucciones) {
          if (instr.IDSECCION === 'D') {
            s.DESCRIPCIONVALOR = instr.DESCRIPCIONVALOR;
            s.VALOR = instr.VALOR;
            //s.IDSECCION = instr.IDSECCION;
            s.idseccion = instr.IDSECCION;
          }
        }
        for (let b of pregT) {
          this.logger.debug(b.IDSECCION + ' === ' + a.IDSECCION);

          let p: PreguntasEDD = new PreguntasEDD();
          p.descripcionpregunta = b.DESCRIPCIONPREGUNTA;
          p.idpregunta = b.IDPREGUNTA;
          p.idprocesovigente = b.IDPROCESOVIGENTE + '';
          p.idseccion = b.IDSECCION;
          p.backgroundPregunta = '#FFFFFF';
          s.backgroundColor = '#ff0000';
          p.idPreguntaResp = '';
          p.respuestas = [];

          // for (let c : resp[0]) {
          let c: RespuestasEDD = resp[0];
          let pr: RespuestasPortal;//pregP.filter(e => { e.idseccion == 'CA' && e.idpregunta == b.IDPREGUNTA && e.idrespuesta == c.IDRESPUESTA });
          for (let g of pregP) {
            if (g.idseccion === 'D' && g.idpregunta === b.IDPREGUNTA) {
              pr = new RespuestasPortal();
              pr = g;
              c.PUNTAJE = pr.idrespuesta;
            }
          }
          this.logger.debug('La pregunta existe en el portal => ', pr);
          let r: RespuestasEDDB = new RespuestasEDDB();
          r.idrespuesta = c.IDRESPUESTA;
          r.puntaje = c.PUNTAJE;
          r.respuesta = c.RESPUESTA;
          if (pr != undefined) {
            r.selected = 'true';
            p.idPreguntaResp = pr.idDtEvaluacionDesempenio;
          } else {
            r.selected = 'true';
          }
          r.namegroup = p.idpregunta + p.idseccion;
          // r.puntaje = pr.idpregunta;
          p.respuestas.push(r);
          //}
          s.preguntas.push(p);

        }
      }
      this.seccionFinal.push(s);
    }
    this.logger.debug('Lista final =>', this.seccionFinal)
    this.calcularTotal();
  }
  /**
   * cambia el fondo de la fila
   */
  changeColor(preg: PreguntasEDD, res: RespuestasEDDB, sec: SeccionesEddPR) {
    this.logger.debug('Preguntaa', preg);
    for (let p of preg.respuestas) {

      if (preg.tipoCampo === '1') {
        if (p.selected === 'true') {
          if (p.idrespuesta !== res.idrespuesta) {
            p.selected = '';
          }
        }
      } else {
        p.selected = 'true';
        this.logger.debug('demeritos 1 ->',p.idrespuesta);
        this.logger.debug('demeritos ->',p.puntaje)
        p.idrespuesta= p.puntaje;
      }
    }

    this.logger.debug('color seccion =>', sec);
    for (let p of preg.respuestas) {
      this.logger.debug('selected =>', p.selected);
      if (p.selected === 'true') {
        for (let tp of this.seccionFinal) {
          this.logger.debug('Comparar secciones =>', tp.idseccion, preg.idseccion);
          if (tp.idseccion === preg.idseccion) {
            for (let x of tp.preguntas) {
              if (x.idpregunta === preg.idpregunta) {
                x.backgroundPregunta = sec.backgroundColor;

              }
            }
          }
        }

      }
    }
    this.logger.debug('NUEVO BACKGROUND =>', this.seccionFinal);
    //this.progressBar = this.seccionFinal[0].preguntas.length + this.seccionFinal[1].preguntas.length + this.seccionFinal[2].preguntas.length
    this.calcularTotal();

  }

  onBlurMethod(res: RespuestasEDDB){
    this.logger.debug('Blur respuesta=>',res);

    let resto =Number(res.puntaje) % Number(res.puntos);
    if(resto !== 0){
      res.puntaje='0';
     // res.respuesta='valor no válido'
     this.messageService.clear();
     this.messageService.add({severity:'warn', summary: 'Valor no válido', detail:'Valor ingresado no es válido'});
    }

  }
  guradarAvance() {
    this.logger.debug('Enviando al servicio');
    this.service.guardarAvanece(this.seccionFinal).subscribe(e => {
      this.logger.debug('suscribe =>', e);
      this.logger.debug('v 0', this.seccionFinal[0]);
      this.logger.debug('v 1', this.seccionFinal[1]);
      this.logger.debug('v 2', this.seccionFinal[2]);

      var myJSON = JSON.stringify(e);
      this.logger.debug('response 1', myJSON);
      let jsonObj: any = JSON.parse(myJSON);
      this.logger.debug('response 2', jsonObj);
      let resp: SeccionesEddPR[] = jsonObj.seccionesEddPR;
      this.logger.debug('response 3', resp);
      
      //Se asigna denuevo el valor para saber que Id le corresponde cuando se guardo.
      this.seccionFinal.forEach(secciones => {
          secciones.preguntas.forEach(preguntas => {
            //Iteramos las respuestas que se nos regresaron
            resp.forEach(seccionesRes => {
              seccionesRes.preguntas.forEach(preguntasRes => {
                if (preguntas.idpregunta === preguntasRes.idpregunta && preguntas.idseccion === preguntasRes.idseccion ) {
                  this.logger.debug('Validacion respuestas =>', preguntas.idpregunta + ", " + preguntasRes.idpregunta);
                  preguntas.idPreguntaResp = preguntasRes.idPreguntaResp;
                }
                });
            });
          });
      });

      //this.seccionFinal = resp;
      this.logger.debug('Final=>4', this.seccionFinal);
      //mensaje
      this.messageService.add({severity:'success', summary: 'Guardado correctamente', detail:'Se ha guardado el avance'});
    })

  }

  finalizarEncuesta() {

    this.messageService.clear();
    // Validamos que se hayan contestado todas las respuestas de demeritos.
    if (this.progressBar === '100') {
      // Realizamos Guardado
      this.service.guardarRespuestasFinal(this.seccionFinal, this.idEvaluado, this.currentUser.CLAVESERVIDOR,
                                          this.precesoSesion.claveUnidad, this.tipoEval, this.tiposResp, this.precesoSesion.estatusProceso);
    } else {
      this.messageService.add({severity: 'warn', summary: 'Error',
                                                 detail: 'No se puede guardar hasta contestar el 100% de la evaluación.'});
    }
    // this.finalizarQuiz();
  }

  calcularTotal() {
    let totalPreguntas: number = 0;
    this.seccionFinal.forEach(a => {
      totalPreguntas += a.preguntas.length
    })
    //let totalPreguntas: number = this.seccionFinal[0].preguntas.length + this.seccionFinal[1].preguntas.length + this.seccionFinal[2].preguntas.length;
    let totalSeleted: number = 0;
    /*     for (let x of this.seccionFinal[0].preguntas) {
          for (let y of x.respuestas) {
            if (y.selected === 'true') {
              totalSeleted++;
            }
          }
        }
        for (let x of this.seccionFinal[1].preguntas) {
          for (let y of x.respuestas) {
            if (y.selected === 'true') {
              totalSeleted++;
            }
          }
        }
        for (let x of this.seccionFinal[2].preguntas) {
          for (let y of x.respuestas) {
            if (y.puntaje !== '') {
              totalSeleted++;
            }
          }
        } */
    this.seccionFinal.forEach(b => {
      b.preguntas.forEach(c => {
        c.respuestas.forEach(d => {
          if (d.selected === 'true') {
            totalSeleted++;
          }
        })
      })
    })

    if (totalSeleted == 0 && totalPreguntas == 0) {
      this.progressBar = '0';
    } else {
      this.progressBar = ((totalSeleted * 100) / totalPreguntas).toFixed(0);
    }

  }


  btnFinalizar(): boolean {
    if (this.existeDesempenio === '1' &&  this.existeMensaje != '1') {
      return true;
    } else {
      return false;
    }
  }

  IsTotalDemeritosContestado() {
    let totalPreguntas: number = 0;
    this.seccionFinal.forEach(a => {
      if  (a.idseccion === environment.ID_SEC_DEMERITOS)  {
        totalPreguntas += a.preguntas.length;
      }
    });
    
    let totalSeleted: number = 0;

    this.seccionFinal.forEach(b => {
      if  (b.idseccion === environment.ID_SEC_DEMERITOS)  {
            b.preguntas.forEach(c => {
              c.respuestas.forEach(d => {
                if (d.selected === 'true') {
                  totalSeleted++;
                }
              })
            })
        }
    });

    if (totalPreguntas === totalSeleted) {
      return true;
    } else {
      return false;
    }

  }




  btnDemeritos(): boolean {

    if (this.existeDemeritos === '1') {
      return true;
    } else {
      return false;
    }
  }

  showGuardarAvance():boolean{
    if((this.tipoEval === '2' || (this.tipoEval === '3' && this.existeMensaje === '1'))) {
      return true;
    }

    if(this.existeMensaje !== '1' && this.tipoEval !== '2'){
      return true;
    }
    return false;
  }

  guardarDemeritos() {
    this.messageService.clear();
    // Validamos que se hayan contestado todas las respuestas de demeritos.
    if (this.IsTotalDemeritosContestado()) {
      // Realizamos Guardado
      this.service.guardarRespuestasFinalDemeritos(this.seccionFinal, this.idEvaluado, this.currentUser.CLAVESERVIDOR,
                                                   this.precesoSesion.claveUnidad, this.tipoEval);
      this.messageService.add({severity: 'success', summary: 'Guardado correctamente', detail: 'Deméritos guardado correctamente'});
    } else {
      this.messageService.add({severity: 'warn', summary: 'Error', detail: 'No se puede guardar hasta contestar el 100% de deméritos.'});
    }
  }

  colorTab(color: any): any {
    const colorfinal = { background: color, 'box-shadow': '0 0 0 0.2em #'+ color +' !important'};
    return colorfinal;
  }

  bordeTab(color: string){
    
    if(color.toUpperCase().replace('0000', '').length != color.length){
      return "li-focus";
    }else{
      return "";
    }
  }

}
