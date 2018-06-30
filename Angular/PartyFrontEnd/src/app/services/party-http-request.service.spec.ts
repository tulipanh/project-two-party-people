import { TestBed, inject } from '@angular/core/testing';

import { PartyHttpRequestService } from './party-http-request.service';

describe('PartyHttpRequestService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PartyHttpRequestService]
    });
  });

  it('should be created', inject([PartyHttpRequestService], (service: PartyHttpRequestService) => {
    expect(service).toBeTruthy();
  }));
});
