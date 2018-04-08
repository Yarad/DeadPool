import { Crime } from './crime';

export class Evidence {
    id: -1;
    name: "";
    description: "";
    type: "";
    dateAdded: ""
    details: "";
    photoPath: "";
    
    constructor(object) {
        if (object) {
            this.id = object.id;
            this.name = object.name
            this.photoPath = object.photoPath;
            this.description = object.description;
            this.type = object.type;
            this.dateAdded = object.dateAdded;
            this.details = object.details;
        } 
    }        
    
}