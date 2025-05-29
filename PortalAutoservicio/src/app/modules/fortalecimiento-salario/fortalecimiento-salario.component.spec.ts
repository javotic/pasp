import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FortalecimientoSalarioComponent } from './fortalecimiento-salario.component';

describe('FortalecimientoSalarioComponent', () => {
  let component: FortalecimientoSalarioComponent;
  let fixture: ComponentFixture<FortalecimientoSalarioComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FortalecimientoSalarioComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FortalecimientoSalarioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
