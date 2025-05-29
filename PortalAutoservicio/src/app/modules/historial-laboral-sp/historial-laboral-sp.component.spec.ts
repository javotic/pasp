import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { HistorialLaboralSpComponent } from './historial-laboral-sp.component';

describe('HistorialLaboralSpComponent', () => {
  let component: HistorialLaboralSpComponent;
  let fixture: ComponentFixture<HistorialLaboralSpComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ HistorialLaboralSpComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HistorialLaboralSpComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
