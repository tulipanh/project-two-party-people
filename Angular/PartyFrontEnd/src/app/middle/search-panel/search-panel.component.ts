import { Component, OnInit, NgZone} from '@angular/core';
import { UpdateMarkerEventsService } from '../../services/update-marker-events.service';
import { EventOverview } from '../../models/EventOverview';
import { EventStore } from '../../stores/event-store';
import { Event } from '../../models/Event';
import { User } from '../../models/User';
import { UserStore } from '../../stores/user-store';

@Component({
  selector: 'app-search-panel',
  templateUrl: './search-panel.component.html',
  styleUrls: ['./search-panel.component.css']
})
export class SearchPanelComponent implements OnInit {

  hidden: boolean = false;
  toggleButtonContent: string = ">";
  events: EventOverview[] = [];
  userRSVPs: EventOverview[] = [];
  userCreated: EventOverview[] = [];
  activeUser: User;
  
  constructor(private updateMarkerEvent: UpdateMarkerEventsService, private zone: NgZone, private eventStore: EventStore, private userStore: UserStore) {
    
  }

  ngOnInit() {
    this.subscribeActiveUser();
    this.updateMarkerEvent.currentMarkers.subscribe((markers: google.maps.Marker[])=> {
      this.zone.run(()=>{    
        let tempEvents : EventOverview[] = [];

          for(let marker of markers) {
            let myEvent : EventOverview = {
              id: Number.parseInt(marker.get('partyId')),
              name: marker.getTitle(),
              date: new Date(marker.get('partyDate')),
              address: marker.get('address'),
              picture: marker.get('pictureUrl')? marker.get('pictureUrl') : 'https://seda.college/wp-content/uploads/party.jpg'
            }
            tempEvents.push(myEvent);
          }

          this.events = tempEvents;
          console.log(this.events);
        
      })
    });
    
  }

  subscribeActiveUser() {
    console.log("subscribing");
    this.userStore.activeUser.subscribe(user => {
      console.log("New active user from search panel.");
      this.activeUser = user;
      this.updateRSVPs();
      this.updateCreated();
    });
  }

  updateRSVPs() {
    for (let e of this.activeUser.eventsRSVP) {
      let newEO = new EventOverview();
      newEO.date = e.partyDate;
      newEO.address = e.address;
      newEO.id = e.partyId;
      newEO.picture = e.pictureUrl;
      newEO.name = e.partyName;
      this.userRSVPs.push(newEO);
    }
  }

  updateCreated() {
    for (let e of this.activeUser.creatorEvents) {
      let newEO = new EventOverview();
      newEO.date = e.partyDate;
      newEO.address = e.address;
      newEO.id = e.partyId;
      newEO.picture = e.pictureUrl;
      newEO.name = e.partyName;
      this.userCreated.push(newEO);
    }
  }

  toggleHide() {
    this.hidden = !(this.hidden);
    this.toggleButtonContent = this.hidden ? "<" : ">";
  }

  openDetails(eventId) {
    this.eventStore.setActiveEvent(eventId);
  }
}
