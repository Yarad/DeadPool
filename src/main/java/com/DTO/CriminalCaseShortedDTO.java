package com.DTO;

public class CriminalCaseShortedDTO extends CriminalCaseOnlyNumberDTO {
    private long id;
    private String type;

    public CriminalCaseShortedDTO(long id, String number, String type) {
        super(number);
        this.id = id;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
