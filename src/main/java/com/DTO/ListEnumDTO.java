package com.DTO;

import java.util.List;

public class ListEnumDTO {
    private List<EnumDTO> enums;

    public ListEnumDTO(List<EnumDTO> enums) {
        this.enums = enums;
    }

    public List<EnumDTO> getEnums() {
        return enums;
    }

    public void setEnums(List<EnumDTO> enums) {
        this.enums = enums;
    }
}
