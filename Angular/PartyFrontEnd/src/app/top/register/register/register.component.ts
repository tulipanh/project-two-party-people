import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { User } from "../../../models/User";
@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  inputUsername: string = null;
  inputPassword: string = null;
  inputReenter: string = null;
  inputEmail: string = null;
  inputAge: number = null;
  inputStreet: string = null;
  inputCity: string = null;
  inputState: string = null;
  inputZipcode: string = null;
  @Input() errorField: string = null;
  @Output() registerEvent: EventEmitter<User> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  attemptRegister() {
    this.errorField = "";
    if (this.inputUsername === null || this.inputUsername === undefined) {
      this.errorField += "Username required.\n";
    }
    if (this.inputPassword === undefined || this.inputReenter === undefined) {
      this.errorField += "Password entry required.\n";
    }
    if (this.inputPassword === null || this.inputReenter === null) {
      this.errorField += "Password entry required.\n";
    }
    if (this.inputPassword !== this.inputReenter) {
      this.errorField += "Password fields must match.\n";
    }
    if (this.inputEmail === null || this.inputEmail == undefined) {
      this.errorField += "Email is required.\n";
    }
    if (this.errorField != "") {
      return;
    }

    let newUser: User = new User({
      "username": this.inputUsername,
      "password": this.inputPassword,
      "email": this.inputEmail,
      "age": this.inputAge,
      "address": {
        "streetName": this.inputStreet,
        "city": this.inputCity,
        "state": this.inputState,
        "zipCode": this.inputZipcode
      }
    });

    console.log(newUser);

    this.registerEvent.next(newUser);

  }

}
