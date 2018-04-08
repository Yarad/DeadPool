import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddParticipantPageComponent } from './add-participant-page.component';

describe('AddParticipantPageComponent', () => {
  let component: AddParticipantPageComponent;
  let fixture: ComponentFixture<AddParticipantPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddParticipantPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddParticipantPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
