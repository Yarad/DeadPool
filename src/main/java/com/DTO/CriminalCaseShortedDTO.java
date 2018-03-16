package com.DTO;

public class CriminalCaseShortedDTO {
    private long id;
    private String number;
    private String type;

    public CriminalCaseShortedDTO(long id, String number, String type) {
        this.id = id;
        this.number = number;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
