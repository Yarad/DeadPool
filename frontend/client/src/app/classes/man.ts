export class Man {
    id: number
    name: string;
    surname: string;
    photoPath: string;    
    birthday: string;
    homeAddress: string;
    crimesPartAmount: number;
    
    constructor(object) {
        this.id = object.id;
        this.name = object.name
        this.surname = object.surname;
        this.photoPath = object.photoPath;
        this.birthday = object.birthday;
        this.homeAddress = object.homeAddress;
        this.crimesPartAmount = object.crimesPartAmount;
    }
    
}