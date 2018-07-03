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
      (user) => this._activeUser.next(user)
    )
    //Below is just for the fake backend I was running from localhost:3000
    // let users = this._allUsers.getValue();
    // for (let u of users) {
    //   if (u.username === username && u.password === password) {
    //     this._activeUser.next(u);
    //     this._loginError.next("");
    //     return
    //   }
    // }
    
    // this._loginError.next("Invalid Username or Password.");
  }

  logout() {
    this._activeUser.next(new User());
  }

  updateUser(id: number, fields) {
    // Do some validation on the fields
    let pass = fields.password;
    let age = fields.age;
    let add = fields.address;
    let email = fields.email;

    let updatedUser = new User(this._activeUser);
    if (pass.length > 0) {
      updatedUser.password = pass;
    }
    if (age > 0 && age < 200) {
      updatedUser.age = age;
    }
    if (add.length > 0) {
      updatedUser.address = add;
    }
    if (email.length > 0) {
      updatedUser.email = email;
    }
    // Send an update request through the UserDataService
    // Edit the activeUser based on the response
    // OR Refresh the activeUser with a GET request to the UserDataService

    // What will a failure look like?
    console.log("Update User: " + id + " with values: " + `${fields.password} ${fields.age} ${fields.address} ${fields.email}`);
    this.userService.updateUser(updatedUser).subscribe((user) => {
      if (user.username !== null && user.username !== undefined) {
        this._activeUser.next(user);
      }
    });
    //this.refreshActiveUser();
  }

  createUser(newUser: User) {
    // What will a failure look like?
    this.userService.addUser(newUser).subscribe((user) =>  {
      if (user.username !== null && user.username !== undefined) {
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

  getAllUsers() {
    this.userService.getAllUsers().subscribe(
      (users) => {
        this._allUsers.next(users);
        console.log(users);
      }
    );
  }


}
