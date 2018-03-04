import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-criminal-case-page',
  templateUrl: './criminal-case-page.component.html',
  styleUrls: ['./criminal-case-page.component.css']
})
export class CriminalCasePageComponent implements OnInit {
  criminalCase = {
    id: 1,
    number: "УПН1854",
    type: "Oткрыто",
    createDate: "2017-01-25",
    closeDate: null,
    detective:
    {
            id: 2,
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

  constructor() { }

  ngOnInit() {
  }

}
