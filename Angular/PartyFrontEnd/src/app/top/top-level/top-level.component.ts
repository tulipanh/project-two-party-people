import { Component, OnInit } from '@angular/core';
import { TopLevelActivity } from './TopLevelActivity';
import { UserStore } from '../../stores/user-store';
import { EventStore } from '../../stores/event-store';
import { User } from '../../models/User';
import { Event } from '../../models/Event';
import { InterfaceStore } from '../../stores/interface-store';

@Component({
  selector: 'app-top-level',
  templateUrl: './top-level.component.html',
  styleUrls: ['./top-level.component.css']
})
export class TopLevelComponent implements OnInit {

  currentActivity: TopLevelActivity = TopLevelActivity.None;
  shroudOn: boolean = false;
  activeUser: User;
  loginError: string;
  registerError: string;
  profileError: string;
  createError: string;

  constructor(private userStore: UserStore, private eventStore: EventStore, private interfaceStore: InterfaceStore) {}

  ngOnInit() {
    this.subscribeActiveUser();
    this.subscribeLoginError();
    this.subscribeRegisterError();
    this.subscribeProfileError();
    this.subscribeCreateError();
    this.subscribeActivity();
  }

  subscribeActiveUser() {
    this.userStore.activeUser.subscribe(user => {
      this.activeUser = user;
      if (this.currentActivity === TopLevelActivity.Login) this.switchToNone();
      if (this.currentActivity === TopLevelActivity.Register) this.switchToNone();
      if (this.currentActivity === TopLevelActivity.Profile) this.switchToNone();
    });
  }

  subscribeLoginError() {
    this.userStore.loginError.subscribe(errTxt => this.loginError = errTxt);
  }

  subscribeRegisterError() {
    this.userStore.registerError.subscribe(errTxt => this.registerError = errTxt);
  }

  subscribeProfileError() {
    this.userStore.profileError.subscribe(errTxt => this.profileError = errTxt);
  }

  subscribeCreateError() {
    this.eventStore.createError.subscribe(errTxt => this.createError = errTxt);
  }

  subscribeActivity() {
    this.interfaceStore.topActivity.subscribe(activity => this.currentActivity = activity);
    this.interfaceStore.shroudState.subscribe(state => this.shroudOn = state);
  }

  logout() {this.userStore.logout();}
  attemptLogin(arg) {this.userStore.login(arg[0], arg[1]);}
  attemptUpdate(arg) {this.userStore.updateUser(arg);}
  attemptRegister(arg) {this.userStore.createUser(arg);}
  attemptCreate(arg) {
    arg.creator = {personId: this.activeUser.personId, username: this.activeUser.username, email: this.activeUser.email};
    console.log(arg);
    this.eventStore.createEvent(arg);
    this.switchToNone();
  }

  switchToPartyDetails() {this.interfaceStore.setActivity(TopLevelActivity.Event);}
  switchToCreate() {this.interfaceStore.setActivity(TopLevelActivity.Create);}
  switchToProfile() {this.interfaceStore.setActivity(TopLevelActivity.Profile);}
  switchToLogin() {this.interfaceStore.setActivity(TopLevelActivity.Login);}
  switchToRegister() {this.interfaceStore.setActivity(TopLevelActivity.Register);}
  switchToNone() {this.interfaceStore.setActivity(TopLevelActivity.None);}
}


