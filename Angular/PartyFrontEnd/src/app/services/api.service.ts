import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../models/User';
import { Event } from '../models/Event';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

const API_URL = environment.apiUrl;
const REAL = "http://testpipeline-env.cmsw7gc4gr.us-east-1.elasticbeanstalk.com";
const headers = new Headers({
  'Content-Type': 'application/json'
});

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: Http, private httpclient: HttpClient) {

  }

  public getAllUsers(): Observable<User[]> {
    return this.http
      .get(API_URL + '/users').pipe(
      map(response => {
        const users = response.json();
        return users.map((user) => new User(user));
      }));
  }

  public getUserById(userId: number): Observable<User> {
    return this.http
      .get(API_URL + '/users/' + userId).pipe(
      map(response => {
        return new User(response.json());
      }));
  }

  public createUser(user: User): any {
    console.log("Api Service: ");
    console.log(user);
    return this.http.post(REAL + "/user", user, {headers}).pipe(
      map(response => {
        console.log(response);
        let user = response.json();
        return new User(user);
      }
    ));
  }

  public updateUser(user: User): Observable<User> {
    console.log("Api Service: ");
    console.log(user);
    return this.http
      .put(REAL + '/user', user, {headers}).pipe(
      map(response => {
        return new User(response.json());
      }));
  }

  public deleteUserById(userId: number): Observable<null> {
    return this.http
      .delete(API_URL + '/users/' + userId).pipe(
      map(response => null));
  }

  public login(username: string, password: string): Observable<User> {
    console.log("Api Service: ");
    console.log(username + " " + password);
    return this.http
      .get(REAL + '/login' + "?username="+username+"&password="+password, {headers}).pipe(
      map(response => {
        // What do do with an empty response?
        console.log(response);
        let user = response.json();
        console.log(user);
        return new User(user);
      }));
  }

  public createEvent(newEvent: Event): Observable<Event> {
    console.log("Api Service creating: ");
    console.log(newEvent);
    return this.http.post(REAL + "/party", newEvent, {headers}).pipe(
      map(response => {
        console.log(response);
        let event = response.json();
        return new Event(event);
      })
    );
  }

  public handleError (error: Response | any) {
    console.error("ApiService::handleError", error);
    return Observable.throw(error);
  }

}
