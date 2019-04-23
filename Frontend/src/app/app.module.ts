import { BrowserModule } from '@angular/platform-browser';

import {RouterModule, Routes} from '@angular/router';
import {MovieModule} from './modules/movie/movie.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {MatButtonModule} from '@angular/material/button';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations' ;
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatDialogModule} from '@angular/material/dialog';

import {MatInputModule} from '@angular/material/input';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { FormGroup, FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
const appRoutes: Routes=[

  {
    path:'',
    redirectTo:'movies',
    pathMatch: 'full',
  }

]
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    MatButtonModule,
    MatDialogModule,
    FormsModule,
    MovieModule,
    MatInputModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,

    RouterModule.forRoot(appRoutes),
  ],

  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
