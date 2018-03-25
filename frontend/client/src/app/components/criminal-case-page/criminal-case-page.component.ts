import { Component, OnInit } from '@angular/core';
import { CriminalCaseService } from '../../services/criminal-case.service';

@Component({
  selector: 'app-criminal-case-page',
  templateUrl: './criminal-case-page.component.html',
  styleUrls: ['./criminal-case-page.component.css']
})
export class CriminalCasePageComponent implements OnInit {
  readMode = true;
  allDetectives = [];

  criminalCase = {
    id: 1,
    number: "УПН1854",
    type: "Oткрыто",
    createDate: "2017-01-25",
    closeDate: null,
    detective:
    {
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
 }
 oldCriminalCase;

  constructor(
    private criminalCaseService:CriminalCaseService
  ) { }

  ngOnInit() {
  }

  switchMode(mode) {
    this.readMode = mode;
    if (!mode) {
      this.allDetectives = this.loadAllDetectives();
      this.oldCriminalCase = this.copyObject(this.criminalCase);
    }
  }

  saveChanges() {
    this.switchMode(true);
  }

  cancellationOfChanges() {
    this.criminalCase = this.oldCriminalCase;
    this.switchMode(true);
  }
  
  changeSelectedDetective($event) {
    const index = this.allDetectives.map(detective => detective.id).indexOf(+$event.target.value);
    if (index > -1) {
      this.criminalCase.detective = this.allDetectives[index];
    }
  }

  closeCriminalCase() {
  }

  loadAllDetectives() {
    return [
      {
        id: 1,
        name: "Шерлок",
        surname: "Холмс"
      },
      {
        id: 2,
        name: "Жареная",
        surname: "Картошка"
      },
      {
        id: 3,
        name: "Андрей",
        surname: "Жлобич"
      }
    ]
  }

  copyObject(currObject) {
    const newObject = {};

    Object.keys(currObject).forEach(key => {
      Object.assign(newObject, {
        [key]: currObject[key]
      })
    });

    return newObject;
  }
}
