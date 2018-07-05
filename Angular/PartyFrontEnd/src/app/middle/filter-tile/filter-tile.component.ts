import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { EventFilterService } from '../../services/event-filter.service';
@Component({
  selector: 'app-filter-tile',
  templateUrl: './filter-tile.component.html',
  styleUrls: ['./filter-tile.component.css']
})
export class FilterTileComponent implements OnInit {

  today = new FormControl(new Date());
  start = new FormControl(new Date());
  end = new FormControl();
  categories = [];
  radius;
  name;
  categoryList = [
    {name:'Concert' , value: 1},
    {name: 'Lecture', value: 2 },
    {name: 'Social', value: 3},
    {name: 'Workshop', value: 4},
    {name: 'Party', value: 5},
    {name: 'Food', value: 6},
    {name: 'Alcohol', value: 7},
    {name: 'Visual Art', value: 8},
    {name: 'Performace Art', value: 9},
    {name: 'Sports', value: 10},
    {name: 'Outdoor', value: 11},
    {name: 'Indoor', value: 12}
  ];
  constructor( private eventFilter:EventFilterService ) { }

  ngOnInit() {
  }

  pushRadiusChange = (event)=> {
    this.eventFilter.updateRadius(event.value);
  }
  pushStartDate = (event)=> {
    this.eventFilter.updateStartDate(event.value);
  }

  pushEndDate = (event)=> {
    this.eventFilter.updateEndDate(event.value);
  }

  pushName = ()=> {
    this.eventFilter.updateName(this.name);
  }

  pushCategories = ()=> {
    this.eventFilter.updateCategories(this.categories);
  }

}
