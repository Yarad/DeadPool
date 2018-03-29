import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CriminalCaseService } from '../../services/criminal-case.service';
import { CrimeService } from '../../services/crime.service';
import { DetectiveService } from '../../services/detective.service';
import { EvidenceOfCrimeService } from '../../services/evidence-of-crime.service';
import { ManService } from '../../services/man.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  activeCategoryId = 0;
  
  criminalCases = [];
  crimes = [];
  evidencesOfCrime = [];
  men = [];
  
  constructor(
    private criminalCaseService: CriminalCaseService,
    private crimeService: CrimeService,
    private datectiveService: DetectiveService,
    private evidenceOfCrimeService: EvidenceOfCrimeService,
    private manService: ManService
  ) {  
    const observer = this.criminalCaseService.getAllCriminalCases();
    observer.subscribe(
      data => this.criminalCases = data,
      error => this.criminalCases = this.criminalCaseService.criminalCases
    );
  }

  ngOnInit() {
  }

  changeActiveCategory(id) {
    this.activeCategoryId = id;
    switch(id) {
      case 0: {        
        const observer = this.criminalCaseService.getAllCriminalCases();
        observer.subscribe(
          data => this.criminalCases = data,
          error => this.criminalCases = this.criminalCaseService.criminalCases
        );
        break;
      }
      case 1: {
        const observer = this.crimeService.getAllCrimes();
        observer.subscribe(
          data => this.crimes = data,
          error => this.crimes = this.crimeService.crimes
        );
        break;
      }
      case 2: {
        const observer = this.evidenceOfCrimeService.getAllEvidences();
        observer.subscribe(
          data => this.evidencesOfCrime = data,
          error => this.evidencesOfCrime = this.evidenceOfCrimeService.evidences
        );
        break;
      }
      case 3: {
        const observer = this.manService.getAllMen();
        observer.subscribe(
          data => this.men = data,
          error => this.men = this.manService.men
        );
        break;
      }
    }
  }
}
