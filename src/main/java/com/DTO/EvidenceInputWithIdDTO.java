package com.DTO;

public class EvidenceInputWithIdDTO extends EvidenceInputDTO {
    private long id;

    public EvidenceInputWithIdDTO() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
