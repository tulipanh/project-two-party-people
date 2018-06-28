import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MapComponentComponent } from './bottom/map-component/map-component.component';
import { MapDetailsComponent } from './middle/map-details/map-details.component';
import { SearchPanelComponent } from './middle/search-panel/search-panel.component';
import { LoginComponent } from './top/login/login.component';
import { TopLevelComponent } from './top/top-level/top-level.component';
import { FormsModule } from '@angular/forms';
import { ProfileComponent } from './top/profile/profile/profile.component';
import { CreateComponent } from './top/create/create/create.component';
import { RegisterComponent } from './top/register/register/register.component';
import { EventTileComponent } from './middle/event-tile/event-tile.component';

@NgModule({
  declarations: [
    AppComponent,
    MapComponentComponent,
    MapDetailsComponent,
    SearchPanelComponent,
    LoginComponent,
    TopLevelComponent,
    ProfileComponent,
    CreateComponent,
    RegisterComponent,
    EventTileComponent,
  ],
  imports: [
    BrowserModule, FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
