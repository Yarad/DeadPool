import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-add-evidence-page',
  templateUrl: './add-evidence-page.component.html',
  styleUrls: ['./add-evidence-page.component.css']
})
export class AddEvidencePageComponent implements OnInit {

  crime: {
    name: "Похищение ёлки",
    criminalCase: {
      id: "1",
      number: "УПН1854",
    }  
  }
  
  evidence = {    
    
  }

  constructor() { }

  ngOnInit() {
  }

}
