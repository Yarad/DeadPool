import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { Routes, RouterModule } from '@angular/router';
import {TabsModule} from "ng2-tabs";
import { DatepickerModule } from 'angular2-material-datepicker';
import { LocalStorageModule } from 'angular-2-local-storage';

import { MainService } from './services/main.service';
import { AuthorizationService } from './services/authorization.service';
import { CriminalCaseService } from './services/criminal-case.service';
import { CrimeService } from './services/crime.service';
import { DetectiveService } from './services/detective.service';
import { EvidenceService } from './services/evidence.service';
import { EvidenceOfCrimeService } from './services/evidence-of-crime.service';
import { ParticipantService } from './services/participant.service';
import { ManService } from './services/man.service';

import { SecurityGuard }   from './components/security.guard';

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
import { NotFoundPageComponent } from './components/not-found-page/not-found-page.component';
import { AddManPageComponent } from './components/add-man-page/add-man-page.component';
import { EvidencePageComponent } from './components/evidence-page/evidence-page.component';

const appRoutes: Routes = [
  {path: '', component: MainPageComponent, canActivate: [SecurityGuard], pathMatch:'full'},
  {path: 'login', component: AuthorizationPageComponent},
  {path: 'registration', component: RegistrationPageComponent, pathMatch:'full'},
  {path: 'criminal_case/:id', component: CriminalCasePageComponent, canActivate: [SecurityGuard], pathMatch:'full'},
  {path: 'crime/:id', component: CrimePageComponent, canActivate: [SecurityGuard], pathMatch:'full'},
  {path: 'add_evidence', component: AddEvidencePageComponent, canActivate: [SecurityGuard], pathMatch:'full'},
  {path: 'add_criminal_case', component: AddCriminalCasePageComponent, canActivate: [SecurityGuard], pathMatch:'full'},
  {path: 'add_crime', component: AddCrimePageComponent, canActivate: [SecurityGuard], pathMatch:'full'},
  {path: '**', component: NotFoundPageComponent},
  {path: 'evidence', component: EvidencePageComponent, canActivate: [SecurityGuard], pathMatch:'full'},
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
    AddCrimePageComponent,
    NotFoundPageComponent,
    AddManPageComponent,
    EvidencePageComponent
  ],
  imports: [
    BrowserModule,
    HttpModule, 
    FormsModule, 
    RouterModule.forRoot(appRoutes),
    TabsModule,
    LocalStorageModule
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
    SecurityGuard,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
