package com.logic;

public enum CrimeType {
    MURDER("Убийство"),
    ROBBERY("Ограбление"),
    RAPE("Изнасилование"),
    ARSON("Поджог"),
    SUICIDE("Самоубийство"),
    DELETED("УДАЛЕНО");
    //TODO: дополнить

    private final String fieldDescription;

    private CrimeType(String value) {
        fieldDescription = value;
    }

    public String getName() {
        return fieldDescription;
    }
}
