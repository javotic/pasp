import { Component, OnInit } from '@angular/core';
import { ProcesoescalafonariovigenteService } from 'src/app/services/escalafon/procesoescalafonariovigente.service';
import { NgxSpinnerService } from 'ngx-spinner';

@Component({
  selector: 'app-general-escalafon',
  templateUrl: './general-escalafon.component.html',
  styleUrls: ['./general-escalafon.component.css']
})
export class GeneralEscalafonComponent implements OnInit {

  cargarComponentes = false;
  cargarComponenteProceso = false;
  activeTabIndex: number = 0;

  constructor(private service: ProcesoescalafonariovigenteService,
    private spinner: NgxSpinnerService) { }

  ngOnInit(): void {
    console.log('1');
    sessionStorage.removeItem('idProcesoVigente');
    this.service.getProcesoEscalafonarioVigente().subscribe(data => {
      if (data != null) {
        if (data.response != null) {
          if (data.response.length > 0) {
            sessionStorage.setItem('idProcesoVigente', data.response[0].idProcesoVigente);
            this.cargarComponentes = true;
            this.cargarComponenteProceso = true;
          }else{
            this.cargarComponenteProceso = true;
          } 
        }else{
          this.cargarComponenteProceso = true;
        } 
      }else{
        this.cargarComponenteProceso = true;
      }
    }, error=>{
      this.cargarComponenteProceso = true;
    });
  }

}
