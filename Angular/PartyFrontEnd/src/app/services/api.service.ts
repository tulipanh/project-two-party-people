import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Http, Response } from '@angular/http';
import { User } from '../models/User';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

const API_URL = environment.apiUrl;

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
    return this.http
      .post(API_URL + '/users', user).pipe(
      map(response => {
        return new User(response.json());
      }));
  }

  public updateUser(user: User): Observable<User> {
    return this.http
      .put(API_URL + '/users/' + user.id, user).pipe(
      map(response => {
        return new User(response.json());
      }));
  }

  public deleteUserById(userId: number): Observable<null> {
    return this.http
      .delete(API_URL + '/users/' + userId).pipe(
      map(response => null));
  }

  public handleError (error: Response | any) {
    console.error("ApiService::handleError", error);
    return Observable.throw(error);
  }
}
