import { Component, OnInit } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  usernameInput: string;
  passwordInput: string;
  errorMessage: string;
  
  // Only for testing
  users: User[];

  constructor(private userService: UserService) { }

  ngOnInit() {
  }

  getUsers() {
    this.userService.getUsers().subscribe(users => this.users = users);
  }

  authenticate(username, password) {
    
  }
}
