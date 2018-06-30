import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {Subject} from "rxjs";
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class InterfaceStore {

  private _topActivity: BehaviorSubject<number> = new BehaviorSubject(0);
  private _shroudState: BehaviorSubject<boolean> = new BehaviorSubject(false);

  constructor() {

  }

  get topActivity() {
    return this._topActivity.asObservable();
  }

  get shroudState() {
    return this._shroudState.asObservable();
  }

  setActivity(activityNum: number) {
    if (activityNum > 4 || activityNum < 1) {
      this._topActivity.next(0);
      this._shroudState.next(false);
    } else {
      this._topActivity.next(activityNum);
      this._shroudState.next(true);
    }
  }
  
}
