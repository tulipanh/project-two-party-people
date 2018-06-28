import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

import { } from '@types/googlemaps';

@Injectable({
  providedIn: 'root'
})
export class SearchCoordinatesDataService {


  private coordinatesSource = new BehaviorSubject(this.getStartingCoordinates());
  currentCoordinates = this.coordinatesSource.asObservable();

  constructor() { }

  updateCoordinates(coordinates: google.maps.LatLng) {
    this.coordinatesSource.next(coordinates);
  }

  private getStartingCoordinates() : google.maps.LatLng {
    let coordinates =  new google.maps.LatLng(40.6974034, -74.1197636);

    if(navigator.geolocation) {   
      navigator.geolocation.getCurrentPosition(
        // success
        (position) => {
        this.updateCoordinates(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));
        })   
    } else {
      console.log("no navigator.geolocation");
    }
    return coordinates;
  }

  
  
}
