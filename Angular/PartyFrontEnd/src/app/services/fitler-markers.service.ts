import { Injectable } from '@angular/core';
import { NullInjector } from '@angular/core/src/di/injector';
import { PartyHttpRequestService } from './party-http-request.service';

@Injectable({
  providedIn: 'root'
})
export class FitlerMarkersService {

  constructor( private httpParty: PartyHttpRequestService ) { }

  filter  = (latlng: google.maps.LatLng, markers, filterParams) => {

    let filteredMarkers : google.maps.Marker[] = markers;

    //filter by radius
    if(filterParams.radius) {
     
      filteredMarkers = filteredMarkers.filter(marker => {
        return 3963*Math.acos((Math.sin(marker.getPosition().lat() / 57.3) * 
        Math.sin(latlng.lat()/ 57.3))  + 
        (Math.cos(marker.getPosition().lat() / 57.3) * 
        Math.cos(latlng.lat()/ 57.3) *
        Math.cos(marker.getPosition().lng()/ 57.3 - latlng.lng()/57.3 ))) 
          < filterParams.radius
      });
     
      console.log('filteredByRadius');
      console.log(filteredMarkers);
    } 
    
    //filter by date
    if(filterParams.startDate != null) {
      filteredMarkers = filteredMarkers.filter(marker=>{
        let partyDate = new Date(marker.get('partyDate'));
        return partyDate > filterParams.startDate;
      });
    } 
    

    if (filterParams.endDate!= null) {
      filteredMarkers = filteredMarkers.filter(marker=>{
        let partyDate = new Date(marker.get('partyDate'));
        return  partyDate < filterParams.endDate;
      });
    }

    //filter by category
    if(filterParams.categories.length > 0) {
      filteredMarkers = filteredMarkers.filter(marker=>{
        return filterParams.categories.every((tag)=>{
          return marker.get('partyTags').indexOf(tag.value) > -1;
        });
      });
    }

    //filter by name
    filteredMarkers = filteredMarkers.filter(marker=>{
      return marker.getTitle().toLowerCase().includes(filterParams.name.toLowerCase());
    });
    return filteredMarkers;
  }
}
