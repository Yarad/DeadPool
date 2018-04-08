package com.logic;

public enum EvidenceType {
    CRIME_INSTRUMENT("Орудие преступления"),                            //орудие преступления
    PRINT("Отпечаток"),                                                 //предмет с отпечатком (пальца/подошвы/др.)
    OBJECT_FROM_CRIME_SCENE("Предмет с места преступления"),            //потерпевший
    DELETED("УДАЛЕНО");
    //TODO: дополнить

    private final String fieldDescription;

    private EvidenceType(String value) {
        fieldDescription = value;
    }

    public String getName() {
        return fieldDescription;
    }
}
