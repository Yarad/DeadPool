import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { CrimeService } from '../../services/crime.service';
import { Crime } from '../../classes/crime';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-add-crime-page',
  templateUrl: './add-crime-page.component.html',
  styleUrls: ['./add-crime-page.component.css']
})
export class AddCrimePageComponent implements OnInit {
  
  crimeEnumTypes = [];
  crimeTypes = [];

  crime = new Crime({
    type: this.crimeTypes[0],
    criminalCase: {
      id: -1
    },
  });
  criminalCaseId;


  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private crimeService: CrimeService
  ) {
    //this.routeSubscription = route.params.subscribe(params => this.criminalCaseId = params['criminal_case']);
    route.queryParams.subscribe(params => console.log(+params['criminal_case']))

    this.routeSubscription = route.queryParams.subscribe(params => this.criminalCaseId = +params['criminal_case']);
    this.crime.criminalCase.id = this.criminalCaseId;
    this.crimeService.getCrimeTypes()
    .subscribe(
      data => {
        this.crimeEnumTypes = data;
        this.crime.type = this.crimeEnumTypes[0];        
      },
      error => {}
    );
  }

  ngOnInit() {
  }

  changeSelectedCrimeType($event) {
    console.log(this.crimeEnumTypes.map(type=> type.name))
    this.crime.type = this.crimeEnumTypes[this.crimeEnumTypes.map(type=> type.enumValue).indexOf($event.target.value)];
  }

  isCrimeValidate(crime) {
    if (crime.type && crime.date && crime.place) {
        return true;
    }
    return false;
  }

  addNewCrime() {
    console.log(this.crime);
    
    if (this.isCrimeValidate(this.crime)) { 
      this.crimeService.addNewCrime(this.crime)
      .subscribe(
        result => {if(result) this.router.navigate(['criminal_case/'+this.crime.criminalCase.id])},
        error => {console.log("error" + error)}
      );
    }
  }

}
