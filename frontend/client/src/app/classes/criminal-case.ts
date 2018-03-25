export class CriminalCase {
    id: number;
    number: string;
    status: string;
    createDate: string;
    closeDate: string;
    detective = [];
    crimes = [];
    
    constructor(object) {
        this.id = object.id;
        this.number = object.number;
        this.status = object.type;
        this.createDate = "2018-12-12"; //object.createDate;
        this.closeDate = null;//object.closeDate;
    }
}