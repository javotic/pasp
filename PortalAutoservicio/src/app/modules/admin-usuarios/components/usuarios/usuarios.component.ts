import { Component, OnInit, ViewChild } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';

import { FormGroup, FormBuilder, Validators  } from '@angular/forms';
import { MessageService } from 'primeng/api';
import { CatUsuarios } from 'src/app/models/Usuarios/cat-usuarios';
import { UsuariosService } from './../../../../services/usuarios.service';
import { CatRoles } from 'src/app/models/Usuarios/cat-roles';
import { ConfigProrroga } from 'src/app/models/Usuarios/config-prorroga';
import { AuthenticationServiceService } from 'src/app/services/authentication-service.service';
import { first } from 'rxjs/operators';
import { Combo, ComboDependencia } from 'src/app/models/combo';
import { forkJoin, Observable } from 'rxjs';
import { RespuestaServiceM4 } from 'src/app/repuesta/respuesta-service-m4';
import { EMPTY, empty, of } from "rxjs";
import { digest } from '@angular/compiler/src/i18n/digest';
@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

    //Elementos Consulta
    formConsulta: FormGroup;
    formEdicion: FormGroup;
    formProrroga: FormGroup;
    pre_valuesProrroga: ConfigProrroga;

    executeUpdate:boolean = false;
    esEdicionProrroga: boolean = false;

    paramProrroga: string = 'PROR';

    isErrorDias: boolean = false;

    idUsuarioSession: string = '';

  constructor(
    private usuariosService: UsuariosService,
    private messageService: MessageService,
    private spinner: NgxSpinnerService,
    private formBuilder: FormBuilder,
    private authenticationService: AuthenticationServiceService

  ) {  
    this.buildFormConsulta();
    this.buildFormEdicion('', null, '0', 0)
    this.buildformProrroga('', '', '');
  }

  

  listUsuarios: CatUsuarios[] = [];
  usuarioEdicion: CatUsuarios;

  roles: CatRoles[] = [];

  mostrarEditarUsuario: boolean = false;
  mostrarCambioPermiso: boolean = false;
  checked2:boolean = true;
  checked3:boolean = true;

  //Elementos para la seleccion de dependencias.
  lstDependencia: Combo[] = [];
  lstSubSecretaria: Combo[] = [];
  lstDirGeneral: Combo[] = [];

  selectSecretaria: string;
  selectSubSecretaria: string;
  selectDireccionG: string;

  HistDependencia: ComboDependencia[] = [];
  HistSubSecretaria: ComboDependencia[] = [];
  HistDirGeneral: ComboDependencia[] = [];

  BusByDependenciaSUB: string[] = [];
  BusByDependenciaDG: string[] = [];
  

  //Contiene los elementos que se van a buscar
  arrayBusqueda: string[] = [];

  idRolEdicion: number;


  lstSeleccionados: Combo[] = [];
  lstSelecionado: Combo;


  ngOnInit(): void {
    this.adminConfigProrroga(this.paramProrroga, '', '');
    this.idUsuarioSession = this.authenticationService.currentUserValue.CLAVESERVIDOR;
    
    //Inicializamos los catalogos de las unidades.
  }

  get idRol() { 
    console.log('Buscando el rol');
    console.log('Rol:' + this.formEdicion.get('idRol').value);
    console.log('Rol2:' +  JSON.stringify(this.formEdicion.get('idRol').value.IDROL));
    return this.formEdicion.get('idRol').value; 
  }

  adminConfigProrroga(claveParametro: string, noDias: string, dsusuario: string){
    this.spinner.show();
    this.usuariosService.adminConfigProrroga(claveParametro, noDias, dsusuario).subscribe(result=>{
      var jsonResult = JSON.stringify(result.response);
      this.pre_valuesProrroga = <ConfigProrroga>JSON.parse(jsonResult);

      this.buildformProrroga(this.pre_valuesProrroga.FCACTUALIZACION, 
                           this.pre_valuesProrroga.NODIAS, 
                            this.pre_valuesProrroga.DSUSUARIO);
      // fcupdate: string, noDias: number, dsusuario: string
      this.spinner.hide();
    }, error=>{
      console.log('Error al consultar la prorroga');
      this.spinner.hide();
    });
  }

  consultaUsuarios(event: Event){
    this.spinner.show();
    event.preventDefault();
    console.log('Se realiza la consulta de los usuarios');
    const value_con = this.formConsulta.value;
    if(value_con.clave == '' && value_con.nombre == ''){
      console.log('Sin parametros');
      this.listUsuarios = [];
      this.spinner.hide();
    }else{
      console.log('Consultando con parametros');
      this.usuariosService.getUsuariosByParam(value_con.clave, value_con.nombre).subscribe(result=>{
        this.listUsuarios = result.response;
        this.spinner.hide();
      }, error=>{
        this.listUsuarios = [];
        console.log('Error al ejecutar la consulta de usuarios');
        this.spinner.hide();
      });
    }
  }

  editarUsuario(usuario: CatUsuarios){
    this.spinner.show();

    this.lstSeleccionados = [];
    this.lstSubSecretaria = [];
    this.lstDirGeneral = [];
    this.selectSecretaria = null
    this.selectSubSecretaria = null
    this.selectDireccionG = null

    const rolUser = usuario.ROL.IDROL;
    this.idRolEdicion = rolUser;

    if(this.roles.length === 0){
      this.usuariosService.getRoles().subscribe(result=>{
        this.roles = result.response;
        console.log('roles:' + this.roles);

        this.mostrarEdicionUsuario(usuario);
        //this.spinner.hide();
        
        const rolesUnidades = [3, 4, 5];

        if(rolesUnidades.indexOf(rolUser) >= 0){
          this.usuariosService.getUsrUnidadesAsig(usuario.ID_PERSON).subscribe(result=>{
            this.lstSeleccionados = result.response;
          }, error=>{
            this.spinner.hide();
            this.mostrarMsgError('No se pudieron obtener los datos de la dependencia');
            this.ocultarEdicionUsuario();
          });
        }
        

        if(rolesUnidades.indexOf(rolUser) >= 0 && this.lstDependencia.length === 0){
          let ldtUnidadesD = this.usuariosService.getUnidades('', '1');
          forkJoin([ldtUnidadesD]).subscribe(result=>{
            this.lstDependencia = result[0].response;
            this.mostrarEdicionUsuario(usuario);
            this.spinner.hide();
          });

        }else{
          this.mostrarEdicionUsuario(usuario);
          this.spinner.hide();
        }
        

      }, error=>{
        this.spinner.hide();
        this.mostrarMsgError("No se pudieron obtener los datos de roles.");
      });
    }else{

      this.usuariosService.getUsrUnidadesAsig(usuario.ID_PERSON).subscribe(result=>{
        this.lstSeleccionados = result.response;
        this.spinner.hide();
      }, error=>{
        this.spinner.hide();
      });

      this.mostrarEdicionUsuario(usuario);

    }
  }

  mostrarEdicionUsuario(usuario: CatUsuarios){
    this.executeUpdate = false;
    this.mostrarEditarUsuario = true;
    this.usuarioEdicion = usuario;

    console.log(this.usuarioEdicion);
    const usRol = usuario.ROL.IDROL == 0 ? null: usuario.ROL;
    this.buildFormEdicion(usuario.ID_PERSON, usRol, usuario.BOACTIVO, 0)
  }

  ocultarEdicionUsuario(){
    this.mostrarEditarUsuario = false;
  }

  actualizarUsuario(event: Event){
    console.log('ejecuta actualizacion');
    event.preventDefault();
    this.executeUpdate = true;
    let dependenciasValid = true;

    if( this.lstSeleccionados.length == 0 && (this.idRolEdicion == 3 || this.idRolEdicion == 4 || this.idRolEdicion == 5)){
      dependenciasValid = false;
      this.mostrarMsgError("No ha agregado ninguna dependencia.");
    }else if(this.idUsuarioSession == ''){
      this.mostrarMsgError("No se pudo obtener el usuario que realiza la acción.");
    }


    if(this.formEdicion.valid && dependenciasValid){
      const values = this.formEdicion.value;
      let dependenciasUpd = '';
      this.lstSeleccionados.forEach(item=>{
        dependenciasUpd = dependenciasUpd + item.value + ',';
      });

      dependenciasUpd = dependenciasUpd.substring(0, dependenciasUpd.length - 1);

      this.usuariosService.UpdInsRolUsuario(values.clave, values.idRol.IDROL, values.boActivo?'1':'0', dependenciasUpd, this.idUsuarioSession).subscribe(result=>{
        if(String(result.response) != '0'){
          this.mostrarEditarUsuario = false;
          console.log('Los datos del usuario se actualizaron correctamente.');
          this.mostrarMsgExito("Los datos del usuario se actualizaron correctamente.");
          //Actualizamos los datos en pantalla
          this.listUsuarios.forEach(x=>{
            if(x.ID_PERSON === values.clave){
              x.ROL = values.idRol;
              x.BOACTIVO = (values.boActivo?'1':'0');
            }
          }) 
        }else{
          console.log('Error al actualizar los datos del usuario.');
          this.mostrarMsgError("Error al actualizar los datos del usuario.");
        }
      }, error=>{
        console.log('Error al actualizar los datos del usuario.');
        this.mostrarMsgError("Error al actualizar los datos del usuario.");
      }); 
    }
  }

  ActualizarProrroga(event: Event){
    event.preventDefault();
    this.executeUpdate = true;
    this.spinner.show();
    if(this.formProrroga.valid ){
      const values = this.formProrroga.value;
      let valDias: string = values.nodias;
     if(valDias.toString().indexOf('.')  <= 0){
      this.authenticationService.currentUser.pipe(first()).subscribe(usr => {
        this.usuariosService.adminConfigProrroga(this.paramProrroga, valDias, usr.CLAVESERVIDOR).subscribe(result=>{
          var jsonResult = JSON.stringify(result.response);
          this.pre_valuesProrroga = <ConfigProrroga>JSON.parse(jsonResult);
    
          this.buildformProrroga(this.pre_valuesProrroga.FCACTUALIZACION, 
                               this.pre_valuesProrroga.NODIAS, 
                                this.pre_valuesProrroga.DSUSUARIO);

          this.esEdicionProrroga = false;
          this.isErrorDias = false;
          this.mostrarMsgExito('Los datos se guardado correctamente.');
          this.spinner.hide();
        }, error=>{
          this.spinner.hide();
          this.mostrarMsgError('No se pudieron guardar los datos, inténtelo mas tarde.');
        });
      }, error=>{
        this.spinner.hide();
        this.mostrarMsgError('No se pudieron obtener los datos del usuario que realiza la actualización.');
      });
     }else{
      this.spinner.hide();
       this.isErrorDias = true;
     }
    }else{
      this.spinner.hide();
      this.isErrorDias = true;
    }
  }

  interactuaProrroga(isCancelar: boolean = false){
    this.isErrorDias = false;
    this.esEdicionProrroga = !this.esEdicionProrroga;
    if(isCancelar){
      console.log('Regresamos los dias');
      this.formProrroga.patchValue({
        nodias: this.pre_valuesProrroga.NODIAS, 
      });
    }
  }

  private buildFormConsulta() {
    this.formConsulta = this.formBuilder.group({
      clave: ['', []], //851851525
      nombre: ['', []],
    });
  }

  private buildformProrroga(fcupdate: string, noDias: string, dsusuario: string) {
    this.formProrroga = this.formBuilder.group({
      nodias: [noDias, [Validators.required]],
      fcupdate: [fcupdate, []],
      dsusuario: [dsusuario, []],
    });
  }

  private buildFormEdicion(clave: string, idRol: CatRoles, boactivo: string, idDependencia: number) {
    console.log('Identificador del rol:' + idRol);
    this.formEdicion = this.formBuilder.group({
      clave: [clave, [Validators.required]],
      idRol: [idRol, [Validators.required]],
      boActivo: [(boactivo == '0'? false: true), [Validators.required]],
    //  idDependencia: [idDependencia, [Validators.required]],

    });
  }

  onChangeRol(event){
    let changedValue = event.value;
    let domEvent = event.originalEvent;
    console.log('changedValue:' + changedValue.IDROL);
    console.log('domEvent:' + domEvent);

    this.idRolEdicion = changedValue.IDROL;
    //Limpiamos los elementos seleccionados.
    this.lstSeleccionados = [];
    this.selectSecretaria = null
    this.selectSubSecretaria = null
    this.selectDireccionG = null

    this.spinner.show();
    //Obtenemos los datos de 

    if(changedValue.IDROL == 3){
      if(this.lstDependencia.length == 0){
        this.usuariosService.getUnidades('', '1').subscribe(result=>{
          this.lstDependencia = result.response;
          this.spinner.hide();
        })
      }else{
        this.spinner.hide();
      }
    }else if(changedValue.IDROL == 4 || changedValue.IDROL == 5){
      this.lstSubSecretaria= [];
      this.lstDirGeneral= [];
    
      this.spinner.hide();
    }else{
      this.spinner.hide();
    }

  }

  onChangeSec(event){
    this.spinner.show();
    this.selectSecretaria = event.value;
    console.log('this.selectSecretaria:' + this.selectSecretaria);
    console.log('idROl:' + this.idRolEdicion)

    if(this.selectSecretaria == ''){
      this.lstSubSecretaria = [];
      this.spinner.hide();
    }else{
      if(this.idRolEdicion == 4 || this.idRolEdicion == 5){

        const seccionT = this.idRolEdicion == 4 ? 2 : 3; 

        this.limpiarSeleccion(this.selectSecretaria, seccionT);

        if(this.arrayBusqueda.length > 0){
          this.usuariosService.getUnidades(this.arrayBusqueda.join(), seccionT.toString()).subscribe(result=>{
            let resultSub: ComboDependencia[] = result.response;
            resultSub.forEach(x=>{
              if(seccionT == 2){
                this.HistSubSecretaria.push(x);
              }else{
                this.HistDirGeneral.push(x);
              }
              
            });
  
            this.gestionarListas(this.selectSecretaria, seccionT);
            this.spinner.hide();
          }, error=>{ 
            this.spinner.hide();
            console.log('No se pudierón obtener las subsecretarias:' + error);
          });
        }else{
          //Todos los elementos ya han sido consultados.
          this.gestionarListas(this.selectSecretaria, seccionT);
          this.spinner.hide();
        }

      }else{
        this.spinner.hide();
      }
    }
  }

  mostrarMsgError(mensaje:string){
    this.messageService.clear();
    this.messageService.add({ key: 'd', severity: 'error', summary: "Error", detail: mensaje });
  }

  mostrarMsgExito(mensaje:string){
    this.messageService.clear();
    this.messageService.add({ key: 'd', severity: 'success', summary: "", detail: mensaje });
  }

  limpiarSeleccion(seleccionadas: string, tipo: number){

    console.log('seleccionadas:' + seleccionadas);
    this.arrayBusqueda = [];
    const arraySelect = seleccionadas.toString().split(",");

    arraySelect.forEach(itemSelect =>{
      let findSelect: number = 0;
      let findBuscado: number = 0;
      if(tipo == 1){
        findSelect = this.HistDependencia.filter(x=> x.origen == itemSelect).length;
      }else if(tipo == 2){
        findSelect = this.HistSubSecretaria.filter(x=> x.origen == itemSelect).length;
        findBuscado = this.BusByDependenciaSUB.filter(x=> x == itemSelect).length;
        if(findBuscado == 0){
          this.BusByDependenciaSUB.push(itemSelect);
        }
        console.log('conteo:' + findSelect);
      }else if(tipo == 3){
        findSelect = this.HistDirGeneral.filter(x=> x.origen == itemSelect).length;
        findBuscado = this.BusByDependenciaDG.filter(x=> x == itemSelect).length;
        if(findBuscado == 0){
          this.BusByDependenciaDG.push(itemSelect);
        }
      }

      if(findSelect == 0 && findBuscado == 0){
        this.arrayBusqueda.push(itemSelect);
        
        console.log('Elementos de busqueda:' + this.arrayBusqueda);
      }

    });
    
    console.log('Array Consulta:' + this.arrayBusqueda);
  }

  gestionarListas(dependencia: string, tipo: number){
    console.log('this.dependencia:' + dependencia);
    
    switch(tipo){
      case 2: 
        this.lstSubSecretaria = [];

          this.HistSubSecretaria.forEach(itemhh=>{
            let itemCombo: Combo = new Combo;
            if(dependencia == itemhh.origen){
              itemCombo.label = itemhh.label;
              itemCombo.value = itemhh.value;
              this.lstSubSecretaria.push(itemCombo);
            }
          });
        break;
      case 3: 
      this.lstDirGeneral = [];
        this.HistDirGeneral.forEach(itemhh=>{
          let itemCombo: Combo = new Combo;
          if(dependencia == itemhh.origen){
            itemCombo.label = itemhh.label;
            itemCombo.value = itemhh.value;
            this.lstDirGeneral.push(itemCombo);
          }
        });     
      break;
    }
  }

  agregarElemento(){
    switch(this.idRolEdicion){
      case 3: 
        console.log('ItemSeleccionado:'+ this.selectSecretaria);

        if(this.selectSecretaria == undefined || this.selectSecretaria == null){
         console.log('No se ha seleccionado ningun elemento');
         this.mostrarMsgError('No se ha seleccionado ninguna Secretaria');
        }else{
          const countItems = this.lstSeleccionados.filter(x=>x.value === this.selectSecretaria).length;
          const itemSelect = this.lstDependencia.find(x=>x.value === this.selectSecretaria);
      
          if(countItems > 0){
            console.log('Esa unidad ya ha sido agregada.');
            this.mostrarMsgError('La unidad fue agregada con anterioridad, intente con otra opción.');
          }else{
            if(itemSelect === undefined){
              console.log('No se ha podido encontrar el elemento');
              this.mostrarMsgError('Ha ocurrido un error al intentar agregar la unidad.');
            }else{
              this.lstSeleccionados.push(itemSelect);
              this.selectSecretaria = null;
            }
          }
        }
      break;
      case 4: 
        console.log('ItemSeleccionado:'+ this.selectSubSecretaria);

        if(this.selectSubSecretaria == undefined || this.selectSubSecretaria == null){
         console.log('No se ha seleccionado ningun elemento');
         this.mostrarMsgError('No se ha seleccionado ninguna Sub Secretaria');
        }else{
          const countItems = this.lstSeleccionados.filter(x=>x.value === this.selectSubSecretaria).length;
          const itemSelect = this.lstSubSecretaria.find(x=>x.value === this.selectSubSecretaria);
      
          if(countItems > 0){
            console.log('Esa unidad ya ha sido agregada.');
            this.mostrarMsgError('La unidad fue agregada con anterioridad, intente con otra opción.');
          }else{
            if(itemSelect === undefined){
              this.mostrarMsgError('Ha ocurrido un error al intentar agregar la unidad.');
            }else{
              this.lstSeleccionados.push(itemSelect);
              this.selectSubSecretaria = null;
            }
          }
        }        
      break;
      case 5: 
        console.log('ItemSeleccionado:'+ this.selectDireccionG);

        if(this.selectDireccionG == undefined || this.selectDireccionG == null){
         console.log('No se ha seleccionado ningun elemento');
         this.mostrarMsgError('No se ha seleccionado ninguna Dirección General');
        }else{
          const countItems = this.lstSeleccionados.filter(x=>x.value === this.selectDireccionG).length;
          const itemSelect = this.lstDirGeneral.find(x=>x.value === this.selectDireccionG);
      
          if(countItems > 0){
            console.log('Esa unidad ya ha sido agregada.');
            this.mostrarMsgError('La unidad fue agregada con anterioridad, intente con otra opción.');
          }else{
            if(itemSelect === undefined){
              console.log('No se ha podido encontrar el elemento');
              this.mostrarMsgError('Ha ocurrido un error al intentar agregar la unidad.');
            }else{
              this.lstSeleccionados.push(itemSelect);
              this.selectDireccionG = null;
            }
          }
        }           
      break;
    }
  }

  quitarElemento(dependenciaDel: string){
    this.lstSeleccionados = this.lstSeleccionados.filter(x=> x.value != dependenciaDel);
  }

}



