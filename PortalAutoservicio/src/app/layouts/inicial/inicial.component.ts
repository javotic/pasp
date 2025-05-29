import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ConsultaDatosServidorPublico } from 'src/app/models/consulta-datos-servidor-publico';
import { DatosPersonales } from 'src/app/models/datos-personales';

import SwiperCore, { Autoplay, Pagination, Navigation, Scrollbar, Swiper } from "swiper/core";


@Component({
  selector: 'app-inicial',
  templateUrl: './inicial.component.html',
  styleUrls: ['./inicial.component.css']
})
export class InicialComponent implements OnInit, AfterViewInit  {

  mySwiper: Swiper;

  isVisibleMenu = false;

  datosPersonales: DatosPersonales;
  consultaDatosServidorPublico: ConsultaDatosServidorPublico;

  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit() {
    this.mySwiper = new Swiper('.swiper-container');
  }

  changeVisibleMenu(hidenshow: boolean) {
    this.isVisibleMenu = false;
  }


  getDatosPersonales(datosPersonales: DatosPersonales){
    this.datosPersonales = datosPersonales;
  }

  getServidorPublico(consultaDatosServidorPublico: ConsultaDatosServidorPublico){
    this.consultaDatosServidorPublico = consultaDatosServidorPublico;
  }
  
}
