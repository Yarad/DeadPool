package com.DTO;

public class CrimeInputWithIdDTO extends CrimeInputDTO {
    private long id;

    public CrimeInputWithIdDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
