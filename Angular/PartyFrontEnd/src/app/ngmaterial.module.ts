import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {  MatButtonModule, MatMenu, MatMenuModule, MatMenuItem, MatFormFieldModule, MatToolbarModule, MatFormField, MatSelectModule, MatOptionModule, MatDatepickerModule, MatNativeDateModule, MatInputModule, MatCardModule, MatExpansionModule } from '@angular/material';

@NgModule({
  imports: [
    MatButtonModule, 
    MatMenuModule,
    MatFormFieldModule,
    MatToolbarModule ,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatCardModule,
    MatExpansionModule,
      
],
  exports: [
    MatButtonModule, 
    MatMenuModule,
    MatFormFieldModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatSelectModule,
    MatOptionModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatCardModule,
    MatExpansionModule
  ]
})
export class MaterialAppModule { }