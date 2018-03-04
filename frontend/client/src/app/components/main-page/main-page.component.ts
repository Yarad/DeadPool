import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  activeCategoryId = 0;

  criminalCases = [
    {
      criminalCaseId: 1,
      criminalCaseNumber: "РК1244",
      criminalCaseStatus: "Закрыто"
    },
    {
      criminalCaseId: 2,
      criminalCaseNumber: "TC23423",
      criminalCaseStatus: "Открыто"
    }
  ];

  crimes = [
    {
      id: 1,
      criminalCaseNumber: "РК1244",
      crimeDate: "12.11.2017",
      criminalCaseStatus: "Закрыто"
    },
    {
      id: 2,
      criminalCaseNumber: "TC23423",
      crimeDate: "25.02.2018",
      criminalCaseStatus: "Открыто"
    }
  ];

  evidences = [
    {
      photoPath: "https://i2.rozetka.ua/goods/8796/cold_steel_jungle_machete_97jms_images_8796146.jpg",
      name: "Мачете Cold Steel Jungle Machete 97JMS",
      criminalCaseNumber: "РК1244",
    },
    {
      photoPath: "https://cdnmedia.220-volt.ru/content/products/485/485859/images/thumb_220/n1200x800/1.jpeg",
      name: "Бензопила КАЛИБР БП-1500/16У",
      criminalCaseNumber: "TC23423",
    }
  ];

  participants = [
    {
      photoPath: "https://res.cloudinary.com/dyzdll94h/image/upload/v1512924240/mpv69dvnolst2gtjjljh.jpg",
      name: "Ангелина",
      surname: "Хилькевич",
      crimes_part_amount: 3
    },
    {
      photoPath: "https://res.cloudinary.com/dyzdll94h/image/upload/v1513188126/kxj1l9ekt31bp67ir8db.jpg",
      name: "Алина",
      surname: "Ивченко",
      crimes_part_amount: 1
    }
  ];
  
  constructor() { }

  ngOnInit() {
  }

  changeActiveCategory(id) {
    this.activeCategoryId = id;
  }

}
