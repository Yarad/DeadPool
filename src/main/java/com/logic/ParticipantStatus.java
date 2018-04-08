package com.logic;

public enum ParticipantStatus {
    SUSPECTED("Подозреваемый"), //подозоеваемый
    WITNESS("Свидетель"),       //просто опросили (свидетель)
    VICTIM("Потерпевший"),      //потерпевший
    SPECTATOR("Очевидец"),      //очевидец
    DELETED("УДАЛЕНО");

    private final String fieldDescription;

    private ParticipantStatus(String value) {
        fieldDescription = value;
    }

    public String getName() {
        return fieldDescription;
    }
}
