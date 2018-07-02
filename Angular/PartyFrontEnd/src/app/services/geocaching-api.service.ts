import { Injectable } from '@angular/core';
import { } from '@types/googlemaps';



@Injectable({
  providedIn: 'root'
})
export class GeocachingApiService {

  geocoder: google.maps.Geocoder;


  getCoordsFromAddress(address: string){
    this.geocoder.geocode({'address':address},function(results,status){
        if (status.toString() == 'OK') {
          return results[0].geometry.location;
        }
    })
  }

  getAddressFromCords(coords: google.maps.LatLng){
    this.geocoder.geocode({'location':coords},function(results,status){
        if (status.toString() == 'OK') {
          return results[0].formatted_address;
        }
    })
  }

  constructor() { }
}
