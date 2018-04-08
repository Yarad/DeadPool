import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { ManService } from '../../services/man.service';
import { Man } from '../../classes/man';
import { ParticipantService } from '../../services/participant.service';
import { Participant } from '../../classes/participant';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';
import { CrimeService } from '../../services/crime.service';

const CLOUDYNARY_URL = 'https://api.cloudinary.com/v1_1/dkdzgw7kp/image/upload';
const CLOUDYNARY_UPLOAD_PRESET = 'jr7zzle9';

@Component({
  selector: 'app-add-participant-page',
  templateUrl: './add-participant-page.component.html',
  styleUrls: ['./add-participant-page.component.css']
})
export class AddParticipantPageComponent implements OnInit {

  crimeId;
  
  participant = new Participant(null);
  men = [];
  participantStatuses = [];

  newMan;

  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private crimeService: CrimeService,
    private manService: ManService,
    private participantService: ParticipantService
  ) {
    this.routeSubscription = route.queryParams.subscribe(params => this.crimeId = +params['crime']);
    this.crimeService.getCrime(this.crimeId)
    .subscribe(
      data => this.participant.crime = data,
      error => console.log('error', error)
    );

    this.manService.getAllMen()
    .subscribe(
      data => {
        this.men = data;
        this.participant.man = this.men[0] || new Man(null)
      },
      error => console.log('error', error)
    );

    this.participantService.getParticipantStatuses()
    .subscribe(
      data => {
        this.participantStatuses = data;
        this.participant.status = this.participantStatuses[0]
      },
      error => console.log('error', error)
    )
  }

  ngOnInit() {
  }

  changeSelectedMan($event) {
    const index =  this.men.map(man => man.id).indexOf(+($event.target.value));
    this.participant.man = this.men[index];
  }

  changeSelectedParticipantStatus($event) {
    const index = this.participantStatuses.map(status => status.enumValue).indexOf($event.target.value);
    this.participant.status = this.participantStatuses[index];
  }

  tabSelect($event) {
    console.log($event) ;
    switch($event) {
      case 0: {
        this.newMan = null;
        break;
      }
      case 1: {
        this.newMan = new Man(null);
        break;
      }
    }
  }

  addParticipant() {
    if (this.participant.man && this.participant.status && this.participant.dateAdded 
      && this.participant.timeAdded) { 
      this.participantService.addNewParticipant(this.participant)
      .subscribe(
        result => {if(result) this.router.navigate(['crime/'+this.crimeId])},
        error => {console.log("error" + error)}
      );
    }
  }

  addMan() {
    if (this.newMan) { 
      if (this.newMan.name && this.newMan.surname) { 
        this.manService.addNewMan(this.newMan)
        .subscribe(
          result => {
            this.manService.getAllMen()
            .subscribe(
              data => {
                this.men = data;                
                const index =  this.men.map(man => man.name).indexOf(this.newMan.name);
                this.participant.man = this.men[index];
                this.addParticipant();
              },
              error => console.log('error', error)
            );
          },
          error => {console.log("error" + error)}
        );
      }
    } else {
      this.addParticipant();
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
        const response = JSON.parse(xhr.responseText); 
        const imgs = document.getElementsByTagName('img'); 
        for (let i = 0; i < imgs.length; i++) { 
          const img = imgs[i]; 
          if (img.id === 'man-image') { 
            this.newMan.photoPath = response.secure_url;
            
        console.log(JSON.parse(xhr.responseText));  
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
