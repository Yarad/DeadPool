package com.DTO;

import java.time.LocalDate;

public class ManInfoDTO extends ManInfoWithoutIdDTO {
    private long id;

    public ManInfoDTO(long id, String name, String surname, LocalDate birthday, String homeAddress, String photoPath) {
        super(name, surname, birthday, homeAddress, photoPath);
        this.id = id;
    }
    public ManInfoDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
