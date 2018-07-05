import { Component, OnInit, ViewChild } from '@angular/core';
import { GooglePlaceDirective } from 'ngx-google-places-autocomplete';

import { SearchCoordinatesDataService } from '../../services/search-coordinates-data.service';
import { } from '@types/googlemaps';



@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html',
  styleUrls: ['./search-bar.component.css']
})
export class SearchBarComponent implements OnInit {

  @ViewChild("placesRef") placesRef : GooglePlaceDirective;
   
  coordinates: google.maps.LatLng;

  

  constructor(private geoData: SearchCoordinatesDataService) { }

  ngOnInit() {
    this.geoData.currentCoordinates.subscribe(coordinates => this.coordinates = coordinates);
  }

  public handleAddressChange(address:google.maps.places.PlaceResult) {
    var latitude = address.geometry.location.lat();
    var longitude = address.geometry.location.lng();
    var newCoordinates = new google.maps.LatLng(latitude, longitude);
    this.geoData.updateCoordinates(newCoordinates);
  }

}
