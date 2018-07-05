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
    console.log('updating list of events');
    this.markersSource.next(markers);
  }


}
