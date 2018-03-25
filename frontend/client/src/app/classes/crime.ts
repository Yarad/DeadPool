import { CriminalCase } from './criminal-case';

export class Crime {

    criminalCase = {};
    id: string;
    type: string;
    description: string;
    date: string;
    time: string;
    place: string
    
    
    constructor(object) {
        this.id = object.id || -1;
        this.type = object.type;
        this.description = object.description;
        this.date = object.date;
        this.time = object.time;
        this.place = object.place;
        this.criminalCase = new CriminalCase(object.criminalCase);
    }
}