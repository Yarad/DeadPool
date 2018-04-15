import { Component, OnInit } from '@angular/core';
import { DocumentationGenerationService } from '../../services/documentation-generation.service';
import { Router} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';
import { CriminalCaseService } from '../../services/criminal-case.service';
import { DetectiveService } from '../../services/detective.service';
import { CrimeService } from '../../services/crime.service';
import { ManService } from '../../services/man.service';
import { EvidenceService } from '../../services/evidence.service';

@Component({
  selector: 'app-documentation-deneration-page',
  templateUrl: './documentation-deneration-page.component.html',
  styleUrls: ['./documentation-deneration-page.component.css']
})
export class DocumentationDenerationPageComponent implements OnInit {

  params = {}

  formats = [
    {
      name: "pdf",
      value: "pdf"
    },
    {
      name: "Защищенный pdf",
      value: "pdf_s"
    },
    {
      name: "xlsx",
      value: "xlsx"
    },  
    {
      name: "csv",
      value: "csv"
    },
  ]

  subjects = [
    {
      name: "Дело",
      value: "criminal_case"
    },
    {
      name: "Детектив",
      value: "detective"
    },
    {
      name: "Человек",
      value: "man"
    },   
     {
      name: "Преступление",
      value: "crime"
    },
    {
      name: "Улика",
      value: " evidence"
    },
    {
      name: "Преступления за период",
      value: "crimes_between_dates"
    }
  ]

  subject;
  format;
  data;
  selectedElement;

  constructor(
    private criminalCaseService:CriminalCaseService,
    private detectiveService: DetectiveService,
    private documentationGenerationService: DocumentationGenerationService,
    private manService:ManService,
    private crimeService:CrimeService,
    private evidenceService:EvidenceService,
  ) { 
    this.format = this.formats[0].value;
    this.subject = this.subjects[0].value;
    const observer = this.criminalCaseService.getAllCriminalCases();
    observer.subscribe(
      data => {     
        this.data = data.map(elem => {
        return {
          value: elem.number,
          id: elem.id
        }        
      })
      this.selectedElement = this.data[0].id
    },
      error => console.log('error', error)
    )
  }

  ngOnInit() {
  }

  changeSelectedFormat($event){
    this.format = $event.target.value;
    console.log( $event.target.value)
  }

  changeSelectedSubject($event) {
    this.data = [];
    this.subject = $event.target.value
    switch ($event.target.value) {
      case this.subjects[0].value: {
        const observer = this.criminalCaseService.getAllCriminalCases();
        observer.subscribe(
          data => this.data = data.map(elem => {
            return {
              value: elem.number,
              id: elem.id
            }
          }),
          error => console.log('error', error)
        );   
        break; 
      }
      case this.subjects[1].value: {
        const observer = this.detectiveService.getAllDetectives();
        observer.subscribe(
          data => this.data = data.map(elem => {
            return {
              value: elem.name + " " + elem.surname,
              id: elem.id
            }
          }),
          error => console.log('error', error)
        );   
        break; 
      }
      case this.subjects[2].value: {
        const observer = this.manService.getAllMen();
        observer.subscribe(
          data => this.data = data.map(elem => {
            return {
              value: elem.name + " " + elem.surname,
              id: elem.id
            }
          }),
          error => console.log('error', error)
        );   
        break; 
      }
      case this.subjects[3].value: {
        const observer = this.crimeService.getAllCrimes();
        observer.subscribe(
          data => this.data = data.map(elem => {
            return {
              value: elem.criminalCase.number + ', ' + elem.date + ", " + elem.place,
              id: elem.id
            }
          }),
          error => console.log('error', error)
        );   
        break; 
      }
      case this.subjects[4].value: {
        const observer = this.evidenceService.getAllEvidences();
        observer.subscribe(
          data => this.data = data.map(elem => {
            return {
              value: elem.name,
              id: elem.id
            }
          }),
          error => console.log('error', error)
        );   
        break; 
      }     
       case this.subjects[5].value: {
        const observer = this.crimeService.getAllCrimes();
        observer.subscribe(
          data => this.data = data.map(elem => {
            return {
              value: elem.criminalCase.number + ', ' + elem.date + ", " + elem.place,
              id: elem.id
            }
          }),
          error => console.log('error', error)
        );   
        break; 
      }

    }
  }

  changeSelectedData($event) {
    this.selectedElement = $event.target.value
  }

  generationReport() {
    let p;
    if (this.subject === "crimes_between_dates") {
      p = this.params;
    } else {
      p =  {
        id: this.selectedElement
      }
    }
    const observer = this.documentationGenerationService.getReport(this.subject, this.format, p);
    observer.subscribe(
      data => console.log(data),
      error => console.log('error', error)
    )
  }

}
