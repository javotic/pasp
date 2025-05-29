import { Component, EventEmitter, OnInit, Output } from "@angular/core";
import { ConsultaCategorias } from "src/app/models/consulta-categorias";
import { ConsultaEventos } from "src/app/models/consulta-eventos";
import { CarouselInformativoServiceService } from "src/app/services/carousel-informativo-service.service";
import { BusgenericserviceService } from "src/app/services/busgenericservice.service";
//import { SwiperOptions } from "swiper";
import { Router, ActivatedRoute} from "@angular/router";
import { EncriptdecriptService } from "src/app/services/encriptdecript.service";
import { AuthenticationServiceService } from "src/app/services/authentication-service.service";
import { NGXLogger } from "ngx-logger";
import { UtilsService } from "src/app/services/utils.service";
import { ServiceBus } from 'src/app/services/service-bus';
import { environment } from 'src/environments/environment';
import { RequestEventos } from 'src/app/models/request-eventos';
import { RequestCategorias } from 'src/app/models/request-categorias';
import { RespuestaApi } from 'src/app/repuesta/respuesta-api';
import { NotificacionesService } from 'src/app/services/notificaciones.service';
import { AutoservicioService } from "src/app/services/autoservicio.service";
import { Menu } from "src/app/models/menu";

import SwiperCore, { Autoplay, Pagination, Navigation, Scrollbar, Swiper} from "swiper/core";
import { SidebarService } from "src/app/services/sidebar.service";
import { ConsultaDatosPersonales } from "src/app/models/consulta-datos-personales";

SwiperCore.use([Pagination, Autoplay, Navigation, Scrollbar, Swiper ]);

@Component({
  selector: "app-home",
  templateUrl: "./home.component.html",
  styleUrls: ["./home.component.css"],
  providers: [NGXLogger],
})
export class HomeComponent implements OnInit {
  

  categorias: ConsultaCategorias[] = [];
  eventos: ConsultaEventos[] = [];
  displayDialog: boolean = false;
  displayDialogLogOut: boolean = false;
  idServidorPublico: String;
  detalleEvento: ConsultaEventos = new ConsultaEventos();
  error = "";
  lbldetalle: string;
  lblverdetalle: string;
  lblcerrar: string;
  urlWSBUS: string = `frwsr_LPSAUT_CONS_CAT${environment.HEDER_WS}.php`;

  menu: Menu[];

  constructor(
    private autoservicioService: AutoservicioService,
    private authenticationService: AuthenticationServiceService,
    private logger: NGXLogger,
    private utilsService: UtilsService,
    private serviceBusProxy: ServiceBus,
    private sidebarService: SidebarService,
  ) {
    this.obtenerEtiquetasHome();
  }

  ngOnInit(): void {
    if (this.authenticationService.currentUserValue) {


      this.sidebarService.consultarDatosPersonales(this.authenticationService.currentUserValue.CLAVESERVIDOR, '-', '-', '-')
      .subscribe(result=>{
        const datosPerson = <ConsultaDatosPersonales>JSON.parse(JSON.stringify(result.response));
        console.log('Obteniendo el Rol de home:' + datosPerson.CLAVESERVIDOR);
        this.autoservicioService.listarMenuByRol(datosPerson.CLAVESERVIDOR).subscribe(menu => {
          this.menu = menu;
        });

      });
      //el usuario sigue en sesión
      /*
      this.authenticationService.currentUser.pipe(first()).subscribe(usr => {
        this.notificacionesService.obtenerNotificacionesPortal(usr.CLAVESERVIDOR);
      });
      */
      
      this.logger.debug("Usuario logueado en Home");
    } else {
      this.logger.debug("Usuario no logueado en Home");
    }

    //Obtiene los eventos
    this.obtenerTodosEventos();
    //Obtiene las categorias de los eventos
    let requestCategorias: RequestCategorias = new RequestCategorias();
    requestCategorias.funcion = 'CONSULTACATEGORIAS';
    requestCategorias.IDCATEGORIA = '';

    this.serviceBusProxy.consultarServiceBus(this.urlWSBUS, requestCategorias).subscribe(data => {
      let vari = <RespuestaApi<ConsultaCategorias>>data;
      this.categorias = <ConsultaCategorias[]>vari.response;

    });

  }

  /**
   * Muestro el Dialog
   * @param event
   */
  showDialog(event: ConsultaEventos) {
    this.displayDialog = true;
    this.detalleEvento = event;
  }
  /**
   * oculta el Dialog
   */
  hideDialog() {
    this.displayDialog = false;
  }

  /**
   * Obtiene los eventos por categoria
   * @param categoria
   */
  obtenerCategoria(categoria: ConsultaCategorias) {
    this.eventos = [];
    let requestEvents: RequestEventos = new RequestEventos();
    requestEvents.funcion = 'CONSULTAEVENTOS';
    requestEvents.IDEVENTO = '';
    requestEvents.IDCATEGORIA = categoria.IDCATEGORIA + '';

    this.serviceBusProxy.consultarServiceBus(this.urlWSBUS, requestEvents).subscribe(data => {
      let vari = <RespuestaApi<ConsultaEventos>>data;
      this.eventos = <ConsultaEventos[]>vari.response;
    });


  }
  /**
   * Obtiene todos los eventos
   */
  obtenerTodosEventos() {
    this.eventos = [];
    let requestEvents: RequestEventos = new RequestEventos();
    requestEvents.funcion = 'CONSULTAEVENTOS';
    requestEvents.IDEVENTO = '';
    requestEvents.IDCATEGORIA = 'EV001';
    this.serviceBusProxy.consultarServiceBus(this.urlWSBUS, requestEvents).subscribe(data => {
      let vari = <RespuestaApi<ConsultaEventos>>data;
      this.eventos = <ConsultaEventos[]>vari.response;
    });


  }

  valueIsEmpty(val: String): Boolean {
    let isEmtyVal: Boolean;
    if (val === "" || val === undefined) {
      isEmtyVal = false;
    } else {
      isEmtyVal = true;
    }
    return isEmtyVal;
  }
  /**
   * Obtiene las etiquetas para la pantalla de home
   */

  obtenerEtiquetasHome() {
    const usuario = this.utilsService
      .ObtenerEtiquetasPagina("/home", "español (México)")
      .subscribe((data) => {
        Object.keys(data).map((key) => {
        
          if (key === "btn.detalle") {
            this.lbldetalle = data[key];
          }
          if (key === "lbl.verDetalle") {
            this.lblverdetalle = data[key];
          }
          if (key === "lbl.cerrar") {
            this.lblcerrar = data[key];
          }
        });
      });
  }
}

