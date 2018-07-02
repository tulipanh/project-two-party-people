import { Component, OnInit, ViewChild } from '@angular/core';
import { SearchCoordinatesDataService } from '../../services/search-coordinates-data.service';
import { PartyHttpRequestService } from '../../services/party-http-request.service';
import { EventFilterService } from '../../services/event-filter.service';

import { } from '@types/googlemaps';

@Component({
  selector: 'app-map-view',
  templateUrl: './map-view.component.html',
  styleUrls: ['./map-view.component.css']
})

export class MapViewComponent implements OnInit {
  @ViewChild('gmap') gmapElement: any;
  map: google.maps.Map;
  prevCoordinates : google.maps.LatLng; 
  currentCoordinates : google.maps.LatLng;
  markers : google.maps.Marker[] = [];
  minLat: Number;
  maxLat: Number;
  minLong: Number;
  maxLong: Number;

  // inject coordinate service to get updated coordinates
  // inject service for get requests
  constructor(private geoData: SearchCoordinatesDataService,
              private partyRequest : PartyHttpRequestService,
              private eventFilters: EventFilterService) { 

  }

  ngOnInit() {
    this.createMap();
    this.listenForMapLoaded();    
    this.subscribeToCoordinateChanges();
    this.subscribeToFilterChanges();  
  }

  subscribeToFilterChanges = ()=> {
    this.eventFilters.currentFilterParams.subscribe(this.filterEvents);
  }

  filterEvents = (filterParams) => {
    console.log(filterParams);
  }

  createMap = ()=> {
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
  }

  listenForMapLoaded = ()=> {
    // whenever map finished loading, get the new bounds and get the markers
    // withing those bounds
    this.map.addListener('tilesloaded', ()=>{
      this.maxLat = this.map.getBounds().getNorthEast().lat();
      this.maxLong = this.map.getBounds().getNorthEast().lng();
      this.minLat = this.map.getBounds().getSouthWest().lat();
      this.minLong = this.map.getBounds().getSouthWest().lng();
      
      // add markers
      this.getMarkers();
    });
  }

  subscribeToCoordinateChanges = ()=> {
    // change center when new coordinates are pushed
    this.geoData.currentCoordinates.subscribe(this.centerMap);
  }

  // gets the data from the Observable
  getMarkers = ()=> {
    this.partyRequest
      .getPartiesByCoordinates(this.minLat, this.maxLat,this.minLong, this.maxLong)
      .subscribe((data) => {
        this.addMarkers(data, this);
      })
  }



  // centers map on coordinates passed in
  centerMap = (updatedCoordinates:google.maps.LatLng) => {
    this.map.panTo(updatedCoordinates);
  }

  
  // function that adds an array of markers
  addMarkers = (markerData, context) => {
    console.log(`there were ${this.markers.length} markers`);
    //clear the previous markers
    for(let marker of this.markers) {
      marker.setMap(null);
    }
    this.markers = [];
    //add all the new markers
    for(let marker of markerData) {
      // make a new marker option object
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
      context.markers.push(newMarker);
    }
    console.log(`there are now ${this.markers.length} markers`);
  }

  // if bounds increased, only add new markers from outside of the bounds
  boundsIncreased = ()=> {

  }

  //if the bounds decrease, only remove markers that are outside the new bounds

    }
