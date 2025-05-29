import { Component, OnInit } from '@angular/core';
import { ArchivosNomina } from 'src/app/models/archivos-nomina';
import { RecibosNominaService } from 'src/app/services/recibos-nomina.service';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { BusgenericserviceService } from 'src/app/services/busgenericservice.service';
import { DatePipe, formatDate } from '@angular/common';
import { NgxSpinnerService } from "ngx-spinner";
import { FormGroup, FormBuilder } from '@angular/forms';
import { UtilsService } from 'src/app/services/utils.service';
import * as moment from 'moment';
import { MessageService } from 'primeng/api';
import { ServiceBus } from 'src/app/services/service-bus';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { Combo } from 'src/app/models/combo';
import { ServidorComprobanteDTO, ServidorComprobanteRecibo } from 'src/app/models/servidor-comprobante-recibo';
import { forkJoin } from 'rxjs';


@Component({
  selector: 'app-recibos-nomina',
  templateUrl: './recibos-nomina.component.html',
  styleUrls: ['./recibos-nomina.component.css'],
  providers: [DatePipe]
})
export class RecibosNominaComponent implements OnInit {

  //Variables Globales de la clase
  archivos: ArchivosNomina[];
  archivosSelect: ArchivosNomina[] = [];
  currentUser: DatosPersonales = new DatosPersonales();
  minDate: Date;
  maxDate: Date;
  fechaInicio: Date;
  fechaFin: Date;
  es: any;
  theFormGroup: FormGroup;
  ttlrecibosnomina: string;
  lbltooltipfechas: string;
  lblfiltrofechas: string;
  lblfechainicio: string;
  lblfechafin: string;
  lblfecha: string;
  btnbuscar: string;
  lblperiodo: string;
  lblnombre: string;
  finicioffin: string;
  finicioreq: string;
  noseencontraron: string;
  rangoAnios: string;
  idServidorPublico: string = '';
  nombreServidorPublico: string = '';
  descnotifica: string;
  index: number = 0;
  idcancelar: string;
  urlDat: string = `frwsr_LPSAUT_CONS${environment.HEDER_WS}.php`;


  mostrarInstrucciones: boolean = false;

  quincenas: Combo[] = [{ label: 'Primera', value: '1' },
  { label: 'Segunda', value: '2' }];

  selected15Inicio = '1';
  selected15Fin = '1';

  //Constructor de la clase
  constructor(
    //private service: RecibosNominaService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private servicegeneric: BusgenericserviceService,
    private datePipe: DatePipe,
    private spinner: NgxSpinnerService,
    private builder: FormBuilder,
    private messageService: MessageService,
    private utilsService: UtilsService,
    private serviceBusProxy: ServiceBus,
    private recibosNominaService: RecibosNominaService) {
    this.maxDate = new Date();
    this.minDate = new Date();
    this.minDate.setMonth(new Date().getMonth() - 24);
    this.theFormGroup = this.builder.group({
      startDate: ["", []],
      endDate: ["", []]
    });
    this.obtenerEtiquetasRecibosNomina();
  }


  //Metodo principal de la clase
  ngOnInit(): void {
    let anio = moment(new Date()).format("YYYY");

    this.fechaFin = new Date()

    this.fechaInicio = new Date();
    this.fechaInicio.setMonth(new Date().getMonth() - 6);
    // Number(anio) -1+':'+ anio //
    this.rangoAnios = Number(anio) - 1 + ':' + anio; //'1995:' + anio;
    this.iniciaCalendario();
    //validación de usuario en sesión 
    if (this.authenticationService.currentUserValue) {
      this.logger.debug('Usuario logueado en Home');
      this.authenticationService.currentUser.subscribe(usr => {
        this.currentUser = usr;
        this.idServidorPublico = usr.CLAVESERVIDOR;
        this.nombreServidorPublico = usr.NOMBRECOMPLETO;
      });
    } else {
      this.logger.debug('Usuario no logueado en Home');


    }

    //Obtiene notificaciones en base al idServidorPublico
    //this.obtenerNotificaciones();
    this.obtenerRecibosNomina();

  }
  /**
   * Obtiene los recibos de nomina del servidor logueado
   */
  obtenerRecibosNomina() {
    //Limpiamos la lista cada que realizamos una busqueda
    this.archivosSelect = [];

    this.spinner.show();
    let converFechaInicio = this.fechaInicio === undefined ? '' : this.datePipe.transform(this.fechaInicio, 'yyyy-MM-dd hh:mm:ss');
    let converFechaFin = this.fechaFin === undefined ? '' : this.datePipe.transform(this.fechaFin, 'yyyy-MM-dd hh:mm:ss');
    this.logger.debug('Fecha: ', converFechaInicio);
    this.logger.debug('Fecha: ', converFechaFin);
    /*
     let datas = {
       "funcion": "consultarRecibosNomina",
       "IDSERVIDORPUBLICO": this.currentUser.CLAVESERVIDOR,
       "FECHAINICIO": converFechaInicio,
       "FECHAFIN": converFechaFin
     };
 
     */
    let dateInicio: Date = new Date(converFechaInicio);
    let dateFin: Date = new Date(converFechaFin);

    // Fecha Inicio
    if (this.selected15Inicio == "1") {
      dateInicio.setDate(15);
    } else {
      var ultimoDia = new Date(dateInicio.getFullYear(), dateInicio.getMonth() + 1, 0);
      dateInicio.setDate(ultimoDia.getDate());
    }

    //Fecha Fin
    if (this.selected15Fin == "1") {
      dateFin.setDate(15);
    } else {
      var ultimoDia = new Date(dateFin.getFullYear(), dateFin.getMonth() + 1, 0);
      dateFin.setDate(ultimoDia.getDate());
    }

    converFechaInicio = formatDate(dateInicio, 'yyyy-MM-dd HH:mm:ss', 'es_MX');
    converFechaFin = formatDate(dateFin, 'yyyy-MM-dd HH:mm:ss', 'es_MX');;

    this.recibosNominaService.consultarComprobantes(converFechaInicio, converFechaFin, '', '', this.idServidorPublico, '1').subscribe(data => {
      //let vari = <RespuestaApi<ArchivosNomina>>data;
      //this.archivos = <ArchivosNomina[]>vari.response;
      this.archivos = data.response;
      this.spinner.hide();
    })


  }

  iniciaCalendario() {
    this.es = {
      firstDayOfWeek: 1,
      dayNames: ["domingo", "lunes", "martes", "miércoles", "jueves", "viernes", "sábado"],
      dayNamesShort: ["dom", "lun", "mar", "mié", "jue", "vie", "sáb"],
      dayNamesMin: ["Dom", "Lun", "Mar", "Mie", "Jue", "Vie", "Sab"],
      monthNames: ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"],
      monthNamesShort: ["Ene", "Feb", "Mar", "Abr", "May", "Jun", "Jul", "Ago", "Sep", "Oct", "Nov", "Dic"],
      today: 'Hoy',
      clear: 'Borrar'
    }
  }

  /**
 * Obtiene las etiquetas de la pantalla
 */
  obtenerEtiquetasRecibosNomina() {

    const usuario = this.utilsService.ObtenerEtiquetasPagina('/recibosNomina', 'español (México)').subscribe(data => {
      this.logger.debug(data);
      Object.keys(data).map((key) => {
        if (key === 'ttl.recibosnomina') {
          this.ttlrecibosnomina = data[key];
        }
        if (key === 'lbl.tooltipfechas') {
          this.lbltooltipfechas = data[key];
        }
        if (key === 'lbl.filtrofechas') {
          this.lblfiltrofechas = data[key];
        }
        if (key === 'lbl.fechainicio') {
          this.lblfechainicio = data[key];
        }
        if (key === 'lbl.fechafin') {
          this.lblfechafin = data[key];
        }
        if (key === 'lbl.fecha') {
          this.lblfecha = data[key];
        }
        if (key === 'btn.buscar') {
          this.btnbuscar = data[key];
        }
        if (key === 'lbl.periodo') {
          this.lblperiodo = data[key];
        }
        if (key === 'lbl.nombre') {
          this.lblnombre = data[key];
        }

        if (key === 'lbl.finicioffin') {
          this.finicioffin = data[key];
        }
        if (key === 'lbl.finicioreq') {
          this.finicioreq = data[key];
        }
        if (key === 'lbl.noseencontraron') {
          this.noseencontraron = data[key];
        }

      });
    });


  }


  rechazar() {
    this.messageService.clear('c');
  }


  /**********************Notificaciones Etiquetas************************************** */
  //Metodo Obtener notificaciones
  obtenerNotificaciones() {
    console.log('Dentro2 de obtenerNotificaciones');


    this.utilsService.obtenerNotificaciones(this.idServidorPublico).subscribe(data => {

      data.forEach(obj => {

        this.descnotifica = obj[0];
        this.idcancelar = obj[1];


        if (this.index <= 4) {
          this.index++;

          this.messageService.add({
            key: 'notifi', severity: 'warn', summary:
              'Tienes Notificaciones Por Revisar', detail: this.descnotifica
          });

          /*Cancelando Notificaciones*/
          this.utilsService.cancelarNotificaciones(this.idcancelar).subscribe();
        }


        console.log('-------------------');

      });

    });

    console.log('Saliendo2 de obtenerNotificaciones');


  }



  cambiar15Inicio(event) {
    console.log('event:' + event.value);
    this.selected15Inicio = event.value;
  }

  cambiar15Fin(event) {
    console.log('event:' + event.value);
    this.selected15Fin = event.value;
  }

  descargarFormato(itemNomina: ArchivosNomina, isMasivo: boolean) {
    //Si ya se cancelo el pago, no se permite la descarga
    if (itemNomina.cancelado) {
      return;
    }

    //Administramos los comprobante selecionado para su envio
    let reciboSP: ServidorComprobanteDTO[] = [];
    let nombreFile = '';
    if (isMasivo == false) {
      nombreFile = itemNomina.idServidor + '_' + itemNomina.fechaInicio + '.pdf';
      let itemUserRecibo: ServidorComprobanteDTO = new ServidorComprobanteDTO();
      itemUserRecibo.CLAVESP = itemNomina.idServidor
      itemUserRecibo.FECHAINICIO = itemNomina.fechaInicio
      itemUserRecibo.FECHAFIN = itemNomina.fechaFin;

      reciboSP.push(itemUserRecibo);
    } else {
      nombreFile = 'Recibos_' + moment(new Date()).format("YYYY-MM-DD") + '.zip';
      this.archivosSelect.forEach(item => {
        let itemUserRecibo: ServidorComprobanteDTO = new ServidorComprobanteDTO();
        itemUserRecibo.CLAVESP = item.idServidor
        itemUserRecibo.FECHAINICIO = item.fechaInicio
        itemUserRecibo.FECHAFIN = item.fechaFin;

        reciboSP.push(itemUserRecibo);
      });
    }


    this.spinner.show();
    const a = document.createElement('a');

    let servicePDF = this.recibosNominaService.generarComprobantes(reciboSP);

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
        a.download = nombreFile;
        a.click();
        this.spinner.hide();
      }
    });


  }



}
