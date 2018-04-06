export class Enum {
    enumValue: "";
    name: "";

    constructor(object) {
        this.enumValue = object.enumValue || '';
        this.name = object.name || '';
    }
}