import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute} from '@angular/router';
import { Man } from '../../classes/man';
import { ManService } from '../../services/man.service';

const CLOUDYNARY_URL = 'https://api.cloudinary.com/v1_1/dkdzgw7kp/image/upload';
const CLOUDYNARY_UPLOAD_PRESET = 'jr7zzle9';

@Component({
  selector: 'app-man-page',
  templateUrl: './man-page.component.html',
  styleUrls: ['./man-page.component.css']
})
export class ManPageComponent implements OnInit {

  readMode = true;
  
  man;
  manId;
  oldMan;

  private routeSubscription: Subscription;
  constructor( private router: Router,
    private route: ActivatedRoute,
    private manService: ManService,
  ) {
    this.routeSubscription = route.params.subscribe(params => {
      this.manId = params['id'];
    });
 
    this.manService.getMan(this.manId)
    .subscribe(
      data => {this.man = data},
      error => console.log('error', error)
    );    
   }

  ngOnInit() {
  }

  saveChanges() {
    if (true) {
      this.manService.updateMan(this.man)
      .subscribe(
        result => {
          if (result) {
            this.switchMode(true)
          }
        },
        error => this.switchMode(true)
      );
    }
  }

  cancellationOfChanges() {
    this.man = this.oldMan;
    this.switchMode(true);
  }

  switchMode(mode) {
    this.readMode = mode;
    if (!mode) {
      this.oldMan = this.copyObject(this.man);
    }
  }

  copyObject(currObject) {
    const newObject = {};

    Object.keys(currObject).forEach(key => {
      Object.assign(newObject, {
        [key]: currObject[key]
      })
    });

    return newObject;
  }
  
  public uploadFile(event: any) { 
    
    const file = event.target.files[0]; 
    const xhr = new XMLHttpRequest(); 
    const fd = new FormData();   

    xhr.open('POST', CLOUDYNARY_URL, true); 
    xhr.setRequestHeader('X-Requested-With', 'XMLHttpRequest'); 
    
    xhr.onreadystatechange = (e) => { 
      if (xhr.readyState === 4 && xhr.status === 200) { 
        const response = JSON.parse(xhr.responseText); 
        const imgs = document.getElementsByTagName('img'); 
        for (let i = 0; i < imgs.length; i++) { 
          const img = imgs[i]; 
          if (img.id === 'man-image') { 
            this.man.photoPath = response.secure_url;
            
        console.log(JSON.parse(xhr.responseText));  
            img.src = response.secure_url; 
            img.alt = response.public_id; 
          } 
        } 
      } 
    };
    fd.append('upload_preset', CLOUDYNARY_UPLOAD_PRESET ); 
    fd.append('file', file); 
    xhr.send(fd);  
  }

}
