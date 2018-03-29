import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { CriminalCaseService } from '../../services/criminal-case.service';
import { CriminalCase } from '../../classes/criminal-case';
import { DetectiveService } from '../../services/detective.service';

@Component({
  selector: 'app-add-criminal-case-page',
  templateUrl: './add-criminal-case-page.component.html',
  styleUrls: ['./add-criminal-case-page.component.css']
})
export class AddCriminalCasePageComponent implements OnInit {

  detectives = [];

  criminalCase = new CriminalCase({});

  constructor(private router: Router,
    private detectiveService: DetectiveService,
    private criminalCaseService: CriminalCaseService
  ) { 
    this.detectiveService.getAllDetectives()
    .subscribe(
      data => this.detectives = data,
      error => this.detectives = this.detectiveService.detectives
    );
    this.criminalCase.detective.id = 1;
  }

  ngOnInit() {
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

      this.criminalCaseService.addNewCriminalCase(this.criminalCase)
      .subscribe(
        result => {if(result) this.router.navigate(['/'])},
        error => {this.router.navigate(['/'])}
      );
    }
  }

}
