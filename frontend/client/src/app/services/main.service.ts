import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/map';

const CLOUDYNARY_URL = 'https://api.cloudinary.com/v1_1/dkdzgw7kp/image/upload';
const CLOUDYNARY_UPLOAD_PRESET = 'jr7zzle9';

@Injectable()
export class MainService {

  public serverUrl = "http://localhost:8090";

  constructor(private http:Http) { }

  public isCurrentUserAuthorized() {
    if(localStorage.getItem('user')) {
    }
    return true;
  }

  public addAuthorizationHeaders(headers) {
    if(localStorage.getItem('user')) {
      const currentUser = JSON.parse(localStorage.getItem('user'));
      headers.append('deadpool-token', currentUser.token); 
    }
  }

  public addHeaders(headers) {     
    headers.append("Content-Type", "application/json");
  }

  public getAuthorizedRequest(urlPattern) {
    if (this.isCurrentUserAuthorized()) {
      const headers = new Headers();
      this.addHeaders(headers);
      this.addAuthorizationHeaders(headers);
      return this.http.get(this.serverUrl + urlPattern, {headers})
          .map(res => res.json())
          .map(res => {
            console.log("main",res);
            return res;
          });
    }
    return null;
  }

  public postAuthorizedRequest(urlPattern, params) {
    if (this.isCurrentUserAuthorized()) {
      console.log("main req", params);
      const headers = new Headers();
      this.addHeaders(headers);
      this.addAuthorizationHeaders(headers);
      return this.http.post(this.serverUrl + urlPattern, JSON.stringify(params), {headers})
          .map(res => res.json())
          .map(res => {
            console.log("main",res);
            return res;
          });
    }
    return null;
  }


  public postRequest(urlPattern, params) {
    const headers = new Headers();
    this.addHeaders(headers);
    return this.http.post(this.serverUrl + urlPattern, JSON.stringify(params), {headers})
      .map(res => res.json())
      .map(res => {
        console.log("main",res);
        return res;
      });
  }

  public getAuthorizedFormatedRequest(urlPattern) {
    if (this.isCurrentUserAuthorized()) {
      const headers = new Headers();
      this.addAuthorizationHeaders(headers);
      headers.append("Accept", "text/csv, application/pdf, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
      return this.http.get(this.serverUrl + urlPattern, {headers})          
          .map(res => {
            console.log("main",res);
            return res;
          });
    }
    return null;
  }

 
}