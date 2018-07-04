import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class EventFilterService {

  constructor() { }




  private getStartingFilterParams = ()=> {
    return [];
  }

  private filterParamsSource = new BehaviorSubject(this.getStartingFilterParams());

  currentFilterParams = this.filterParamsSource.asObservable();

  private _updateFilterParams(filter) {
    this.filterParamsSource.next(filter);
  }

  updateRadius(radius: number) {
    this._updateFilterParams({type: 'radius', radius: radius});
  }

  updateStartDate(startDate: Date) {
    this._updateFilterParams({type: 'startDate', startDate: startDate});
  }

  updateEndDate(endDate: Date) {
    endDate.setHours(11, 59, 59, 99);
    this._updateFilterParams({type: 'endDate', endDate: endDate});
  }

  updateName(name: string) {
    this._updateFilterParams({type: 'name', name: name});
  }

  updateCategories(categories) {
    this._updateFilterParams({type: 'categories', categories: categories});
  }
}
