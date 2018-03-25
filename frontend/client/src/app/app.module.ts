import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Routes, RouterModule } from '@angular/router';
import {TabsModule} from "ng2-tabs";
import { DatepickerModule } from 'angular2-material-datepicker';

import { MainService } from './services/main.service';
import { AuthorizationService } from './services/authorization.service';
import { CriminalCaseService } from './services/criminal-case.service';
import { CrimeService } from './services/crime.service';
import { DetectiveService } from './services/detective.service';
import { EvidenceService } from './services/evidence.service';
import { EvidenceOfCrimeService } from './services/evidence-of-crime.service';
import { ParticipantService } from './services/participant.service';
import { ManService } from './services/man.service';

import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AuthorizationPageComponent } from './components/authorization-page/authorization-page.component';
import { RegistrationPageComponent } from './components/registration-page/registration-page.component';
import { MainPageComponent } from './components/main-page/main-page.component';
import { CriminalCasePageComponent } from './components/criminal-case-page/criminal-case-page.component';
import { CrimeTableComponent } from './components/crime-table/crime-table.component';
import { CrimePageComponent } from './components/crime-page/crime-page.component';
import { ParticipantsTableComponent } from './components/participants-table/participants-table.component';
import { EvidencePopupComponent } from './components/evidence-popup/evidence-popup.component';
import { EvidenceTableComponent } from './components/evidence-table/evidence-table.component';
import { config } from './app.config';
import { AddEvidencePageComponent } from './components/add-evidence-page/add-evidence-page.component';
import { AddCriminalCasePageComponent } from './components/add-criminal-case-page/add-criminal-case-page.component';
import { AddCrimePageComponent } from './components/add-crime-page/add-crime-page.component';

const appRoutes: Routes = [
  {path: '', component: MainPageComponent},
  {path: 'login', component: AuthorizationPageComponent},
  {path: 'registration', component: RegistrationPageComponent},
  {path: 'criminal_case/:id', component: CriminalCasePageComponent},
  {path: 'crime/:id', component: CrimePageComponent},
  {path: 'crime/:id/add_evidence', component: AddEvidencePageComponent},
  {path: 'add_criminal_case', component: AddCriminalCasePageComponent}
];


@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    AuthorizationPageComponent,
    RegistrationPageComponent,
    MainPageComponent,
    CriminalCasePageComponent,
    CrimeTableComponent,
    CrimePageComponent,
    ParticipantsTableComponent,
    EvidencePopupComponent,
    EvidenceTableComponent,
    AddEvidencePageComponent,
    AddCriminalCasePageComponent,
    AddCrimePageComponent
  ],
  imports: [
    BrowserModule,
    HttpModule, 
    FormsModule, 
    RouterModule.forRoot(appRoutes),
    TabsModule
  ],
  providers: [
    MainService,
    AuthorizationService,
    CriminalCaseService,
    CrimeService,
    DetectiveService,
    EvidenceService,
    EvidenceOfCrimeService,
    ParticipantService,
    ManService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
