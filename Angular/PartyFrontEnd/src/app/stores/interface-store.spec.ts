import { TestBed, inject } from '@angular/core/testing';

import { InterfaceStore } from './interface-store';

describe('InterfaceStoreService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [InterfaceStore]
    });
  });

  it('should be created', inject([InterfaceStore], (service: InterfaceStore) => {
    expect(service).toBeTruthy();
  }));
});
