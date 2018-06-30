import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { User } from '../../models/User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usernameInput: string;
  passwordInput: string;
  @Input() errorMessage: string;
  @Output() loginEvent: EventEmitter<string[]> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  attemptLogin(username, password) {
    this.loginEvent.next([username, password]);
    this.usernameInput = "";
    this.passwordInput = "";
  }
}
