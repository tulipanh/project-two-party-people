import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { User } from '../models/User';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

const API_URL = environment.apiUrl;
const REAL = "http://testpipeline-env.cmsw7gc4gr.us-east-1.elasticbeanstalk.com";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  constructor(private http: Http) {

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

  public createUser(user: User): Observable<User> {
    // let h = new Headers();
    // h.append("Access-Control-Allow-Origin", "*");
    // let ro = new RequestOptions();
    // ro.headers = h;
    return this.http
      .post(REAL + '/user-create', user).pipe(
      map(response => {
        let user = response.json();
        console.log(user);
        return new User(user);
      }));
  }

  public updateUser(user: User): Observable<User> {
    console.log("Api Service: " + user);
    return this.http
      .put(API_URL + '/users/' + user.personId, user).pipe(
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
    // let h = new Headers();
    // h.append("Access-Control-Allow-Origin", "*");
    return this.http
      .get(REAL + '/login' + "?username="+username+"&password="+password).pipe(
      map(response => {
        let user = response.json();
        console.log(user);
        return new User(user);
      }));
  }

  public handleError (error: Response | any) {
    console.error("ApiService::handleError", error);
    return Observable.throw(error);
  }

}
