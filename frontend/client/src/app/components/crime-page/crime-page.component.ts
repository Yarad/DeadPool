import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';
import { CrimeService } from '../../services/crime.service';
import { Crime } from '../../classes/crime';

@Component({
  selector: 'app-crime-page',
  templateUrl: './crime-page.component.html',
  styleUrls: ['./crime-page.component.css']
})
export class CrimePageComponent implements OnInit {
  crime;
  readMode = true;
  oldCrime;
  crimeEnumTypes = [];
 
  crimeId;

  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private crimeService:CrimeService
  ) {
    this.routeSubscription = route.params.subscribe(params => this.crimeId = +params['id']);
 
    this.crimeService.getCrime(this.crimeId)
    .subscribe(
      data => {this.crime = data;
        console.log(this.crime.evidencesOfCrime)},
      error => this.crime = new Crime(this.crimeService.crimes[this.crimeService.crimes.map(crime => crime.id).indexOf(this.crimeId)])
    );    

    this.crimeService.getCrimeTypes()
    .subscribe(
      data => this.crimeEnumTypes = data,
      error => {}
    );    
   }


  saveChanges() {
    if (this.crime.date && this.crime.place && this.crime.type) {
    //  this.crime.type = this.crimeEnumTypes[this.crimeEnumTypes.map(type=> type.name).indexOf(this.crime.type.name)].enumValue;
      this.crimeService.updateCrime(this.crime)
      .subscribe(
        result => this.switchMode(true),
        error => this.switchMode(true)
      );
    }
  }

  cancellationOfChanges() {
    this.crime = this.oldCrime;
    this.switchMode(true);
  }

  changeSelectedCrimeType($event) {
    // const index = this.allDetectives.map(detective => detective.id).indexOf(+$event.target.value);
    // if (index > -1) {
    //   this.criminalCase.detective = this.allDetectives[index];
    // }
    console.log(this.crimeEnumTypes[this.crimeEnumTypes.map(type=> type.enumValue).indexOf($event.target.value)])
    this.crime.type = this.crimeEnumTypes[this.crimeEnumTypes.map(type=> type.enumValue).indexOf($event.target.value)];

  }

  ngOnInit() {
  }

  switchMode(mode) {
    this.readMode = mode;
    if (!mode) {
      this.oldCrime = this.copyObject(this.crime);
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

}
