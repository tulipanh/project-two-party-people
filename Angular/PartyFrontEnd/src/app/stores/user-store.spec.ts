import { TestBed, inject } from '@angular/core/testing';

import { UserStore } from './user-store';

describe('UserStoreService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [UserStore]
    });
  });

  it('should be created', inject([UserStore], (service: UserStore) => {
    expect(service).toBeTruthy();
  }));
});
