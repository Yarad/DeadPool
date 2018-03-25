import { Crime } from './crime';
import { Evidence } from './evidence';

export class EvidenceOfCrime {
    evidence: {};
    crime: {};
    type: string;
    photoPath: string;
    
    constructor(object) {
        this.evidence = new Evidence(object.evidence);        
        this.crime = new Crime(object.crime);
        this.type = object.type;
        this.photoPath = object.photoPath;
    }        
    
}