import { TestBed, inject } from '@angular/core/testing';

import { UpdateMarkerEventsService } from './update-marker-events.service';

describe('UpdateMarkerEventsService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UpdateMarkerEventsService]
    });
  });

  it('should be created', inject([UpdateMarkerEventsService], (service: UpdateMarkerEventsService) => {
    expect(service).toBeTruthy();
  }));
});
