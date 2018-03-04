import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCriminalCasePageComponent } from './add-criminal-case-page.component';

describe('AddCriminalCasePageComponent', () => {
  let component: AddCriminalCasePageComponent;
  let fixture: ComponentFixture<AddCriminalCasePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCriminalCasePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCriminalCasePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
