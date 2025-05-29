
import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, FormControl, Validators } from '@angular/forms';
import { DatospersonalesService } from '../../services/datospersonales.service';
import { GeneralesService } from 'src/app/services/generales.service';
import { DatosPersonalesDTO } from 'src/app/dto/datosPersonalesDTO';
import { Combo } from 'src/app/models/combo';
import { MessageService } from 'primeng/api';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { BitacoraService } from 'src/app/services/bitacora.service';
import { Bitacora } from 'src/app/models/bitacora';
import { Modulo } from 'src/app/models/modulo';
import { TipoRegistro } from 'src/app/models/tipo-registro';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { NGXLogger } from 'ngx-logger';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { NgxSpinnerService } from 'ngx-spinner';
import { DatosPersonalesEtiquetas } from 'src/app/etiquetas/datos-personales-etiquetas';
import { SidebarService } from 'src/app/services/sidebar.service';
import { UsuariosService } from 'src/app/services/usuarios.service';
import { CatalogosService } from 'src/app/services/catalogos.service';
import { ProcesoescalafonariovigenteService } from 'src/app/services/escalafon/procesoescalafonariovigente.service';
import { ServidorPublicoModel } from 'src/app/models/servidor-publico-model';

@Component({
  selector: 'app-datos-personales',
  templateUrl: './datos-personales.component.html',
  styleUrls: ['./datos-personales.component.css']
})
export class DatosPersonalesComponent implements OnInit {

  //#region Atributos de clase
  cmbEstados: Combo[] = [];
  cmbMunicipios: Combo[] = [];
  cmbMunicipiosFiscales: Combo[] = [];
  cmbColonias: Combo[] = [];
  cmbColoniasFiscales: Combo[] = [];
  cmbEstadoCivil: Combo[] = [];
  cmbSexo: Combo[] = [];
  datosPersonalesform: FormGroup;
  idServidorPublico: string = '';
  currentUser: DatosPersonales = new DatosPersonales();
  consultaDPtiquetas: DatosPersonalesEtiquetas = new DatosPersonalesEtiquetas();
  cmbNivelEstudios: Combo[] = [];
  dsRolUsuario = '';
  isRedirect: boolean = false;
  //Banderas para busqueda de datos
  buscarMunicipios: boolean = true;
  buscarMunicipiosFiscales: boolean = true;
  buscarColonias: boolean = true;
  buscarColoniasFiscales: boolean = true;
  mostrarInstrucciones: boolean = false;
  //Elementos que permiten la actualizacion
  boActualizaInfo = false;
  boEnableElements = true;
  //#endregion

  //#region Constructores
  constructor(
    private datospersonalesService: DatospersonalesService,
    private messageService: MessageService,
    private generalesService: GeneralesService,
    private bitacoraService: BitacoraService,
    private authenticationService: AuthenticationServiceService,
    private fb: FormBuilder,
    private router: Router,
    private spinner: NgxSpinnerService,
    private logger: NGXLogger,
    private serviceSidebar: SidebarService,
    private usuariosService: UsuariosService,
    private activatedRoute: ActivatedRoute,
    private catalogosService: CatalogosService,
    private procesoescalafonariovigenteService: ProcesoescalafonariovigenteService
  ) {

  }

  ngOnInit(): void {
    this.obtenerEtiquetasPagina();
    if (this.authenticationService.currentUserValue) {
      this.authenticationService.currentUser.subscribe(usr => {
        this.currentUser = usr;
        this.idServidorPublico = usr.CLAVESERVIDOR;
      });
    }

    //Validamos si se pueden ejecutar actualizaciones
    this.procesoescalafonariovigenteService.escalafonActualizaInfo().subscribe(result => {
      if (result.response == '1') {
        this.boEnableElements = true;
      } else {
        this.boEnableElements = false;
      }
    });

    //Generar formulario principal
    this.generarFormulario();

    this.obtenerDatosSevidorPublico();
  }
  //#endregion

  //#region Funciones privadas
  /**
   * Genear formulario principal.
   * @author Desconocido
   * @version 1.0, Desconocido.
   * @version 1.1, 18/01/2022, Javier Alvarado, Adicion de campos de domicilio fiscal.
   */
  private generarFormulario(): void {
    this.datosPersonalesform = this.fb.group({
      'nombre': new FormControl(''),
      'apellidoPaterno': new FormControl(''),
      'apellidoMaterno': new FormControl(''),
      'fechaNacimiento': new FormControl(''),
      'curp': new FormControl(''),
      'rfc': new FormControl(''),
      'issemym': new FormControl(''),
      'nombrePais': new FormControl(''),
      'nombreEstado': new FormControl(''),
      'descripcionEscolaridad': new FormControl(''),
      'telefono': new FormControl('', Validators.required),
      'correoElectronico': new FormControl('', [Validators.email, Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]),
      'idNivelEstudios': new FormControl(''),
      'nombreEstadoCivil': new FormControl(''),
      'idSexo': new FormControl(''),
      //Domicilio personal
      'idEstado': new FormControl('', Validators.required),
      'idMunicipio': new FormControl('', Validators.required),
      'colonia': new FormControl('', Validators.required),
      'calle': new FormControl('', Validators.required),
      'numeroInterior': new FormControl('', Validators.required),
      'numeroExterior': new FormControl('', Validators.required),
      'codigoPostal': new FormControl('', Validators.required),
      //Domicilio fiscal
      'idEstadoFiscal': new FormControl('', Validators.required),
      'idMunicipioFiscal': new FormControl('', Validators.required),
      'coloniaFiscal': new FormControl('', Validators.required),
      'calleFiscal': new FormControl('', Validators.required),
      'numeroInteriorFiscal': new FormControl('', Validators.required),
      'numeroExteriorFiscal': new FormControl('', Validators.required),
      'codigoPostalFiscal': new FormControl('', Validators.required)
    });
  }

  /**
   * Cargar datos de formulario a traves de un objeto de servidor publico.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 25/01/2025.
   * @param _servidorPublicoModel Servidor publico del que se cargara la informacion
   */
  private cargarDatosFormulario(_servidorPublicoModel: ServidorPublicoModel): void {
    this.datosPersonalesform = this.fb.group({
      'nombre': new FormControl(_servidorPublicoModel.nombre),
      'apellidoPaterno': new FormControl(_servidorPublicoModel.primerApellido),
      'apellidoMaterno': new FormControl(_servidorPublicoModel.segundoApellido),
      'fechaNacimiento': new FormControl(_servidorPublicoModel.fechaNacimiento),
      'curp': new FormControl(_servidorPublicoModel.curp),
      'rfc': new FormControl(_servidorPublicoModel.rfc),
      'issemym': new FormControl(_servidorPublicoModel.issemym),
      'telefono': new FormControl(_servidorPublicoModel.telefono, Validators.required),
      'correoElectronico': new FormControl(_servidorPublicoModel.mail),
      'idNivelEstudios': new FormControl(_servidorPublicoModel.idNivelEstudios),
      'nombreEstadoCivil': new FormControl(_servidorPublicoModel.idEstadoCivil),
      'idSexo': new FormControl(_servidorPublicoModel.idSexo.toString()),
      //Domicilio personal
      'idEstado': new FormControl(_servidorPublicoModel.idEstadoPersonal, Validators.required),
      'idMunicipio': new FormControl(_servidorPublicoModel.idMunicipioPersonal, Validators.required),
      'colonia': new FormControl(_servidorPublicoModel.idColoniaPersonal, Validators.required),
      'calle': new FormControl(_servidorPublicoModel.callePersonal, Validators.required),
      'numeroInterior': new FormControl(_servidorPublicoModel.numeroInteriorPersonal, Validators.required),
      'numeroExterior': new FormControl(_servidorPublicoModel.numeroExteriorPersonal, Validators.required),
      'codigoPostal': new FormControl(_servidorPublicoModel.codigoPostalPersonal, Validators.required),
      //Domicilio fiscal
      'idEstadoFiscal': new FormControl(_servidorPublicoModel.idEstadoFiscal, Validators.required),
      'idMunicipioFiscal': new FormControl(_servidorPublicoModel.idMunicipioFiscal, Validators.required),
      'coloniaFiscal': new FormControl(_servidorPublicoModel.idColoniaFiscal, Validators.required),
      'calleFiscal': new FormControl(_servidorPublicoModel.calleFiscal, Validators.required),
      'numeroInteriorFiscal': new FormControl(_servidorPublicoModel.numeroInteriorFiscal, Validators.required),
      'numeroExteriorFiscal': new FormControl(_servidorPublicoModel.numeroExteriorFiscal, Validators.required),
      'codigoPostalFiscal': new FormControl(_servidorPublicoModel.codigoPostalFiscal, Validators.required)
    });
  }

  /**
   * Cargar datos de combos de un servidor publico determinado.
   * @author Javier Alvarado Rodriguez.
   * @version 1.0, 25/01/2025.
   * @param _servidorPublicoModel Servidor publico del que se cargara la informacion.
   */
  private cargarDatosCombos(_servidorPublicoModel: ServidorPublicoModel): void {
    if (_servidorPublicoModel.estadoPersonal != "") {
      this.cmbEstados = [{ label: _servidorPublicoModel.estadoPersonal, value: _servidorPublicoModel.idEstadoPersonal }];
    }

    if (_servidorPublicoModel.municipioPersonal != "") {
      this.cmbMunicipios = [{ label: _servidorPublicoModel.municipioPersonal, value: _servidorPublicoModel.idMunicipioPersonal }];
    }

    if (_servidorPublicoModel.coloniaPersonal != "") {
      this.cmbColonias = [{ label: _servidorPublicoModel.coloniaPersonal, value: _servidorPublicoModel.idColoniaPersonal }];
    }

    if (_servidorPublicoModel.municipioFiscal != "") {
      this.cmbMunicipiosFiscales = [{ label: _servidorPublicoModel.municipioFiscal, value: _servidorPublicoModel.idMunicipioFiscal }];
    }

    if (_servidorPublicoModel.coloniaFiscal != "") {
      this.cmbColoniasFiscales = [{ label: _servidorPublicoModel.coloniaFiscal, value: _servidorPublicoModel.idColoniaFiscal }];
    }
  }
  //#endregion

  //#region Funciones publicas
  confirmacionDatosPersonales() {
    this.messageService.clear();
    this.messageService.add({ key: 'c', sticky: true, severity: 'warn', summary: '¿Estás seguro?', detail: 'Confirmar para continuar' });
  }

  onSubmit(value: string) {
    this.confirmacionDatosPersonales();
  }

  rechazar() {
    this.messageService.clear('c');
  }

  cargaColonias(event: any) {
    //this.consultaCatalogoColonias(event.value);
    this.cmbColonias = [];
    this.buscarColonias = true;
  }

  guardaBitacora(idServidorPublico: string, idModulo: number, idTipoRegistro: number) {
    let bitacora = new Bitacora();
    bitacora.idServidorPublico = idServidorPublico;
    let modulo = new Modulo();
    modulo.idModulo = idModulo;
    let tipoRegistro = new TipoRegistro();
    tipoRegistro.idTipoRegistro = idTipoRegistro;
    bitacora.modulo = modulo;
    bitacora.tipoRegistro = tipoRegistro;
    bitacora.descripcion = 'Se registro';
    return bitacora;
  }

  obtenerEtiquetasPagina() {
    this.datospersonalesService.getEtiquetasPagos().subscribe(data => {
      this.consultaDPtiquetas = data;
    });
  }

  obtenerDatosSevidorPublico() {
    this.spinner.show();
    this.datospersonalesService.cargaDatosServidorPublico2(this.idServidorPublico).
      subscribe(data => {
        let parametros_url = this.router.parseUrl(this.router.url);
        if (parametros_url.queryParams['redirect'] == '1') {
          this.isRedirect = true;
          window.scroll(0, 0);
        }
        this.spinner.hide();

        if (data.codigo === 200) {
          //Obtener datos de servidor publico con nuevo metodo para obtener direccion fiscal
          this.datospersonalesService.obtenerDatosGenerales(
            this.idServidorPublico
          ).subscribe(resultado => {
            let _servidorPublicoModel: ServidorPublicoModel = resultado.response[0];
            /**
             * Hot Fix: en caso de que no cuente con domicilio fiscal, por 
             * default el IdEstadoFiscal será MEX.
             * @version 1.0, 11/03/2022, Javier Alvarado Rodriguez.
            */
            if (!_servidorPublicoModel.idEstadoFiscal) {
              _servidorPublicoModel.idEstadoFiscal = 'MEX';
            }
            /**
             * Hot Fix: en caso de que no cuente con numero interior personal o 
             * numero exterior personal, por  default valor será 0.
             * @version 1.0, 15/03/2022, Javier Alvarado Rodriguez.
            */
            if (!_servidorPublicoModel.numeroInteriorPersonal) {
              _servidorPublicoModel.numeroInteriorPersonal = '0';
            }
            if (!_servidorPublicoModel.numeroExteriorPersonal) {
              _servidorPublicoModel.numeroExteriorPersonal = '0';
            }
            this.cargarDatosFormulario(_servidorPublicoModel);

            this.cmbNivelEstudios = data.lstNivelEstudios;
            this.cmbEstadoCivil = data.lstEstadoCivil;
            this.cmbSexo = data.lstGenero;

            this.cargarDatosCombos(_servidorPublicoModel);

            this.usuariosService.getUsuariosByParam(this.idServidorPublico, "").subscribe(result => {
              let usuarioResult = result.response;
              this.dsRolUsuario = "";
              if (usuarioResult.length > 0) {
                this.dsRolUsuario = usuarioResult[0].ROL.DSROL;
              }
            });
          }, err => {
            console.error('Ocurrió un error ->', err);
          });

        } else {
          console.error('Ocurrio un error al consultar servicio de datos personales.');
          this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrió un error al obtener los datos personales.." });
          this.spinner.hide();
        }
      }, err => {
        console.error("ERROR ->", err);
        this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrió un error al obtener los datos personales." });
        this.spinner.hide();
      });
  }

  onShowMunicipios() {
    if (this.buscarMunicipios == true && this.datosPersonalesform.value['idEstado'] != "") {
      this.spinner.show();
      this.catalogosService.getMunicipiosByEstado('MEX', this.datosPersonalesform.value['idEstado']).subscribe(result => {
        this.cmbMunicipios = result.response;
        this.spinner.hide();
        this.buscarMunicipios = false;
      }, error => {
        this.spinner.hide();
      });
    }
  }

  onShowColonias() {
    if (this.buscarColonias == true && this.datosPersonalesform.value['idMunicipio'] != "") {
      this.spinner.show();
      this.generalesService.consultaCatalogoColonias(this.datosPersonalesform.value['idEstado'],
        this.datosPersonalesform.value['idMunicipio']).subscribe(result => {
          this.cmbColonias = [];
          for (let index = 0; index < result.response.length; index++) {
            let colonias = new Combo();
            colonias.label = result.response[index].NOMBRECOLONIA;
            colonias.value = result.response[index].IDCOLONIA;
            this.cmbColonias.push(colonias);
          }
          this.spinner.hide();
          this.buscarColonias = false;
        }, error => {
          this.spinner.hide();
        });
    }
  }

  ValidarEdicion() {
    this.boActualizaInfo = !this.boEnableElements;
  }
  //#endregion

  //#region Eventos
  /**
   * Cargar las colonias fiscales correspondientes
   * @author Desconocido
   * @version 1.0, 18/01/2022.
   * @param event Evento que dispara el control.
   */
  public cargaColoniasFiscales(event: any): void {
    this.cmbColoniasFiscales = [];
    this.buscarColoniasFiscales = true;
  }

  /**
   * Mostrar los municipios fiscales correspondientes
   * @author Desconocido
   * @version 1.0, 18/01/2022.
   */
  public onShowMunicipiosFiscales(): void {
    if (this.buscarMunicipiosFiscales == true &&
      this.datosPersonalesform.value['idEstadoFiscal'] != "") {
      this.spinner.show();
      this.catalogosService.getMunicipiosByEstado(
        'MEX',
        this.datosPersonalesform.value['idEstadoFiscal']
      ).subscribe(result => {
        this.cmbMunicipiosFiscales = result.response;
        this.spinner.hide();
        this.buscarMunicipiosFiscales = false;
      }, error => {
        this.spinner.hide();
        console.error(error);
      });
    }
  }

  public onShowColoniasFiscales(): void {
    if (this.buscarColoniasFiscales == true && this.datosPersonalesform.value['idMunicipioFiscal'] != "") {
      this.spinner.show();
      this.generalesService.consultaCatalogoColonias(
        this.datosPersonalesform.value['idEstadoFiscal'],
        this.datosPersonalesform.value['idMunicipioFiscal']
      ).subscribe(result => {
        this.cmbColoniasFiscales = [];
        for (let index = 0; index < result.response.length; index++) {
          let colonias = new Combo();
          colonias.label = result.response[index].NOMBRECOLONIA;
          colonias.value = result.response[index].IDCOLONIA;
          this.cmbColoniasFiscales.push(colonias);
        }
        this.spinner.hide();
        this.buscarColoniasFiscales = false;
      }, error => {
        this.spinner.hide();
      });
    }
  }

  /** 
   * Guardar los datos personales.
   * @author Desconocido.
   * @version 1.0, desconocido.
   * @version 1.2, 25/01/2022, Javier Alvarado, Envio de informacion de direccion fiscal.
  */
  public guardarDatosPersonales() {
    let datosPersonalesDTO = new DatosPersonalesDTO();
    datosPersonalesDTO.correoElectronico = this.datosPersonalesform.value['correoElectronico'];
    datosPersonalesDTO.telefono = this.datosPersonalesform.value['telefono'];
    datosPersonalesDTO.idEstadoCivil = this.datosPersonalesform.value['nombreEstadoCivil'];
    datosPersonalesDTO.idNivelEstudios = this.datosPersonalesform.value['idNivelEstudios'];
    //Direccion personal
    datosPersonalesDTO.idEstado = this.datosPersonalesform.value['idEstado'];
    datosPersonalesDTO.idMunicipio = this.datosPersonalesform.value['idMunicipio'];
    datosPersonalesDTO.numeroInterior = this.datosPersonalesform.value['numeroInterior'];
    datosPersonalesDTO.numeroExterior = this.datosPersonalesform.value['numeroExterior'];
    datosPersonalesDTO.codigoPostal = this.datosPersonalesform.value['codigoPostal'];
    datosPersonalesDTO.idColonia = this.datosPersonalesform.value['colonia'];
    datosPersonalesDTO.direcion = this.datosPersonalesform.value['calle'];
    //Direccion fiscal
    datosPersonalesDTO.idEstadoFiscal = this.datosPersonalesform.value['idEstadoFiscal'];
    datosPersonalesDTO.idMunicipioFiscal = this.datosPersonalesform.value['idMunicipioFiscal'];
    datosPersonalesDTO.idColoniaFiscal = this.datosPersonalesform.value['coloniaFiscal'];
    datosPersonalesDTO.direccionFiscal = this.datosPersonalesform.value['calleFiscal'];
    datosPersonalesDTO.numeroInteriorFiscal = this.datosPersonalesform.value['numeroInteriorFiscal'];
    datosPersonalesDTO.numeroExteriorFiscal = this.datosPersonalesform.value['numeroExteriorFiscal'];
    datosPersonalesDTO.codigoPostalFiscal = this.datosPersonalesform.value['codigoPostalFiscal'];


    this.datospersonalesService.actualizarDatosPersonalesGenerales(
      this.idServidorPublico,
      datosPersonalesDTO
    ).subscribe(() => {
      this.messageService.clear('c');
      this.messageService.clear('d');
      this.messageService.add({ key: 'd', severity: 'success', summary: "Resultado", detail: "Se actualizaron los datos de forma existosa." });
    }, err => {
      console.error('Ocurrió un error -> ', err);
      this.messageService.add({ key: 'd', severity: 'error', summary: "Resultado", detail: "Ocurrió un error, favor de contactar al área de soporte." });
    });


    this.bitacoraService.registrar(this.idServidorPublico, 1, 1).subscribe(() => {
    }, err => {
      console.error('Ocurrió un error ->', err);
      this.messageService.add({ severity: 'error', summary: "Resultado", detail: "Ocurrio un error al guardar en la bitacora" });
    });
  }
  //#endregion

}



