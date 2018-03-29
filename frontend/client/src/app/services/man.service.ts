import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';
import { Man } from '../classes/man';

@Injectable()
export class ManService {

  constructor(private http:Http,
    private mainService: MainService
  ) {}

  getAllMen(): Observable<Man[]> { 
    return this.mainService.getAuthorizedRequest("/man")
    .map(res => {
      if(!res.error && res.result.men) {
        return res.result.men.map(man => {
          return new Man(man);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  men = [
    {
      id: 1,
      photoPath: "https://res.cloudinary.com/dyzdll94h/image/upload/v1512924240/mpv69dvnolst2gtjjljh.jpg",
      name: "Ангелина",
      surname: "Хилькевич",
      crimesPartAmount: 3
    },
    {
      id: 2,
      photoPath: "https://res.cloudinary.com/dyzdll94h/image/upload/v1513188126/kxj1l9ekt31bp67ir8db.jpg",
      name: "Алина",
      surname: "Ивченко",
      crimesPartAmount: 1
    }
  ];

}
