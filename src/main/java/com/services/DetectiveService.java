package com.services;

import com.DAO.interfaces.IDAODetective;
import com.logic.Detective;
import com.services.interfaces.IDetectiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetectiveService implements IDetectiveService {
    @Autowired
    private IDAODetective daoDetective;

    @Autowired
    private HashService hashService;

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
}
