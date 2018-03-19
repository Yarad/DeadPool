package com.DTO;

import java.time.LocalDate;

public class CriminalCaseInputDTO {
    private IdOnlyDTO detective;
    private String number;
    private LocalDate createDate;
    private boolean closed;
    private LocalDate closeDate;

    public CriminalCaseInputDTO() { }

    public IdOnlyDTO getDetective() {
        return detective;
    }

    public void setDetective(IdOnlyDTO detective) {
        this.detective = detective;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public boolean getClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }
}
