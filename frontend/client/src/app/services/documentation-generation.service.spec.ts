import { TestBed, inject } from '@angular/core/testing';

import { DocumentationGenerationService } from './documentation-generation.service';

describe('DocumentationGenerationService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [DocumentationGenerationService]
    });
  });

  it('should be created', inject([DocumentationGenerationService], (service: DocumentationGenerationService) => {
    expect(service).toBeTruthy();
  }));
});
