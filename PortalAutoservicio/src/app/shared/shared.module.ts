import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HeaderComponent } from './components/header/header.component';
import { SidebarCusComponent } from './components/sidebar/sidebar.component';
import { FooterComponent } from './components/footer/footer.component';
import { RouterModule } from '@angular/router';
import { FlexLayoutModule } from '@angular/flex-layout';
import {MenuModule} from 'primeng/menu';
import {MenuItem} from 'primeng/api';
import { ButtonModule } from 'primeng/button';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';
import { BnNgIdleService } from 'bn-ng-idle';
import { ConfirmationService } from 'primeng/api';
import { DialogModule } from 'primeng/dialog';
import { CountdownModule } from 'ngx-countdown';
import {SidebarModule} from 'primeng/sidebar';


@NgModule({
  declarations: [
    HeaderComponent,
    SidebarCusComponent,
    FooterComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    MenuModule,
    ButtonModule,
    ToastModule,
    DialogModule,
    CountdownModule,
    SidebarModule
  ],
  exports: [
    HeaderComponent,
    SidebarCusComponent,
    FooterComponent
  ],
  providers: [ConfirmationService, MessageService, BnNgIdleService]
})
export class SharedModule { }
