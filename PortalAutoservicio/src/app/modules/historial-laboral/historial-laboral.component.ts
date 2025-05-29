import { Component, OnInit, ViewChild } from '@angular/core';
import { Table } from 'primeng/table/table';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { MenuItem, MessageService } from 'primeng/api';
import { ConstanciaHistorialLaboral } from 'src/app/models/constancia-historial-laboral';
import { HistoriallaboralService } from 'src/app/services/historiallaboral.service';
import { SolicitudconstanciaService } from 'src/app/services/solicitudconstancia.service';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { UtilsService } from 'src/app/services/utils.service';
import { NgxSpinnerService } from 'ngx-spinner';
import { DatePipe } from '@angular/common';
import { HistorialLaboralEtiquetas } from 'src/app/etiquetas/historial-laboral-etiquetas';
import { DatospersonalesService } from 'src/app/services/datospersonales.service';
import { Combo } from 'src/app/models/combo';
import { ThrowStmt } from '@angular/compiler';
import { HistorialLaboralModel } from 'src/app/models/historialLaboralModel';
import { SolicitudHistorialLaboralService } from 'src/app/services/solicitud-historial-laboral.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-historial-laboral',
  templateUrl: './historial-laboral.component.html',
  styleUrls: ['./historial-laboral.component.css']
})
export class HistorialLaboralComponent implements OnInit {

  //#region Propiedades globales
  theFormGroup: FormGroup;
  idServidorPublico: string = '';
  nombreServidorPublico: string = '';
  mostrarInstrucciones: boolean = false;
  mostrarDialogoNuevaSolicitud: boolean = false;
  es: any;
  lsMotivosSolicitud: Combo[];
  motivoSeleccionado: Combo;
  lsSolicitudesHistorial: HistorialLaboralModel[];
  comentario: String;


  displayDialog: boolean;
  cols: any[];
  @ViewChild('dt') table: Table;
  items: MenuItem[];
  constanciaHistorialForm: FormGroup;
  constanciaHistorialLaboral: ConstanciaHistorialLaboral[] = [];;
  consultaHistorialEtiquetas: HistorialLaboralEtiquetas = new HistorialLaboralEtiquetas();
  habilitaBotonNuevaSolicitud: boolean;
  nombrePlaza: string = '';
  tipoMensaje: string;
  colorIcono: string = 'green';
  //#endregion

  //#region Etiquetas de interfaz de usuario
  etiquetas = new Map();
  //#endregion

  //#region Constructores
  constructor(
    private messageService: MessageService,
    private fb: FormBuilder,
    private spinner: NgxSpinnerService,
    private datePipe: DatePipe,
    private utilsService: UtilsService,
    private authenticationService: AuthenticationServiceService,
    private datospersonalesService: DatospersonalesService,
    private solicitudHistorialLaboralService: SolicitudHistorialLaboralService,
    private historialLaboralService: HistoriallaboralService,
    private logger: NGXLogger) {
    this.obtenerEtiquetas();
  }

  ngOnInit(): void {
    if (this.authenticationService.currentUserValue) {
      this.authenticationService.currentUser.subscribe(usr => {
        this.idServidorPublico = usr.CLAVESERVIDOR;
        this.nombreServidorPublico = usr.NOMBRECOMPLETO;
      });
    }

    this.cargaDatosServidorPublico();
    //this.obtenerHistorialLaboral();
    this.obtenerMotiviosSolicitud();
    this.obtenerSolicitudesRealizadas();
  }
  //#endregion

  //#region Funciones privadas
  /**
   * Obtener etiquetas de pagina.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 03/11/2021.
   */
  private obtenerEtiquetas(): void {
    this.utilsService.ObtenerEtiquetasPagina
      ('/historialLaboral',
        'español (México)'
      ).subscribe(etiquetas => {
        Object.keys(etiquetas).map((key) => {
          this.etiquetas.set(key, etiquetas[key])
        });
      });
  }

  /**
   * Obtener listado de motivos de solicitud;
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 03/11/2021.
   */
  private obtenerMotiviosSolicitud(): void {
    this.solicitudHistorialLaboralService.obtenerMotiviosSolicitud().subscribe(
      resultado => {
        this.lsMotivosSolicitud = resultado.response;
      }
    );
  }

  /**
   * Obtener listado de solicitudes realizadas por el servidor publico.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 03/11/2021.
   */
  private obtenerSolicitudesRealizadas(): void {
    this.solicitudHistorialLaboralService.obtenerSolicitudesRealizadas(
      this.idServidorPublico,
      this.idServidorPublico,
      1
    ).subscribe(
      resultado => {
        this.lsSolicitudesHistorial = resultado.response;
      }
    );
  }

  /**
   * Obtener datos de servidor publico.
   * @author DESCONOCIDO.
   * @version 1.0, DESCONOCIDO.
   */
  private cargaDatosServidorPublico(): void {
    this.datospersonalesService.cargaDatosServidorPublico(
      this.idServidorPublico
    ).subscribe(data => {
      this.nombrePlaza = data.code === '200' ?
        data.response.NOMBREPLAZA : '';
    });
  }

  private inicializarFormulario(): void {
    this.theFormGroup = this.fb.group({
    });
  }
  //#endregion

  //#region Eventos
  /**
   * Validar componente para generacion de nueva solicitud de historial laboral.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 25/11/2021.
   */
  btnNuevaSolicitudClick() {
    //Bandera de procesamiento
    let puedeProcesar = true;
    //Si existe un elemento con estatus de "PENDIENTE" se impide procesar
    this.lsSolicitudesHistorial.forEach(e => { if (e.estatus === 'Pendiente') puedeProcesar = false; });

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
   * Generar una nueva solicitud de historial laboral del servidor publico.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 20/11/2021.
   */
  btnAceptarSolicitudClick() {
    //Mostrar loader
    this.spinner.show();
    this.solicitudHistorialLaboralService.insertarSolicitudHistorialLaboral(
      this.idServidorPublico,
      Number(this.motivoSeleccionado),
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
        //detail: resultado.response.toString()
        detail: 'La solicitud de historial laboral se ha generado correctamente'
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
   * Descargar documento de solicitud de historial laboral.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 25/11/2021.
  */
  btnDescargarHistorialClick() {
    //Mostrar spinner de procesamiento
    this.spinner.show();
    //Crear elemento de descarga
    const a = document.createElement('a');
    //Consumir servicio de descarga de historial laboral
    let servicePDF = this.solicitudHistorialLaboralService.
      obtenerArchivoHistorialLaboral(this.idServidorPublico);
    //Generar documento de descarga
    forkJoin([servicePDF]).subscribe(results => {
      if (results !== undefined) {
        a.setAttribute('style', 'display:none');
        document.body.appendChild(a);
        a.href = window.URL.createObjectURL(results[0]);
        a.download = "solicitud-historial-laboral.pdf";
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

  //#region Eventos anteriores
  private obtenerHistorialLaboral(): void {
    this.spinner.show();
    this.historialLaboralService.obtenerHistoralLaboral(
      this.idServidorPublico
    ).subscribe((data) => {
      console.log('obtenerHistoralLaboral=>' + JSON.stringify(data));
      for (let index = 0; index < data.response.length; index++) {
        let x = new ConstanciaHistorialLaboral();
        let obj = data.response[index];
        var res = obj.ESTATUS.split("|");
        x.FECHASOLICITUD = obj.FECHASOLICITUD;
        x.GESTORADMINISTRATIVO = obj.GESTORADMINISTRATIVO;
        x.ESTATUS = res[1];
        x.IDESTATUS = parseInt(res[0]);
        if (res[0] === '0') {
          this.habilitaBotonNuevaSolicitud = true;
        }
        this.constanciaHistorialLaboral.push(x);
      }
    });
    this.spinner.hide();
  }

  confirmacionConstanciaNoAdeudo() {
    this.messageService.clear();
    this.messageService.add({ key: 'c', sticky: true, severity: 'warn', summary: '¿Estás seguro?', detail: 'Confirmar para continuar' });
  }

  rechazar() {
    this.messageService.clear('c');
  }
  //#endregion
}
