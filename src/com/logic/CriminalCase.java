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

    public void setClosed(Object closed) {
        this.closed = Boolean.valueOf(closed.toString());
    }

    public int getDetectiveId() {
        return detectiveId;
    }

    public void setDetectiveId(int detectiveId) {
        this.detectiveId = detectiveId;
    }

    public void setDetectiveId(Object detectiveId) {
        try {
            this.detectiveId = Integer.parseInt(detectiveId.toString());
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }

    public String getCriminalCaseNumber() {
        return criminalCaseNumber;
    }

    public void setCriminalCaseNumber(String criminalCaseNumber) {
        this.criminalCaseNumber = criminalCaseNumber;
    }

    public void setCriminalCaseNumber(Object criminalCaseNumber) {
        this.criminalCaseNumber = criminalCaseNumber.toString();
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public void setCreateDate(Object createDate) {
        try {
            this.createDate = LocalDate.parse(createDate.toString());
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }

    public LocalDate getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(LocalDate closeDate) {
        this.closeDate = closeDate;
    }

    public void setCloseDate(Object closeDate) {
        try {
            this.closeDate = LocalDate.parse(closeDate.toString());
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }

    public int getCriminalCaseId() {
        return criminalCaseId;
    }

    public void setCriminalCaseId(int criminalCaseId) {
        this.criminalCaseId = Math.abs(criminalCaseId);
    }

    public void setCriminalCaseId(Object criminalCaseId) {
        try {
            this.criminalCaseId = Math.abs(Integer.parseInt(criminalCaseId.toString()));
        } catch (Exception e) {
            LogicLog.log(e.toString());
        }
    }
}
