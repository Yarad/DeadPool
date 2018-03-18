package com.services.interfaces;

import java.time.LocalDate;

public interface IManService {
    boolean addMan(String name, String surname, LocalDate birthday, String homeAddress, String photoPath);
}
