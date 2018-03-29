import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { AuthorizationService } from '../../services/authorization.service';
import { LocalStorageService } from 'angular-2-local-storage';

@Component({
  selector: 'app-authorization-page',
  templateUrl: './authorization-page.component.html',
  styleUrls: ['./authorization-page.component.css']
})
export class AuthorizationPageComponent implements OnInit {

  error = {
    isError: false,
    text: ""
  };

  user = {
    login: "",
    password: ""
  }

  constructor(private authorizationService: AuthorizationService,
  private router: Router) { }

  ngOnInit() {

  }

  authorize(): void{
   // if (this.user.login && this.user.password) {
    this.authorizationService.authorization(this.user)
      .subscribe(res => {
        if(res.error){
          this.error = res.error;
        } else {
          if(res.result){
            localStorage.setItem('user', JSON.stringify({ token: res.result, login: this.user.login}));
            this.router.navigate(['/']);
          }
        }
      });
   // }
  }

}
