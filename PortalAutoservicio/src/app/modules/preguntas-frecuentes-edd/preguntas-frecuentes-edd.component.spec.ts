import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PreguntasFrecuentesEddComponent } from './preguntas-frecuentes-edd.component';

describe('PreguntasFrecuentesEddComponent', () => {
  let component: PreguntasFrecuentesEddComponent;
  let fixture: ComponentFixture<PreguntasFrecuentesEddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PreguntasFrecuentesEddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PreguntasFrecuentesEddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
