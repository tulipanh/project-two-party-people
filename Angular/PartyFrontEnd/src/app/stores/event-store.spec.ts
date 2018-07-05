import { TestBed, inject } from '@angular/core/testing';

import { EventStoreService } from './event-store.service';

describe('EventStoreService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [EventStoreService]
    });
  });

  it('should be created', inject([EventStoreService], (service: EventStoreService) => {
    expect(service).toBeTruthy();
  }));
});
