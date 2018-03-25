package com.DTO;

public class DetectiveWithoutManIdDTO extends DetectiveBaseDTO {
    private ManInfoWithoutIdDTO man;

    public DetectiveWithoutManIdDTO() { }

    public ManInfoWithoutIdDTO getMan() {
        return man;
    }

    public void setMan(ManInfoWithoutIdDTO man) {
        this.man = man;
    }
}
