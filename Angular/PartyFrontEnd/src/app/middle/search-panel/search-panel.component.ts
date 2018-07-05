import { Component, OnInit } from '@angular/core';
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
  // events: Event[] = [
  //   {partyId: 1, partyDate: new Date(7/27/2018), partyName: "Phased static application", pictureurl: "https://robohash.org/velitautemiusto.jpg?size=50x50&set=set1", address: "71 Hoffman Plaza", creatorid: 54},
  //   {id: 2, date: new Date(7/18/2018), name: "Business-focused coherent workforce", pictureurl: "https://robohash.org/nisietdignissimos.bmp?size=50x50&set=set1", address: "3369 Bunker Hill Circle", creatorid: 79},
  //   {id: 3, date: new Date(7/2/2018), name: "Cross-group context-sensitive synergy", pictureurl: "https://robohash.org/temporeetratione.png?size=50x50&set=set1", address: "0 Rusk Plaza", creatorid: 33}
  // ];
  
  constructor() { }

  ngOnInit() {
  }

  toggleHide() {
    this.hidden = !(this.hidden);
    this.toggleButtonContent = this.hidden ? "<" : ">";
  }

}
