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

  private _activeUser: BehaviorSubject<User>;
  private _loginError: BehaviorSubject<string> = new BehaviorSubject("");
  private _registerError: BehaviorSubject<string> = new BehaviorSubject("");
  private _profileError: BehaviorSubject<string> = new BehaviorSubject("");
  
  constructor(private userService: UserDataService) {
    this._activeUser = new BehaviorSubject(new User({address:{}, eventsRSVP: [], creatorEvents: []}));
  }

  get activeUser() {
    return this._activeUser.asObservable();
  }

  get loginError() {
    return this._loginError.asObservable();
  }

  get registerError() {
    return this._registerError.asObservable();
  }

  get profileError() {
    return this._profileError.asObservable();
  }

  login(username: string, password: string) {
    this._loginError.next("");
    this.userService.login(username, password).subscribe(
      (user) => {
        console.log(user);
        this._activeUser.next(user);
      },
      (error) => this._loginError.next("Unable to log in with those credentials.")
    )
  }

  logout() {
    this._activeUser.next(new User({address:{}, eventsRSVP: [], creatorEvents: []}));
  }

  updateUser(updatedUser) {
    this._profileError.next("");
    this.userService.updateUser(updatedUser).subscribe(
      (user) => this._activeUser.next(user), 
      (error) => this._profileError.next("Could not update your profile with that information.")
    )
  }

  createUser(newUser: User) {
    this._registerError.next("");
    newUser.eventsRSVP = [];
    newUser.creatorEvents = [];
    this.userService.addUser(newUser).subscribe((user) =>  {
      if (user && user.username !== null && user.username !== undefined && user.username != "" ) {
        this._activeUser.next(user);
      }
    }, (error) => this._registerError.next("Could not create a new user with that information."));
  }

  refreshActiveUser() {
    this._loginError.next("");
    this.userService.getUserById(this._activeUser.getValue().personId).subscribe(
      (user) => {
        this._activeUser.next(user);
        console.log(user);
      }, (error) => this._loginError.next("Could not refresh use information.")
    );
  }

  clearAllErrors() {
    this._loginError.next("");
    this._profileError.next("");
    this._registerError.next("");
  }

}
