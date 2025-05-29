import { Component, OnInit } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { DatosPersonales } from 'src/app/models/datos-personales';
import { ConsultaDatosServidorPublico } from 'src/app/models/consulta-datos-servidor-publico';

@Component({
  selector: 'app-default',
  templateUrl: './default.component.html',
  styleUrls: ['./default.component.css']
})
export class DefaultComponent implements OnInit {
  display= true;
  isVisibleMenu = true;

  datosPersonales: DatosPersonales;
  consultaDatosServidorPublico: ConsultaDatosServidorPublico;
  constructor(
    ) {}

  ngOnInit(): void {
  }

  changeVisibleMenu(hidenshow: boolean) {
    this.isVisibleMenu = !this.isVisibleMenu;
  }


  getDatosPersonales(datosPersonales: DatosPersonales){
    this.datosPersonales = datosPersonales;
  }

  getServidorPublico(consultaDatosServidorPublico: ConsultaDatosServidorPublico){
    this.consultaDatosServidorPublico = consultaDatosServidorPublico;
  }
}
