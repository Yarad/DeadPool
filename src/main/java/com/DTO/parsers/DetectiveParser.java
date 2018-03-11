package com.DTO.parsers;

import com.DTO.DetectivePersonDTO;
import com.logic.Detective;

public final class DetectiveParser {
    private DetectiveParser() {}

    public static DetectivePersonDTO parseDetectivePerson(Detective detective) {
        return new DetectivePersonDTO(detective.getManId(),
                detective.getName(), detective.getPassword());
    }
}
