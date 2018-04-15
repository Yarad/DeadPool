import {Input, Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-participants-table',
  templateUrl: './participants-table.component.html',
  styleUrls: ['./participants-table.component.css']
})
export class ParticipantsTableComponent implements OnInit {

  @Input() participants;
  @Input() tableType;
  @Input() crimeId;

  constructor() { 
    console.log('ParticipantsTableComponent',this.participants)
  }

  ngOnInit() {
  }

}
