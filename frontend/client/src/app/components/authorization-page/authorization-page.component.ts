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

  constructor() { }

  ngOnInit() {

  }

}
