package com.DTO;

public class CriminalCaseInputWithIdDTO extends CriminalCaseInputDTO {
    private long id;

    public CriminalCaseInputWithIdDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
