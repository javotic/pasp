import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DefaultModule } from './layouts/default/default.module';
import { DatosPersonalesComponent } from './modules/datos-personales/datos-personales.component';
import { DatosProfesionalesComponent } from './modules/datos-profesionales/datos-profesionales.component';
import { DatosBancariosComponent } from './modules/datos-bancarios/datos-bancarios.component';
import { HomeComponent } from './modules/home/home.component';
import { RecibosNominaComponent } from './modules/recibos-nomina/recibos-nomina.component';
import { ConsultaPagosComponent } from './modules/consulta-pagos/consulta-pagos.component';
import { IncidenciasTiempoComponent } from './modules/incidencias-tiempo/incidencias-tiempo.component';
import { ConstanciasNoAdeudoComponent } from './modules/constancias-no-adeudo/constancias-no-adeudo.component';
import { HistorialSalarialComponent } from './modules/historial-salarial/historial-salarial.component';
import { MovimientosFumpComponent } from './modules/movimientos-fump/movimientos-fump.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CalendarModule } from 'primeng/calendar';
import { PanelMenuModule } from 'primeng/panelmenu';
import { TableModule } from 'primeng/table';
import { CommonModule } from '@angular/common';
import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { TabViewModule } from 'primeng/tabview';
import { CodeHighlighterModule } from 'primeng/codehighlighter';
import { DropdownModule } from 'primeng/dropdown';
import { PerfilgeneralComponent } from './modules/perfilgeneral/perfilgeneral.component';
import { MenuModule } from 'primeng/menu';
import { MenuItem } from 'primeng/api';
//import { NgxUsefulSwiperModule } from 'ngx-useful-swiper';
import { MessagesModule } from 'primeng/messages';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ConfirmationService } from 'primeng/api';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FieldsetModule } from 'primeng/fieldset';
import { PanelModule } from 'primeng/panel';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { MessageModule } from 'primeng/message';
import { BnNgIdleService } from 'bn-ng-idle';
import { CountdownModule } from 'ngx-countdown';
import { MenubarModule } from 'primeng/menubar';
import { HistorialLaboralComponent } from './modules/historial-laboral/historial-laboral.component';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';
import { DateFormatPipe } from 'src/app/utils/DateFormatPipe ';
import { NgxSpinnerModule } from "ngx-spinner";
import { ValidatorCalendar } from 'src/app/utils/ValidatorCalendar';
import { TabMenuModule } from 'primeng/tabmenu';
import { RadioButtonModule } from 'primeng/radiobutton';
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
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { CardModule } from 'primeng/card';
import { TooltipModule } from 'primeng/tooltip';
import { StepsModule } from 'primeng/steps';
import { GeneralEscalafonComponent } from './modules/escalafon/general-escalafon/general-escalafon.component';
import { ProgressBarModule } from 'primeng/progressbar';
import { PreguntasFrecuentesEddComponent } from './modules/preguntas-frecuentes-edd/preguntas-frecuentes-edd.component';
import { AccordionModule } from 'primeng/accordion';
import { FileUploadModule } from 'primeng/fileupload';
import { CheckboxModule } from 'primeng/checkbox';

import { DetalleEddHistoricoComponent } from './modules/detalle-edd-historico/detalle-edd-historico.component';
import { DetalleEddUaHistoricoComponent } from './modules/detalle-edd-ua-historico/detalle-edd-ua-historico.component';

import { InputSwitchModule } from 'primeng/inputswitch';

import { UsuariosComponent } from './modules/admin-usuarios/components/usuarios/usuarios.component';
import { InicialModule } from './layouts/inicial/inicial.module';

import { SwiperModule } from 'swiper/angular';
import { MultiSelectModule } from 'primeng/multiselect';
import { ListboxModule } from 'primeng/listbox';
import { RecibosNominaSPComponent } from './modules/recibos-nomina-sp/recibos-nomina-sp.component';
import { ConsultaPagosSPComponent } from './modules/consulta-pagos-sp/consulta-pagos-sp.component';

import { ConstanciaAnualComponent } from './modules/constancia-anual/constancia-anual.component';
import { ConstanciaAnualSPComponent } from './modules/constancia-anual-sp/constancia-anual-sp.component';
import { HistorialLaboralSpComponent } from './modules/historial-laboral-sp/historial-laboral-sp.component';
import { ConstanciaNoAdeudoSpComponent } from './modules/constancia-no-adeudo-sp/constancia-no-adeudo-sp.component';
import { FortalecimientoSalarioComponent } from './modules/fortalecimiento-salario/fortalecimiento-salario.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    DatosPersonalesComponent,
    DatosProfesionalesComponent,
    DatosBancariosComponent,
    RecibosNominaComponent,
    ConsultaPagosComponent,
    IncidenciasTiempoComponent,
    ConstanciasNoAdeudoComponent,
    HistorialSalarialComponent,
    MovimientosFumpComponent,
    PerfilgeneralComponent,
    HistorialLaboralComponent,
    DateFormatPipe,
    ValidatorCalendar,
    EvaluacionDesempenoComponent,
    DetalleEddComponent,
    DetalleEddUaComponent,
    EjecucionEddAmbosComponent,
    EjecucionEddDesempenoComponent,
    EjecucionEddDemeritosComponent,
    ProcesoEscalafonarioComponent,
    ProcesoEscalafonarioVigenteComponent,
    MihistoricoComponent,
    ConsultaKPIComponent,
    QuizKpiComponent,
    MisEDDComponent,
    GeneralEscalafonComponent,
    PreguntasFrecuentesEddComponent,
    DetalleEddHistoricoComponent,
    DetalleEddUaHistoricoComponent,
    UsuariosComponent,
    RecibosNominaSPComponent,
    ConsultaPagosSPComponent,
    ConstanciaAnualComponent,
    ConstanciaAnualSPComponent,
    HistorialLaboralSpComponent,
    ConstanciaNoAdeudoSpComponent,
    FortalecimientoSalarioComponent
  ],
  imports: [
    FileUploadModule,
    BrowserModule,
    TabMenuModule,
    RadioButtonModule,
    AppRoutingModule,
    DefaultModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    CalendarModule,
    AccordionModule,
    HttpClientModule,
    PanelMenuModule,
    TableModule,
    DialogModule,
    CommonModule,
    ButtonModule,
    TabViewModule,
    CodeHighlighterModule,
    DropdownModule,
    DialogModule,
    MessagesModule,
    ConfirmDialogModule,
    MenuModule,
    //NgxUsefulSwiperModule,
    AutoCompleteModule,
    FieldsetModule,
    PanelModule,
    ToastModule,
    MessageModule,
    CountdownModule,
    MenubarModule,
    NgxSpinnerModule,
    PdfViewerModule,
    TooltipModule,
    StepsModule,
    CardModule,
    ProgressBarModule,
    CheckboxModule,

    InputSwitchModule,
    InicialModule,
    //BadgeModule,
    SwiperModule,
    MultiSelectModule,
    ListboxModule,
    LoggerModule.forRoot({
      serverLoggingUrl: '/api/logs',
      level: NgxLoggerLevel.DEBUG,
      serverLogLevel: NgxLoggerLevel.ERROR
    })

  ],
  providers: [ConfirmationService, MessageService, BnNgIdleService],
  bootstrap: [AppComponent]

})
export class AppModule { }