import { TestBed, inject } from '@angular/core/testing';

import { DetectiveService } from './detective.service';

describe('DetectiveService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DetectiveService]
    });
  });

  it('should be created', inject([DetectiveService], (service: DetectiveService) => {
    expect(service).toBeTruthy();
  }));
});
