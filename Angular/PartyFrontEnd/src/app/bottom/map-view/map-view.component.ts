import { Component, OnInit, ViewChild } from '@angular/core';
import { SearchCoordinatesDataService } from '../../services/search-coordinates-data.service';

import { } from '@types/googlemaps';

@Component({
  selector: 'app-map-view',
  templateUrl: './map-view.component.html',
  styleUrls: ['./map-view.component.css']
})

export class MapViewComponent implements OnInit {
  @ViewChild('gmap') gmapElement: any;
  map: google.maps.Map;
  coordinates : google.maps.LatLng; 

  // inject coordinate service to get updated coordinates
  // inject service for get requests
  constructor(private geoData: SearchCoordinatesDataService) { }

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
    this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);
    this.geoData.currentCoordinates.subscribe(this.centerMap);

    // get markers

    // add markers
    this.addMarkers(this.markers);
    // listen for map changes to redraw markers
    this.map.addListener('idle', this.redrawMap);
    
  }

  // when the map bounds change, does stuff
  redrawMap = () => {
    //get new markers
    console.log('I am going to get new markers');
  }

  // centers map on coordinates passed in
  centerMap = (updatedCoordinates:google.maps.LatLng) => {
    this.map.panTo(updatedCoordinates);
  }

  
  // function that adds an array of markers
  addMarkers = ( markers) => {
    for(let marker of markers) {
     
      // make a new marker option objecy
      let newMarker = new google.maps.Marker();
      newMarker.setPosition(new google.maps.LatLng(marker.coordinates.lat, marker.coordinates.lng));
      newMarker.set('id', marker.id);
      newMarker.setTitle(marker.title);
      newMarker.setLabel(marker.title.substring(0,1));
     
      // add a listener
      newMarker.addListener('click', (event) => {
        // do something with the id 
        console.log(newMarker.get('id'));
        this.centerMap(event.latLng);
      });
      // add directly to map
      newMarker.setMap(this.map);
    }
  }


  // dummy data
  markers = [
    {
        id: 1,
        title: "Revature Party",
        label: 'R', //change to custom icon
        coordinates: {lat: 38.9534019, lng: -77.3527004}
    },
    {
        id: 2,
        title: "Dulles Greene Party",
        label: 'D',
        coordinates: {lat: 38.968193, lng: -77.4142168}
    },
    {
        id: 3,
        title: "Bowtie Movie Party",
        label: 'B',
        coordinates: {lat: 38.9590691, lng: -77.3581058}
    }
    ]
}
