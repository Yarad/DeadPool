import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CriminalCasePageComponent } from './criminal-case-page.component';

describe('CriminalCasePageComponent', () => {
  let component: CriminalCasePageComponent;
  let fixture: ComponentFixture<CriminalCasePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CriminalCasePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CriminalCasePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
