package com.services;

import com.DAO.interfaces.IDAODetective;
import com.DTO.AddResult;
import com.logic.Detective;
import com.logic.Man;
import com.services.interfaces.IDetectiveService;
import com.services.interfaces.IEMailService;
import com.services.interfaces.IHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DetectiveService implements IDetectiveService {
    @Autowired
    private IEMailService eMailService;

    @Autowired
    private IDAODetective daoDetective;

    @Autowired
    private IHashService hashService;

    @Transactional
    @Override
    public AddResult addDetective(String name, String surname, LocalDate birthday, String homeAddress, String photoPath,
                                  String login, String password, String email) {
        Detective detective = new Detective();
        detective.setName(name);
        detective.setSurname(surname);
        detective.setBirthDay(birthday);
        detective.setHomeAddress(homeAddress);
        detective.setPhotoPath(photoPath);
        detective.setLogin(login);
        detective.setHashOfPassword(hashService.getMD5Hash(password));
        detective.setEmail(email);
        boolean result = daoDetective.addDetective(detective);
        if (result) {
            eMailService.sendTextEMailToAllDetectivesExceptId(detective.getManId(), "Приветствуем в системе нового коллегу!",
                    "Здравствуйте!\n" + "\n" +
                            "Доступ к системе получил новый сотрудник - " + detective.getSurname() + ", " + detective.getName() + "\n" +
                            "Теперь вы можете общаться с коллегой в нашей системе!");
        }
        return new AddResult(result, detective.getManId());
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
        detective.setLogin(login);
        detective.setHashOfPassword(hashService.getMD5Hash(password));
        detective.setEmail(email);
        return daoDetective.updateDetective(detective);
    }

    @Transactional
    @Override
    public Detective getDetectiveByLogin(String login) {
        return daoDetective.getDetectiveByLogin(login);
    }

    @Override
    public Detective getDetectiveById(long id) {
        return daoDetective.getDetectiveById(id);
    }

    @Transactional
    @Override
    public boolean existDetectiveWithLogin(String login) {
        return daoDetective.existDetectiveWithLogin(login);
    }

    @Transactional
    @Override
    public List<Detective> getAllDetectives() {
        return daoDetective.getAllDetectives();
    }
}
