import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { Subject } from "rxjs";
import { BehaviorSubject } from "rxjs";
import { User } from "../models/User";
import { Event } from "../models/Event";
import { UserStore } from "./user-store";
import { InterfaceStore } from "./interface-store";
import { EventDataService } from "../services/event-data.service";

@Injectable({
  providedIn: 'root'
})
export class EventStore {

  private _activeEvent: BehaviorSubject<Event>;
  private _userCreatedEvents: BehaviorSubject<Event[]>;
  private _userRSVPEvents: BehaviorSubject<Event[]>;
  private _searchResults: BehaviorSubject<Event[]>;
  private _createError: BehaviorSubject<string> = new BehaviorSubject("");

  activeUser: User;

  constructor(private userStore: UserStore, private interfaceStore: InterfaceStore, private eventService: EventDataService) {
    let blankEvent = new Event();
    blankEvent['address'] = { streetName: '', city: '', state: '', zipCode: ''};
    blankEvent['attendees'] = [];
    blankEvent['tagList'] = [];
    blankEvent['creator'] = {};

    let cr: Event[] = [];
    let rs: Event[] = [];
    let se: Event[] = [];
    this._userCreatedEvents = new BehaviorSubject(cr);
    this._userRSVPEvents = new BehaviorSubject(rs);
    this._searchResults = new BehaviorSubject(se);
    this._activeEvent = new BehaviorSubject(blankEvent);

    this.subscribeActiveUser();
  }

  get activeEvent() {return this._activeEvent.asObservable();}
  get userCreatedEvents() {return this._userCreatedEvents.asObservable();}
  get userRSVPEvents() {return this._userRSVPEvents.asObservable();}
  get searchResults() {return this._searchResults.asObservable();}
  get createError() {return this._createError.asObservable();}

  setActiveEvent(eventId) {
    this.eventService.getEventById(eventId).subscribe((event) => this._activeEvent.next(event));
    this.interfaceStore.setActivity(5);
  }

  subscribeActiveUser() {
    this.userStore.activeUser.subscribe(user => {
      this.activeUser = user;
      this.refreshUserCreatedEvents();
      this.refreshUserRSVPEvents();
    });
  }

  refreshUserCreatedEvents() {

  }

  refreshUserRSVPEvents() {

  }

  createEvent(newEvent) {
    // TODO: What will a failed create look like?
    console.log("From EventStore: ");
    console.log(event);
    this.eventService.addEvent(newEvent).subscribe((event) => {
      let newList: Event[] = this._userCreatedEvents.getValue();
      newList.push(event);
      this._userCreatedEvents.next(newList);
    });
  }

  clearAllErrors() {
    this._createError.next("");
  }

}
