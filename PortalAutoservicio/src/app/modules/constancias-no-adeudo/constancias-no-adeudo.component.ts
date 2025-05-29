
import { Component, OnInit, ViewChild } from '@angular/core';
import { ConstanciaadeudoService } from 'src/app/services/constanciaadeudo.service';
import { ConstanciaAdeudo } from 'src/app/models/constancia-adeudo';
import { Table } from 'primeng/table/table';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import { UtilsService } from 'src/app/services/utils.service';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import * as moment from 'moment';
import { switchMap } from 'rxjs/operators';
import { NgxSpinnerService } from 'ngx-spinner';
import { DatospersonalesService } from 'src/app/services/datospersonales.service';
import { ConstanciaNoAdeudoModel } from 'src/app/models/constanciaNoAdeudoModel';
import { SolicitudConstanciaNoAdeudoService } from 'src/app/services/solicitud-constancia-no-adeudo.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-constancias-no-adeudo',
  templateUrl: './constancias-no-adeudo.component.html',
  styleUrls: ['./constancias-no-adeudo.component.css']
})
export class ConstanciasNoAdeudoComponent implements OnInit {
  //#region Atributos de clase
  idServidorPublico: string = '';
  nombreServidorPublico: string = '';
  nombrePlaza: string = '';
  constanciaNoAdeudoForm: FormGroup;
  etiquetas = new Map();
  mostrarInstrucciones: boolean = false;
  mostrarDialogoNuevaSolicitud: boolean = false;
  lsConstanciaNoAdeudo: ConstanciaNoAdeudoModel[];
  comentario: String;
  //Gestion de mensajes 
  tipoMensaje: string;
  colorIcono: string = 'green';

  //Revisar usabilidad
  constanciaAdeudos: ConstanciaAdeudo[] = [];
  map = new Map<String, String>();
  cols: any[];
  @ViewChild('dt') table: Table;
  items: MenuItem[];
  displayDialog: boolean;
  habilitaBotonNuevaSolicitud: boolean;
  //#endregion

  //#region Constructores
  constructor(
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private _formBuilder: FormBuilder,
    private solicitudConstanciaNoAdeudoService: SolicitudConstanciaNoAdeudoService,

    //Revisar usabilidad
    private constanciaadeudoService: ConstanciaadeudoService,
    private utilsService: UtilsService,
    private messageService: MessageService,
    private datospersonalesService: DatospersonalesService,
    private spinner: NgxSpinnerService,) { }

  ngOnInit(): void {
    //Cargar datos de servidor publico logueado
    this.cargaDatosServidorPublico();

    //Obtener etiquetas de pagina
    //No existen etiquetas utilizablezes, se comenta para ligerar carga
    //this.obtenerEtiquetas();

    //Inicializar formulario
    this.inicializarFormulario();

    //Obtener listado de solicitudes realizadas
    this.obtenerSolicitudesRealizadas();

    //Definir columnas de tabla
    this.definirColumnas();
  }
  //#endregion

  //#region Eventos
  /**
   * Validar componente para generacion de nueva solicitud 
   * de constancia de no adedudo.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 16/12/2021.
   */
  btnNuevaSolicitudClick() {
    //Bandera de procesamiento
    let puedeProcesar = true;
    //Si existe un elemento con estatus de "PENDIENTE" se impide procesar
    this.lsConstanciaNoAdeudo.forEach(e => { if (e.estatus === 'Pendiente') puedeProcesar = false; });

    //Si no se puede procesar, se muestra mensaje de error
    if (!puedeProcesar) {
      this.messageService.clear();
      this.tipoMensaje = "pi-times";
      this.colorIcono = "red";

      this.messageService.add({
        key: 'c',
        severity: 'error',
        summary: 'Error',
        //detail: resultado.response.toString()
        detail: 'Ya cuenta con una solicitud pendiente de resolución, favor de validar.'
      });
    } else {
      //Caso contrario, se muestra dialogo de captura de solicitud
      this.mostrarDialogoNuevaSolicitud = true;
    }
  }

  /**
   * Generar una nueva solicitud de constancia de no adeudo.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 20/11/2021.
   */
  btnAceptarSolicitudClick() {
    //Mostrar loader
    this.spinner.show();
    this.solicitudConstanciaNoAdeudoService.insertarSolicitudHistorialLaboral(
      this.idServidorPublico,
      this.comentario
    ).subscribe(resultado => {
      //Mostrar mensaje de resultado
      this.messageService.clear();
      this.tipoMensaje = "pi-check";
      this.colorIcono = "green";
      this.messageService.add({
        key: 'c',
        severity: 'success',
        summary: 'Correcto',
        detail: resultado.response.toString()
        // detail: 'La solicitud de constancia de no adeudo se ha generado correctamente'
      });

      //Cerrar popup
      this.mostrarDialogoNuevaSolicitud = false;

      //Volver a obtener listado de solicituds
      this.obtenerSolicitudesRealizadas();

      //Ocultar loader
      this.spinner.hide();
    });
  }

  /**
   * Descargar documento de Constancia de no adeudo.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 05/01/2022.
  */
  btnDescargarConstancia() {
    //Mostrar spinner de procesamiento
    this.spinner.show();

    //Obtener ultima solicitud de constancia de no adeudo
    let ultimaSolicitud = this.lsConstanciaNoAdeudo[this.lsConstanciaNoAdeudo.length - 1];

    //Crear elemento de descarga
    const a = document.createElement('a');
    //Consumir servicio de descarga de historial laboral
    let servicePDF = this.solicitudConstanciaNoAdeudoService.
      obtenerConstanciaNoAdeudo(this.idServidorPublico, ultimaSolicitud.folio);
    //Generar documento de descarga
    forkJoin([servicePDF]).subscribe(results => {
      if (results !== undefined) {
        a.setAttribute('style', 'display:none');
        document.body.appendChild(a);
        a.href = window.URL.createObjectURL(results[0]);
        a.download = "constancia.pdf";
        a.click();
        //Ocultar spinner de procesamiento
        this.spinner.hide();
      }
    }, error => {
      //Ocultar spinner de procesamiento
      this.spinner.hide();
      //Generar log de error en consola
      console.error(error);
    });
  }
  //#endregion

  //#region Funciones privadas
  /**
   * Cargar datos del servidor publico logueado.
   * @author Desconocido
   * @version 1.0, 14/12/2021.
  */
  private cargaDatosServidorPublico() {
    if (this.authenticationService.currentUserValue) {
      this.authenticationService.currentUser.subscribe(usr => {
        this.idServidorPublico = usr.CLAVESERVIDOR;
        this.nombreServidorPublico = usr.NOMBRECOMPLETO;
      });
    }

    this.datospersonalesService.cargaDatosServidorPublico(
      this.idServidorPublico
    ).subscribe(data => {
      if (data.code === '200') {
        this.nombrePlaza = data.response.NOMBREPLAZA;
      } else {
        this.nombrePlaza = '';
      }
    });
  }

  /**
   * Inicializar formulario.
   * @author Desconocido
   * @version 1.0, Desconocido.
   * @version 2.0, 14/11/2021, Javier Alvarado Rodriguez.
  */
  private inicializarFormulario() {
    let fecha = moment(new Date()).format('MMMM Do YYYY');
    this.constanciaNoAdeudoForm = this._formBuilder.group({
      'idServidorPublico': new FormControl(this.idServidorPublico),
      'nombreServidorPublico': new FormControl(this.nombreServidorPublico),
      'plaza': new FormControl(this.nombrePlaza),
      'fecha': new FormControl(fecha),
      'justificacion': new FormControl('', Validators.required)
    });
  }

  /**
   * Definir columnas de tabla principal.
   * @author Desconocido
   * @version 1.0, Desconocido.
   * @version 2.0, 14/11/2021, Javier Alvarado Rodriguez.
  */
  private definirColumnas() {
    this.cols = [
      { field: 'FECHASOLICITUD', header: 'Fecha' },
      { field: 'GESTORADMINISTRATIVO', header: 'Gestor Administrativo' },
      { field: 'RESPUESTA', header: 'Comentarios' },
      { field: 'ESTATUS', header: 'Estatus' },
      { field: '', header: 'Archivo' }
    ];
  }

  /**
   * Obtener etiquetas de pagina.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 15/12/2021.
   */
  private obtenerEtiquetas(): void {
    this.utilsService.ObtenerEtiquetasPagina
      ('/constanciaNoAdeudo',
        'español (México)'
      ).subscribe(etiquetas => {
        Object.keys(etiquetas).map((key) => {
          this.etiquetas.set(key, etiquetas[key])
        });
      });
  }

  /**
   * Obtener listado de solicitudes realizadas por el servidor publico.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 16/12/2021.
   */
  private obtenerSolicitudesRealizadas(): void {
    this.solicitudConstanciaNoAdeudoService.obtenerSolicitudesRealizadas(
      this.idServidorPublico,
      this.idServidorPublico,
      1
    ).subscribe(
      resultado => {
        this.lsConstanciaNoAdeudo = resultado.response;
      }
      , error => {
        console.error(error);
      });
  }
  //#endregion

  //#region Funciones publicas
  //#endregion

  //#region Funciones anteriores
  obtenerConstanciaNoAdeudo() {
    this.spinner.show();
    this.constanciaadeudoService.obtenerConstanciaNoAdeudo(this.idServidorPublico)
      .subscribe((data) => {
        // console.log('obtenerConstanciaNoAdeudo=>' + JSON.stringify(data));
        this.spinner.hide();
        // this.constanciaAdeudos = data.response;
        for (let index = 0; index < data.response.length; index++) {
          let x = new ConstanciaAdeudo();
          let obj = data.response[index];
          var res = obj.ESTATUS.split("|");
          x.FECHASOLICITUD = obj.FECHASOLICITUD;
          x.GESTORADMINISTRATIVO = obj.GESTORADMINISTRATIVO;
          x.RESPUESTA = obj.RESPUESTA;
          x.ESTATUS = res[1];
          x.IDESTATUS = parseInt(res[0]);
          if (res[0] === '0') {
            this.habilitaBotonNuevaSolicitud = true;
          }
          this.constanciaAdeudos.push(x);
        }
      });
  }

  showSaveDialog() {
    this.displayDialog = true;
    let fecha = moment(new Date()).locale('es').format('Do-MMMM-YYYY');
    let res = fecha.replace("º", "");
    this.constanciaNoAdeudoForm = this._formBuilder.group({
      'idServidorPublico': new FormControl(this.idServidorPublico),
      'nombreServidorPublico': new FormControl(this.nombreServidorPublico),
      'plaza': new FormControl(this.nombrePlaza),
      'fecha': new FormControl(res),
      'justificacion': new FormControl('', Validators.required)
    });
  }

  confirmacionConstanciaNoAdeudo() {
    this.messageService.clear();
    this.messageService.add({ key: 'c', sticky: true, severity: 'warn', summary: '¿Estás seguro?', detail: 'Confirmar para continuar' });
  }

  solicitarConstanciaAdeudo() {
    this.spinner.show();
    this.constanciaAdeudos = [];
    this.constanciaadeudoService.
      insertaSolicitudConstanciaNoAdeudo(this.idServidorPublico, this.constanciaNoAdeudoForm.value['justificacion']).pipe(switchMap(() => {
        return this.constanciaadeudoService.obtenerConstanciaNoAdeudo(this.idServidorPublico)
      })).subscribe(data => {
        for (let index = 0; index < data.response.length; index++) {
          let x = new ConstanciaAdeudo();
          let obj = data.response[index];
          var res = obj.ESTATUS.split("|");
          x.FECHASOLICITUD = obj.FECHASOLICITUD;
          x.GESTORADMINISTRATIVO = obj.GESTORADMINISTRATIVO;
          x.RESPUESTA = obj.RESPUESTA;
          x.ESTATUS = res[1];
          x.IDESTATUS = parseInt(res[0]);
          if (res[0] === '0') {
            this.habilitaBotonNuevaSolicitud = true;
          }
          this.constanciaAdeudos.push(x);
        }
        this.messageService.clear('c');
        this.messageService.add({ key: 'd', severity: 'success', summary: "Resultado", detail: "La operación se realizó correctamente." });
        this.displayDialog = false;
        this.spinner.hide();
      }, err => {
        this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrio un error" });
        this.spinner.hide();
      });
  }

  rechazar() {
    this.messageService.clear('c');
  }

  no() {
    this.displayDialog = false;
  }
  //#endregion
}