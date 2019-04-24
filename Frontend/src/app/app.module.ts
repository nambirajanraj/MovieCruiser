import { BrowserModule } from '@angular/platform-browser';

import {RouterModule, Routes} from '@angular/router';
import {MovieModule} from './modules/movie/movie.module';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { NgModule } from '@angular/core';
import { SharedModule } from './modules/shared/shared.module';
import { AuthenticationModule } from './modules/authentication/authentication.module';
import { AuthGuardService } from './auth-guard.service';
const appRoutes:Routes = [

  {
    path:'',
    redirectTo: '/login',
    pathMatch:'full'
  }
]

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    SharedModule,
    AuthenticationModule,
    MovieModule,
    AppRoutingModule,
    RouterModule.forRoot(appRoutes),
  ],

  providers: [AuthGuardService],
  bootstrap: [AppComponent]
})
export class AppModule { }
