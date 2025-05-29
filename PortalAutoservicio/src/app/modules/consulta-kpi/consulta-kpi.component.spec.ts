import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaKPIComponent } from './consulta-kpi.component';

describe('ConsultaKPIComponent', () => {
  let component: ConsultaKPIComponent;
  let fixture: ComponentFixture<ConsultaKPIComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultaKPIComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaKPIComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
