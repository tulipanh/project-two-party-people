import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { USERS } from './mock-users';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  
  currentLoggedInUser: User;

  constructor() { }

  getUsers(): Observable<User[]> {
    return of(USERS);
  }

  setCurrent(user) {
    this.currentLoggedInUser = user;
  }
}
