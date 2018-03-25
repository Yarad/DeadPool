import { Injectable } from '@angular/core';

function loadAllDetectives() {
  return [
    {
      id: 1,
      name: "Шерлок",
      surname: "Холмс"
    },
    {
      id: 2,
      name: "Жареная",
      surname: "Картошка"
    },
    {
      id: 3,
      name: "Андрей",
      surname: "Жлобич"
    }
  ]
}

@Injectable()
export class DetectiveService {

  constructor() { }

  getAllDetectives() {
    // loadAllDetectives()
    //   .subscribe(res => {
    //   if(!res.error && res.detectives) {
    //     return res.detectives;
    //   }
    //   return [];
    // });
    return loadAllDetectives();
  }
  
}
