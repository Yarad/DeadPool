import { Man } from './man';
import { Crime } from './crime';

export class Participant {
    man: {};
    crime: {};
    status: string;
    photoPath: string;
    dateAdded: string;
    alibi: string;
    witnessReport: string;

    constructor(object) {
        this.man = new Man(object.man);
        this.crime = new Crime(object.crime);
        this.status = object.status;
        this.photoPath = object.photoPath;
        this.dateAdded = object.dateAdded;
        this.alibi = object.alibi;
        this.witnessReport = object.witnessReport;
    }
        
}