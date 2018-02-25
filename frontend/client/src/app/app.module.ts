import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';
import {Routes, RouterModule} from '@angular/router';


import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AuthorizationPageComponent } from './components/authorization-page/authorization-page.component';
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';

const appRoutes: Routes = [
  {path: 'login', component: AuthorizationPageComponent},
  {path: 'registration', component: RegistrationPageComponent},
];


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AuthorizationPageComponent,
    RegistrationPageComponent
  ],
  imports: [
    BrowserModule,
    HttpModule, 
    FormsModule, 
    RouterModule.forRoot(appRoutes)
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
