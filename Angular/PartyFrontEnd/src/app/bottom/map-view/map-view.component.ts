import { Component, OnInit, ViewChild } from '@angular/core';
import { SearchCoordinatesDataService } from '../../services/search-coordinates-data.service';
import { PartyHttpRequestService } from '../../services/party-http-request.service';

import { } from '@types/googlemaps';
import { MergeMapSubscriber } from 'rxjs/internal/operators/mergeMap';
import { GooglePlaceDirective } from 'ngx-google-places-autocomplete';

@Component({
  selector: 'app-map-view',
  templateUrl: './map-view.component.html',
  styleUrls: ['./map-view.component.css']
})

export class MapViewComponent implements OnInit {
  @ViewChild('gmap') gmapElement: any;
  map: google.maps.Map;
  coordinates : google.maps.LatLng; 
  markers : google.maps.Marker[];
  minLat;
  maxLat;
  minLong;
  maxLong;



  // inject coordinate service to get updated coordinates
  // inject service for get requests
  constructor(private geoData: SearchCoordinatesDataService, private partyRequest : PartyHttpRequestService) { }

  ngOnInit() {
    // initial map properties
    var mapProp = {
      center: new google.maps.LatLng(38, -77),
      zoom: 15,
      mapTypeId: google.maps.MapTypeId.ROADMAP,
      mapTypeControlOptions: {
        style: google.maps.MapTypeControlStyle.HORIZONTAL_BAR,
        position: google.maps.ControlPosition.BOTTOM_RIGHT
      }
    };
    
    // add map to dom
    this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);

    // get bounds when loaded
    this.map.addListener('tilesloaded', ()=>{
      this.maxLat = this.map.getBounds().getNorthEast().lat();
      this.maxLong = this.map.getBounds().getNorthEast().lng();
      this.minLat = this.map.getBounds().getSouthWest().lat();
      this.minLong = this.map.getBounds().getSouthWest().lng();
    
      this.getMarkers();
    });

    // 
    
    //
    this.geoData.currentCoordinates.subscribe(this.centerMap);

    
  }

  getMarkers = ()=> {
    this.partyRequest
      .getPartiesByCoordinates(this.minLat, this.maxLat,this.minLong, this.maxLong)
      .then((data) => {
        this.addMarkers(data);
      })
  }

  // centers map on coordinates passed in
  centerMap = (updatedCoordinates:google.maps.LatLng) => {
    this.map.panTo(updatedCoordinates);
  }

  
  // function that adds an array of markers
  addMarkers = (markers) => {

    //clear the previous markers

    for(let marker of markers) {
     
      // make a new marker option objecy
      let newMarker = new google.maps.Marker();
      newMarker.setPosition(new google.maps.LatLng(marker.address.coordinates.latitude, marker.address.coordinates.longitude));
      newMarker.set('id', marker.partyId);
      newMarker.setTitle(marker.partyName);
      newMarker.setLabel(marker.partyName.substring(0,1));
     
      // add a listener
      newMarker.addListener('click', (event) => {
        // do something with the id 
       let id = newMarker.get('id');
        this.centerMap(event.latLng)   
    }
  );
      // add directly to map
      newMarker.setMap(this.map);
      
    }
  }

  // dummy data
  // markers = [
  //   {
  //       id: 1,
  //       title: "Revature Party",
  //       label: 'R', //change to custom icon
  //       coordinates: {lat: 38.9534019, lng: -77.3527004}
  //   },
  //   {
  //       id: 2,
  //       title: "Dulles Greene Party",
  //       label: 'D',
  //       coordinates: {lat: 38.968193, lng: -77.4142168}
  //   },
  //   {
  //       id: 3,
  //       title: "Bowtie Movie Party",
  //       label: 'B',
  //       coordinates: {lat: 38.9590691, lng: -77.3581058}
  //   }
  //   ]

    }
