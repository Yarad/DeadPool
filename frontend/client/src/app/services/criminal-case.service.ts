import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';
import { CriminalCase } from '../classes/criminal-case';

@Injectable()
export class CriminalCaseService {

  constructor(private http:Http,
    private mainService: MainService
  ) {}

  getAllCriminalCases(): Observable<CriminalCase[]> { 
    return this.mainService.getAuthorizedRequest("/criminal_cases")
    .map(res => {
      if(!res.error && res.criminalCases) {
        return res.criminalCases.map(criminalCase => {
          return new CriminalCase(criminalCase);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  getAllSolvedCriminalCases() {   
    this.mainService.getAuthorizedRequest("/criminal_cases/solved")
    .map(res => {
      if(!res.error && res.result.criminalCases) {
        return res.result.criminalCases.map(criminalCase => {
          return new CriminalCase(criminalCase);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  getAllUnsolvedCriminalCases() {   
    this.mainService.getAuthorizedRequest("/criminal_cases/unsolved")
    .map(res => {
      if(!res.error && res.result.criminalCases) {
        return res.result.criminalCases.map(criminalCase => {
          return new CriminalCase(criminalCase);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  getAllOpenCriminalCases() {   
    this.mainService.getAuthorizedRequest("/criminal_cases/open")
    .map(res => {
      if(!res.error && res.result.criminalCases) {
        return res.result.criminalCases.map(criminalCase => {
          return new CriminalCase(criminalCase);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  addNewCriminalCase(criminalCase) {
    // return this.mainService.postAuthorizedRequest("/criminal_cases/add", criminalCase)
    // .subscribe(res => {
    //   if(!res.error && res.result.criminalCases) {
    //     return res.result.criminalCases;
    //   }
    //   return [];
    // });
  }

  

  criminalCases = [
    {
      id: 1,
      number: "УПН1854",
      type: "Oткрыто",
      createDate: "2017-01-25",
      closeDate: null,
      detective: {
        id: 1,
        name: "Шерлок",
        surname: "Холмс",
      },
      crimes:[
        {
                id: 1,
                type: "Убийство",
                date: "2018-02-26",
                place: "БГУИР, каб. 213а"
        }
      ]
    },
    {
      id: 2,
      number: "aaaaa",
      type: "Oткрыто",
      createDate: "2017-01-25",
      closeDate: null,
      detective: {
        id: 2,
        name: "Мяу",
        surname: "Котик",
      },
      crimes:[
        {
                id: 1,
                type: "Убийство",
                date: "2018-02-26",
                place: "БГУИР, каб. 213а"
        }
      ]
    }
  ];
  
  

}
