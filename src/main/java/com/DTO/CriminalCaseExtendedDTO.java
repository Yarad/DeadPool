package com.DTO;

import java.time.LocalDate;
import java.util.List;

public class CriminalCaseExtendedDTO extends CriminalCaseObjectDTO {
    private List<CrimeShortedDTO> crimes;

    public CriminalCaseExtendedDTO(long id, String number, String type,
                                   LocalDate createDate, LocalDate closeDate,
                                   DetectivePersonDTO detective, List<CrimeShortedDTO> crimes) {
        super(id, number, type, createDate, closeDate, detective);
        this.crimes = crimes;
    }

    public List<CrimeShortedDTO> getCrimes() {
        return crimes;
    }

    public void setCrimes(List<CrimeShortedDTO> crimes) {
        this.crimes = crimes;
    }
}
