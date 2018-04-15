import {Input, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-crime-table',
  templateUrl: './crime-table.component.html',
  styleUrls: ['./crime-table.component.css']
})
export class CrimeTableComponent implements OnInit {
  @Input() crimes;

  constructor() { 
  }

  ngOnInit() {
    console.log(this.crimes);
  }

}
