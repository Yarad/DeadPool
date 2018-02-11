package com.logic;

import java.time.LocalDate;

public class CriminalCase {
    private int criminalCaseId = -1;
    private boolean closed = false;
    private int detectiveId = -1;
    private String criminalCaseNumber = "NoCriminalCaseNumber";
    private LocalDate createDate = LocalDate.now();
    private LocalDate closeDate = null;

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public int getDetectiveId() {
        return detectiveId;
    }

    public void setDetectiveId(int detectiveId) {
        this.detectiveId = detectiveId;
    }

    public String getCriminalCaseNumber() {
        return criminalCaseNumber;
    }

    public void setCriminalCaseNumber(String criminalCaseNumber) {
        this.criminalCaseNumber = criminalCaseNumber;
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

    public int getCriminalCaseId() {
        return criminalCaseId;
    }

    public void setCriminalCaseId(int criminalCaseId) {
        this.criminalCaseId = Math.abs(criminalCaseId);
    }
}
