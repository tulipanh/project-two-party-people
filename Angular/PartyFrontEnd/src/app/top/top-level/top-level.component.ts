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
  loggedIn: boolean = true;

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
  }
}


