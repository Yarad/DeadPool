package com.DTO;

public class ManShortedDTO extends ManOnlyPersonDTO {
    private String photoPath;

    public ManShortedDTO(long id, String name, String surname, String photoPath) {
        super(id, name, surname);
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }
}
