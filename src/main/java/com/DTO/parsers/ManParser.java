package com.DTO.parsers;

import com.DTO.ManOnlyPersonDTO;
import com.DTO.ManShortedDTO;
import com.logic.Man;

public final class ManParser {
    private ManParser () {}

    public static ManOnlyPersonDTO parseManOnlyPerson(Man man) {
        if (man != null) {
            return new ManOnlyPersonDTO(man.getManId(), man.getName(), man.getSurname());
        } else {
            return null;
        }
    }

    public static ManShortedDTO parseManShorted(Man man) {
        if (man != null) {
            return new ManShortedDTO(man.getManId(), man.getName(), man.getSurname(), man.getPhotoPath());
        } else {
            return null;
        }
    }
}
