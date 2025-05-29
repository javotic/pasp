import { NgModule } from '@angular/core';

import { CalendarModule } from 'primeng/calendar';
import { PanelMenuModule } from 'primeng/panelmenu';
import { TableModule } from 'primeng/table';

import { DialogModule } from 'primeng/dialog';
import { ButtonModule } from 'primeng/button';
import { TabViewModule } from 'primeng/tabview';
import { CodeHighlighterModule } from 'primeng/codehighlighter';
import { DropdownModule } from 'primeng/dropdown';
import { MenuModule } from 'primeng/menu';
import { MessagesModule } from 'primeng/messages';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { AutoCompleteModule } from 'primeng/autocomplete';
import { FieldsetModule } from 'primeng/fieldset';
import { PanelModule } from 'primeng/panel';
import { ToastModule } from 'primeng/toast';
import { MessageModule } from 'primeng/message';
import { MenubarModule } from 'primeng/menubar';
import {TabMenuModule} from 'primeng/tabmenu';
import {RadioButtonModule} from 'primeng/radiobutton';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import {CardModule} from 'primeng/card';
import {TooltipModule} from 'primeng/tooltip';
import {StepsModule} from 'primeng/steps';
import {AccordionModule} from 'primeng/accordion';
import {FileUploadModule} from 'primeng/fileupload';
import {CheckboxModule} from 'primeng/checkbox';
import {InputSwitchModule} from 'primeng/inputswitch';

import { ConfirmationService } from 'primeng/api';
import { MessageService } from 'primeng/api';


@NgModule({
  declarations: [],
  exports: [
    FileUploadModule,
    TabMenuModule,
    RadioButtonModule,
    CalendarModule,
    AccordionModule,
    PanelMenuModule,
    TableModule,
    DialogModule,
    ButtonModule,
    TabViewModule,
    CodeHighlighterModule,
    DropdownModule,
    DialogModule,
    MessagesModule,
    ConfirmDialogModule,
    MenuModule,
    AutoCompleteModule,
    FieldsetModule,
    PanelModule,
    ToastModule,
    MessageModule,
    MenubarModule,
    PdfViewerModule,
    TooltipModule,
    StepsModule,
    CardModule,
    CheckboxModule,
    InputSwitchModule,
  ],
  imports: [
    FileUploadModule,
    TabMenuModule,
    RadioButtonModule,
    CalendarModule,
    AccordionModule,
    PanelMenuModule,
    TableModule,
    DialogModule,
    ButtonModule,
    TabViewModule,
    CodeHighlighterModule,
    DropdownModule,
    DialogModule,
    MessagesModule,
    ConfirmDialogModule,
    MenuModule,
    AutoCompleteModule,
    FieldsetModule,
    PanelModule,
    ToastModule,
    MessageModule,
    MenubarModule,
    PdfViewerModule,
    TooltipModule,
    StepsModule,
    CardModule,
    CheckboxModule,
    InputSwitchModule,
    
  ],
  providers: [ConfirmationService, MessageService]
})
export class PrimengModule { }
