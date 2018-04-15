import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { EvidenceService } from '../../services/evidence.service';
import { Evidence } from '../../classes/evidence';
import { EvidenceOfCrimeService } from '../../services/evidence-of-crime.service';
import { EvidenceOfCrime } from '../../classes/evidence-of-crime';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';
import { CrimeService } from '../../services/crime.service';

const CLOUDYNARY_URL = 'https://api.cloudinary.com/v1_1/dkdzgw7kp/image/upload';
const CLOUDYNARY_UPLOAD_PRESET = 'jr7zzle9';

@Component({
  selector: 'app-add-evidence-page',
  templateUrl: './add-evidence-page.component.html',
  styleUrls: ['./add-evidence-page.component.css']
})
export class AddEvidencePageComponent implements OnInit {

  crimeId;
  
  evidenceOfCrime = new EvidenceOfCrime(null);
  evidences = [];
  evidenceTypes = [];

  newEvidence;

  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private crimeService: CrimeService,
    private evidenceService: EvidenceService,
    private evidenceOfCrimeService: EvidenceOfCrimeService
  ) {
    this.routeSubscription = route.queryParams.subscribe(params => this.crimeId = +params['crime']);
    this.crimeService.getCrime(this.crimeId)
    .subscribe(
      data => this.evidenceOfCrime.crime = data,
      error => console.log('error', error)
    );

    this.evidenceService.getAllEvidences()
    .subscribe(
      data => {
        this.evidences = data;
        this.evidenceOfCrime.evidence = this.evidences[0] || new Evidence(null)
      },
      error => console.log('error', error)
    );

    this.evidenceService.getEvidenceTypes()
    .subscribe(
      data => {
        this.evidenceTypes = data;
        this.evidenceOfCrime.type = this.evidenceTypes[0]
      },
      error => console.log('error', error)
    )
  }

  ngOnInit() {
  }

  changeSelectedEvidence($event) {
    const index =  this.evidences.map(evidence => evidence.id).indexOf(+($event.target.value));
    this.evidenceOfCrime.evidence = this.evidences[index];
  }

  changeSelectedEvidenceType($event) {
    const index = this.evidenceTypes.map(evidence => evidence.enumValue).indexOf($event.target.value);
    this.evidenceOfCrime.type = this.evidenceTypes[index];
  }

  tabSelect($event) {
    console.log($event) ;
    switch($event) {
      case 0: {
        this.newEvidence = null;
        break;
      }
      case 1: {
        this.newEvidence = new Evidence(null);
        break;
      }
    }
  }

  addEvidenceOfCrime() {
    if (this.evidenceOfCrime.evidence && this.evidenceOfCrime.type && this.evidenceOfCrime.dateAdded 
      && this.evidenceOfCrime.timeAdded) { 
      this.evidenceOfCrimeService.addNewEvidenceOfCrime(this.evidenceOfCrime)
      .subscribe(
        result => {if(result) this.router.navigate(['crime/'+this.evidenceOfCrime.crime.id])},
        error => {console.log("error" + error)}
      );
    }
  }

  addEvidence() {
    if (this.newEvidence) { 
      if (this.newEvidence.name) { 
        this.evidenceService.addNewEvidence(this.newEvidence)
        .subscribe(
          result => {
            this.evidenceService.getAllEvidences()
            .subscribe(
              data => {
                this.evidences = data;                
                const index =  this.evidences.map(evidence => evidence.name).indexOf(this.newEvidence.name);
                this.evidenceOfCrime.evidence = this.evidences[index];
                this.addEvidenceOfCrime();
              },
              error => console.log('error', error)
            );
          },
          error => {console.log("error" + error)}
        );
      }
    } else {
      this.addEvidenceOfCrime();
    }
  }

  public uploadFile(event: any) { 
    
    const file = event.target.files[0]; 
    const xhr = new XMLHttpRequest(); 
    const fd = new FormData();   

    xhr.open('POST', CLOUDYNARY_URL, true); 
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest'); 
    
    xhr.onreadystatechange = (e) => { 
      if (xhr.readyState === 4 && xhr.status === 200) { 
        console.log(JSON.parse(xhr.responseText)); 
        const response = JSON.parse(xhr.responseText); 
        const imgs = document.getElementsByTagName('img'); 
        for (let i = 0; i < imgs.length; i++) { 
          const img = imgs[i]; 
          if (img.id === 'evidence-image') { 
            this.evidenceOfCrime.photoPath = response.secure_url; 
            img.src = response.secure_url; 
            img.alt = response.public_id; 
          } 
        } 
      } 
    };
    fd.append('upload_preset', CLOUDYNARY_UPLOAD_PRESET ); 
    fd.append('file', file); 
    xhr.send(fd);  
  }
    
}
