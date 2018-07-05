import { TestBed, inject } from '@angular/core/testing';

import { GeocachingApiService } from './geocaching-api.service';

describe('GeocachingApiService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [GeocachingApiService]
    });
  });

  it('should be created', inject([GeocachingApiService], (service: GeocachingApiService) => {
    expect(service).toBeTruthy();
  }));
});
