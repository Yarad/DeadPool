import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';
import { Detective } from '../classes/detective';

@Injectable()
export class DetectiveService {

  constructor(private http:Http,
    private mainService: MainService
  ) {}

  getAllDetectives(): Observable<Detective[]> { 
    return this.mainService.getAuthorizedRequest("/detectives")
    .map(res => {
      if(!res.error && res.result.men) {
        return res.result.men.map(detective => {
          return new Detective(detective);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  detectives = [
    {
      id: 1,
      name: "Шерлок",
      surname: "Холмс",
      photoPath: null
    },
    {
      id: 2,
      name: "Жареная",
      surname: "Картошка",
      photoPath: null
    },
    {
      id: 3,
      name: "Андрей",
      surname: "Жлобич",
      photoPath: null
    }
  ]
  
}
