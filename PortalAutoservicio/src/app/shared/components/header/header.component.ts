import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { AutoservicioService } from 'src/app/services/autoservicio.service';
import { Menu } from 'src/app/models/menu';
import { MenuItem } from 'primeng/api/menuitem';
import { UtilsService } from 'src/app/services/utils.service';
import { NGXLogger } from 'ngx-logger';
import { NotificacionesService } from 'src/app/services/notificaciones.service';
import { CatalogoNotificaciones } from 'src/app/models/notificaciones';
import { forkJoin } from 'rxjs';
import { Notificaciones } from 'src/app/models/notifiaciones';
import { DtNotificaciones } from 'src/app/modules/dt-notificaciones';
import { NotificacionesDropDown } from 'src/app/models/notificaciones-drop-down';
import { ConsultaDatosServidorPublico } from 'src/app/models/consulta-datos-servidor-publico';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { ServiceBus } from 'src/app/services/service-bus';
import { environment } from 'src/environments/environment';
import { ConsultaDatosPersonales } from 'src/app/models/consulta-datos-personales';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { first } from 'rxjs/operators';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { MessageService } from 'primeng/api';

import { BehaviorSubject, Observable } from 'rxjs';
@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  menu: Menu[];
  items: MenuItem[];
  lblMenu: string;
  lblnotificaciones: string;
  notifiaciones: CatalogoNotificaciones[] = [];
  totalNotifiaciones: number = 0;
  notDropDown$: Observable<NotificacionesDropDown[]>;
  idServidorPublico: string = '';
  urlWSBUS: string = `frwsr_LPSAUT_CONS${environment.HEDER_WS}.php`;
  urlDat: string = `frwsr_LPSAUT_CONS_DAT${environment.HEDER_WS}.php`;
  datosServidorPublico: ConsultaDatosServidorPublico = new ConsultaDatosServidorPublico();
  datosPersonales: DatosPersonales = new DatosPersonales();
  backGraundNot: string = '#5070e6';

  showNotificaciones: boolean = false;


  @Input() InpDatosPersonales = new DatosPersonales;
  @Input() InpDatosServidorPublico = new ConsultaDatosServidorPublico;

  @Output() hideShowMenu: EventEmitter<boolean> = new EventEmitter(false);

  lblPerfil = environment.PERFIL_WEB
  constructor(private service: AutoservicioService,
    private utilsService: UtilsService,
    private logger: NGXLogger,
    private notificacionesservice: NotificacionesService,
    private authenticationService: AuthenticationServiceService,
    private serviceBusProxy: ServiceBus,
    private messageService: MessageService
  ) {
    this.obtenerEtiquetasHome();
    this.notDropDown$ = this.notificacionesservice.notDropDown$;
  }

  ngOnInit(): void {

    let urlPara: string = window.location.href;
    let paramEncripted: string[] = [];
    this.logger.info(urlPara);
    //recupera el valor de sesión de la URL cuando se ingresa por primera vez
    if (urlPara.includes('idServidorPublico')) {
      paramEncripted = urlPara.split("idServidorPublico=");
      this.logger.debug("ENCRIPT HEADER =>>>>===>", paramEncripted[1]);
      //desencripta la clave y obtiene el usuario en sesion 
      let claveServidor = this.obtenerParametrosSesion(paramEncripted[1]);
    } else

      //recupera la sesión cuando ya existe una
      if (this.authenticationService.currentUserValue) {
        //el usuario sigue en sesión 
        this.authenticationService.currentUser.pipe(first()).subscribe(usr => {
          this.datosPersonales = usr;
          this.idServidorPublico = this.datosPersonales.CLAVESERVIDOR;
          this.notificacionesservice.obtenerNotificacionesPortal(this.idServidorPublico);
        });
      }

    //this.obtenerNotificacionesPortal();
  }

  /**
   * Obtiene las etiquetas de la pantalla
   */
  obtenerEtiquetasHome() {

    const usuario = this.utilsService.ObtenerEtiquetasPagina('/home', 'español (México)').subscribe(data => {
      Object.keys(data).map((key) => {
        if (key === 'btn.menu') {
          this.lblMenu = data[key];
        }
        if (key === 'btn.notificaciones') {
          this.lblnotificaciones = data[key];
        }
      });
    });
  }

  obtenerNotifiaciones() {
    this.notifiaciones = this.notificacionesservice.getNotificaciones();

    //descontar notificaciones que ya se abrieron 
  }

  /**
   * Obtiene las notificaciones del bus y las notificaciones registradas en el portal
   * compara para marcar las leidas y no mostrar las vencidas
   */
  /*
  obtenerNotificacionesPortal() {
    this.logger.debug('OBTENIENDO NOTIFICACIONES=>1');
    let notiPortal = this.utilsService.obtenerNotificaciones(this.idServidorPublico);
    let notiBus = this.notificacionesservice.getNotificacionesBus(this.idServidorPublico);
    this.notDropDown = [];
    forkJoin([notiPortal, notiBus]).subscribe(results => {
      this.logger.debug('NOTIFICACIONES 123 =>', results[1]);
      this.logger.debug('ESTATUS=>', (results[1].response));
      let existenPortal = results[0].length > 0 ? true : false;
      let existenBus = results[1].code === '200' ? true : false;
      this.logger.debug('datos Portal', results[0])
      this.logger.debug('existenPortal', existenPortal);
      this.logger.debug('existenBus', existenBus);
      if (existenBus) {
        let nportal: DtNotificaciones[] = [];
        for (let h of results[0]) {
          let nNot: DtNotificaciones = new DtNotificaciones();

          nNot.cveservidorpublico = '';
          nNot.estatusnotificacion = h[5];
          nNot.fechanotificacion = h[2];
          nNot.idnotificacionbus = h[3];
          nNot.llnotificaciones = h[1];
          nNot.lltiponotificacion = h[4];
          nportal.push(nNot);
        }
        this.logger.debug('Casteo =>>', nportal)
        let bus: Notificaciones[] = results[1].response;

        if (existenPortal) {
          this.logger.debug('notificaciones del BUS=>', bus)
          for (let b of bus) {
            //let nportal: DtNotificaciones[] = results[0];
            //valida si las notificaciones del bus no existen en el portal
            this.logger.debug('valida si las notificaciones del bus no existen en el portal', nportal);
            let existeNot: DtNotificaciones[] = [];
            //let existeNot: DtNotificaciones[] = nportal.filter(e => { e.llnotificaciones == b.IDPROCESOVIGENTE && e.lltiponotificacion == b.TIPONOFICIACION });
            for (let nn of nportal) {
              this.logger.debug('idnotificacionbus', nn.idnotificacionbus);
              this.logger.debug('IDPROCESOVIGENTE', b.IDPROCESOVIGENTE);
              this.logger.debug('lltiponotificacion', nn.lltiponotificacion);
              this.logger.debug('TIPONOFICIACION', b.TIPONOFICIACION);
              if (nn.idnotificacionbus === b.IDPROCESOVIGENTE && nn.lltiponotificacion == b.TIPONOFICIACION) {
                existeNot.push(nn);
              }
            }
            this.logger.debug('Las que existen=>', existeNot);
            if (existeNot.length > 0) {
              //si existe una notifiación en el portal con los mismos que el BUS
              this.logger.debug('si existe una notifiación en el portal con los mismos que el BUS');
              let notOne: NotificacionesDropDown = new NotificacionesDropDown();
              notOne.descNotificacion = b.DESCRIPCION;
              //valida si la notificación fue leida
              this.logger.debug('valida si la notificación fue leida', existeNot[0].estatusnotificacion);
              if (existeNot[0].estatusnotificacion) {
                notOne.backgroundNotificacion = '#2bde20'; //#2b2f3dbd 2B2F3D
              } else {
                notOne.backgroundNotificacion = '#2bde20';
              }
              notOne.fechaNotificacion = b.DESCRIPCION;
              notOne.idNotificacionPortal = existeNot[0].llnotificaciones;
              notOne.idTipoNotificacion = b.TIPONOFICIACION;
              notOne.urlNotificacion = this.urlByTipoNotificacion(b.TIPONOFICIACION);
              notOne.idnotificacionbus = b.IDPROCESOVIGENTE;
              this.notDropDown.push(notOne);
            } else {
              //la notificación del bus no esta registrada en las del portal
              this.logger.debug('la notificación del bus no esta registrada en las del portal');
              let notTwo: NotificacionesDropDown = new NotificacionesDropDown();
              notTwo.descNotificacion = b.DESCRIPCION;
              notTwo.backgroundNotificacion = 'oscuro';
              notTwo.fechaNotificacion = b.DESCRIPCION;
              notTwo.idNotificacionPortal = '0';
              notTwo.idTipoNotificacion = b.TIPONOFICIACION;
              notTwo.urlNotificacion = this.urlByTipoNotificacion(b.TIPONOFICIACION);
              notTwo.idnotificacionbus = b.IDPROCESOVIGENTE;
              this.notDropDown.push(notTwo);
            }
          }

        } else {
          //El servidor publico no tiene notificaciones registradas en el portal
          this.logger.debug('El servidor publico no tiene notificaciones registradas en el portal');
          for (let b of bus) {
            let notThree: NotificacionesDropDown = new NotificacionesDropDown();
            notThree.descNotificacion = b.DESCRIPCION;
            notThree.backgroundNotificacion = 'oscuro';
            notThree.fechaNotificacion = b.DESCRIPCION;
            notThree.idNotificacionPortal = '0';
            notThree.idTipoNotificacion = b.TIPONOFICIACION;
            notThree.urlNotificacion = this.urlByTipoNotificacion(b.TIPONOFICIACION);
            notThree.idnotificacionbus = b.IDPROCESOVIGENTE
            this.notDropDown.push(notThree);
          }

        }
      }
      this.logger.debug('TOTAL DE NOTIFICACIONES => 5', this.notDropDown);
      let cero: string = '0';
      let notificacionNot: NotificacionesDropDown[] = [];
      for (let y of this.notDropDown) {
        if (y.idNotificacionPortal == '0') {
          notificacionNot.push(y);
        }
      }
      this.logger.debug('GUARDANDO => 6', notificacionNot);
      this.utilsService.guargarNotificaciones(notificacionNot, this.idServidorPublico, false).subscribe(e => {
        this.logger.debug('suscribe', e);
      });
      this.totalNotifiaciones = this.notDropDown.length;

    })
  }
  */

  /**
   * obtiene la URL de navegación de la notificación
   * @param tipo 
   */
  urlByTipoNotificacion(tipo: string): string {
    let url: string = '';
    switch (tipo) {
      case '1':
        url = '/evaluacionDesempeno';
        break;
      case '2':
        url = '/consultaKPI';
        break;
      case '3':
        url = '/escalafon';
        break;
      case '4':
        url = '/escalafon';
        break;
      case '5':
        url = '/misEdd';
        break;
      case '6':
        url = '/historialLaboral';
        break;
      case '7':
        url = '/constanciaNoAdeudo';
        break;
      case '8':
        url = '/evaluacionDesempeno';
        break;
    }
    return url;
  }
  /**
   * marca las notificaciones seleccionadas como leidas
   * @param notificacion 
   */
  leerNotificacion(notificacion: NotificacionesDropDown) {
    this.adminNotificaciones();
    this.logger.debug('Notificacion seleccionada =>', notificacion);
    let notificacionNot: NotificacionesDropDown[] = [];

    notificacionNot.push(notificacion);
    this.utilsService.guargarNotificaciones(notificacionNot, this.idServidorPublico, true).subscribe(e => {
      this.logger.debug('suscribe', e);
    });
  }
  /**
   * desencripta el parameto de URL
   */
  obtenerParametrosSesion(claveEncripted: string) {
    this.authenticationService.login(claveEncripted).pipe(first()).subscribe(
      data => {
        this.idServidorPublico = data;
        this.notificacionesservice.obtenerNotificacionesPortal(this.idServidorPublico);
        this.logger.debug('valor obtenido HEADER >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>', data);
      },
      error => {
        this.logger.error

      });

  }

  toggleSideNav() {
    this.hideShowMenu.emit(true);
  }

  closeNotificaciones() {
  }

  adminNotificaciones() {
    this.showNotificaciones = !this.showNotificaciones;
  }


  cerrarsessionConfirm() {

    this.messageService.clear();
    this.messageService.add({ key: 'cd', sticky: true, severity: 'warn', summary: '¿Estás seguro?', detail: 'Confirmar para cerrar sesión' });
  }

  cerrarsession() {
    window.open(environment.URL_SIGAP, "_self");
    sessionStorage.removeItem('currentUser');
    this.logger.debug('eliminando parametros de sesion');
  }

  rechazar() {
    this.messageService.clear('cd');
  }



}
