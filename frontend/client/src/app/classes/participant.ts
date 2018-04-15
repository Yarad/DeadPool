import { Man } from './man';
import { Crime } from './crime';

export class Participant {
    man: {};
    crime: {};
    status: string;
    photoPath: string;
    dateAdded = '';
    timeAdded = '';
    alibi: string;
    witnessReport: string;

    constructor(object) {
        if (object) {
            this.man = new Man(object.man);
            this.crime = new Crime(object.crime);
            this.status = object.status;
            this.photoPath = object.photoPath;
            this.dateAdded = object.dateAdded;
            this.timeAdded = object.timeAdded;
            this.alibi = object.alibi;
            this.witnessReport = object.witnessReport;
        } else {
            const nowDate = new Date();
            this.dateAdded = nowDate.getFullYear() + '-' + ('0' + (nowDate.getMonth() + 1)).slice(-2) + '-' + ('0' + nowDate.getDate()).slice(-2);
            this.timeAdded = ('0' + (nowDate.getHours())).slice(-2)+ ':' + ('0' + nowDate.getMinutes()).slice(-2);
            
        }
    }
        
}