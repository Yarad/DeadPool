import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';
import { CriminalCaseService } from '../../services/criminal-case.service';
import { CriminalCase } from '../../classes/criminal-case';
import { DetectiveService } from '../../services/detective.service';


@Component({
  selector: 'app-criminal-case-page',
  templateUrl: './criminal-case-page.component.html',
  styleUrls: ['./criminal-case-page.component.css']
})
export class CriminalCasePageComponent implements OnInit {
  readMode = true;
  allDetectives = [];

  criminalCase: CriminalCase;
  oldCriminalCase;
  criminalCaseId;

  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private criminalCaseService:CriminalCaseService,
    private detectiveService: DetectiveService
  ) {
    this.routeSubscription = route.params.subscribe(params => this.criminalCaseId = +params['id']);

    this.criminalCaseService.criminalCases[this.criminalCaseService.criminalCases.map(criminalCase => criminalCase.id).indexOf(this.criminalCaseId)];
    const observer = this.criminalCaseService.getCriminalCase(this.criminalCaseId);
    observer.subscribe(
      data => this.criminalCase = data,
      error => this.criminalCase = new CriminalCase(this.criminalCaseService.criminalCases[this.criminalCaseService.criminalCases.map(criminalCase => criminalCase.id).indexOf(this.criminalCaseId)])
    );    
   }

  ngOnInit() {
  }

  switchMode(mode) {
    this.readMode = mode;
    if (!mode) {
      this.detectiveService.getAllDetectives()
      .subscribe(
        data => this.allDetectives = data,
        error => this.allDetectives = this.detectiveService.detectives
      );
      this.oldCriminalCase = this.copyObject(this.criminalCase);
    }
  }

  saveChanges() {
    if (this.criminalCase.isValidate()) {
      this.criminalCaseService.updateNewCriminalCase(this.criminalCase)
      .subscribe(
        result => this.switchMode(true),
        error => this.switchMode(true)
      );
    }
  }

  cancellationOfChanges() {
    this.criminalCase = this.oldCriminalCase;
    this.switchMode(true);
  }
  
  changeSelectedDetective($event) {
    const index = this.allDetectives.map(detective => detective.id).indexOf(+$event.target.value);
    if (index > -1) {
      this.criminalCase.detective = this.allDetectives[index];
    }
  }

  closeCriminalCase() {
    this.criminalCase.closed = true;
    const closeDate = new Date();
    this.criminalCase.closeDate = closeDate.getFullYear() + '-' + ('0' + (closeDate.getMonth() + 1)).slice(-2) + '-' + ('0' + closeDate.getDate()).slice(-2);
    this.criminalCase.type = "Закрыто";
  }

  copyObject(currObject) {
    const newObject = {};

    Object.keys(currObject).forEach(key => {
      Object.assign(newObject, {
        [key]: currObject[key]
      })
    });

    return newObject;
  }
}
