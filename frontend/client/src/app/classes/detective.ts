export class Detective {
    id: number
    name: string;
    surname: string;
    photoPath: string;
    
    constructor(object) {
        this.id = object.id;
        this.name = object.name;
        this.surname = object.surname;
        this.photoPath = object.photoPath;
    }
}