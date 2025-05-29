import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConstanciaAnualComponent } from './constancia-anual.component';

describe('ConsultaPagosComponent', () => {
  let component: ConstanciaAnualComponent;
  let fixture: ComponentFixture<ConstanciaAnualComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConstanciaAnualComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConstanciaAnualComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
