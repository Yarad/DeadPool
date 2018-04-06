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
      if(!res.result.error && res.result.criminalCases) {
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

  getAllSolvedCriminalCases(): Observable<CriminalCase[]> {   
    return this.mainService.getAuthorizedRequest("/criminal_cases/solved")
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

  getAllUnsolvedCriminalCases(): Observable<CriminalCase[]> {   
    return this.mainService.getAuthorizedRequest("/criminal_cases/unsolved")
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

  getAllOpenCriminalCases(): Observable<CriminalCase[]> {   
    return this.mainService.getAuthorizedRequest("/criminal_cases/open")
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

  getCriminalCase(id): Observable<CriminalCase> {
    return this.mainService.getAuthorizedRequest("/criminal_cases/" + id)
    .map(res => {
      if(!res.error && res.result) { 
        return new CriminalCase(res.result);
      }
      return {};
    })
    .catch((error: any) => { 
      return Observable.throw(error);
    });
  }

  addNewCriminalCase(criminalCase): Observable<Boolean> {
    return this.mainService.postAuthorizedRequest("/criminal_cases/add", criminalCase)
    .map(res => {
        return res.success;
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  updateCriminalCase(criminalCase): Observable<Boolean> {
    console.log()
    return this.mainService.postAuthorizedRequest("/criminal_cases/update", {      
      id: criminalCase.id,
      detective:
      {
          id: criminalCase.detective.id
      },
      number: criminalCase.number,
      createDate: criminalCase.createDate,
      closed: criminalCase.closed,
      closeDate: criminalCase.closeDate    
    })
    .map(res => {
      return res.success;
    })
    .catch((error: any)=> {
      return Observable.throw(error);
    });
  }

  

  criminalCases = [
    {
      id: 1,
      number: "У54",
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
