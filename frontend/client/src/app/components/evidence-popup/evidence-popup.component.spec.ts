import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EvidencePopupComponent } from './evidence-popup.component';

describe('EvidencePopupComponent', () => {
  let component: EvidencePopupComponent;
  let fixture: ComponentFixture<EvidencePopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EvidencePopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EvidencePopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
