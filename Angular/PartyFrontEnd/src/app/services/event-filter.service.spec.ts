import { TestBed, inject } from '@angular/core/testing';

import { EventFilterService } from './event-filter.service';

describe('EventFilterService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EventFilterService]
    });
  });

  it('should be created', inject([EventFilterService], (service: EventFilterService) => {
    expect(service).toBeTruthy();
  }));
});
