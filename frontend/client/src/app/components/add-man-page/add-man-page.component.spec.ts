import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddManPageComponent } from './add-man-page.component';

describe('AddManPageComponent', () => {
  let component: AddManPageComponent;
  let fixture: ComponentFixture<AddManPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddManPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddManPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
