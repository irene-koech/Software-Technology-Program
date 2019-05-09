import { TestBed, inject } from '@angular/core/testing';

import { CurrentCategoryService } from './current-category.service';

describe('CurrentCategoryService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CurrentCategoryService]
    });
  });

  it('should be created', inject([CurrentCategoryService], (service: CurrentCategoryService) => {
    expect(service).toBeTruthy();
  }));
});
