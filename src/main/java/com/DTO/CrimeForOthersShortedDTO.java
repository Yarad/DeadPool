package com.DTO;

public class CrimeForOthersShortedDTO {
    private long id;
    private String type;
    private CriminalCaseOnlyNumberDTO criminalCase;

    public CrimeForOthersShortedDTO(long id, String type, CriminalCaseOnlyNumberDTO criminalCase) {
        this.id = id;
        this.type = type;
        this.criminalCase = criminalCase;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CriminalCaseOnlyNumberDTO getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCaseOnlyNumberDTO criminalCase) {
        this.criminalCase = criminalCase;
    }
}
