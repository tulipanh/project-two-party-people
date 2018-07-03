import { Component, OnInit, Input,EventEmitter, Output } from '@angular/core';
import { User } from '../../../models/User';
import { isNumber } from 'util';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  inputUsername: string = null;
  usernameEdit: boolean = false;
  inputPassword: string = null;
  inputReenter: string = null;
  passwordEdit: boolean = false;
  inputEmail: string = null;
  emailEdit: boolean = false;
  inputAge: number = null;
  ageEdit: boolean = false;
  inputStreet: string = null;
  streetEdit: boolean = false;
  inputCity: string = null;
  cityEdit: boolean = false;
  inputState: string = null;
  stateEdit: boolean = false;
  inputZipcode: string = null;
  zipcodeEdit: boolean = false;
  keepText: string = "Un-Edit";
  editText: string = "Edit";

  @Input() activeUser: User;
  @Input() errorField: string = null;
  @Output() updateEvent: EventEmitter<User> = new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  userString() {
    return JSON.stringify(this.activeUser);
  }

  updateInfo() {
    this.errorField = "";
    let newInfo = new User(this.activeUser);
    let edited = this.usernameEdit || this.passwordEdit || this.emailEdit || this.ageEdit || 
                  this.streetEdit || this.cityEdit || this.stateEdit || this.zipcodeEdit;
    if (this.usernameEdit && this.usernameValid()) newInfo.username = this.inputUsername;
    if (this.passwordEdit && this.passwordValid()) newInfo['password'] = this.inputPassword;
    if (this.emailEdit && this.emailValid()) newInfo['email'] = this.inputEmail;
    if (this.ageEdit && this.ageValid()) newInfo['age'] = this.inputAge;
    if (this.streetEdit && this.streetValid()) newInfo.address['streetName'] = this.inputStreet;
    if (this.cityEdit && this.cityValid()) newInfo.address['city'] = this.inputCity;
    if (this.stateEdit && this.stateValid()) newInfo.address['state'] = this.inputState;
    if (this.zipcodeEdit && this.zipcodeValid()) newInfo.address['zipCode'] = this.inputZipcode;

    if (edited && this.errorField === "") {
      this.updateEvent.next(newInfo);
    }
  }

  usernameValid() {
    if(this.inputUsername !== null && this.inputUsername !== undefined && this.inputUsername !== "") return true;
      this.errorField += "Username cannot be empty./n";
      return false;
  }

  passwordValid() {
    let returnVal: boolean;
    if (this.inputPassword === undefined || this.inputReenter === undefined) {
      this.errorField += "Password cannot be empty.\n"
      returnVal = returnVal && false;
    }
    if (this.inputPassword === null || this.inputReenter === null) {
      this.errorField += "Password cannot be empty.\n"
      returnVal = returnVal && false;
    }
    if (this.inputPassword !== this.inputReenter) {
      this.errorField += "Password fields must match.\n";
      returnVal = returnVal && false;
    }
    return returnVal;
  }

  emailValid() {
    // TODO: Set this up with an email regex check
    if (this.inputEmail !== null && this.inputEmail !== undefined && true) return true;
    this.errorField += "Invalid Email.\n";
    return false;
  }
  
  ageValid() {
    if (isNumber(this.inputAge) && this.inputAge < 151 && this.inputAge > 12) return true;
    this.errorField += "Invalid Age.\n";
    return false;
  }

  streetValid() {
    if (this.inputStreet != null && this.inputStreet != undefined && this.inputStreet.length > 0) return true;
    this.errorField += "Invalid Street.\n";
    return false;
  }

  cityValid() {
    if (this.inputCity != null && this.inputCity != undefined && this.inputStreet.length > 0) return true;
    this.errorField += "Invalid City.\n";
    return false;
  }

  stateValid() {
    if (this.inputState != null && this.inputState != undefined && this.inputState.length > 0) return true;
    this.errorField += "Invalid State.\n";
    return false;
  }

  zipcodeValid() {
    // TODO: Set this up with a zipcode regex
    if (this.inputZipcode !== null && this.inputZipcode !== undefined && true) return true;
    this.errorField += "Invalid Zipcode.\n";
    return false;
  }

  toggleEditUsername() {this.usernameEdit = !this.usernameEdit;}
  toggleEditPassword() {this.passwordEdit = !this.passwordEdit;}
  toggleEditEmail() {this.emailEdit = !this.emailEdit;}
  toggleEditAge() {this.ageEdit = !this.ageEdit;}
  toggleEditStreet() {this.streetEdit = !this.streetEdit;}
  toggleEditCity() {this.cityEdit = !this.cityEdit;}
  toggleEditState() {this.stateEdit = !this.stateEdit;}
  toggleEditZipcode() {this.zipcodeEdit = !this.zipcodeEdit;}
}
