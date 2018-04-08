import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-evidence-table',
  templateUrl: './evidence-table.component.html',
  styleUrls: ['./evidence-table.component.css']
})
export class EvidenceTableComponent implements OnInit {

  @Input() evidences;
  @Input() crimeId;

  constructor() { 
    console.log('evidence-table',this.evidences)
  }

  ngOnInit() {
  }

}
