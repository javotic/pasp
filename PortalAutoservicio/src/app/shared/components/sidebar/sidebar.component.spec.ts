import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebarCusComponent } from './sidebar.component';

describe('SidebarComponent', () => {
  let component: SidebarCusComponent;
  let fixture: ComponentFixture<SidebarCusComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SidebarCusComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SidebarCusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
