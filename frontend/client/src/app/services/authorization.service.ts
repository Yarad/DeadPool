import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';

@Injectable()
export class AuthorizationService {

  constructor(private http:Http,
    private mainService: MainService
  ) {}

  registration(user) {
    return this.mainService.postRequest("/sign_up", user)
    .map(res => {
      if(!res.error) {
        return res
      }
      return Observable.throw(res.result);
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  authorization(user) {
    return this.mainService.postRequest("/sign_in", user)
    .map(res => {
      return res;
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  logout() {
    
  }
}
