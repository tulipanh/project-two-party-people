import { Component, OnInit, NgZone} from '@angular/core';
import { UpdateMarkerEventsService } from '../../services/update-marker-events.service';


import { Event } from '../../models/Event';
/* Need to figure out a way to auto-hide this 
    panel when the window is shrunk below a certain width.
*/
@Component({
  selector: 'app-search-panel',
  templateUrl: './search-panel.component.html',
  styleUrls: ['./search-panel.component.css']
})
export class SearchPanelComponent implements OnInit {

  hidden: boolean = false;
  toggleButtonContent: string = ">";
  events: Event[] = [
    //{id: 1, date: new Date(7/27/2018), name: "Phased static application", picture: "https://seda.college/wp-content/uploads/party.jpg", address: "71 Hoffman Plaza"},
    //{id: 2, date: new Date(7/18/2018), name: "Business-focused coherent workforce", picture: "https://seda.college/wp-content/uploads/party.jpg", address: "3369 Bunker Hill Circle"},
    //{id: 3, date: new Date(7/2/2018), name: "Cross-group context-sensitive synergy", picture: "https://seda.college/wp-content/uploads/party.jpg", address: "0 Rusk Plaza"}
  ];
  
  constructor(private updateMarkerEvent: UpdateMarkerEventsService, private zone: NgZone) { }

  ngOnInit() {
    
    this.updateMarkerEvent.currentMarkers.subscribe((markers: google.maps.Marker[])=> {
      this.zone.run(()=>{    
        let tempEvents : Event[] = [];
        if(markers.length != 0) {
          for(let marker of markers) {
            let myEvent : Event = {
              id: Number.parseInt(marker.get('partyId')),
              name: marker.getTitle(),
              date: new Date(marker.get('partyDate')),
              address: marker.get('address'),
              picture: marker.get('pictureUrl')
            }
            tempEvents.push(myEvent);
          }

          this.events = tempEvents;
          console.log(this.events);
        }
      })
    });
    
  }

  toggleHide() {
    this.hidden = !(this.hidden);
    this.toggleButtonContent = this.hidden ? "<" : ">";
  }

}
