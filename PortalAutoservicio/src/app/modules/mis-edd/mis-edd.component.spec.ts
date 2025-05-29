import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MisEDDComponent } from './mis-edd.component';

describe('MisEDDComponent', () => {
  let component: MisEDDComponent;
  let fixture: ComponentFixture<MisEDDComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MisEDDComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MisEDDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
