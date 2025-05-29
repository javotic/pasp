import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RecibosNominaSPComponent } from './recibos-nomina-sp.component';

describe('RecibosNominaSPComponent', () => {
  let component: RecibosNominaSPComponent;
  let fixture: ComponentFixture<RecibosNominaSPComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RecibosNominaSPComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RecibosNominaSPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
