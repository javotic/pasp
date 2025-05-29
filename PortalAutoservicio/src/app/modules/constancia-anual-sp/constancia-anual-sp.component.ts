import { Component, OnInit } from '@angular/core';
import { ArchivosNomina } from 'src/app/models/archivos-nomina';
import { RecibosNominaService } from 'src/app/services/recibos-nomina.service';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { DatePipe } from '@angular/common';
import { NgxSpinnerService } from "ngx-spinner";
import { FormGroup, FormBuilder} from '@angular/forms';
import { UtilsService } from 'src/app/services/utils.service';
import * as moment from 'moment';
import { MessageService } from 'primeng/api';
import { environment } from 'src/environments/environment';
import { Combo } from 'src/app/models/combo';
import { ServidorComprobanteDTO } from 'src/app/models/servidor-comprobante-recibo';
import { forkJoin } from 'rxjs';
import { PagosService } from 'src/app/services/pagos.service';


@Component({
  selector: 'app-constancia-anual-sp',
  templateUrl: './constancia-anual-sp.component.html',
  styleUrls: ['./constancia-anual-sp.component.css'],
  providers: [DatePipe]
})
export class ConstanciaAnualSPComponent implements OnInit {

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
  idServidorPublico: string = '';
  nombreServidorPublico: string = '';
  descnotifica: string;
  index: number = 0;
  idcancelar: string;
  urlDat: string = `frwsr_LPSAUT_CONS${environment.HEDER_WS}.php`;

  mostrarInstrucciones: boolean = false;

  aniosSeleccion: Combo[] = [];

  selectedAnio: string;

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
    private spinner: NgxSpinnerService,
    private builder: FormBuilder,
    private messageService: MessageService,
    private utilsService: UtilsService,
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
    let anioActual = Number(moment(new Date()).format("YYYY"));
    this.selectedAnio = anioActual.toString();

    for(let i = anioActual; i >= 1996; i--){
      this.aniosSeleccion.push({label: i.toString(), value: i.toString()});
    }

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

  }

  obtenerLstConstanciasAnuales() {
    //Limpiamos la lista cada que realizamos una busqueda
    this.archivosSelect = [];

    this.SPRequerido = false;

    let validForm = true;

    if( this.claveSPBusqueda == '' && this.nombreSPBusqueda == ''){
      validForm = false;
      this.SPRequerido = true;
      console.log('Mostrando el mensaje requerido');
      console.log('claveSPBusqueda:' + this.claveSPBusqueda);
      console.log('nombreSPBusqueda:' + this.nombreSPBusqueda);

    }else{
      console.log('claveSPBusqueda:' + this.claveSPBusqueda);
      console.log('nombreSPBusqueda:' + this.nombreSPBusqueda);

      this.spinner.show();

      console.log('claveSPBusqueda:' + this.claveSPBusqueda);
      console.log('nombreSPBusqueda:' + this.nombreSPBusqueda);
  
      this.recibosNominaService.consultarConstanciasAnuales(this.selectedAnio + '/01/01', this.selectedAnio + '/12/31', this.claveSPBusqueda, this.nombreSPBusqueda, this.idServidorPublico, '2')
      .subscribe(data => {
        this.archivos = data.response;
        this.spinner.hide();
      }, error=>{
        console.log('No se han podido obtener los datos');
      })
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

  cambiaAnioSeleccion(event){
    console.log('event:' + event.value);
    this.selectedAnio = event.value;
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
      nombreFile = 'Constancia_Anual_' + itemNomina.idServidor + '_' + this.selectedAnio + '.pdf';
      let itemUserRecibo: ServidorComprobanteDTO = new ServidorComprobanteDTO();
      itemUserRecibo.CLAVESP = itemNomina.idServidor
      itemUserRecibo.FECHAINICIO = itemNomina.fechaInicio
      itemUserRecibo.FECHAFIN = itemNomina.fechaFin;

      reciboSP.push(itemUserRecibo);
    }else{
      nombreFile = 'Constancias_Anuales_' +  this.selectedAnio + '.zip' ;
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

    let servicePDF = this.pagosService.generarConstanciaAnual(reciboSP);
    
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
