import { Component, OnInit } from '@angular/core';
import { TopLevelActivity } from './TopLevelActivity';

@Component({
  selector: 'app-top-level',
  templateUrl: './top-level.component.html',
  styleUrls: ['./top-level.component.css']
})
export class TopLevelComponent implements OnInit {

  currentActivity: TopLevelActivity = TopLevelActivity.None;
  shroudOn: boolean = false;
  loggedIn: boolean = false;
  userSet: boolean = false;
  username: string;
  password: string;

  constructor() { }

  ngOnInit() {
  }
  
  switchToNone() {
    this.shroudOn = false;
    this.currentActivity = TopLevelActivity.None;
  }

  switchToLogin() {
    this.shroudOn = true;
    this.currentActivity = TopLevelActivity.Login;
  }

  switchToRegister() {
    this.shroudOn = true;
    this.currentActivity = TopLevelActivity.Register;
  }

  switchToProfile() {
    this.shroudOn = true;
    this.currentActivity = TopLevelActivity.Profile;
  }

  switchToCreate() {
    this.shroudOn = true;
    this.currentActivity = TopLevelActivity.Create;
  }

  logout() {
    this.loggedIn = false;
    this.username = "";
    this.password = "";
  }

  handleMyEvent(arg) {
    this.username = arg[0];
    this.password = arg[1];
    this.loggedIn = true;
    this.switchToNone();
  }
}


