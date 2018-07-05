import { Component, OnInit, Input } from '@angular/core';
import { Event } from '../../models/Event';
import { EventOverview } from '../../models/EventOverview';

@Component({
  selector: 'app-event-tile',
  templateUrl: './event-tile.component.html',
  styleUrls: ['./event-tile.component.css']
})
export class EventTileComponent implements OnInit {

  @Input() event: EventOverview;

  constructor() { }

  ngOnInit() {
  }

}
