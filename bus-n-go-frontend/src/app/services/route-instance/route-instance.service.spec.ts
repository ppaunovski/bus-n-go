import { TestBed } from '@angular/core/testing';

import { RouteInstanceService } from './route-instance.service';

describe('RouteInstanceService', () => {
  let service: RouteInstanceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RouteInstanceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
