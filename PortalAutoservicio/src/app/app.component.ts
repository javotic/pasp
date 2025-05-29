import { Component, ViewChild, ɵConsole } from '@angular/core';
import { BnNgIdleService } from 'bn-ng-idle';
import { CountdownComponent } from 'ngx-countdown';
import { AutoservicioService } from './services/autoservicio.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AuthenticationServiceService } from './services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { environment } from 'src/environments/environment';
import { UtilsService } from './services/utils.service';
import { first } from 'rxjs/operators';
import { ConsultaDatosPersonales } from './models/consulta-datos-personales';
import { async } from 'rxjs/internal/scheduler/async';
import { RespuestaServiceM4 } from './repuesta/respuesta-service-m4';
import { NotificacionesService } from 'src/app/services/notificaciones.service';
import { UsuariosService } from './services/usuarios.service';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [NGXLogger]
})


export class AppComponent {
  TIEMPO_DURACION_TIMER: String = 'TIEMPO_DURACION_TIMER';
  TIEMPO_MOSTRAR_TIMER: String = 'TIEMPO_MOSTRAR_TIMER';
  title = 'PortalAutoservicio';
  displayModal: boolean = false;
  displayErrorUser: boolean = false;
  displayUserNoEncontrado: boolean= false;
  tiempoInactividad: Number = 900;
  tiempoDurancion: number = 10;
  cierreAutomatico: string;
  ingresarnuevamente: string;
  config = {};
  //@ViewChild('cd1') timer: CountdownComponent;
  @ViewChild('cd1', { static: false }) private timer: CountdownComponent;


  constructor(private bnIdle: BnNgIdleService, private servicio: AutoservicioService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private utilsService: UtilsService,
    private notificacionesService: NotificacionesService,
    private usuariosService: UsuariosService) {
      this.obtenerEtiquetasHome();
    //obtiene la URL
    let urlPara: string = window.location.href;
    let paramEncripted: string[] = [];
    this.logger.info(urlPara);
    //valida que la URL contenga la clave se servidor publico
    if (urlPara.includes('idServidorPublico')) {
      paramEncripted = urlPara.split("idServidorPublico=");
      this.logger.debug(paramEncripted[1]);
      //desencripta la clave y obtiene el usuario en sesion 
      let claveServidor = this.obtenerParametrosSesion(paramEncripted[1]);
    } else {
      this.router.navigate(['home']);
      this.logger.debug('Mandando a home desde app.component');
        if (!this.authenticationService.currentUserValue) {
         this.logger.debug('NO HAY DATOSS...')
         window.open(environment.URL_SIGAP, "_self");
       } else {
         this.logger.debug('SI HAY DATOSS...');
       } 
    }

    //Obtiene el tiempo de duración de contador
    this.obtenerTiempoDuracionTimer();
    //Obtiene el tiempo se inactividad 
    this.obtenerParametros();
    
  }

  ngOnInit(): void {


    // iniciliza el timer de inactividad
    this.bnIdle.startWatching(this.tiempoInactividad).subscribe((isTimedOut: boolean) => {
      this.logger.debug('bnIdle status = ', this.bnIdle.expired$);
      if (isTimedOut) {
        this.logger.debug('Tiempo de inactividad alcanzado');
        this.obtenerTiempoDuracionTimer();
        this.displayModal = true;
        this.timer.restart();
        this.bnIdle.stopTimer();

      } else {
        this.timer.pause;
      }
    });




  }
  /**
   * Cierra el popup de inactividad
   * e inicializa el timer 
   */
  cerrarDialog() {
    this.timer.stop();
    this.displayModal = false;
    this.bnIdle.resetTimer();
    //this.router.navigate(['home']);
  }
  /**
   * cacha los eventos del timer 
   * @param $event 
   */
  handleEvent($event) {
    this.logger.debug(this.timer.i);
    this.logger.debug('Evento timer: ', $event.action)
    if ($event.action === 'done') {
      this.timer.stop();
      this.logger.debug('el timer se detuvo');

    }
    if (this.displayModal) {
      this.logger.debug('Display: ', this.displayModal);
      if (this.timer.i['value'] === 0 && this.timer.i['text'] === '00:00' && $event.action === 'stop') {
        this.logger.debug('Value es 0 ', 'Text  es 00:00 ');
        this.authenticationService.logout();
        window.open(environment.URL_SIGAP, "_self");

      }
    }





  }
  /**
   * Obtiene el tiempo para mostrar el popup de  inactividad
   */
  obtenerParametros(): Number {
    var tiemp: Number;
    this.servicio.parametroByClave(this.TIEMPO_MOSTRAR_TIMER).subscribe(parametro => {
      tiemp = Number(parametro.valorParametro);

    });
    return tiemp;
  }
  /**
   * Obtiene el tiempo de duración del timer
   */
  obtenerTiempoDuracionTimer() {
    this.servicio.parametroByClave(this.TIEMPO_DURACION_TIMER).subscribe(parametro => {
      this.tiempoDurancion = Number(parametro.valorParametro);

    });
    this.config = {
      leftTime: this.tiempoDurancion + '',
      size: 'large',
      format: 'mm:ss'
    };
  }
  /**
   * Obtiene el usuario en sesión por la clave encriptada
   * @param claveEncripted 
   */
  obtenerParametrosSesion(claveEncripted: string) {
    this.authenticationService.login(claveEncripted).subscribe( //.pipe(first()).subscribe(
      // () => this.redirectHome(data),
      // () => this.redirectAnotherSite()
      data => {
        console.log('Cargando los datos nuevos:' + data);
        this.redirectHome(data);
      },
      error => {
        console.log('error app:' + JSON.stringify(error));
        this.redirectAnotherSite();
      });
    

  }
  /**
   * Si el Login es correcto direcciona al Home
   */
  redirectHome(dataus: string) {
  this.authenticationService.validUser(dataus).subscribe(datoss =>{
    this.logger.debug('VALOR DE USUARIO =>' +JSON.stringify(datoss));

    //Asignamos el usuario con corrente
    //this.logger.debug("VALOR DE USUARIO =>", res);
    let vari = <RespuestaServiceM4<ConsultaDatosPersonales>>datoss;
    let user: ConsultaDatosPersonales[] = <ConsultaDatosPersonales[]>vari.response;
    this.logger.debug('USER LOGIN ==>>>', user);
    this.logger.debug('USER LOGIN vari==>>>', vari.response);
    var myJSON = JSON.stringify(user);

    if(datoss === undefined ||  myJSON.indexOf('"CLAVESERVIDOR":null') > 0){
      this.logger.debug('login success undefined');
      this.displayUserNoEncontrado = true;
      this.displayErrorUser = false;
      //this.redirectAnotherSite();
    }else{
      this.logger.debug('login success home');
      
      const datosPerson = <ConsultaDatosPersonales>JSON.parse(myJSON);
      this.logger.debug('vari[0].IDROL:' + datosPerson.IDROL);
      
      if(datosPerson.IDROL == null || datosPerson.IDROL == undefined){
        this.usuariosService.UpdInsRolUsuario(datosPerson.CLAVESERVIDOR, 0, '1', '', datosPerson.CLAVESERVIDOR).subscribe(result=>{
          let idRolUser = Number(result.response);
          if(idRolUser != 0){
            sessionStorage.setItem('currentUser', myJSON);
            this.authenticationService.asignarUsuario(myJSON);
            this.router.navigate(['home']);
            console.log('Obteniendo el Rol de appComponent:' + idRolUser);
          }else{
            console.log("No se pudo asignar el rol por appComponent");
          }
        }, error=>{
          console.log("No se pudo asignar el rol por appComponent");
        });
        
      }else{
        sessionStorage.setItem('currentUser', myJSON);
        this.authenticationService.asignarUsuario(myJSON);
        this.router.navigate(['home']);
      }
    }
  }, error =>{
    this.logger.debug('login success error');

    this.displayErrorUser = true;
    this.displayUserNoEncontrado = false;
  });

  }

  /**
   * Si el Login es incorrecto destruye los objetos 
   */
  reintentarIngreso() {
    this.logger.debug('worng login')
    //this.authenticationService.logout();
    location.reload();
  }


  /**
   * Si el Login es incorrecto destruye los objetos 
   */
  redirectAnotherSite() {
    this.logger.debug('worng login')
    this.authenticationService.logout();
  }

  /**
* Obtiene las etiquetas para la pantalla de home
*/

  obtenerEtiquetasHome() {
    const usuario = this.utilsService
      .ObtenerEtiquetasPagina("/home", "español (México)")
      .subscribe((data) => {
        Object.keys(data).map((key) => {
         
          if (key === "lbl.cierreAutomatico") {
            this.cierreAutomatico = data[key];
          }
          if (key === "lbl.ingresarnuevamente") {
            this.ingresarnuevamente = data[key];
          }
        });
      });
  }

}
