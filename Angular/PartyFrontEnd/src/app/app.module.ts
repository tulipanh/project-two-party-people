import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';


import {SearchCoordinatesDataService} from "./services/search-coordinates-data.service";

import { AppComponent } from './app.component';
import { MapDetailsComponent } from './middle/map-details/map-details.component';
import { SearchPanelComponent } from './middle/search-panel/search-panel.component';
import { LoginComponent } from './top/login/login.component';
import { TopLevelComponent } from './top/top-level/top-level.component';
import { FormsModule } from '@angular/forms';
import { ProfileComponent } from './top/profile/profile/profile.component';
import { CreateComponent } from './top/create/create/create.component';
import { RegisterComponent } from './top/register/register/register.component';
import { EventTileComponent } from './middle/event-tile/event-tile.component';
import { MapViewComponent } from './bottom/map-view/map-view.component';
import { SearchBarComponent } from './bottom/search-bar/search-bar.component';

@NgModule({
  declarations: [
    AppComponent,
    MapDetailsComponent,
    SearchPanelComponent,
    LoginComponent,
    TopLevelComponent,
    ProfileComponent,
    CreateComponent,
    RegisterComponent,
    EventTileComponent,
    MapViewComponent,
    SearchBarComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    GooglePlaceModule
  ],
  providers: [SearchCoordinatesDataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
