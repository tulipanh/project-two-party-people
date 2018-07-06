import { Component, OnInit, ViewChild } from '@angular/core';
import { SearchCoordinatesDataService } from '../../services/search-coordinates-data.service';
import { PartyHttpRequestService } from '../../services/party-http-request.service';
import { EventFilterService } from '../../services/event-filter.service';

import { } from '@types/googlemaps';
import { FitlerMarkersService } from '../../services/fitler-markers.service';
import { UpdateMarkerEventsService } from '../../services/update-marker-events.service';
import { GeocachingApiService } from '../../services/geocaching-api.service';

@Component({
  selector: 'app-map-view',
  templateUrl: './map-view.component.html',
  styleUrls: ['./map-view.component.css']
})

export class MapViewComponent implements OnInit {
  @ViewChild('gmap') gmapElement: any;
  map: google.maps.Map;
  currentCoordinates : google.maps.LatLng;
  markers : google.maps.Marker[] = [];
  filteredMarkers : google.maps.Marker[] = [];
  filters = {
    radius: '',
    startDate: null,
    endDate: null,
    name: '',
    categories: []
  };

  minLat: Number;
  maxLat: Number;
  minLong: Number;
  maxLong: Number;

  // inject coordinate service to get updated coordinates
  // inject service for get requests
  constructor(private geoData: SearchCoordinatesDataService,
              private partyRequest : PartyHttpRequestService,
              private eventFilters: EventFilterService,
              private markerFilter: FitlerMarkersService,
              private updateMarkerEvents: UpdateMarkerEventsService,
              private geocaching: GeocachingApiService) { 
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
    switch(filterParams.type) {
      case 'startDate':
        this.filters.startDate = filterParams.startDate;
        break;
      case 'endDate':
        this.filters.endDate = filterParams.endDate;
        break;
      case 'radius':
        this.filters.radius = filterParams.radius;
        break;
      case 'name':
        this.filters.name = filterParams.name;
        break;
      case 'categories':  
        this.filters.categories = filterParams.categories;
        break;
    }

    this.filteredMarkers = this.markerFilter.filter(this.currentCoordinates, this.markers, this.filters);
    this.addMarkers(this.filteredMarkers);
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
      },
      minZoom: 8,
      maxZoom: 16,
    };

    // add map to dom
    this.map = new google.maps.Map(this.gmapElement.nativeElement, mapProp);
  }

  listenForMapLoaded = ()=> {
    // whenever map finished loading, get the new bounds and get the markers
    // withing those bounds
    this.map.addListener('tilesloaded', ()=>{
      console.log('boop');
      this.maxLat = this.map.getBounds().getNorthEast().lat();
      this.maxLong = this.map.getBounds().getNorthEast().lng();
      this.minLat = this.map.getBounds().getSouthWest().lat();
      this.minLong = this.map.getBounds().getSouthWest().lng();

      this.geoData.updateCoordinates(this.map.getCenter());
      this.filterEvents({type: 'radius', radius: this.filters.radius});
      
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
        this.addMarkerData(data);
      })
  }

  // centers map on coordinates passed in
  centerMap = (updatedCoordinates:google.maps.LatLng) => {
    this.currentCoordinates = updatedCoordinates;
    this.map.panTo(updatedCoordinates);
  }

  
  // function that adds an array of markers from data
  addMarkerData = (markerData) => {
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
      newMarker.set('partyId', marker.partyId);
      newMarker.set('partyDate', marker.partyDate);
      newMarker.set('address', marker.address);
      newMarker.set('pictureUrl', marker.pictureUrl);
      newMarker.setTitle(marker.partyName);
      newMarker.setLabel(marker.partyName.substring(0,1).toUpperCase());
     
      let tagList = [];
      for(let tag of marker.tagList) {
        tagList.push(tag.tagName);
      }
      newMarker.set('partyTags', tagList);

      // add a listener
      newMarker.addListener('click', (event)=> {
        let data = this.partyRequest.getPartyById(newMarker.get('partyId'));
        this.centerMap(event.latLng);
      });

      // add directly to map
      newMarker.setMap(this.map); 
      this.markers.push(newMarker);
    }
    this.updateMarkerEvents.updateMarkers(this.markers);
  }

  //function that adds markers from a list of markers
  addMarkers = (filteredMarkers: google.maps.Marker[])=> {
    for(let marker of this.markers) {
      marker.setMap(null);
    }
    for(let fMarker of filteredMarkers) {
      fMarker.setMap(this.map)
    }
    this.updateMarkerEvents.updateMarkers(filteredMarkers);

  }


}
