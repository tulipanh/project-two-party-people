import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Event } from "../../../models/Event";
import { GeocachingApiService } from '../../../services/geocaching-api.service';

@Component({
  selector: 'app-create',
  templateUrl: './create.component.html',
  styleUrls: ['./create.component.css']
})
export class CreateComponent implements OnInit {

  TAGS: Object[] = [
    {name: "Concert", id: 1},
    {name: "Lecture", id: 2},
    {name: "Social", id: 3},
    {name: "Workshop", id: 4},
    {name: "Party", id: 5},
    {name: "Food", id: 6},
    {name: "Alcohol", id: 7},
    {name: "Visual Art", id: 8},
    {name: "Performance Art", id: 9},
    {name: "Sports", id: 10},
    {name: "Outdoor", id: 11},
    {name: "Indoor", id: 12}
  ];

  inputEventName: string = "";
  inputEventDate: Date = null;
  inputStreet: string = "";
  inputCity: string = "";
  inputState: string = "";
  inputZipcode: string = "";
  inputDescription: string = "";
  inputCost: number = 0.00;
  inputPictureUrl: string = "";
  coordinates : {
    latitude:number,
    longitude: number
  } = { latitude: null, longitude: null};

  selectedTags = new FormControl();

  @Input() errorField: string = "";
  @Output() createEvent: EventEmitter<Event> = new EventEmitter();

  constructor(private geoService: GeocachingApiService) { }

  ngOnInit() {
  }

  handleAddressChange(address: google.maps.places.PlaceResult) {

    this.coordinates.latitude = address.geometry.location.lat();
    this.coordinates.longitude = address.geometry.location.lng();
    let addressComponents = address.address_components;
    for(let component of addressComponents) {
      switch(component.types[0]) {
        case "postal_code":
          this.inputZipcode = component.long_name;
          break;
        case "street_number":
          this.inputStreet = component.long_name;
          break;
        case "route":
          this.inputStreet +=  " " + component.long_name;
          break;
        case "locality":
          this.inputCity = component.long_name;
          break;
        case "administrative_area_level_1":
          this.inputState = component.long_name;
          break;

      }
    }

  }

  attemptCreateEvent() {

    /*
    TODO: Input validation and pre-submit error messages.
    */

    let ne = new Event();

    ne.partyId = null;
    ne.creator = null;
    ne.partyName = this.inputEventName;
    ne.partyDate = this.inputEventDate;
    ne.address = {
      streetName: this.inputStreet,
      city: this.inputCity,
      state: this.inputState,
      zipCode: this.inputZipcode,
      coordinates: this.coordinates
    };
    ne.description = this.inputDescription;
    ne.cost = this.inputCost;
    ne.pictureUrl = this.inputPictureUrl;
    ne.tagList = [];

    if (this.selectedTags.value) {
      for (let i of this.selectedTags.value) {
        ne.tagList.push({tagName: i});
      }
    }




    this.createEvent.next(ne);
  }

}
