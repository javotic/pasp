import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DefaultComponent } from './layouts/default/default.component';
import { HomeComponent } from './modules/home/home.component';
import { PostsComponent } from './modules/posts/posts.component';
import { DatosPersonalesComponent } from './modules/datos-personales/datos-personales.component';
import { DatosProfesionalesComponent } from './modules/datos-profesionales/datos-profesionales.component';
import { DatosBancariosComponent } from './modules/datos-bancarios/datos-bancarios.component';
import { RecibosNominaComponent } from './modules/recibos-nomina/recibos-nomina.component';
import { ConsultaPagosComponent } from './modules/consulta-pagos/consulta-pagos.component';
import { IncidenciasTiempoComponent } from './modules/incidencias-tiempo/incidencias-tiempo.component';
import { ConstanciasNoAdeudoComponent } from './modules/constancias-no-adeudo/constancias-no-adeudo.component';
import { HistorialSalarialComponent } from './modules/historial-salarial/historial-salarial.component';
import { MovimientosFumpComponent } from './modules/movimientos-fump/movimientos-fump.component';
import { PerfilgeneralComponent } from './modules/perfilgeneral/perfilgeneral.component';
import { HistorialLaboralComponent } from './modules/historial-laboral/historial-laboral.component';
import { HistorialLaboralSpComponent } from './modules/historial-laboral-sp/historial-laboral-sp.component'
import { EvaluacionDesempenoComponent } from './modules/evaluacion-desempeno/evaluacion-desempeno.component';
import { DetalleEddComponent } from './modules/detalle-edd/detalle-edd.component';
import { DetalleEddUaComponent } from './modules/detalle-edd-ua/detalle-edd-ua.component';
import { EjecucionEddAmbosComponent } from './modules/ejecucion-edd-ambos/ejecucion-edd-ambos.component';
import { EjecucionEddDesempenoComponent } from './modules/ejecucion-edd-desempeno/ejecucion-edd-desempeno.component';
import { EjecucionEddDemeritosComponent } from './modules/ejecucion-edd-demeritos/ejecucion-edd-demeritos.component';
import { ProcesoEscalafonarioComponent } from './modules/escalafon/proceso-escalafonario/proceso-escalafonario.component';
import { ProcesoEscalafonarioVigenteComponent } from './modules/escalafon/proceso-escalafonario-vigente/proceso-escalafonario-vigente.component';
import { MihistoricoComponent } from './modules/escalafon/mihistorico/mihistorico.component';
import { ConsultaKPIComponent } from './modules/consulta-kpi/consulta-kpi.component';
import { QuizKpiComponent } from './modules/quiz-kpi/quiz-kpi.component';
import { MisEDDComponent } from './modules/mis-edd/mis-edd.component';
import { GeneralEscalafonComponent } from './modules/escalafon/general-escalafon/general-escalafon.component';
import { PreguntasFrecuentesEddComponent } from './modules/preguntas-frecuentes-edd/preguntas-frecuentes-edd.component';
import { DetalleEddHistoricoComponent } from './modules/detalle-edd-historico/detalle-edd-historico.component';
import { DetalleEddUaHistoricoComponent } from './modules/detalle-edd-ua-historico/detalle-edd-ua-historico.component';
import { UsuariosComponent } from './modules/admin-usuarios/components/usuarios/usuarios.component';
import { InicialComponent } from './layouts/inicial/inicial.component';
import { RecibosNominaSPComponent } from './modules/recibos-nomina-sp/recibos-nomina-sp.component';
import { ConsultaPagosSPComponent } from './modules/consulta-pagos-sp/consulta-pagos-sp.component';

import { ConstanciaAnualComponent } from './modules/constancia-anual/constancia-anual.component';
import { ConstanciaAnualSPComponent } from './modules/constancia-anual-sp/constancia-anual-sp.component';
import { ConstanciaNoAdeudoSpComponent } from './modules/constancia-no-adeudo-sp/constancia-no-adeudo-sp.component';
import { FortalecimientoSalarioComponent } from './modules/fortalecimiento-salario/fortalecimiento-salario.component';

const routes: Routes = [

  {
    path: '',
    component: InicialComponent,
    children: [{
      path: 'home',
      component: HomeComponent
    }],
  },
  {
    path: '',
    component: DefaultComponent,
    children: [
      /* {
       path: 'home',
       component: HomeComponent
     }, */
      {
        path: 'posts',
        component: PostsComponent
      }, {
        path: 'datosPersonales',
        component: DatosPersonalesComponent
      }, {
        path: 'datosProfesionales',
        component: DatosProfesionalesComponent
      }, {
        path: 'datosBancarios',
        component: DatosBancariosComponent
      }, {
        path: 'recibosNomina',
        component: RecibosNominaComponent
      }, {
        path: 'consultaPagos',
        component: ConsultaPagosComponent
      }, {
        path: 'incidenciasTiempo',
        component: IncidenciasTiempoComponent
      }, {
        path: 'constanciaNoAdeudo',
        component: ConstanciasNoAdeudoComponent
      }, {
        path: 'historialSalarial',
        component: HistorialSalarialComponent
      }, {
        path: 'movimientosFUMP',
        component: MovimientosFumpComponent
      }
      , {
        path: 'perfilGeneral',
        component: PerfilgeneralComponent
      }
      , {
        path: 'historialLaboral',
        component: HistorialLaboralComponent
      }
      , {
        path: 'evaluacionDesempeno',
        component: EvaluacionDesempenoComponent
      }
      , {
        path: 'detalleEdd',
        component: DetalleEddComponent
      }
      , {
        path: 'detalleEddua',
        component: DetalleEddUaComponent
      }
      , {
        path: 'ejecucionEddambos',
        component: EjecucionEddAmbosComponent
      }
      , {
        path: 'ejecucionEdddesempeno',
        component: EjecucionEddDesempenoComponent
      }
      , {
        path: 'ejecucionEdddemeritos',
        component: EjecucionEddDemeritosComponent
      }, {
        path: 'procesoEscalafonario',
        component: ProcesoEscalafonarioComponent
      }, {
        path: 'procesoEscalafonariovigente',
        component: ProcesoEscalafonarioVigenteComponent
      }, {
        path: 'miHistorico',
        component: MihistoricoComponent
      }
      , {
        path: 'consultaKPI',
        component: ConsultaKPIComponent
      }
      , {
        path: 'quizKIP',
        component: QuizKpiComponent
      },
      {
        path: 'misEdd',
        component: MisEDDComponent
      }, {
        path: 'escalafon',
        component: GeneralEscalafonComponent
      },
      {
        path: 'preguntasFrecuentesEdd',
        component: PreguntasFrecuentesEddComponent
      },
      {
        path: 'detalleEddHistorico',
        component: DetalleEddHistoricoComponent
      }
      ,
      {
        path: 'detalleEdduaHistorico',
        component: DetalleEddUaHistoricoComponent
      },
      /*
      {
        path: 'adminUsuarios',
        loadChildren: ()=> import('./modules/admin-usuarios/admin-usuarios.module').then(m => m.AdminUsuariosModule)
      }
      */

      {
        path: 'adminUsuarios',
        component: UsuariosComponent
      },
      {
        path: 'recibosNominaSP',
        component: RecibosNominaSPComponent
      },
      {
        path: 'consultaPagosSP',
        component: ConsultaPagosSPComponent
      },
      {
        path: 'historialLaboralSP',
        component: HistorialLaboralSpComponent
      },
      {
        path: 'constanciaNoAdeudoSP',
        component: ConstanciaNoAdeudoSpComponent
      },
      {
        path: 'constanciaAnual',
        component: ConstanciaAnualComponent
      },
      {
        path: 'fortalecimiento-salario',
        component: FortalecimientoSalarioComponent
      }
    ]
  }
  ,
  {
    path: 'detalleEdduaHistorico',
    component: DetalleEddUaHistoricoComponent
  },
  /*
  {
    path: 'adminUsuarios',
    loadChildren: ()=> import('./modules/admin-usuarios/admin-usuarios.module').then(m => m.AdminUsuariosModule)
  }
  */

  {
    path: 'adminUsuarios',
    component: UsuariosComponent
  },
  {
    path: 'recibosNominaSP',
    component: RecibosNominaSPComponent
  },
  {
    path: 'consultaPagosSP',
    component: ConsultaPagosSPComponent
  },
  {
    path: 'constanciaAnualSP',
    component: ConstanciaAnualSPComponent
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
