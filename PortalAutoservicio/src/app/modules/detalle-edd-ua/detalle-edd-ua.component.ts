import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table/table';
import { FormBuilder } from '@angular/forms';
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { UtilsService } from 'src/app/services/utils.service';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import * as moment from 'moment';
import { NgxSpinnerService } from 'ngx-spinner';
import { DetalleProcesoEddua } from 'src/app/models/detalle-proceso-edd-ua';
import { DetalleEdduaService } from 'src/app/services/detalle-edd-ua.service';
import { Router, ActivatedRoute, } from '@angular/router';

import { servidoresPublicosEddUa } from 'src/app/models/servidores-publicos-eddua';
import { ProcesoEddSession } from 'src/app/models/proceso-edd-session';
import { EjecucionEdddemeritosService } from 'src/app/services/ejecucionedd-demeritos.service';
import { forkJoin } from 'rxjs';
import { RespuestaServiceM4 } from 'src/app/repuesta/respuesta-service-m4';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { EjecucionEddambosService } from 'src/app/services/ejecucioneddambos.service';
import { InstruccionesEDD } from 'src/app/models/instrucciones-edd';
import { SeccionesEddPreg } from 'src/app/models/resultados-edd-preg';
import { RespuestasEDD } from 'src/app/models/respuestad-edd';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-detalle-edd-ua',
  templateUrl: './detalle-edd-ua.component.html',
  styleUrls: ['./detalle-edd-ua.component.css']
})
export class DetalleEddUaComponent implements OnInit {

  //Variables Globales de la clase
  movimidetalleproua: DetalleProcesoEddua = new DetalleProcesoEddua();
  map = new Map<String, String>();
  cols: any[];
  @ViewChild('dt') table: Table;
  items: MenuItem[];
  displayDialog: boolean;
  idservpublico: string;
  idServidorPublico: string = '';
  nombreServidorPublico: string = '';
  descnotifica: string;
  index: number = 0;
  idcancelar: string;
  idAdmon: string = '';
  idProce: string = ';'
  tooltipimg: string = '';
  spPendientes: number = 0;
  spFaltantes: servidoresPublicosEddUa[] = [];
  precesoSesion: ProcesoEddSession = new ProcesoEddSession();
  soloDemeritos: boolean = false;
  servidorp: DatosPersonales = new DatosPersonales();



  //Elementos Detalle de las evaluaciones
  instruccionesEDD: InstruccionesEDD[] = [];
  encabezadoDetalle: string = '';
  showDetalles: boolean = false;

  //Constructor de la clase
  constructor(
    private service: DetalleEdduaService,
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private spinner: NgxSpinnerService,
    private serviceEdd: EjecucionEdddemeritosService,
    private ejecucionEddambosService: EjecucionEddambosService) { }


  //Metodo principal de la clase
  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        this.idAdmon = params.idAdmon;
        this.idProce = params.proVig;
        this.logger.debug('url=>', params.idAdmon);
      });

    this.precesoSesion = JSON.parse(sessionStorage.getItem('headerProceso'));

    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        console.log(usr);
        this.servidorp = usr;
        this.idServidorPublico = usr.CLAVESERVIDOR;
        this.nombreServidorPublico = usr.NOMBRECOMPLETO;
      });
    }

    let fecha = moment(new Date()).format('MMMM Do YYYY');

    /*Informacion del grid*/
    this.getDetalle();

  }

  confirmacionEvaluacionDesempeno() {
    console.log('ss');
    this.messageService.clear();
    this.messageService.add({ key: 'c', sticky: true, severity: 'warn', summary: '¿Estás seguro?', detail: 'Confirmar para continuar' });
  }


  rechazar() {
    this.messageService.clear('c');
  }

  no() {
    this.displayDialog = false;
  }

  showRegresar() {
    this.router.navigate(['/detalleEdd'], { queryParams: { proVig: this.idProce } });
  }

  /**
   * Direcciona a la ejecición de la evaluación
   * y valida si tiene un proceso de evaluación pendiente
   * @param tipo 
   */
  redirectEjecucionEddambos(tipo: servidoresPublicosEddUa) {

    this.messageService.clear();


    if (this.servidorp.CONSULTA === '1' || tipo.TIPOEVALUACION == '4') {
      return;
    }

    let est = tipo.ESTATUSEVALUACION.split('|');

    if (tipo.TIPOEVALUACION == '1' && est[0] === '1') {
      this.messageService.add({
        key: 'd', sticky: true, severity: 'warn',
        summary: 'La evaluación de desempeño ya fue realizada.', detail: ''
      });
      return;
    }

    if (tipo.TIPOEVALUACION == '2' && est[1] === '1') {
      this.messageService.add({
        key: 'd', sticky: true, severity: 'warn',
        summary: 'La evaluación de deméritos ya fue realizada.', detail: ''
      });
      return;
    }


    if (tipo.TIPOEVALUACION == '3' && est[0] === '1' && est[1] === '1') {
      this.messageService.add({
        key: 'd', sticky: true, severity: 'warn',
        summary: 'La evaluación ya fue realizada.', detail: ''
      });
      return;
    }

    this.logger.debug('Entro al metodo redirectEjecucionEddambos', tipo);

    this.precesoSesion.estatusProceso = tipo.ESTATUSEVALUACION;
    this.tooltipimg = '';
    this.precesoSesion.claveEvaluado = tipo.CLAVESERVIDORPUBLICO;
    this.precesoSesion.nombreEvaluado = tipo.NOMBRESERVIDORPUBLICO + ' ' + tipo.APELLIDOPATERNOSERVIDORPUBLICO + ' ' + tipo.APELLIDOMATERNOSERVIDORPUBLICO;
    sessionStorage.setItem('headerProceso', JSON.stringify(this.precesoSesion));
    this.logger.debug('headerProceso', this.precesoSesion);
    this.router.navigate(['/ejecucionEddambos'], { queryParams: { idEvaluado: tipo.CLAVESERVIDORPUBLICO, idProcesoVig: tipo.IDPROCESOVIGENTE, tipoEval: tipo.TIPOEVALUACION, unidadAdmin: this.idAdmon, tipoResp: '1' } });
  }

  mostratToolTip(tipo: servidoresPublicosEddUa): string {
    let est = tipo.ESTATUSEVALUACION.split('|');

    if (tipo.IDEVALUACIONKP === '0') {
      return 'La Persona Servidora Pública no ha contestado su encuesta asociada, no puedes evaluar sus competencias.';
    }

  }
  /**
   * obtiene los servidores publicos a evaluar de la unidad
   * administrativa seleccionada anteriormente
   */
  getDetalle() {
    this.spinner.show();
    this.service.DetalleProcesoEddua(this.idServidorPublico, this.idAdmon, this.idProce).subscribe(e => {
      let vari = <RespuestaServiceM4<DetalleProcesoEddua>>e;
      //this.logger.debug('DETALLE =>>', vari)
      //let deta: DetalleProcesoEddua = <DetalleProcesoEddua>vari.response[0];
      //this.logger.debug('DETA=>>', deta);
      // this.logger.debug('Response=>', vari.response);
      var myJSON = JSON.stringify(vari.response);
      // //let jsonObj: any = JSON.parse(vari.response);
      // this.logger.debug('Detalle 1=> ', myJSON);
      let listDetalle = <DetalleProcesoEddua>JSON.parse(myJSON)

      this.movimidetalleproua = listDetalle;



      this.logger.debug('Detalle 2=>', this.movimidetalleproua);
      this.spinner.hide();
      this.evaluarSoloDemeritos();


    });
  }
  /**
   * Finaliza los procesos de demetitos 
   */
  finalizarEvaluacionesDemeritos() {
    var pendientes: number = 0;
    console.log('Finaliza Demeritos' + JSON.stringify(this.movimidetalleproua.LSTSERVIDORESPUBLICOS));
    //let spFaltantes: servidoresPublicosEddUa[] = [];
    for (let dem of this.movimidetalleproua.LSTSERVIDORESPUBLICOS) {

      let es = dem.ESTATUSEVALUACION.split('|')
      if (es[1] == '0') {
        pendientes++;
        this.spFaltantes.push(dem);
      }

    }

    this.spPendientes = pendientes;
    if (pendientes > 0) {
      this.messageService.clear();
      this.messageService.add({ key: 'endDem', sticky: true, severity: 'warn', summary: '', detail: '' });
    } else {
      this.messageService.add({ key: 'd', sticky: true, severity: 'warn', summary: 'No se finalizó ninguna evaluación de deméritos.', detail: '' });
    }

    console.log("spFaltantes -> ", this.spFaltantes);

  }
  /**
   * cerrar dialogo
   */
  cerrarDialog() {
    this.messageService.clear('endDem');
  }

  /**
   * Finaliza demeritos
   */
  finalizarDemeritos() {
    this.service.finalizarDemeritos(this.movimidetalleproua, this.spFaltantes, this.idServidorPublico);

    this.messageService.clear('endDem');
    //Mostrar mensaje de espera de procesamiento sincrono
    this.messageService.add({ key: 'respDem', sticky: true, severity: 'info', summary: '', detail: '' });
  }

  /**
   * Redireccionar a pantalla inicial de evaluacion.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 11/02/2022.
  */
  redireccionarInicio() {
    this.router.navigate(['/evaluacionDesempeno']);
  }

  /**
   * valida que la la evaluación no este cerrada
   * @param estatus 
   */
  validacionEstatus(estatus: string): boolean {
    let est = estatus.split('|');

    if (est[0] === '0' || est[1] === '0') {
      return false;
    } else {
      return true;
    }
  }

  validaEvaluacionCerradaDemeritos(estatus: string): boolean {
    let est = estatus.split('|');

    if (est[1] === '1') {
      return true;
    } else {
      return false;
    }
  }

  validaEvaluacionCerrada(estatus: string): boolean {
    let est = estatus.split('|');

    if (est[0] === '1') {
      return true;
    } else {
      return false;
    }
  }

  descargarPDF(tipo: servidoresPublicosEddUa) {
    this.logger.debug('entro al metodo descargarPDF');

    this.spinner.show();
    const a = document.createElement('a');

    let servicePDF = this.serviceEdd.generarPDF(tipo.IDPROCESOVIGENTE, tipo.CLAVESERVIDORPUBLICO);
    forkJoin([servicePDF]).subscribe(results => {
      this.logger.debug('Result=>', results);
      if (results !== undefined) {
        const url = window.URL.createObjectURL(results[0]);
        this.logger.debug('URL=>', url);
        let src: string = url;
        this.logger.debug('src=>', src);
        a.setAttribute('style', 'display:none');
        document.body.appendChild(a);
        a.href = src;
        a.download = 'Reporte_EDD.pdf';
        a.click();
        this.spinner.hide();
      }
    });
  }

  evaluarSoloDemeritos() {
    this.movimidetalleproua.LSTSERVIDORESPUBLICOS.forEach(e => {

      if (e.TIPOEVALUACION === '3' || e.TIPOEVALUACION === '2') {
        this.soloDemeritos = true;
        return;
      }
    });
  }

  disabledDemeritos(estatus: string): boolean {
    this.logger.debug('disable demeritos', this.soloDemeritos);
    this.logger.debug('estatus=>', estatus);
    if (this.soloDemeritos) {
      let est = estatus.split('|');
      if (est[1] === '1') {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  detalleEvaluacion(tipoDetalle: number, datosServidor: servidoresPublicosEddUa) {
    //Obtenemos las instrucciones del proceso de evaluacion
    const instrucciones = this.ejecucionEddambosService.getinstrucciones(datosServidor.IDPROCESOVIGENTE);

    // Obtenemos desempeño
    if (tipoDetalle == 1) {
      this.encabezadoDetalle = "Detalle Desempeño";
      const respuestas = this.ejecucionEddambosService.getRespuestasEDD(datosServidor.IDPROCESOVIGENTE);

      let respuestasSec01 = this.ejecucionEddambosService.getResultadosEdd(
        datosServidor.CLAVESERVIDORPUBLICO,
        environment.ID_SEC_COMPETENCIA_APTITU,
        datosServidor.IDPROCESOVIGENTE);

      let respuestasSec02 = this.ejecucionEddambosService.getResultadosEdd(
        datosServidor.CLAVESERVIDORPUBLICO,
        environment.ID_SEC_COMPETENCIA_SOCIO,
        datosServidor.IDPROCESOVIGENTE);

      forkJoin([instrucciones, respuestasSec01, respuestasSec02, respuestas]).subscribe(preguntasEval => {

        this.instruccionesEDD = preguntasEval[0].response.filter(x => x.IDSECCION != environment.ID_SEC_DEMERITOS);

        this.instruccionesEDD.forEach(preg => {
          if (preg.IDSECCION == environment.ID_SEC_COMPETENCIA_APTITU) {
            let secciones = <SeccionesEddPreg>JSON.parse(JSON.stringify(preguntasEval[1].response));
            preg.DetalleEjecucionEddambos = secciones.RESULTADOSPREGUNTASEDDDTO;
          } else if (preg.IDSECCION == environment.ID_SEC_COMPETENCIA_SOCIO) {
            let secciones = <SeccionesEddPreg>JSON.parse(JSON.stringify(preguntasEval[2].response));
            preg.DetalleEjecucionEddambos = secciones.RESULTADOSPREGUNTASEDDDTO;
          }

          preg.DetalleEjecucionEddambos.forEach(respuesta => {
            respuesta.DSRESPUESTA = (<RespuestasEDD[]>preguntasEval[3].response).find(X => X.IDRESPUESTA == respuesta.IDRESPUESTA).RESPUESTA;
          });

        });

        this.showDetalles = true;
      }, error => {
        this.messageService.add({
          key: 'd', sticky: true, severity: 'warn',
          summary: 'No se pudo obtener el detalle de la evaluación.', detail: ''
        });
        this.showDetalles = false;
      });

    } else { // Obtenemos demeritos
      this.encabezadoDetalle = "Detalle Demeritos";
      let respuestasSec03 = this.ejecucionEddambosService.getResultadosEdd(
        datosServidor.CLAVESERVIDORPUBLICO,
        environment.ID_SEC_DEMERITOS,
        datosServidor.IDPROCESOVIGENTE);

      forkJoin([instrucciones, respuestasSec03]).subscribe(preguntasEval => {
        console.log('respuestasSec03=>' + JSON.stringify(preguntasEval[1].response));

        this.instruccionesEDD = preguntasEval[0].response.filter(x => x.IDSECCION === environment.ID_SEC_DEMERITOS);

        this.instruccionesEDD.forEach(preg => {
          if (preg.IDSECCION == environment.ID_SEC_DEMERITOS) {
            let secciones = <SeccionesEddPreg>JSON.parse(JSON.stringify(preguntasEval[1].response));
            preg.DetalleEjecucionEddambos = secciones.RESULTADOSPREGUNTASEDDDTO;
          }

          preg.DetalleEjecucionEddambos.forEach(respuesta => {
            respuesta.DSRESPUESTA = respuesta.IDRESPUESTA;
          });


        });
        this.showDetalles = true;
      }, error => {
        this.messageService.add({
          key: 'd', sticky: true, severity: 'warn',
          summary: 'No se pudo obtener el detalle de la evaluación.', detail: ''
        });
        this.showDetalles = false;
      });
    }
  }

}


