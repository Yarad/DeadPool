import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-crime-page',
  templateUrl: './crime-page.component.html',
  styleUrls: ['./crime-page.component.css']
})
export class CrimePageComponent implements OnInit {
  crime = {
    id: 1,
    criminalCase: {
            id: 1,
            number: "УПН1854",
            status: "Открыто",
    },
    type: "Убийство",
    description: "Убита Агата Кристи. Убийца нанёс 3 удара ножом в грудь виновной",
    date: "2018-02-26",
    time: "12:00:00",
    place: "БГУИР, каб. 213а",
    detective: {
        id: 2,
        name: "Шерлок",
        surname: "Холмс"
    },
    participants: [
      {
      photoPath: "https://res.cloudinary.com/dyzdll94h/image/upload/v1512924240/mpv69dvnolst2gtjjljh.jpg",
      name: "Ангелина",
      surname: "Хилькевич",
      status: "Подозреваемый"
    },
    {
      photoPath: "https://res.cloudinary.com/dyzdll94h/image/upload/v1513188126/kxj1l9ekt31bp67ir8db.jpg",
      name: "Алина",
      surname: "Ивченко",
      status: "Очевидец"
    },
    {
      photoPath: "https://res.cloudinary.com/dyzdll94h/image/upload/v1513188126/kxj1l9ekt31bp67ir8db.jpg",
      name: "Алина",
      surname: "Ивченко",
      status: "Очевидец"
    }
    ],
    evidences: [
      {
        photoPath: "https://i2.rozetka.ua/goods/8796/cold_steel_jungle_machete_97jms_images_8796146.jpg",
        name: "Мачете Cold Steel Jungle Machete 97JMS",
        type: "орудие убийства"
      },
      {
        photoPath: "https://cdnmedia.220-volt.ru/content/products/485/485859/images/thumb_220/n1200x800/1.jpeg",
        name: "Бензопила КАЛИБР БП-1500/16У",
        type: "что-то"
      }
    ]
  }

  constructor() { }

  ngOnInit() {
  }

  tabSelect($event) {
  }

}
