package com.DTO;

import java.util.List;

public class ListCriminalCasesDTO {
    private List<CriminalCaseObjectDTO> criminalCases;

    public ListCriminalCasesDTO(List<CriminalCaseObjectDTO> criminalCases) {
        this.criminalCases = criminalCases;
    }

    public List<CriminalCaseObjectDTO> getCriminalCases() {
        return criminalCases;
    }

    public void setCriminalCases(List<CriminalCaseObjectDTO> criminalCases) {
        this.criminalCases = criminalCases;
    }
}
