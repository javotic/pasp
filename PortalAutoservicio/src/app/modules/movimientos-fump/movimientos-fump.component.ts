import { FileService } from './../../services/SIMP/file.service';
import { FumpService } from './../../services/SIMP/fumpService.service';
import { fumpModel } from './../../models/SIMP/fumpModel';
import { Component, OnInit } from '@angular/core';
import { NGXLogger } from 'ngx-logger';
import { FormGroup, FormBuilder } from '@angular/forms';
import { UtilsService } from 'src/app/services/utils.service';
import * as moment from 'moment';
import { MessageService } from 'primeng/api';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { BusgenericserviceService } from 'src/app/services/busgenericservice.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { DatePipe } from '@angular/common';
import { DomSanitizer } from '@angular/platform-browser';
import { HttpClient, HttpErrorResponse, HttpEvent, HttpEventType } from '@angular/common/http';
import { ServiceBus } from 'src/app/services/service-bus';
import { saveAs } from 'file-saver';


@Component({
  selector: 'app-movimientos-fump',
  templateUrl: './movimientos-fump.component.html',
  styleUrls: ['./movimientos-fump.component.css']
})

export class MovimientosFumpComponent implements OnInit {

  //#region Propiedades globales
  theFormGroup: FormGroup;
  idServidorPublico: string = '';
  nombreServidorPublico: string = '';
  mostrarInstrucciones: boolean = false;
  es: any;

  rangoAnios: string;
  fechaInicio: Date;
  fechaFin: Date;
  lsFumps: fumpModel[];
  //#endregion

  //#region Etiquetas de interfaz de usuario
  etiquetasUsuario = new Map();
  //#endregion

  //#region Constructores
  constructor(private logger: NGXLogger,
    private builder: FormBuilder,
    private authenticationService: AuthenticationServiceService,
    private messageService: MessageService,
    private utilsService: UtilsService,
    private servicegeneric: BusgenericserviceService,
    private datePipe: DatePipe,
    private spinner: NgxSpinnerService,
    private sanitizer: DomSanitizer,
    private http: HttpClient,
    private serviceBusProxy: ServiceBus,
    private fumpService: FumpService,
    private fileService: FileService) {
    this.theFormGroup = this.builder.group({
      startDate: ["", []],
      endDate: ["", []]
    });
    this.obtenerEtiquetasMovimientosFump();
  }

  /**
   * Metodo de inicializacion.
   *
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 24/09/2021.
   */
  ngOnInit(): void {
    this.inicializarFechas();

    if (this.authenticationService.currentUserValue) {
      this.authenticationService.currentUser.subscribe(usr => {
        this.idServidorPublico = usr.CLAVESERVIDOR;
        this.nombreServidorPublico = usr.NOMBRECOMPLETO;
      });
    }
  }
  //#endregion

  //#region Eventos
  /**
   * Rechazar proceso a traves de modal.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 24/09/2021.
   */
  rechazar(): void {
    this.messageService.clear('c');
  }

  /**
   * Buscar los FUMPS correspondientes.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 20/10/2021.
   */
  buscarFumps() {
    //Mostrar loader
    this.spinner.show();
    //Convertir fechas de inicio y fin
    let converFechaInicio = this.fechaInicio === undefined ? '' : this.datePipe.transform(this.fechaInicio, 'yyyy-MM-dd hh:mm:ss');
    let converFechaFin = this.fechaFin === undefined ? '' : this.datePipe.transform(this.fechaFin, 'yyyy-MM-dd hh:mm:ss');
    //Buscar FUMPS en servicio
    this.fumpService.consultarFumps(
      this.idServidorPublico,//Clave de servidor publico
      this.fechaInicio,//Fecha de inicio
      this.fechaFin//Fecha de fin
    ).subscribe(resultado => {
      this.lsFumps = resultado;//Asociar datos
      //Ocultar loader
      this.spinner.hide();
    });
  }

  //Funcion para descargar archivos
  onDownloadFile(nombreArchivo: string): void {
    console.log(nombreArchivo);
    //Mostrar loader
    this.spinner.show();

    let ws = "https://descargafump.edomex.gob.mx/fump/descargar/".concat(nombreArchivo);
    const a = document.createElement('a');
    a.setAttribute('style', 'display:none');
    document.body.appendChild(a);
    a.href = ws;
    a.download = nombreArchivo;
    a.click();

    //Mostrar loader
    this.spinner.show();
  }
  //#endregion

  //#region Funciones privadas
  /**
   * Obtener etiquetas de movimientos de FUMP.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 24/09/2021.
   */
  private obtenerEtiquetasMovimientosFump(): void {
    const usuario = this.utilsService
      .ObtenerEtiquetasPagina('/movimientosFUMP', 'español (México)')
      .subscribe(etiquetas => {
        Object.keys(etiquetas).map((key) => {
          this.etiquetasUsuario.set(key, etiquetas[key])
        });
      });
  }

  /**
   * Iniciar el calendario general.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 19/10/2021.
   */
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
   * Iniciar los valores de fechas del modulo.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 19/10/2021.
   */
  private inicializarFechas(): void {
    //Iniciar fechas de calendario con el dia de hoy
    this.fechaInicio = new Date();
    this.fechaFin = new Date();

    //Generar rango de anios para descarga, tomando 2018 como primer danio donde se encuentran los FUMPs
    this.rangoAnios = 2018 + ':' + moment(new Date()).format("YYYY");

    //Iniciar datos de calendario
    this.iniciaCalendario();
  }

  private reportarProgreso(httpEvent: HttpEvent<string[] | Blob>): void {
    switch (httpEvent.type) {
      case HttpEventType.Response:
        if (!(httpEvent.body instanceof Array)) {
          //Respuesta de descarga de archivos
          saveAs(new File(
            [httpEvent.body!],
            httpEvent.headers.get('File-Name')!,
            { type: `${httpEvent.headers.get('Content-Type')};charset=utf8` }
          ));
        }
        break;
      case HttpEventType.UploadProgress:
      case HttpEventType.DownloadProgress:
      case HttpEventType.ResponseHeader:
      default:
        console.log(httpEvent);
        break;
    }
  }
  //#endregion

}
