import { TestBed } from '@angular/core/testing';

import { MihistoricoService } from './mihistorico.service';

describe('MihistoricoService', () => {
  let service: MihistoricoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MihistoricoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
