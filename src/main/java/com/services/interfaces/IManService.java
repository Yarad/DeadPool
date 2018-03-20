package com.services.interfaces;

import com.logic.Man;

import java.time.LocalDate;
import java.util.Map;

public interface IManService {
    Man getFullManInfo(long id);
    boolean addMan(String name, String surname, LocalDate birthday, String homeAddress, String photoPath);
    boolean updateMan(long id, String name, String surname, LocalDate birthday, String homeAddress, String photoPath);
    Map<Man,Long> getAllManWithCrimeAmount();
}
