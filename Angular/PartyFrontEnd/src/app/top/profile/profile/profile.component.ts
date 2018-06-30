import { Component, OnInit, Input } from '@angular/core';
import { User } from '../../../models/User';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  @Input() activeUser: User;

  constructor() { }

  ngOnInit() {
  }

}
