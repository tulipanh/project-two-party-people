import { Injectable } from '@angular/core';
import { User } from '../models/User';
import { ApiService } from './api.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserDataService {

  constructor(private api: ApiService) {}

  addUser(user: User): Observable<User> {
    return this.api.createUser(user);
  }

  deleteUserById(userId: number): Observable<User> {
    return this.api.deleteUserById(userId);
  }

  updateUser(user: User): Observable<User> {
    return this.api.updateUser(user);
  }
  
  getAllUsers(): Observable<User[]> {
    return this.api.getAllUsers();
  }

  getUserById(userId: number): Observable<User> {
    return this.api.getUserById(userId);
  }

  login(username: string, password: string) {
    return this.api.login(username, password);
  }

}
