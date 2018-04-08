import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';
import { EvidenceOfCrimeService } from '../../services/evidence-of-crime.service';
import { EvidenceOfCrime } from '../../classes/evidence-of-crime';
import { EvidenceService } from '../../services/evidence.service';

@Component({
  selector: 'app-evidence-page',
  templateUrl: './evidence-page.component.html',
  styleUrls: ['./evidence-page.component.css']
})
export class EvidencePageComponent implements OnInit {

  readMode = true;
  evidenceOfCrime;
  evidenceTypes = [];

  crimeId;
  evidenceId;

  oldEvidenceOfCrime;
  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private evidenceOfCrimeService:EvidenceOfCrimeService,
    private evidenceService: EvidenceService,
  ) {
    this.routeSubscription = route.queryParams.subscribe(params => {
      this.evidenceId = +params['evidence_id'];
      this.crimeId = +params['crime_id']
    });
 
    this.evidenceOfCrimeService.getCrimeEvidence(this.evidenceId, this.crimeId)
    .subscribe(
      data => {this.evidenceOfCrime = data},
      error => console.log('error', error)
    );    

    this.evidenceService.getEvidenceTypes()
    .subscribe(
      data => {
        this.evidenceTypes = data;
      },
      error => console.log('error', error)
    )

   }

  ngOnInit() {
  }


  switchMode(mode) {
    this.readMode = mode;
    if (!mode) {
      this.oldEvidenceOfCrime = this.copyObject(this.evidenceOfCrime);
    }
  }

  saveChanges() {
    if (this.evidenceOfCrime.dateAdded && this.evidenceOfCrime.timeAdded) {
      this.evidenceOfCrimeService.updateEvidenceOfCrime(this.evidenceOfCrime)
      .subscribe(
        result => this.switchMode(true),
        error => this.switchMode(true)
      );
    }
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

  cancellationOfChanges() {
    this.evidenceOfCrime = this.oldEvidenceOfCrime;
    this.switchMode(true);
  }

  changeSelectedEvidenceType($event) {
    const index = this.evidenceTypes.map(type=> type.enumValue).indexOf($event.target.value)
    this.evidenceOfCrime.type = this.evidenceTypes[index];
  }

}
