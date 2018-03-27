package com.services;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.logic.Man;
import com.services.interfaces.IDetectiveService;
import com.services.interfaces.IHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DetectiveService implements IDetectiveService {
    @Autowired
    private IDAODetective daoDetective;

    @Autowired
    private IHashService hashService;

    @Transactional
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

    @Transactional
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

    @Transactional
    @Override
    public Detective getDetectiveByLogin(String login) {
        return daoDetective.getDetectiveByLogin(login);
    }

    @Transactional
    @Override
    public boolean existDetectiveWithLogin(String login) {
        return daoDetective.existDetectiveWithLogin(login);
    }

    @Transactional
    @Override
    public List<Man> getAllDetectives() {
        return daoDetective.getAllDetectives();
    }
}
