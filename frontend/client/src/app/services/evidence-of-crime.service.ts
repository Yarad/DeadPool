import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';
import { EvidenceOfCrime } from '../classes/evidence-of-crime';

@Injectable()
export class EvidenceOfCrimeService {

  constructor(private http:Http,
    private mainService: MainService
  ) {}

  getAllEvidences(): Observable<EvidenceOfCrime[]> { 
    return this.mainService.getAuthorizedRequest("/evidences")
    .map(res => {
      if(!res.error && res.result.evidencesOfCrime) {
        return res.result.evidencesOfCrime.map(evidenceOfCrime => {
          return new EvidenceOfCrime(evidenceOfCrime);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  evidences = [
    {
      evidence: {
        id: 1,
        name: "Мачете Cold Steel Jungle Machete 97JMS",
      },
      crime: {
          id: 1,
          type: "Забойства",
         criminalCase:
          {
              number: "УПН1854"
          }
      },
      type: "орудие убийства",      
      photoPath: "https://i2.rozetka.ua/goods/8796/cold_steel_jungle_machete_97jms_images_8796146.jpg",  
    },
    {
      evidence: {
        id: 1,
        name: "Бензопила КАЛИБР БП-1500/16У",
      },
      crime: {
          id: 1,
          type: "Забойства",
         criminalCase:
          {
              number: "УПН1854"
          }
      },
      type: "орудие убийства",      
      photoPath: "https://cdnmedia.220-volt.ru/content/products/485/485859/images/thumb_220/n1200x800/1.jpeg",  
    }
  ]

}
