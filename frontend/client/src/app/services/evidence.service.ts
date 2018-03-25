import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';
import { Evidence } from '../classes/evidence';

@Injectable()
export class EvidenceService {

  constructor(private http:Http,
    private mainService: MainService
  ) {}

}
