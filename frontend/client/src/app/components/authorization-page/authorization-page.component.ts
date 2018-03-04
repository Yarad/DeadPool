import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

@Component({
  selector: 'app-authorization-page',
  templateUrl: './authorization-page.component.html',
  styleUrls: ['./authorization-page.component.css']
})
export class AuthorizationPageComponent implements OnInit {

  error = {
    isError: false,
    text: "Проверьте логин и пароль."
  };

  user = {
    login: "",
    password: ""
  }

  constructor() { }

  ngOnInit() {

  }

  authorize(): void{
    this.error.isError = !this.error.isError;
   /* this.loginService.login(this.user)
        .subscribe(res => {
          if(res.error){
            this.error = res.error;
         } else {
          if(res.token && res.username && res.id){
            localStorage.setItem('currentUser', JSON.stringify({ token: res.token, username: res.username, id: res.id, role: res.role }));
          }
         }
        });*/
  }

}
