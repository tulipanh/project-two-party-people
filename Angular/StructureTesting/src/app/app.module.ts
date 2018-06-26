import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { MapComponentComponent } from './bottom/map-component/map-component.component';
import { MapDetailsComponent } from './middle/map-details/map-details.component';
import { SearchPanelComponent } from './middle/search-panel/search-panel.component';
import { ControlPanelComponent } from './top/control-panel/control-panel.component';

@NgModule({
  declarations: [
    AppComponent,
    MapComponentComponent,
    MapDetailsComponent,
    SearchPanelComponent,
    ControlPanelComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
