package com.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class CriminalCaseObjectDTO {
    private long id;
    private String number;
    private String type;

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private LocalDate createDate;

    @JsonFormat(shape=JsonFormat.Shape.STRING)
    private LocalDate closeDate;

    private DetectivePersonDTO detective;

    public CriminalCaseObjectDTO(long id, String number, String type,
                                 LocalDate createDate, LocalDate closeDate,
                                 DetectivePersonDTO detective) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.createDate = createDate;
        this.closeDate = closeDate;
        this.detective = detective;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
