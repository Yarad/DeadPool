import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';

@Injectable()
export class DocumentationGenerationService {

  constructor(private http:Http,
    private mainService: MainService
  ) {}

  getReport(subject,type,params): Observable<Object> { 
    console.log(subject, type, params)
    let p;
    if (params.id) {
      p = params.id
    }
    if (params.dateStart && params.dateEnd) {
      p = params.dateStart + '/' + params.dateEnd; 
    }
    return this.mainService.getAuthorizedFormatedRequest("/reports/"+subject+"/"+type+"/"+p)
    .map(res => {
      const blob = new Blob([res], { type: 'application/pdf' });
      const url= window.URL.createObjectURL(blob);
      console.log('blob',url);
      window.open(url);
     return url; 
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

}
