import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';
import { Evidence } from '../classes/evidence';
import { Enum } from '../classes/enum';

@Injectable()
export class EvidenceService {

  constructor(private http:Http,
    private mainService: MainService
  ) {}

  getAllEvidences(): Observable<Evidence[]> { 
    return this.mainService.getAuthorizedRequest("/evidences/all_singles")
    .map(res => {
      if(!res.error && res.result.evidences) {
        return res.result.evidences.map(evidence => {
          return new Evidence(evidence);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  getEvidence(evidenceId): Observable<Evidence> {
    return this.mainService.getAuthorizedRequest("/evidences/" + evidenceId)
    .map(res => {
      if(!res.error && res.result) { 
        return new Evidence(res.result);
      }
      return {};
    })
    .catch((error: any) => { 
      return Observable.throw(error);
    });
  }

  addNewEvidence(evidence): Observable<Boolean> {
    return this.mainService.postAuthorizedRequest("/evidences/add_single", evidence)
    .map(res => {
        return res.success;
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  updateEvidence(evidence): Observable<Boolean> {
    return this.mainService.postAuthorizedRequest("/evidences/update_single", evidence)
    .map(res => {
      return res.success;
    })
    .catch((error: any)=> {
      return Observable.throw(error);
    });
  }

  getEvidenceTypes(): Observable<Object[]> {
    return this.mainService.getAuthorizedRequest("/evidences/types_list")
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
