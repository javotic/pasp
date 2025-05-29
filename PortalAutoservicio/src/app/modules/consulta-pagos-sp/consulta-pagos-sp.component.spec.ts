import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultaPagosSPComponent } from './consulta-pagos-sp.component';

describe('ConsultaPagosSPComponent', () => {
  let component: ConsultaPagosSPComponent;
  let fixture: ComponentFixture<ConsultaPagosSPComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConsultaPagosSPComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConsultaPagosSPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
