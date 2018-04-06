import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';
import { Participant } from '../classes/participant';
import { Enum } from '../classes/enum';

@Injectable()
export class ParticipantService {

  constructor(private mainService: MainService) { }

  getCrimeParticipant(manId, crimeId): Observable<Participant> {
    return this.mainService.getAuthorizedRequest("/participants/" + manId + "/" + crimeId)
    .map(res => {
      if(!res.error && res.result) { 
        return new Participant(res.result);
      }
      return {};
    })
    .catch((error: any) => { 
      return Observable.throw(error);
    });
  }

  addNewParticipant(participant): Observable<Boolean> {
    return this.mainService.postAuthorizedRequest("/participants/add", participant)
    .map(res => {
        return res.success;
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  updatePaticipant(participant): Observable<Boolean> {
    return this.mainService.postAuthorizedRequest("/participants/update", participant)
    .map(res => {
      return res.success;
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  getParticipantStatuses(): Observable<Enum[]> {
    return this.mainService.getAuthorizedRequest("/participants/status_list")
    .map(res => {
      if(!res.error && res.enums) {     
        return res.enums.map(status => {
          return new Enum(status);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }


}
