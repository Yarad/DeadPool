package com.DTO.parsers;

import com.DTO.ManShortedDTO;
import com.logic.Man;

public final class ManParser {
    private ManParser () {}

    public static ManShortedDTO parseManShorted(Man man) {
        if (man != null) {
            return new ManShortedDTO(man.getManId(), man.getName(), man.getSurname(), man.getPhotoPath());
        } else {
            return null;
        }
    }
}
