import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { ConfirmationService, MenuItem, MessageService } from 'primeng/api';
import { UtilsService } from 'src/app/services/utils.service';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { NgxSpinnerService } from 'ngx-spinner';
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import { EjecucionEdddemeritosService } from 'src/app/services/ejecucionedd-demeritos.service';
import { DetalleEjecucionEdddemeritos } from 'src/app/models/detalle-ejecucion-edd-demeritos';
import { ProcesoEddSession } from 'src/app/models/proceso-edd-session';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { EjecucionEddambosService } from 'src/app/services/ejecucioneddambos.service';


@Component({
  selector: 'app-ejecucion-edd-demeritos',
  templateUrl: './ejecucion-edd-demeritos.component.html',
  styleUrls: ['./ejecucion-edd-demeritos.component.css']
})
export class EjecucionEddDemeritosComponent implements OnInit {

  //Variables Globales de la clase
  items3: MenuItem[];
  activeItem: MenuItem;
  preguntas: DetalleEjecucionEdddemeritos[];
  activeIndex: number = 1;
  selectedValue: string;
  val1: string;
  src: string;
  precesoSesion: ProcesoEddSession = new ProcesoEddSession();
  currentUser: DatosPersonales = new DatosPersonales();
  tipoEval: string = '';
  tipoResp: string = '';
  estatusEval: string = '';
  hiddenDescargar: boolean = false;
  disableAtras: boolean = false;
  disableFinalizar: boolean = false;

  MsgAlertFinaliza: boolean = false;

  //Constructor de la clase
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private service: EjecucionEdddemeritosService,
    private messageService: MessageService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private spinner: NgxSpinnerService
  ) { }

  //Metodo principal de la clase
  ngOnInit(): void {
    this.route.queryParams
      .subscribe(params => {
        this.tipoEval = params.tipoEval;
        this.tipoResp = params.tipoResp;
        this.estatusEval = params.estatusEval;
        this.logger.debug('url=>', params.idEvaluado);
      });

    this.precesoSesion = JSON.parse(sessionStorage.getItem('headerProceso'));
    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        this.currentUser = usr;
      });
    }
    this.obtenerPDFEdddemeritos();
  }

  obtenerPDF() {
    this.logger.debug('Obteniendo PDF');
    this.spinner.show();
    this.service.generarPDF(this.precesoSesion.claveProceso, this.precesoSesion.claveEvaluado).subscribe(data => {
      this.logger.debug('dat=>', this.logger.debug);
      const url = window.URL.createObjectURL(data);
      this.src = url;

      this.MsgAlertFinaliza = true;
      this.spinner.hide();
    })
  }

  obtenerPDFEdddemeritos() {
    this.logger.debug('Obteniendo PDF');
    this.spinner.show();
    this.service.generarReporteEdddemeritos(this.precesoSesion.claveProceso, this.precesoSesion.claveEvaluado).subscribe(data => {
      this.logger.debug('dat=>', this.logger.debug);
      const url = window.URL.createObjectURL(data);
      this.src = url;

      this.MsgAlertFinaliza = true;
      this.spinner.hide();
    })
  }

  redirectReporteEddFinalizado() {
    this.router.navigate(['/reporteEddfinalizado']);
  }

  showRegresar() {
    if (this.tipoResp === '1') {
      this.router.navigate(['/ejecucionEddambos'], { queryParams: { idEvaluado: this.precesoSesion.claveEvaluado, idProcesoVig: this.precesoSesion.claveProceso, tipoEval: this.tipoEval, unidadAdmin: this.precesoSesion.claveUnidad, tipoResp: this.tipoResp } });
    } else {
      this.router.navigate(['/ejecucionEddambos'], { queryParams: { idEvaluado: this.precesoSesion.claveEvaluado, idProcesoVig: this.precesoSesion.claveProceso, tipoEval: this.tipoEval, unidadAdmin: this.precesoSesion.claveEvaluado, tipoResp: this.tipoResp } });
    }
    //http://localhost:4200/ejecucionEddambos?idEvaluado=821524758&idProcesoVig=2020&tipoEval=2&unidadAdmin=20703001040300T&tipoResp=1
  }

  descargar() {
    this.spinner.show();
    const a = document.createElement('a');
    a.setAttribute('style', 'display:none');
    document.body.appendChild(a);
    a.href = this.src;
    a.download = 'Reporte_EDD.pdf';
    a.click();
    this.spinner.hide();
    this.router.navigate(['/evaluacionDesempeno']);
  }


  finalizarEDD() {
    console.log('Entrando a finalizarEDD');

    this.messageService.clear();
    this.messageService.add({
      key: 'FinalizarEv',
      sticky: true,
      severity: 'warn',
      summary: '',
      detail: ''
    });


    //Siempre guardamos los datos de finalizado this.bodyPreguntas
    // this.service.guardarRespuestasEdd().subscribe(data => {
    //   let est = this.estatusEval.split('|');
    //   if (this.tipoEval === '1' && est[1] === '0') {
    //     this.messageService.clear();
    //     this.messageService.add({
    //       key: 'FinalizarEv',
    //       sticky: true,
    //       severity: 'warn',
    //       summary: '',
    //       detail: 'Has concluido la Evaluación del Desempeño de la Persona Servidora Pública. Da clic al botón de REGRESAR si deseas volver a los criterios de evaluación.'
    //     });
    //   } else {
    //     this.hiddenDescargar = true;
    //     this.disableAtras = true;
    //     this.disableFinalizar = true;
    //   }
    // });
  }

  terminaEvaluacion() {
    this.router.navigate(['/evaluacionDesempeno']);
  }

}
