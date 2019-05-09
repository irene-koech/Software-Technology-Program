import { Injectable } from '@angular/core';
import {BehaviorSubject} from 'rxjs/Rx';

@Injectable()
export class CurrentCategoryService {

  currentCategory: BehaviorSubject<string>= new BehaviorSubject<string>("");
  
  constructor() {}
}
