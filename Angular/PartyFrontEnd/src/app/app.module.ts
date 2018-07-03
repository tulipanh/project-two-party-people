import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';
import { HttpModule } from "@angular/http";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { MatToolbarModule } from "@angular/material/toolbar";
import { MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule, MatCardModule} from '@angular/material';
import { MatDividerModule } from '@angular/material/divider';

import {SearchCoordinatesDataService} from "./services/search-coordinates-data.service";

import { AppComponent } from './app.component';
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
import { FilterTileComponent } from './middle/filter-tile/filter-tile.component';
import { ClarityModule } from '@clr/angular';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { GeocachingApiService } from './services/geocaching-api.service';

@NgModule({
  declarations: [
    AppComponent,
    SearchPanelComponent,
    LoginComponent,
    TopLevelComponent,
    ProfileComponent,
    CreateComponent,
    RegisterComponent,
    EventTileComponent,
    MapViewComponent,
    SearchBarComponent,
    FilterTileComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    GooglePlaceModule,
    ClarityModule,
    BrowserAnimationsModule,
    HttpModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatDividerModule,

  ],
  providers: [SearchCoordinatesDataService,GeocachingApiService],
  bootstrap: [AppComponent]
})
export class AppModule { }
