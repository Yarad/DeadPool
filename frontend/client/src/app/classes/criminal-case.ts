import { Detective } from './detective'

export class CriminalCase {
    id: -1;
    number: string;
    createDate: string;
    closeDate: string;
    detective: Detective;
    closed: boolean;
    type: string;
    crimes = [];
    
    constructor(object) {
        this.id = object.id || -1;
        this.number = object.number || "";
        this.type = object.type || "Открыто";
        this.createDate = object.createDate || null;
        this.closeDate = object.closeDate || null;
        this.closed = object.closed || false;
        this.detective = object.detective ? new Detective(object.detective) : new Detective({});
        this.crimes = object.crimes || [];
    }

    isValidate() {
        if (this.id > -1 && this.number && this.type && this.createDate && this.detective.id) {
            return true;
        }
        return false;
    }
}