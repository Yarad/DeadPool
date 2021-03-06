package com.logic;

import com.DAO.DAODetective;

import java.time.LocalDate;

public class CriminalCase {
    private long criminalCaseId = -1;
    private boolean closed;
    private long detectiveId = -1;
    private String criminalCaseNumber;
    private LocalDate createDate;
    private LocalDate closeDate;
    private Detective parentDetective;

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }

    public long getDetectiveId() {
        return detectiveId;
    }

    public void setDetectiveId(long detectiveId) {
        this.detectiveId = detectiveId;
        parentDetective = null;
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

    public long getCriminalCaseId() {
        return criminalCaseId;
    }

    public void setCriminalCaseId(long criminalCaseId) {
        this.criminalCaseId = Math.abs(criminalCaseId);
    }

    public Detective getParentDetective() {
        if (parentDetective == null) {
            DAODetective daoDetective = new DAODetective();
            parentDetective = daoDetective.getDetectiveById(this.getDetectiveId());
        }

        return parentDetective;
    }

    public void setParentDetective(Detective parentDetective) {
        this.parentDetective = parentDetective;
        this.detectiveId = parentDetective.getManId();
    }
}
