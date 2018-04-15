import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DocumentationDenerationPageComponent } from './documentation-deneration-page.component';

describe('DocumentationDenerationPageComponent', () => {
  let component: DocumentationDenerationPageComponent;
  let fixture: ComponentFixture<DocumentationDenerationPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DocumentationDenerationPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DocumentationDenerationPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
