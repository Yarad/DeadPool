import { Component, OnInit} from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {


  breadcrumbs = [
    {
      name: "Главная страница",
      link: "/"
    }
  ]  

  constructor() { 
    console.log(window.location.href)
  }

  ngOnInit() {
  }

}
