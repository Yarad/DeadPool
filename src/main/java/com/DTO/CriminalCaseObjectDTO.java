package com.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CriminalCaseObjectDTO extends CriminalCaseShortedDTO {
    private LocalDate createDate;
    private LocalDate closeDate;
    private DetectivePersonDTO detective;

    public CriminalCaseObjectDTO(long id, String number, String type,
                                 LocalDate createDate, LocalDate closeDate,
                                 DetectivePersonDTO detective) {
        super(id, number, type);
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.detective = detective;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public DetectivePersonDTO getDetective() {
        return detective;
    }

    public void setDetective(DetectivePersonDTO detective) {
        this.detective = detective;
    }
}
