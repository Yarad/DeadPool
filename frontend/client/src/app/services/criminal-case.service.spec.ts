import { TestBed, inject } from '@angular/core/testing';

import { CriminalCaseService } from './criminal-case.service';

describe('CriminalCaseService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CriminalCaseService]
    });
  });

  it('should be created', inject([CriminalCaseService], (service: CriminalCaseService) => {
    expect(service).toBeTruthy();
  }));
});
