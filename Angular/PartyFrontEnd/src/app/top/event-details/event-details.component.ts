import { Component, OnInit, Input, EventEmitter, Output} from '@angular/core';
import { EventStore } from "../../stores/event-store";
import { Event } from "../../models/Event";
import { User } from "../../models/User";

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit {

  @Input() activeEvent: Event;
  @Input() activeUser: User;
  @Input() errorField: string = null;
  @Output() setRSVPEvent: EventEmitter<boolean> = new EventEmitter();
  
  constructor() { }

  ngOnInit() {
  }

  userIsRSVPed() {
    try {
      return this.activeUser.eventsRSVP.filter((entry) => entry.partyId === this.activeEvent.partyId).length > 0;
    } catch (e) {
      return false;
    }
  }

  userExists() {
    return (this.activeUser && this.activeUser.personId);
  }

  setRSVP(val: boolean) {
    this.setRSVPEvent.next(val);
  }

  getEventDate() {
    return new Date(this.activeEvent.partyDate);
  }

  idToString(arg) {
    let strings = ["Concert", "Lecture", "Social", "Workshop", "Party", "Food", "Alcohol", "Visual Art", "Performance Art", "Sports", "Outdoor", "Indoor"];
    return strings[arg-1];
  }

  getImageUrl() {
    return this.activeEvent.pictureUrl ? this.activeEvent.pictureUrl : 'https://seda.college/wp-content/uploads/party.jpg';
  }
  
}
