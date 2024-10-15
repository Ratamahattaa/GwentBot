import { TestBed } from '@angular/core/testing';

import { SpyRootService } from './spy-root.service';

describe('SpyRootService', () => {
  let service: SpyRootService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SpyRootService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
