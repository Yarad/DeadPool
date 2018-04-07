import { CriminalCase } from './criminal-case';
import { Enum } from './enum';

export class Crime {

    criminalCase = {
        id: -1
    };
    id: string;
    type: Enum;
    description: string;
    date: string;
    time: string;
    place: string
    evidencesOfCrime= [];
    
    constructor(object) {
        console.log('con',object)
        this.id = object.id || -1;
        if (object.type) 
            this.type = new Enum (object.type);
        this.date = object.date;
        this.time = object.time;
        this.place = object.place;
        this.criminalCase = object.criminalCase ? new CriminalCase(object.criminalCase) : new CriminalCase({
            id: 1,
            number: "asdf",
            type: "открыто"
        });
        this.description = object.description;
        this.evidencesOfCrime = object.evidencesOfCrime;
    }
}