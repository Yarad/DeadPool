package com.controller;

import com.DTO.AuthDTO;
import com.DTO.DetectiveWithoutManIdDTO;
import com.DTO.GenericDTO;
import com.DTO.StringOnlyDTO;
import com.logic.Detective;
import com.security.annotations.IsDetective;
import com.services.interfaces.IDetectiveService;
import com.services.interfaces.IHashService;
import com.services.interfaces.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class AuthorizationController {
    @Autowired
    private ITokenService tokenService;

    @Autowired
    private IDetectiveService detectiveService;

    @Autowired
    private IHashService hashService;

    @CrossOrigin
    @RequestMapping(path = "/sign_in", method = RequestMethod.POST)
    public GenericDTO<String> signIn(@RequestBody AuthDTO authData) {
        Detective detective = detectiveService.getDetectiveByLogin(authData.getLogin());
        if (detective == null) {
            return new GenericDTO<>(true,"Нет пользователя с таким именем!");
        } else {
            if (hashService.getMD5Hash(authData.getPassword()).equals(detective.getHashOfPassword())) {
                final String token = UUID.randomUUID().toString();
                tokenService.put(token, authData.getLogin());
                return new GenericDTO<>(false, token);
            } else {
                return new GenericDTO<>(true, "Неправильный пароль!");
            }
        }
    }

    @CrossOrigin
    @RequestMapping(path = "/sign_up", method = RequestMethod.POST)
    public GenericDTO<String> signUp(@RequestBody DetectiveWithoutManIdDTO authData) {
        Detective detective = detectiveService.getDetectiveByLogin(authData.getLogin());
        if (detective != null) {
            return new GenericDTO<>(true, "Пользователь с таким именем уже существует!");
        } else {
            boolean addResult = detectiveService.addDetective(
                    authData.getMan().getName(),
                    authData.getMan().getSurname(),
                    authData.getMan().getBirthday(),
                    authData.getMan().getHomeAddress(),
                    authData.getMan().getPhotoPath(),
                    authData.getLogin(),
                    authData.getPassword(),
                    authData.getEmail()
            );
            if (addResult) {
                return new GenericDTO<>(false, "Вы успешно зарегистрированы в системе!");
            } else {
                return new GenericDTO<>(true, "Не удалось добавить пользователя!");
            }
        }
    }
/*
    @CrossOrigin
    @RequestMapping(path = "/md5", method = RequestMethod.POST)
    public GenericDTO<StringOnlyDTO> md5(@RequestBody StringOnlyDTO password) {
        return new GenericDTO<>(false, new StringOnlyDTO(hashService.getMD5Hash(password.getResult())));
    }
*//*
    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/sign_out", method = RequestMethod.POST)
    public void signOut(@RequestBody StringOnlyDTO token) {
        //TODO: переделать. Брать из заголовков, метод д.б. без параметров, т.е. можжно сделать get
        tokenService.remove(token.getResult());
    }
*/
    @IsDetective
    @CrossOrigin
    @RequestMapping(path = "/sign_out", method = RequestMethod.DELETE)
    public void signOut(@RequestHeader(value = "deadpool-token") String token) {
        tokenService.remove(token);
    }
}
