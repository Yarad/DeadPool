import { TestBed, inject } from '@angular/core/testing';

import { EvidenceOfCrimeService } from './evidence-of-crime.service';

describe('EvidenceOfCrimeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EvidenceOfCrimeService]
    });
  });

  it('should be created', inject([EvidenceOfCrimeService], (service: EvidenceOfCrimeService) => {
    expect(service).toBeTruthy();
  }));
});
