import { Component, OnInit } from '@angular/core';
import { ArchivosNomina } from 'src/app/models/archivos-nomina';
import { RecibosNominaService } from 'src/app/services/recibos-nomina.service';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { BusgenericserviceService } from 'src/app/services/busgenericservice.service';
import { DatePipe, formatDate } from '@angular/common';
import { NgxSpinnerService } from "ngx-spinner";
import { FormGroup, FormBuilder, RequiredValidator, Validators } from '@angular/forms';
import { UtilsService } from 'src/app/services/utils.service';
import * as moment from 'moment';
import { MessageService } from 'primeng/api';
import { ServiceBus } from 'src/app/services/service-bus';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { environment } from 'src/environments/environment';
import { Combo } from 'src/app/models/combo';
import { ServidorComprobanteDTO } from 'src/app/models/servidor-comprobante-recibo';
import { forkJoin } from 'rxjs';
import { PagosService } from 'src/app/services/pagos.service';


@Component({
  selector: 'app-consulta-pagos-sp',
  templateUrl: './consulta-pagos-sp.component.html',
  styleUrls: ['./consulta-pagos-sp.component.css'],
  providers: [DatePipe]
})
export class ConsultaPagosSPComponent implements OnInit {

  //Variables Globales de la clase
  archivos: ArchivosNomina[];
  archivosSelect: ArchivosNomina[] = [];
  currentUser: DatosPersonales = new DatosPersonales();
  maxDate: Date;
  minDate: Date;
  fechaInicio: Date;
  fechaFin: Date;

  claveSPBusqueda: string = '';
  nombreSPBusqueda: string = '';

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
  finicioffin :string;
  finicioreq : string;
  noseencontraron : string;
  rangoAnios: string;
  idServidorPublico: string = '';
  nombreServidorPublico: string = '';
  descnotifica: string;
  index: number = 0;
  idcancelar: string;
  urlDat: string = `frwsr_LPSAUT_CONS${environment.HEDER_WS}.php`;


  mostrarInstrucciones: boolean = false;

  quincenas: Combo[] = [{label: 'Primera', value: '1'},
  {label: 'Segunda', value: '2'}];

  selected15Inicio = '1';
  selected15Fin = '1';

  //Elementos requeridos
  fcInicioRequerido: boolean = false;
  fcFinRequerido: boolean = false;
  SPRequerido: boolean = false;

  isMultiple: boolean = false;

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
    private recibosNominaService: RecibosNominaService,
    private pagosService: PagosService) {
    this.maxDate = new Date();
    this.minDate = new Date();
    this.minDate.setMonth(new Date().getMonth() - 24)
    this.theFormGroup = this.builder.group({
      startDate: ["", []],
      endDate: ["", []],
      claveSPBusqueda: ["", []],
      nombreSPBusqueda: ["", []],
      isMultiple:[false,[]]
    });
    this.obtenerEtiquetasRecibosNomina();
  }


  //Metodo principal de la clase
  ngOnInit(): void {
    let anio = moment(new Date()).format("YYYY");
    this.rangoAnios =  (Number(anio) - 1) + ':' + anio;
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
   // this.obtenerNotificaciones();


  }
  /**
   * Obtiene los recibos de nomina del servidor logueado
   */
  obtenerRecibosNomina() {
    //Limpiamos la lista cada que realizamos una busqueda
    this.archivosSelect = [];

    this.fcInicioRequerido = false;
    this.fcFinRequerido = false;
    this.SPRequerido = false;

    //Validamos si se proporcionario todos los datos requeridos
    let converFechaInicio = this.fechaInicio === undefined ? '' : this.datePipe.transform(this.fechaInicio, 'yyyy-MM-dd hh:mm:ss');
    let converFechaFin = this.fechaFin === undefined ? '' : this.datePipe.transform(this.fechaFin, 'yyyy-MM-dd hh:mm:ss');
    this.logger.debug('Fecha: ', converFechaInicio);
    this.logger.debug('Fecha: ', converFechaFin);

    let validForm = true;

    if(converFechaInicio == ''){
      validForm = false;
      this.fcInicioRequerido = true;
    }

    if(converFechaFin == ''){
      validForm = false;
      this.fcFinRequerido = true;
    }

    if( this.claveSPBusqueda == '' && this.nombreSPBusqueda == ''){
      validForm = false;
      this.SPRequerido = true;
      console.log('Mostrando el mensaje requerido');
      console.log('claveSPBusqueda:' + this.claveSPBusqueda);
      console.log('nombreSPBusqueda:' + this.nombreSPBusqueda);
    }else{
      console.log('claveSPBusqueda:' + this.claveSPBusqueda);
      console.log('nombreSPBusqueda:' + this.nombreSPBusqueda);
    }



    if(!this.theFormGroup.valid){
      
    }else{

    this.spinner.show();

   /*
    let datas = {
      "funcion": "consultarRecibosNomina",
      "IDSERVIDORPUBLICO": this.currentUser.CLAVESERVIDOR,
      "FECHAINICIO": converFechaInicio,
      "FECHAFIN": converFechaFin
    };

    */

    console.log('claveSPBusqueda:' + this.claveSPBusqueda);
    console.log('nombreSPBusqueda:' + this.nombreSPBusqueda);
    let dateInicio: Date = new Date(converFechaInicio);
    let dateFin: Date = new Date(converFechaFin);
    
          // Fecha Inicio
          if(this.selected15Inicio == "1"){
            dateInicio.setDate(15);
          }else{
            var ultimoDia = new Date(dateInicio.getFullYear(), dateInicio.getMonth() + 1, 0);
            dateInicio.setDate(ultimoDia.getDate());
          }

          //Fecha Fin
          if(this.selected15Fin == "1"){
            dateFin.setDate(15);
          }else{
            var ultimoDia = new Date(dateFin.getFullYear(), dateFin.getMonth() + 1, 0);
            dateFin.setDate(ultimoDia.getDate());
          }

          converFechaInicio = formatDate(dateInicio, 'yyyy-MM-dd HH:mm:ss', 'es_MX');
          converFechaFin = formatDate(dateFin, 'yyyy-MM-dd HH:mm:ss', 'es_MX');;

    this.recibosNominaService.consultarComprobantes(converFechaInicio, converFechaFin, this.claveSPBusqueda, this.nombreSPBusqueda, this.idServidorPublico, '2')
    .subscribe(data => {
      //let vari = <RespuestaApi<ArchivosNomina>>data;
      //this.archivos = <ArchivosNomina[]>vari.response;
      this.archivos = data.response;
      this.spinner.hide();
    })

  }
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

  cambiar15Inicio(event){
    console.log('event:' + event.value);
    this.selected15Inicio = event.value;
  }

  cambiar15Fin(event){
    console.log('event:' + event.value);
    this.selected15Fin = event.value;
  }


  activaMultiple(){
    console.log('Activando multiples');
    this.nombreSPBusqueda = '';
  }


  descargarFormato(itemNomina: ArchivosNomina, isMasivo: boolean){
    console.log("Descargando archivo");

    //Administramos los comprobante selecionado para su envio
    let reciboSP: ServidorComprobanteDTO[] = [];
    let nombreFile = '';
    if(isMasivo == false){
      nombreFile = 'Quincenal_' + itemNomina.idServidor + '_' + itemNomina.fechaInicio + '.pdf';
      let itemUserRecibo: ServidorComprobanteDTO = new ServidorComprobanteDTO();
      itemUserRecibo.CLAVESP = itemNomina.idServidor
      itemUserRecibo.FECHAINICIO = itemNomina.fechaInicio
      itemUserRecibo.FECHAFIN = itemNomina.fechaFin;

      reciboSP.push(itemUserRecibo);
    }else{
      nombreFile = 'PagosQuincelanes_' +  moment(new Date()).format("YYYY-MM-DD") + '.zip' ;
      this.archivosSelect.forEach(item =>{
        let itemUserRecibo: ServidorComprobanteDTO = new ServidorComprobanteDTO();
        itemUserRecibo.CLAVESP = item.idServidor
        itemUserRecibo.FECHAINICIO = item.fechaInicio
        itemUserRecibo.FECHAFIN = item.fechaFin;
  
        reciboSP.push(itemUserRecibo);
      });
    }


    this.spinner.show();
    const a = document.createElement('a');

    let servicePDF = this.pagosService.generarPagoQuincenal(reciboSP);
    
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
    }, error=>{
      this.spinner.hide();
      console.log("No se ha podido descargar el formato.");
    });


  }

}
