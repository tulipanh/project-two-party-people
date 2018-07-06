import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Secrets } from '../secrets';

@Injectable({
  providedIn: 'root'
})
export class PartyHttpRequestService {

  constructor(private http: HttpClient) {
    
  }

  baseUrl = Secrets.baseUrl;
  headers :HttpHeaders = new HttpHeaders().set("Access-Control-Allow-Origin", "*");


  getPartyById = (id: Number) => {

    return this.http.get(this.baseUrl+"party/"+id, {headers:{"Access-Control-Allow-Origin": "*"}}).subscribe((data)=>{
      console.log(data);
    });
  }

  getPartiesByCoordinates(minLat: any, maxLat:any, minLong: any, maxLong: any) {

    return this.http
    .get(this.baseUrl+"/party/local",
      { 
        params: {
          "minLat" : minLat,
          "minLong" : minLong,
          "maxLat" : maxLat,
          "maxLong" : maxLong
        } 
      });

  }

  getPartiesWithinRadius(latitude, longitude, radius) {
    return this.http.get(this.baseUrl+'/party/local/radius',
    {
      params: {
        latitude: latitude,
        longitude: longitude,
        radius: radius
      }
    }).toPromise();
  }


}
