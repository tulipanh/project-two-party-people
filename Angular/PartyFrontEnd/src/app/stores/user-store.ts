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
    let blankUser = new User();
    blankUser['address'] = {};
    this._activeUser = new BehaviorSubject(blankUser);
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
    this.userService.login(username, password).subscribe(
      // TODO: What does a failure look like here?
      // Answer: The body is empty.
      (user) => this._activeUser.next(user)
    )
  }

  logout() {
    this._activeUser.next(new User({address:{}}));
  }

  updateUser(updatedUser) {
    this.userService.updateUser(updatedUser).subscribe(
      // TODO: What does a failure look like here?
      (user) => this._activeUser.next(user)
    )
  }

  createUser(newUser: User) {
    // TODO: What will a failure look like?
    this.userService.addUser(newUser).subscribe((user) =>  {
      if (user && user.username !== null && user.username !== undefined && user.username != "" ) {
        this._activeUser.next(user);
      }
    });
  }

  refreshActiveUser() {
    this.userService.getUserById(this._activeUser.getValue().personId).subscribe(
      (user) => {
        this._activeUser.next(user);
        console.log(user);
      }
    );
  }

}
