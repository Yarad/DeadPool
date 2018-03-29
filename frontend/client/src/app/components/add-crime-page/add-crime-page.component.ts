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
  
  crimeTypes = ["Убийство",
  "Ограбление",
  "Изнасилование",
  "Поджог",
  "Самоубийство"];

  crime = new Crime({
    type: this.crimeTypes[0],
    criminalCase: {
      id: -1
    }
  });

  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private crimeService: CrimeService
  ) { 
    this.routeSubscription = route.params.subscribe(params => this.crime.criminalCase.id = params.criminal_case);
    this.crimeService.getCrimeTypes()
    .subscribe(
      data => {this.crimeTypes = data.map(type => type.name)},
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
    console.log(this.crime);
    if (this.isCrimeValidate(this.crime)) { 
      console.log(this.crime);
      this.crimeService.addNewCrime(this.crime)
      .subscribe(
        result => {if(result) this.router.navigate(['/criminalCase'+this.crime.criminalCase.id])},
        error => {console.log("error" + error)}
      );
    }
  }

}
