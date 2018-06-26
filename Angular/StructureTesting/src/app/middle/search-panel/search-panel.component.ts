import { Component, OnInit } from '@angular/core';

/* Need to figure out a way to auto-hide this 
    panel when the window is shrunk below a certain width.
*/
@Component({
  selector: 'app-search-panel',
  templateUrl: './search-panel.component.html',
  styleUrls: ['./search-panel.component.css']
})
export class SearchPanelComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
