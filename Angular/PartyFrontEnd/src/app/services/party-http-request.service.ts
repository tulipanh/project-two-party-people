import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Secrets } from '../secrets';

@Injectable({
  providedIn: 'root'
})
export class PartyHttpRequestService {

  constructor(private http: HttpClient) {
    
  }

  baseUrl = Secrets.baseUrl;

  getPartyById(id: Number) {

    console.log(`${this.baseUrl}/party-location/${id}`);
     return this.http.get(`${this.baseUrl}party-location/${id}`,{
       headers: {
         'Content-Type' : 'application/json',
         'Access-Control-Allow-Origin' : '*'
       }
     });

  }

  getPartiesByCoordinates(minLat: Number, maxLat:Number, minLong: Number, maxLong: Number) {}

  getPartiesByUser() {}

  postNewParty() {}

  updateParty() {}

  deleteParty() {}

}
