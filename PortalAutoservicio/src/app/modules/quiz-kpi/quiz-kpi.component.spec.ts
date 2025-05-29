import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { QuizKpiComponent } from './quiz-kpi.component';

describe('QuizKpiComponent', () => {
  let component: QuizKpiComponent;
  let fixture: ComponentFixture<QuizKpiComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ QuizKpiComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(QuizKpiComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
