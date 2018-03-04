package com.logic;

public class Evidence {
    public long getEvidenceId() {
        return evidenceId;
    }
    public void setEvidenceId(long evidenceId) {
        this.evidenceId = evidenceId;
    }

    private long evidenceId;
    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
