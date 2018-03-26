package com.services;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.logic.Man;
import com.services.interfaces.IDetectiveService;
import com.services.interfaces.IHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DetectiveService implements IDetectiveService {
    @Autowired
    private IDAODetective daoDetective;

    @Autowired
    private IHashService hashService;

    @Override
    public boolean addDetective(String name, String surname, LocalDate birthday, String homeAddress, String photoPath,
                                String login, String password, String email) {
        Detective detective = new Detective();
        detective.setName(name);
        detective.setSurname(surname);
        detective.setBirthDay(birthday);
        detective.setHomeAddress(homeAddress);
        detective.setPhotoPath(photoPath);
        if (!detective.setLogin(login))
            return false;
        detective.setHashOfPassword(hashService.getMD5Hash(password));
        detective.setEmail(email);
        return daoDetective.addDetective(detective);
    }

    @Override
    public boolean updateDetective(long id, String name, String surname, LocalDate birthday, String homeAddress, String photoPath,
                                   String login, String password, String email) {
        Detective detective = new Detective();
        detective.setManId(id);
        detective.setName(name);
        detective.setSurname(surname);
        detective.setBirthDay(birthday);
        detective.setHomeAddress(homeAddress);
        detective.setPhotoPath(photoPath);
        if (!detective.setLogin(login))
            return false;
        detective.setHashOfPassword(hashService.getMD5Hash(password));
        detective.setEmail(email);
        return daoDetective.updateDetective(detective);
    }

    @Override
    public Detective getDetectiveByLogin(String login) {
        return daoDetective.getDetectiveByLogin(login);
    }

    @Override
    public boolean existDetectiveWithLogin(String login) {
        return daoDetective.existDetectiveWithLogin(login);
    }

    @Override
    public List<Man> getAllDeteсtives() {
        return daoDetective.getAllDetectives();
    }
}
