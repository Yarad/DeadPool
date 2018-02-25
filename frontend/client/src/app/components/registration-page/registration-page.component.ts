import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  error = {
    isError: false,
    text: "Проверьте логин и пароль."
  };

  constructor() { }

  ngOnInit() {
  }

}
