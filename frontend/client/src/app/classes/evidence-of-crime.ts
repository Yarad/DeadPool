import { Crime } from './crime';
import { Evidence } from './evidence';

export class EvidenceOfCrime {
    evidence = new Evidence(null);
    crime: Crime;
    type: {};
    photoPath: "";
    details: "";
    dateAdded: string;
    timeAdded: string;
    
    constructor(object) {
        console.log(object)
        if (object) {
            this.evidence = new Evidence(object.evidence);  
            console.log('evidenceCrime',object.crime)      
            this.crime = new Crime(object.crime);
            this.type = object.type;
            this.photoPath = object.photoPath;
            this.details = object.details;
            this.dateAdded = object.dateAdded;
            this.timeAdded = object.timeAdded;
        } else {
            const nowDate = new Date();
            this.dateAdded = nowDate.getFullYear() + '-' + ('0' + (nowDate.getMonth() + 1)).slice(-2) + '-' + ('0' + nowDate.getDate()).slice(-2);
            this.timeAdded = nowDate.getHours()+ ':' + nowDate.getMinutes();
        }
    }        
    
}