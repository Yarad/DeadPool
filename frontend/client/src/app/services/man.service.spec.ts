import { TestBed, inject } from '@angular/core/testing';

import { ManService } from './man.service';

describe('ManService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ManService]
    });
  });

  it('should be created', inject([ManService], (service: ManService) => {
    expect(service).toBeTruthy();
  }));
});
