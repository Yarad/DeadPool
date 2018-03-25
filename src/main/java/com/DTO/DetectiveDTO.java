package com.DTO;

public class DetectiveDTO extends DetectiveBaseDTO {
    private ManInfoDTO man;

    public DetectiveDTO() { }

    public ManInfoDTO getMan() {
        return man;
    }

    public void setMan(ManInfoDTO man) {
        this.man = man;
    }
}
