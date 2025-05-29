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
import { DetalleeddHistoricoService } from 'src/app/services/detalle-edd-historico.service';
import { Router, ActivatedRoute, } from '@angular/router';
import { servidoresPublicosEddUa } from 'src/app/models/servidores-publicos-eddua';
import { ProcesoEddSession } from 'src/app/models/proceso-edd-session';
import { EjecucionEdddemeritosService } from 'src/app/services/ejecucionedd-demeritos.service';
import { forkJoin } from 'rxjs';
import { RespuestaServiceM4 } from 'src/app/repuesta/respuesta-service-m4';
import { DatosPersonales } from 'src/app/models/datos-personales';

@Component({
  selector: 'app-detalle-edd-ua-historico',
  templateUrl: './detalle-edd-ua-historico.component.html',
  styleUrls: ['./detalle-edd-ua-historico.component.css']
})

export class DetalleEddUaHistoricoComponent implements OnInit {

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


  //Constructor de la clase
  constructor(
    private service: DetalleeddHistoricoService,
    private route: ActivatedRoute,
    private router: Router,
    private messageService: MessageService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private spinner: NgxSpinnerService,
    private serviceEdd: EjecucionEdddemeritosService) { }


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

  rechazar() {
    this.messageService.clear('c');
  }

  no() {
    this.displayDialog = false;
  }

  showRegresar() {
    this.router.navigate(['/detalleEddHistorico'], { queryParams: { proVig: this.idProce } });
  }

  /**
   * obtiene los servidores publicos a evaluar de la unidad
   * administrativa seleccionada anteriormente
   */
  getDetalle() {
    this.spinner.show();
    this.service.DetalleProcesoEdduaHistorico(this.idServidorPublico, this.idAdmon, this.idProce).subscribe(e => {
      let vari = <RespuestaServiceM4<DetalleProcesoEddua>>e;
      var myJSON = JSON.stringify(vari.response);
      let listDetalle = <DetalleProcesoEddua>JSON.parse(myJSON)
      this.movimidetalleproua = listDetalle;

      this.logger.debug('Detalle 2=>', this.movimidetalleproua);
      this.spinner.hide();
      this.evaluarSoloDemeritos();
    });
  }

  /**
   * cerrar dialogo
   */
  cerrarDialog() {
    this.messageService.clear('endDem');
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

    let servicePDF = this.serviceEdd.generarPDFHT(tipo.IDPROCESOVIGENTE, tipo.CLAVESERVIDORPUBLICO);
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
    }, error => {
      this.spinner.hide();
      this.messageService.clear();
      this.messageService.add( { key: 'notifi' ,
                                sticky: true ,
                                severity: 'warn' ,
                                summary: 'Error' ,
                                detail: 'No se pudo descargar el reporte, inténtelo más tarde.' });
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
    this.logger.debug('disable demeritos',this.soloDemeritos);
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
}


