import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ConstanciaNoAdeudoSpComponent } from './constancia-no-adeudo-sp.component';

describe('ConstanciaNoAdeudoSpComponent', () => {
  let component: ConstanciaNoAdeudoSpComponent;
  let fixture: ComponentFixture<ConstanciaNoAdeudoSpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ConstanciaNoAdeudoSpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ConstanciaNoAdeudoSpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
