import { Component, OnInit } from '@angular/core';
import { TopLevelActivity } from './TopLevelActivity';
import { UserStore } from '../../stores/user-store';
import { User } from '../../models/User';
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

  constructor(private userStore: UserStore, private interfaceStore: InterfaceStore) {}

  ngOnInit() {
    this.subscribeActiveUser();
    this.subscribeLoginError();
    this.subscribeActivity();
  }

  subscribeActiveUser() {
    this.userStore.activeUser.subscribe(user => {
      this.activeUser = user;
      if (this.currentActivity === TopLevelActivity.Login) this.switchToNone();
    });
  }

  subscribeLoginError() {
    this.userStore.loginError.subscribe(errTxt => this.loginError = errTxt);
  }

  subscribeActivity() {
    this.interfaceStore.topActivity.subscribe(activity => this.currentActivity = activity);
    this.interfaceStore.shroudState.subscribe(state => this.shroudOn = state);
  }

  logout() {
    this.userStore.logout();
  }

  attemptLogin(arg) {
    this.userStore.login(arg[0], arg[1]);
  }

  switchToCreate() {
    this.interfaceStore.setActivity(TopLevelActivity.Create);
  }

  switchToProfile() {
    this.interfaceStore.setActivity(TopLevelActivity.Profile);
  }

  switchToLogin() {
    this.interfaceStore.setActivity(TopLevelActivity.Login);
  }

  switchToRegister() {
    this.interfaceStore.setActivity(TopLevelActivity.Register);
  }

  switchToNone() {
    this.interfaceStore.setActivity(TopLevelActivity.None);
  }
}


