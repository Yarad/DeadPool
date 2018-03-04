import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

@Component({
  selector: 'app-add-criminal-case-page',
  templateUrl: './add-criminal-case-page.component.html',
  styleUrls: ['./add-criminal-case-page.component.css']
})
export class AddCriminalCasePageComponent implements OnInit {

  detectives = [];

  criminalCase = {
    number: "",
    createDate: "",
    detective: {
      id: null
    }
  }

  constructor(private router: Router) { 
    this.detectives = this.loadAllDetectives();
  }

  ngOnInit() {
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
      }
    ]
  }

  changeSelectedDetective($event) {
    this.criminalCase.detective.id = $event.target.value;
  }

  isCriminalCaseValidate(criminalCase) {
    if (criminalCase.number &&
      criminalCase.createDate
    ) {
        return true;
    }
    return false;
  }

  addNewCriminalCase() {
    if (this.isCriminalCaseValidate(this.criminalCase)) { 
      if (!this.criminalCase.detective.id) {
        this.criminalCase.detective.id = this.detectives[0].id;
      }    
      this.router.navigate(['/']);  
    }
  }

}
