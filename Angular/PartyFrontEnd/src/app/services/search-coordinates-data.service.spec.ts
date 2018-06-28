import { TestBed, inject } from '@angular/core/testing';

import { SearchCoordinatesDataService } from './search-coordinates-data.service';

describe('SearchCoordinatesDataService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SearchCoordinatesDataService]
    });
  });

  it('should be created', inject([SearchCoordinatesDataService], (service: SearchCoordinatesDataService) => {
    expect(service).toBeTruthy();
  }));
});
