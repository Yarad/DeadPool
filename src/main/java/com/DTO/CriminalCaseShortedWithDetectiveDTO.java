package com.DTO;

public class CriminalCaseShortedWithDetectiveDTO extends CriminalCaseShortedDTO {
    private DetectivePersonDTO detective;

    public CriminalCaseShortedWithDetectiveDTO(long id, String number, String type, DetectivePersonDTO detective) {
        super(id, number, type);
        this.detective = detective;
    }

    public DetectivePersonDTO getDetective() {
        return detective;
    }

    public void setDetective(DetectivePersonDTO detective) {
        this.detective = detective;
    }
}
