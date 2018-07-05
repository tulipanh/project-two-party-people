import { Component, OnInit, NgZone} from '@angular/core';
import { UpdateMarkerEventsService } from '../../services/update-marker-events.service';


import { EventOverview } from '../../models/EventOverview';
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
  events: EventOverview[] = [];
  
  constructor(private updateMarkerEvent: UpdateMarkerEventsService, private zone: NgZone) { }

  ngOnInit() {
    
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

  toggleHide() {
    this.hidden = !(this.hidden);
    this.toggleButtonContent = this.hidden ? "<" : ">";
  }

}
