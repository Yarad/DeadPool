package com.DTO;

public class EnumDTO {
    private String enumValue;
    private String name;

    public EnumDTO(String enumValue, String name) {
        this.enumValue = enumValue;
        this.name = name;
    }

    public String getEnumValue() {
        return enumValue;
    }

    public void setEnumValue(String enumValue) {
        this.enumValue = enumValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
