package com.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CriminalCaseObjectDTO extends CriminalCaseShortedWithDetectiveDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    private LocalDate createDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    private LocalDate closeDate;

    public CriminalCaseObjectDTO(long id, String number, String type, DetectivePersonDTO detective,
                                 LocalDate createDate, LocalDate closeDate) {
        super(id, number, type, detective);
        this.createDate = createDate;
        this.closeDate = closeDate;
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
}
