import { NgModule } from '@angular/core';

import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgxUsefulSwiperModule } from 'ngx-useful-swiper';
import { CountdownModule } from 'ngx-countdown';
import { NgxSpinnerModule } from "ngx-spinner";
import { PdfViewerModule } from 'ng2-pdf-viewer';

import { BnNgIdleService } from 'bn-ng-idle';

import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';

@NgModule({
  declarations: [
  ],
  exports: [FormsModule, NgxSpinnerModule],
  imports: [
    
    FormsModule,
    NgxSpinnerModule,
   /* BrowserModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
    NgxUsefulSwiperModule,
    CountdownModule,
    ,
    PdfViewerModule,
    LoggerModule.forRoot({
      serverLoggingUrl: '/api/logs',
      level: NgxLoggerLevel.DEBUG,
      serverLogLevel: NgxLoggerLevel.ERROR
    })
    */
  ],
 // providers: [BnNgIdleService]
})
export class GeneralesModule { }
