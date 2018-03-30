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

    this.routeSubscription = route.queryParams.subscribe(params => this.crime.criminalCase.id = +params['criminal_case']);
    this.crimeService.getCrimeTypes()
    .subscribe(
      data => {
        this.crimeEnumTypes = data;
        this.crime.type = this.crimeEnumTypes[0].enumValue;        
      },
      error => {}
    );
  }

  ngOnInit() {
  }

  changeSelectedCrimeType($event) {
    this.crime.type = $event.target.value;
  }

  isCrimeValidate(crime) {
    if (crime.type && crime.date && crime.place) {
        return true;
    }
    return false;
  }

  addNewCrime() {
    if (this.isCrimeValidate(this.crime)) { 
      console.log(this.crime);
      this.crimeService.addNewCrime(this.crime)
      .subscribe(
        result => {if(result) this.router.navigate(['criminal_case/'+this.crime.criminalCase.id])},
        error => {console.log("error" + error)}
      );
    }
  }

}
