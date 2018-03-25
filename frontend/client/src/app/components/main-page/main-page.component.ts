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

  evidences = [
    {
      photoPath: "https://i2.rozetka.ua/goods/8796/cold_steel_jungle_machete_97jms_images_8796146.jpg",
      name: "Мачете Cold Steel Jungle Machete 97JMS",
      criminalCaseNumber: "РК1244",
    },
    {
      photoPath: "https://cdnmedia.220-volt.ru/content/products/485/485859/images/thumb_220/n1200x800/1.jpeg",
      name: "Бензопила КАЛИБР БП-1500/16У",
      criminalCaseNumber: "TC23423",
    }
  ];

  evidencesOfCrime = [];
  men = [];
  
  constructor(
    private criminalCaseService: CriminalCaseService,
    private crimeService: CrimeService,
    private datectiveService: DetectiveService,
    private evidenceOfCrimeService: EvidenceOfCrimeService,
    private manService: ManService
  ) {  
    const criminalCasesObserver = this.criminalCaseService.getAllCriminalCases();
    criminalCasesObserver.subscribe(
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
        const criminalCasesObserver = this.criminalCaseService.getAllCriminalCases();
        criminalCasesObserver.subscribe(
          data => this.criminalCases = data,
          error => this.criminalCases = this.criminalCaseService.criminalCases
        );
        break;
      }
      case 1: {
        const crimesObserver = this.crimeService.getAllCrimes();
        crimesObserver.subscribe(
          data => this.crimes = data,
          error => this.crimes = this.crimeService.crimes
        );
        break;
      }
      case 2: {
        const observer = this.evidenceOfCrimeService.getAllEvidences();
        observer.subscribe(
          data => this.evidencesOfCrime = data,
          error => {this.evidencesOfCrime = this.evidenceOfCrimeService.evidences; 
            console.log(this.evidencesOfCrime)}
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
