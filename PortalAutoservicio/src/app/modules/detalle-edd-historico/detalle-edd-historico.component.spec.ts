import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleEddHistoricoComponent } from './detalle-edd-historico.component';

describe('DetalleEddHistoricoComponent', () => {
  let component: DetalleEddHistoricoComponent;
  let fixture: ComponentFixture<DetalleEddHistoricoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetalleEddHistoricoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalleEddHistoricoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
