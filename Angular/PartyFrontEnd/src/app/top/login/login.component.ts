import { Component, OnInit, EventEmitter, Output, Input } from '@angular/core';
import { User } from '../../models/User';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  inputUsername: string;
  inputPassword: string;
  @Input() errorMessage: string;
  @Output() loginEvent: EventEmitter<string[]> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  attemptLogin() {
    this.loginEvent.next([this.inputUsername, this.inputPassword]);
    this.inputUsername = "";
    this.inputPassword = "";
  }
}
