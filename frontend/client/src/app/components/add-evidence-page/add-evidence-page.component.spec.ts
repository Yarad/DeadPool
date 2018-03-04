import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddEvidencePageComponent } from './add-evidence-page.component';

describe('AddEvidencePageComponent', () => {
  let component: AddEvidencePageComponent;
  let fixture: ComponentFixture<AddEvidencePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddEvidencePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddEvidencePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
