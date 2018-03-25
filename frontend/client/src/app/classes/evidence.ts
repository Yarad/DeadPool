import { Crime } from './crime';

export class Evidence {
    id: number;
    name: string;
    description: string;
    crime: {};
    type: string;
    dateAdded: string
    details: string;
    photoPath: string;
    
    constructor(object) {
        this.id = object.id;
        this.name = object.name
        this.photoPath = object.photoPath;
        this.description = object.description;
        this.type = object.type;
        this.dateAdded = object.dateAdded;
        this.details = object.details;
        this.crime = new Crime(object.crime);
    }        
    
}