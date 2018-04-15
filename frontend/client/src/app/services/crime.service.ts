import { Injectable } from '@angular/core';
import {Http, Headers} from '@angular/http';
import {Observable} from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import 'rxjs/add/observable/throw';
import { MainService } from './main.service';
import { Crime } from '../classes/crime';
import { Enum } from '../classes/enum';


@Injectable()
export class CrimeService {

  constructor(private http:Http,
    private mainService: MainService
  ) {}

  getAllCrimes(): Observable<Crime[]> { 
    return this.mainService.getAuthorizedRequest("/crimes")
    .map(res => {
      if(!res.result.error && res.result.crimes) { 
        return res.result.crimes.map(crime => {
          return new Crime(crime);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  getCrime(id): Observable<Crime> {
    return this.mainService.getAuthorizedRequest("/crimes/" + id)
    .map(res => {
      if(!res.error && res.result) { 
        return new Crime(res.result);
      }
      return {};
    })
    .catch((error: any) => { 
      return Observable.throw(error);
    });
  }

  addNewCrime(crime): Observable<Boolean> {
    return this.mainService.postAuthorizedRequest("/crimes/add", { 
      criminalCase: {
        id: crime.criminalCase.id
      },
      type: crime.type.enumValue,
      description: crime.description,
      date: crime.date,
      time: crime.time,
      place: crime.place
    })
    .map(res => {
      return res.success
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  updateCrime(crime): Observable<Boolean> {
    console.log(crime);
    return this.mainService.postAuthorizedRequest("/crimes/update", {
      id: crime.id, 
      criminalCase: {
        id: crime.criminalCase.id
      },
      type: crime.type.enumValue,
      description: crime.description,
      date: crime.date,
      time: crime.time,
      place: crime.place
    })
    .map(res => {
      return res.success;
    })
    .catch((error: any)=> {
      return Observable.throw(error);
    });
  }

  getCrimeTypes(): Observable<Enum[]> {
    return this.mainService.getAuthorizedRequest("/crimes/types_list")
    .map(res => {
      if(!res.error && res.enums) {     
        return res.enums.map(status => {
          return new Enum(status);
        });
      }
      return [];
    })
    .catch((error: any)=> { 
      return Observable.throw(error);
    });
  }

  crimes = [
    {
    id: 1,
    criminalCase: {
            id: 1,
            number: "УПН1854",
            type: "Открыто",
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
  },
  {
    id: 2,
    criminalCase: {
            id: 1,
            number: "adf1854",
            type: "Открыто",
    },
    type: "Убийство",
    description: "Убита Агата Кристи. Убийца нанёс 3 удара ножом в грудь виновной",
    date: "2018-02-26",
    time: "12:00:00",
    place: "sdf3а",
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
      }
    ],
    evidences: [
      {
        photoPath: "https://cdnmedia.220-volt.ru/content/products/485/485859/images/thumb_220/n1200x800/1.jpeg",
        name: "Бензопила КАЛИБР БП-1500/16У",
        type: "что-то"
      }
    ]
  }
  ]

}
