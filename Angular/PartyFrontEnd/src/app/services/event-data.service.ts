import { Injectable } from '@angular/core';
import { Event } from '../models/Event';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EventDataService {

  constructor(private api: ApiService) {}

  addEvent(event: Event): Observable<Event> {
    return this.api.createEvent(event);
  }

  getEventById(eventId: number): Observable<Event> {
    return this.api.getEventById(eventId);
  }

}
