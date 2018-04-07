import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';
import { EvidenceOfCrimeService } from '../../services/evidence-of-crime.service';
import { EvidenceOfCrime } from '../../classes/evidence-of-crime';


@Component({
  selector: 'app-evidence-page',
  templateUrl: './evidence-page.component.html',
  styleUrls: ['./evidence-page.component.css']
})
export class EvidencePageComponent implements OnInit {

  evidenceOfCrime;

  crimeId;
  evidenceId;
  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private evidenceOfCrimeService:EvidenceOfCrimeService
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
   }

  ngOnInit() {
  }

}
