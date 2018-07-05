import { TestBed, inject } from '@angular/core/testing';

import { FitlerMarkersService } from './fitler-markers.service';

describe('FitlerMarkersService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FitlerMarkersService]
    });
  });

  it('should be created', inject([FitlerMarkersService], (service: FitlerMarkersService) => {
    expect(service).toBeTruthy();
  }));
});
