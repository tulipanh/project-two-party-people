import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { Subject } from "rxjs";
import { BehaviorSubject } from "rxjs";
import { User } from "../models/User";
import { UserDataService } from '../services/user-data.service';

@Injectable({
  providedIn: 'root'
})
export class UserStore {

  private _allUsers: BehaviorSubject<User[]> =  new BehaviorSubject([]);
  private _activeUser: BehaviorSubject<User> = new BehaviorSubject(new User());
  private _loginError: BehaviorSubject<string> = new BehaviorSubject("");
  
  constructor(private userService: UserDataService) {
    this.getAllUsers();
  }

  get activeUser() {
    return this._activeUser.asObservable();
  }

  get loginError() {
    return this._loginError.asObservable();
  }

  login(username: string, password: string) {
    let users = this._allUsers.getValue();
    for (let u of users) {
      if (u.username === username && u.password === password) {
        this._activeUser.next(u);
        this._loginError.next("");
        return
      }
    }
    this._loginError.next("Invalid Username or Password.");
  }

  logout() {
    this._activeUser.next(new User());
  }

  getAllUsers() {
    this.userService.getAllUsers().subscribe(
      (users) => {
        this._allUsers.next(users);
        console.log(users);
      }
    )
  }


}
