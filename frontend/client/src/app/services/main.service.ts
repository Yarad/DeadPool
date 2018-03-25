import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class MainService {

  public serverUrl = "http://localhost:8091";

  constructor(private http:Http) { }

  public isCurrentUserAuthorized() {
    if(localStorage.getItem('currentUser')) {
    }
    return true;
  }

  public addAuthorizationHeaders(headers) {
    if(localStorage.getItem('currentUser')) {
      const currentUser = JSON.parse(localStorage.getItem('currentUser'));
      headers.append('Authorization', currentUser.token); 
    }
  }

  public getAuthorizedRequest(urlPattern) {
   // if (this.isCurrentUserAuthorized()) {
      const headers = new Headers();
     // this.addAuthorizationHeaders(headers);
      return this.http.get(this.serverUrl + urlPattern, {headers})
          .map(res => res.json());
    //}
   // return null;
  }

  public postAuthorizedRequest(urlPattern, params) {
    if (this.isCurrentUserAuthorized()) {
      const headers = new Headers();
      this.addAuthorizationHeaders(headers);
      return this.http.post(this.serverUrl + urlPattern, JSON.stringify(params), {headers})
          .map(res => res.json());
    }
    return null;
  }
}