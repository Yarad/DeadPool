import { Component, OnInit, Input} from '@angular/core';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  @Input() data;

  tree = {
    leaves: [
      {
        name: "Главная страница",
        link: "/",
        leaves: [
          {
            name: "Дело",
            link: "/criminal_case",
            leaves: [
              {
                name: "Преступление",
                link: "/crime",
                leaves: []
              }
            ]
          },
          {
            name: "Добавление дела",
            link: "/add_criminal_case",
            leaves: []
          }
        ]
      }
    ]
  }

  breadcrumbs = [];

  constructor() { 
    console.log(window.location.href);
    const currentUrl = window.location.href;
    let currentLeaves = this.tree.leaves;
    let i = 0;
    while(currentLeaves[i] && currentLeaves.length < i) {
      if(currentUrl.indexOf(currentLeaves[i].link) >= 0) {
        this.breadcrumbs.push({
          name: currentLeaves[i].name,
          link: currentLeaves[i].link
        });
        if(currentLeaves[i].leaves.length === 0) {
          break;
        } else {
          currentLeaves = currentLeaves[i].leaves;
          i= 0;
        }
      } else {
        i++;
      }
    }
  }

  ngOnInit() {
  }

}
