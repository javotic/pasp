import { Component, OnInit, ViewChild, Output, EventEmitter } from '@angular/core';
import { AutoservicioService } from 'src/app/services/autoservicio.service';
import { Menu } from 'src/app/models/menu';
import { MessageService } from 'primeng/api';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { ConsultaDatosServidorPublico } from 'src/app/models/consulta-datos-servidor-publico';
import { environment } from 'src/environments/environment';
import { UtilsService } from 'src/app/services/utils.service';
import { first } from 'rxjs/operators';
import { forkJoin } from 'rxjs';
import { RespuestaServiceM4 } from 'src/app/repuesta/respuesta-service-m4';
import { SidebarService } from 'src/app/services/sidebar.service';
import { UsuariosService } from './../../../services/usuarios.service';
import { CountdownComponent } from 'ngx-countdown';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarCusComponent implements OnInit {

  menu: Menu[];
  datosPersonales: DatosPersonales = new DatosPersonales();
  idServidorPublico: string = '';
  nombreServidorPublico: String = '';
  lblEstasSeguro: string;
  lblConfirmacerrar: string;
  display = false;
  datosServidorPublico: ConsultaDatosServidorPublico = new ConsultaDatosServidorPublico();

  //Elementos para cierre 
  displayModalSalir = false;
  configTimer = {
    leftTime: 30 + '',
    format: 'mm:ss'
  };

  @ViewChild('cd1', { static: false }) private timer: CountdownComponent;

  //DatosUsuario
  @Output() outdatosPersonales = new EventEmitter<DatosPersonales>();
  @Output() outDatosServidorPublico = new EventEmitter<ConsultaDatosServidorPublico>();

  constructor(private service: AutoservicioService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private messageService: MessageService,
    private utilsService: UtilsService,
    private serviceSidebar: SidebarService,
    private usuariosService: UsuariosService) { }
  ngOnInit(): void {

    this.obtenerEtiquetasHome();
    let urlPara: string = window.location.href;
    let paramEncripted: string[] = [];
    //recupera el valor de sesión de la URL cuando se ingresa por primera vez
    if (urlPara.includes('idServidorPublico')) {
      paramEncripted = urlPara.split("idServidorPublico=");
      //desencripta la clave y obtiene el usuario en sesion 
      let claveServidor = this.obtenerParametrosSesion(paramEncripted[1]);

    } else if (this.authenticationService.currentUserValue) {  //recupera la sesión cuando ya existe una
      //el usuario sigue en sesión 
      this.authenticationService.currentUser.pipe(first()).subscribe(usr => {
        this.datosPersonales = usr;
        this.obtenerdatosServidor(this.datosPersonales.CLAVESERVIDOR);
      });
    }
  }
  /**
   * Obtener las opciones de menu
   */
  obtenerMenu(ClaveServidorPublico: string) {
    this.service.listarMenuByRol(ClaveServidorPublico).subscribe(menu => {
      this.menu = menu;
    });
  }
  /**
   * obtiene los datos del servidor publico
   * @param clvSP clave de servidor publico
   */
  obtenerdatosServidor(clvSP: string) {

    let spe: boolean = false; // Servidor público responsable de evaluación
    let spk: boolean = false;// Servidor público responsable de KPI's
    let spes: boolean = false;// Servidor publico con proceso escalafonario activo   
    //serviceSidebar
    let datosServidor = this.serviceSidebar.consultarDatosPersonales(clvSP, '-', '-', '-');
    let datosPersonaless = this.serviceSidebar.consultarDatosServidor(clvSP);

    //notiBus
    forkJoin([datosPersonaless, datosServidor]).subscribe(results => {
      let existenDatospersonales = results[0].code === '200' ? true : false;
      let existenDatosSP = results[1].code === '200' ? true : false;
      //let existenNotificaciones = results[2].code === '200' ? true : false;

      if (existenDatospersonales) {
        let vari = <RespuestaServiceM4<ConsultaDatosServidorPublico>>results[0];
        var myJSON = JSON.stringify(vari.response);
        this.datosServidorPublico = <ConsultaDatosServidorPublico>JSON.parse(myJSON);
        //Asignamos el output datos servidor publico
        this.outDatosServidorPublico.emit(this.datosServidorPublico);
      }
      if (existenDatosSP) {
        let vari = <RespuestaServiceM4<DatosPersonales>>results[1];
        var myJSON = JSON.stringify(vari.response);
        this.datosPersonales = <DatosPersonales>JSON.parse(myJSON);
        //Asignamos el output datos personales
        this.outdatosPersonales.emit(this.datosPersonales);
      }

      //Analizamos si el ususario ya tiene un rol asignado
      if (this.datosPersonales.IDROL == null) {
        console.error("No se pudo asignar el rol por default");
      } else {
        if (this.datosPersonales.BOACTIVO == '0' || this.datosPersonales.BOPRORROGA == '1') {
          this.displayModalSalir = true;
        } else {
          this.obtenerMenu(this.datosPersonales.CLAVESERVIDOR);
        }

      }
    });

  }
  /** mensaje de confirmación para el cierre de sesión */
  cerrarsessionConfirm() {

    this.messageService.clear();
    this.messageService.add({ key: 'cd', sticky: true, severity: 'warn', summary: this.lblEstasSeguro, detail: this.lblConfirmacerrar });
  }

  /**  cierra la sesión, direcciona al SIGAP y destruye los objetos */
  cerrarsession() {
    window.open(environment.URL_SIGAP, "_self");
    sessionStorage.removeItem('currentUser');
  }
  /** cierra el cuadro de confirmación de cerrar sesión  */
  rechazar() {
    this.messageService.clear('cd');
  }

  /**  Obtiene las etiquetas para la pantalla de home */
  obtenerEtiquetasHome() {
    const usuario = this.utilsService
      .ObtenerEtiquetasPagina("/home", "español (México)")
      .subscribe((data) => {
        Object.keys(data).map((key) => {

          if (key === "lbl.estasseguro") {
            this.lblEstasSeguro = data[key];
          }
          if (key === "lbl.confirmacerrar") {
            this.lblConfirmacerrar = data[key];
          }
        });
      });
  }

  /** desencripta el parameto de URL */
  obtenerParametrosSesion(claveEncripted: string) {
    this.authenticationService.login(claveEncripted).pipe(first()).subscribe(
      data => {
        this.idServidorPublico = data;
        this.obtenerdatosServidor(this.idServidorPublico + '')
      },
      error => {
        this.logger.error(error);
      });
  }

  handleEventSalir($event) {
    if ($event.action === 'done') {
      this.timer.stop();
    }
    if (this.displayModalSalir && this.timer.i['value'] === 0 && this.timer.i['text'] === '00:00'
      && $event.action === 'stop') {
      this.authenticationService.logout();
      window.open(environment.URL_SIGAP_INICIO, "_self");
    }
  }

  cerrarDialogSalir() {
    this.authenticationService.logout();
    window.open(environment.URL_SIGAP_INICIO, "_self");
  }

}
