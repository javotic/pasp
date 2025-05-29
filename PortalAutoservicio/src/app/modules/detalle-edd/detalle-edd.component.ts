import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table/table';
import { FormBuilder} from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import * as moment from 'moment';
import { NgxSpinnerService } from 'ngx-spinner';
import { DetalleeddService } from 'src/app/services/detalle-edd.service';
import { DetalleProcesoedd } from 'src/app/models/detalle-proceso-edd';
import { DetalleComisionesedd } from 'src/app/models/detalle-comisiones-edd';
import { Router, ActivatedRoute } from '@angular/router';
import { forkJoin } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PorcesoVigente } from 'src/app/models/proceso-vigente';
import { ProcesoEddSession } from 'src/app/models/proceso-edd-session';
import { EjecucionEdddemeritosService } from 'src/app/services/ejecucionedd-demeritos.service';
import { EvaluaciondesemService } from 'src/app/services/evaluaciondesempeno.service';
import { RespuestaServiceM4 } from 'src/app/repuesta/respuesta-service-m4';

@Component({
  selector: 'app-detalle-edd',
  templateUrl: './detalle-edd.component.html',
  styleUrls: ['./detalle-edd.component.css']
})
export class DetalleEddComponent implements OnInit {

  //Variables Globales de la clase
  movimidetallepro: DetalleProcesoedd[];
  movimidetallecomi: DetalleComisionesedd[];
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
  urlDat: string = `frwsr_LPSAUT_CONS_DAT${environment.HEDER_WS}.php`;
  urlCom: string = `frwsr_LPSAUT_CONS_DAT2${environment.HEDER_WS}.php`;
  paramProcesoVigente: string = '';
  vigente: PorcesoVigente = new PorcesoVigente();
  precesoSesion: ProcesoEddSession = new ProcesoEddSession();

  //Constructor de la clase
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: DetalleeddService,
    private messageService: MessageService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private spinner: NgxSpinnerService,
    private fb: FormBuilder,
    private serviceEdd: EjecucionEdddemeritosService,
    private serviceEval: EvaluaciondesemService) { }

  //Metodo principal de la clase
  ngOnInit(): void {
    this.spinner.show();
    this.route.queryParams
      .subscribe(params => {
        this.paramProcesoVigente = params.proVig;
        this.logger.debug('url=>', params.proVig);
      });
    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        console.log(usr);
        this.idServidorPublico = usr.CLAVESERVIDOR;
        this.nombreServidorPublico = usr.NOMBRECOMPLETO;
      });
    }
    this.obtenerProcesoVigente();
    let fecha = moment(new Date()).format('MMMM Do YYYY');
    /*Informacion de los grids*/
    this.obtenerDetalleEDD(this.idServidorPublico, this.paramProcesoVigente);
    this.obtenerDetalleComisiones(this.idServidorPublico, this.paramProcesoVigente);
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
    this.router.navigate(['/evaluacionDesempeno']);
  }
  /**
   * Redirecciona al detalle de la unidad administrtiva 
   * para evaluar
   * @param obj 
   */
  redirectDetalleEddua(obj: DetalleProcesoedd) {
    let preceso: ProcesoEddSession = new ProcesoEddSession();
    preceso.claveEvaluado = '';
    preceso.claveProceso = this.vigente.CLAVEPROCESOEVALUACION;
    preceso.claveUnidad = obj.IDUNIDADADMINISTRATIVA + '';
    preceso.nombreEvaluado = '';
    preceso.nombreProceso = this.vigente.NOMBREPROCESOEVALUACION;
    preceso.nombreUnidad = obj.NOMBREUNIDADADMINISTRATIVA + '';
    this.logger.debug('headerProceso=>', preceso);
    sessionStorage.setItem('headerProceso', JSON.stringify(preceso));
    this.router.navigate(['/detalleEddua'], { queryParams: { idAdmon: obj.IDUNIDADADMINISTRATIVA, proVig: this.paramProcesoVigente } });
  }

  /**
   * Redirecciona al la evaluación del servidor publico a evaluar
   */
  redirectToTest(evaluado: DetalleComisionesedd) {
    let est = evaluado.ESTATUSEVALUACION;
    this.logger.debug('estatusRedEvaluar=>', est);
    this.logger.debug('est[1]=>', est[1]);
    this.logger.debug('evaluado.TIPOEVALUACION=>', evaluado.TIPOEVALUACION);
    if (est[1] === '0' && evaluado.TIPOEVALUACION === '1') {
      return;
    }
    sessionStorage.removeItem('headerProceso');
    if (evaluado.EVALUACIONKP === '0') {
      return;
    }
    this.precesoSesion.claveProceso = this.vigente.CLAVEPROCESOEVALUACION;
    this.precesoSesion.claveUnidad = '';
    this.precesoSesion.nombreProceso = this.vigente.NOMBREPROCESOEVALUACION;
    this.precesoSesion.nombreUnidad = '';
    this.precesoSesion.claveEvaluado = evaluado.CLAVESERVIDORPUBLICO + '';
    this.precesoSesion.nombreEvaluado = evaluado.NOMBRESERVIDORPUBLICO + ' ' + evaluado.APELLIDOPATERNOSERVIDORPUBLICO + ' ' + evaluado.APELLIDOMATERNOSERVIDORPUBLICO;
    this.precesoSesion.estatusProceso = est;
    sessionStorage.setItem('headerProceso', JSON.stringify(this.precesoSesion));
    this.router.navigate(['/ejecucionEddambos'], { queryParams: { idEvaluado: evaluado.CLAVESERVIDORPUBLICO, idProcesoVig: this.paramProcesoVigente, tipoEval: evaluado.TIPOEVALUACION, unidadAdmin: evaluado.CLAVESERVIDORPUBLICO, tipoResp: '2' } });
  }

  hideButoon(estatus: string): boolean {
    let newDate = new Date(this.vigente.FECHAFINPROCESOGENERAL)
    if (new Date() > newDate) {
      return true;
    }

    if (estatus === '100') {
      return true;
    } else {
      return false;
    }

  }

  /**
   *  consulta detalle de EDD
   * @param idSP 
   * @param idProceso 
   */
  obtenerDetalleEDD(idSP: string, idProceso: string) {
   
    let detalleEdd = {
      "funcion": "consultarDetalleEDD",
      "IDSERVIDORPUBLICO": idSP,
      "IDPROCESOVIGENTE": idProceso
    }
    this.service.DetalleProcesoedd(idSP, idProceso).subscribe(data => {
      let vari = <RespuestaServiceM4<DetalleProcesoedd>>data;
      this.movimidetallepro = <DetalleProcesoedd[]>vari.response;
      this.logger.debug('unidades =>', this.movimidetallepro);
      this.spinner.hide();
    })

  }

  /**
   * Consulta de comisiones especiales
   * @param idSP 
   * @param idProceso 
   */
  obtenerDetalleComisiones(idSP: string, idProceso: string) {
    //this.spinner.show();
    let comisiones = {
      "funcion": "consultarDetalleEDDAE",
      "IDSERVIDORPUBLICO": idSP,
      "IDPROCESOVIGENTE": idProceso
    }
    this.service.DetalleComisionedd(idSP, idProceso).subscribe(data => {
      let vari = <RespuestaServiceM4<DetalleComisionesedd>>data;
      this.movimidetallecomi = <DetalleComisionesedd[]>vari.response;
    })
  }

  /**
   * Valida el estatus de la evaluación
   * @param detalleComisionesedd 
   */
  validarEvaluacion(detalleComisionesedd: string, tip: string): Boolean {
    let estat = detalleComisionesedd.split('|');
   
    if (estat[0] === '1' && estat[1] === '1') {
      return false;
    }

    return true;
  }
  /**
   * muestra check si la evaluación es de desempeño
   * @param tipo 
   */
  tipoEvaluacion(tipo: string): string {
    let estatusa = tipo.split('|')
    if (estatusa[0] === '1') {
      return 'pi pi-check-circle';
    }
    return '';
  }
  /**
   * Muestra check si la evaluación es demeritos o ambos
   * @param tipo 
   */
  tipoEvaluacionD(tipo: string): string {
    let estatusa = tipo.split('|')
    if (estatusa[1] === '1') {
      return 'pi pi-check-circle';
    }
    return '';
  }

  obtenerProcesoVigente() {
    let vigentePro = {
      "funcion": "consultarProcesoEvaluacionVigente",
      "IDSERVIDORPUBLICO": this.idServidorPublico
    }

    this.serviceEval.getProcesoVigente(this.idServidorPublico).subscribe(data => {
      this.logger.debug('Vigente=>', data);
      let vari = <RespuestaServiceM4<PorcesoVigente>>data;
      let proVig = <PorcesoVigente[]>vari.response;
      this.vigente = proVig[0];
      this.logger.debug('VIg=>', this.vigente);
    })

  }

  descargarPDF(tipo: DetalleComisionesedd) {
    this.logger.debug('entro al metodo descargarPDF');
    let est = tipo.ESTATUSEVALUACION.split('|');
    this.logger.debug('EstatusEV=>' + est);
    //Deben completarce ambas evaluaciones para descargar el documento
    if (est[1] === '1' && est[0] === '1') {
 
        this.spinner.show();
        const a = document.createElement('a');

        let servicePDF = this.serviceEdd.generarPDF(this.vigente.CLAVEPROCESOEVALUACION, tipo.CLAVESERVIDORPUBLICO + '');
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
  }

  mostratToolTip(tipo: DetalleComisionesedd): string {
    let est = tipo.ESTATUSEVALUACION.split('|');

    if (tipo.EVALUACIONKP === '0') {
      return 'La Persona Servidora Pública no ha contestado su encuesta asociada, no puedes evaluar sus competencias.';
    }

  }

  mostratToolTipDescarga(tipo: DetalleComisionesedd): string {
    let est = tipo.ESTATUSEVALUACION.split('|');

    
    if (est[1] === '0') {
      return 'No se puede descargar la evaluación, no cuenta con evaluación de demeritos.';
    }

    if (est[0] === '0') {
      return 'No se puede descargar la evaluación, no cuenta con evaluación de desempeño.';
    }

  }
}
