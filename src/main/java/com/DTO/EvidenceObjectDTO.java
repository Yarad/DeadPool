package com.DTO;

public class EvidenceObjectDTO extends EvidenceShortedDTO {
    private String description;

    public EvidenceObjectDTO(long id, String name, String description) {
        super(id, name);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
