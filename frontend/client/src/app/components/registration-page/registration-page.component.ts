import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { AuthorizationService } from '../../services/authorization.service';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  error = {
    isError: false,
    text: ""
  };

  user = { 
    man:
    {
        name: "",
        surname: "",
        birthday: "",
        photoPath: "",
        homeAddress: ""
    },
    login: "",
    password: "",
    email: ""
  }

  constructor(private authorizationService: AuthorizationService,
  private router: Router) { 
  }

  ngOnInit() {
  }

  registrate() {
    this.error.isError = false;

    if (this.user.login && this.user.email && this.user.password && this.user.man.name && this.user.man.surname) {
      this.authorizationService.registration(this.user).subscribe(
        data => {
          if(!data.error) {

            this.router.navigate(['/login']);
          }
          else {
            this.error.isError = true;
            this.error.text = data.result
          }
        },
        error => {          
          this.error.isError = true;
          this.error.text = error
        }
      );
    }
  }

}
