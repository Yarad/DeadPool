package com.services;

import com.DAO.interfaces.IDAOMan;
import com.DTO.AddResult;
import com.logic.Man;
import com.logic.Participant;
import com.services.interfaces.IManService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Service
public class ManService implements IManService {
    @Autowired
    @Qualifier("DAOMan")
    private IDAOMan daoMan;

    @Transactional
    @Override
    public AddResult addMan(String name, String surname, LocalDate birthday, String homeAddress, String photoPath) {
        Man newMan = new Participant();
        newMan.setName(name);
        newMan.setSurname(surname);
        newMan.setBirthDay(birthday);
        newMan.setHomeAddress(homeAddress);
        newMan.setPhotoPath(photoPath);
        return new AddResult(daoMan.addMan(newMan), newMan.getManId());
    }

    @Transactional
    @Override
    public boolean updateMan(long id, String name, String surname, LocalDate birthday, String homeAddress, String photoPath) {
        Man newMan = new Participant();
        newMan.setManId(id);
        newMan.setName(name);
        newMan.setSurname(surname);
        newMan.setBirthDay(birthday);
        newMan.setHomeAddress(homeAddress);
        newMan.setPhotoPath(photoPath);
        return daoMan.updateMan(newMan);
    }

    @Transactional
    @Override
    public Map<Man, Long> getAllManWithCrimeAmount() {
        return daoMan.getAllManWithCrimeAmount();
    }

    @Override
    public List<Man> getAllMan() {
        return daoMan.getAllMan();
    }

    @Transactional
    @Override
    public Man getFullManInfo(long id) {
        return daoMan.getFullManInfo(id);
    }
}
