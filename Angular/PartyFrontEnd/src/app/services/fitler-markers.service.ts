import { Injectable } from '@angular/core';
import { NullInjector } from '@angular/core/src/di/injector';

@Injectable({
  providedIn: 'root'
})
export class FitlerMarkersService {

  constructor() { }

  filter = (markers, filterParams)=> {
    let filteredMarkers = markers;
    //filter by radius
      // ???
      
    //filter by date
    if(filterParams.startDate!= null  && filterParams.endDate!= null) {
      console.log('filtering by date');
      filteredMarkers = filteredMarkers.filter(marker=>{
        let partyDate = new Date(marker.get('partyDate'));
        console.log('date: ' + partyDate + ' start: ' + filterParams.startDate + ' end: ' + filterParams.endDate);
        return partyDate > filterParams.startDate && partyDate < filterParams.endDate;
      });
    }

    //filter by category
    if(filterParams.categories.length > 0) {
      console.log('filtering by category');
      console.log(filterParams.categories);
      
      filteredMarkers = filteredMarkers.filter(marker=>{
        return filterParams.categories.every((tag)=>{
          console.log(`is ${tag.value} in ${marker.get('partyTags')}?
                    ${marker.get('partyTags').indexOf(tag.value) > -1}`);
          return marker.get('partyTags').indexOf(tag.value) > -1;
        });
      });
    }

    //filter by name
    filteredMarkers = filteredMarkers.filter(marker=>{
      console.log('filtering by name');
      return marker.getTitle().toLowerCase().includes(filterParams.name.toLowerCase());
    });
    return filteredMarkers;
  }
}
