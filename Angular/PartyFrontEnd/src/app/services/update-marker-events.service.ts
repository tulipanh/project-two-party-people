import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class UpdateMarkerEventsService {

  constructor() { }

  private markersSource = new BehaviorSubject([]);
  currentMarkers = this.markersSource.asObservable();

  updateMarkers = (markers)=> {
    this.markersSource.next(markers);
  }


}
