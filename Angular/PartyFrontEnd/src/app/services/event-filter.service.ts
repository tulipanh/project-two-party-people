import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventFilterService {

  constructor() { }


  private getStartingFilterParams = ()=> {
    return {
      radius: 10,
      categories: [],
      date: [],
      name: []
    }
  }

  private filterParamsSource = new BehaviorSubject(this.getStartingFilterParams());

  currentFilterParams = this.filterParamsSource.asObservable();

  updateFilterParams(newParams) {
    this.filterParamsSource.next(newParams);
  }
}
