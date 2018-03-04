import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CrimePageComponent } from './crime-page.component';

describe('CrimePageComponent', () => {
  let component: CrimePageComponent;
  let fixture: ComponentFixture<CrimePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CrimePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CrimePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
