import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleEddUaHistoricoComponent } from './detalle-edd-ua-historico.component';

describe('DetalleEddUaHistoricoComponent', () => {
  let component: DetalleEddUaHistoricoComponent;
  let fixture: ComponentFixture<DetalleEddUaHistoricoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetalleEddUaHistoricoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalleEddUaHistoricoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
