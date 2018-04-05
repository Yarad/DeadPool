package com.DTO;

import java.time.LocalDate;

public class CrimeObjectDTO extends CrimeShortedDTO {
    private CriminalCaseShortedDTO criminalCase;

    public CrimeObjectDTO(long id, EnumDTO type, LocalDate date, String place, CriminalCaseShortedDTO criminalCase) {
        super(id, type, date, place);
        this.criminalCase = criminalCase;
    }

    public CriminalCaseShortedDTO getCriminalCase() {
        return criminalCase;
    }

    public void setCriminalCase(CriminalCaseShortedDTO criminalCase) {
        this.criminalCase = criminalCase;
    }
}
