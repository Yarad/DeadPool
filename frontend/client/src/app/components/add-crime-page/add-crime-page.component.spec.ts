import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCrimePageComponent } from './add-crime-page.component';

describe('AddCrimePageComponent', () => {
  let component: AddCrimePageComponent;
  let fixture: ComponentFixture<AddCrimePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddCrimePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCrimePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
