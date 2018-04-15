import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';
import { Participant } from '../../classes/participant';
import { ParticipantService } from '../../services/participant.service';


@Component({
  selector: 'app-participant-page',
  templateUrl: './participant-page.component.html',
  styleUrls: ['./participant-page.component.css']
})
export class ParticipantPageComponent implements OnInit {

  readMode = true;

  participant;
  crimeId;
  manId;
  participantStatuses;

  oldParticipant;

  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private participantService: ParticipantService,
  ) {
    this.routeSubscription = route.queryParams.subscribe(params => {
      this.manId = +params['man_id'];
      this.crimeId = +params['crime_id']
    });
 
    this.participantService.getCrimeParticipant(this.manId, this.crimeId)
    .subscribe(
      data => {this.participant = data},
      error => console.log('error', error)
    );    

    this.participantService.getParticipantStatuses()
    .subscribe(
      data => {
        this.participantStatuses = data;
      },
      error => console.log('error', error)
    )
   }

  ngOnInit() {
  }

  saveChanges() {
    if (true) {
      this.participantService.updatePaticipant(this.participant)
      .subscribe(
        result => this.switchMode(true),
        error => this.switchMode(true)
      );
    }
  }

  cancellationOfChanges() {
    this.participant = this.oldParticipant;
    this.switchMode(true);
  }

  switchMode(mode) {
    this.readMode = mode;
    if (!mode) {
      this.oldParticipant = this.copyObject(this.participant);
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
