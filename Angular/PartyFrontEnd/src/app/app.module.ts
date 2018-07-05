import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { GooglePlaceModule } from 'ngx-google-places-autocomplete';
import { HttpModule } from "@angular/http";
import { MaterialAppModule } from './ngmaterial.module'

import { SearchCoordinatesDataService } from "./services/search-coordinates-data.service";
import { PartyHttpRequestService } from "./services/party-http-request.service";
import { EventFilterService } from "./services/event-filter.service"

import { AppComponent } from './app.component';
import { SearchPanelComponent } from './middle/search-panel/search-panel.component';
import { LoginComponent } from './top/login/login.component';
import { TopLevelComponent } from './top/top-level/top-level.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProfileComponent } from './top/profile/profile/profile.component';
import { CreateComponent } from './top/create/create/create.component';
import { RegisterComponent } from './top/register/register/register.component';
import { EventTileComponent } from './middle/event-tile/event-tile.component';
import { MapViewComponent } from './bottom/map-view/map-view.component';
import { SearchBarComponent } from './bottom/search-bar/search-bar.component';
import { FilterTileComponent } from './middle/filter-tile/filter-tile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { GeocachingApiService } from './services/geocaching-api.service';
import { EventDetailsComponent } from './top/event-details/event-details.component';

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
    EventDetailsComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    GooglePlaceModule,
    BrowserAnimationsModule,
    HttpClientModule,
    HttpModule,
<<<<<<< HEAD
    MaterialAppModule
  ],
  providers: [
    SearchCoordinatesDataService,
    PartyHttpRequestService,
    EventFilterService,
    GeocachingApiService
  ],
=======
    HttpClientModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatIconModule,
    MatCardModule,
    MatSelectModule,
    MatOptionModule,
    MatDividerModule,

  ],
  providers: [SearchCoordinatesDataService,GeocachingApiService],
>>>>>>> development
  bootstrap: [AppComponent]
})
export class AppModule { }
