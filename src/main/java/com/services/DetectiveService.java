package com.services;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.services.interfaces.IDetectiveService;
import com.services.interfaces.IHashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetectiveService implements IDetectiveService {
    @Autowired
    private IDAODetective daoDetective;

    @Autowired
    private IHashService hashService;

    @Override
    public boolean addDetective(long id, String login, String password, String email) {
        Detective detective = new Detective();
        detective.setManId(id);
        if (!detective.setLogin(login))
            return false;
        if (!detective.setHashOfPassword(hashService.getMD5Hash(password)))
            return false;
        detective.setEmail(email);
        return daoDetective.addDetective(detective);
    }

    @Override
    public boolean updateDetective(long id, String login, String password, String email) {
        Detective detective = new Detective();
        detective.setManId(id);
        if (!detective.setLogin(login))
            return false;
        if (!detective.setHashOfPassword(hashService.getMD5Hash(password)))
            return false;
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
}
