import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConstanciaAnualSPComponent } from './constancia-anual-sp.component';

describe('ConsultaPagosSPComponent', () => {
  let component: ConstanciaAnualSPComponent;
  let fixture: ComponentFixture<ConstanciaAnualSPComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConstanciaAnualSPComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConstanciaAnualSPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
