package com.ControllerTest;

import com.Additionals.LogicAdditionals;
import com.DTO.*;
import com.config.CORSFilter;
import com.controller.AuthorizationController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logic.Detective;
import com.services.HashService;
import com.services.interfaces.IAuthorizationService;
import com.services.interfaces.IDetectiveService;
import com.services.interfaces.IHashService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class AuthorizationControllerTest {
    private MockMvc mockMvc;

    @Mock
    private IDetectiveService detectiveService;

    @Mock
    private IHashService hashInjectedService;

    @Mock
    private IAuthorizationService authorizationService;

    private static IHashService hashService;
    private static ObjectMapper objectMapper;
    private final int tokenHoursExpire = 24*7;

    @InjectMocks
    private AuthorizationController controller;

    @BeforeClass
    public static void getDAO() {
        hashService = new HashService();
        objectMapper = LogicAdditionals.objectMapper();
    }

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .addFilters(new CORSFilter())
                .build();
    }

    @Test
    public void signInCorrect() throws Exception {
        AuthDTO inputJson = createAuthJsonObject("testLogin", "testPassword");
        Detective detective = getDetectiveByLogin("testLogin", "testPassword");
        String token = "fygvhibkjnml";
        GenericDTO<String> response = new GenericDTO<>(false, token);

        when(detectiveService.getDetectiveByLogin(inputJson.getLogin())).thenReturn(detective);
        when(hashInjectedService.getMD5Hash(inputJson.getPassword())).thenReturn(hashService.getMD5Hash(inputJson.getPassword()));
        when(authorizationService.getToken(detective, tokenHoursExpire)).thenReturn(token);

        mockMvc.perform(
                post("/sign_in")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void signInNoDetectiveFound() throws Exception {
        AuthDTO inputJson = createAuthJsonObject("testLogin", "testPassword");
        GenericDTO<String> response = new GenericDTO<>(true, "Нет пользователя с таким именем!");

        when(detectiveService.getDetectiveByLogin(inputJson.getLogin())).thenReturn(null);

        mockMvc.perform(
                post("/sign_in")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void signInDifferentPasswords() throws Exception {
        AuthDTO inputJson = createAuthJsonObject("testLogin", "testPassword");
        Detective detective = getDetectiveByLogin("testLogin", "testPasswordOther");
        GenericDTO<String> response = new GenericDTO<>(true, "Неправильный пароль!");

        when(detectiveService.getDetectiveByLogin(inputJson.getLogin())).thenReturn(detective);
        when(hashInjectedService.getMD5Hash(inputJson.getPassword())).thenReturn(hashService.getMD5Hash(inputJson.getPassword()));

        mockMvc.perform(
                post("/sign_in")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void signUpCorrect() throws Exception {
        DetectiveWithoutManIdDTO inputJson = createDetectiveJsonObject("testNewName", "testNewSurname",null,
                null, null, "testLogin", "testPassword", "test@gmail.com");
        AddResult result = new AddResult(true,1);
        GenericDTO<String> response = new GenericDTO<>(false, "Вы успешно зарегистрированы в системе!");

        when(detectiveService.existDetectiveWithLogin(inputJson.getLogin())).thenReturn(false);
        when(detectiveService.addDetective(inputJson.getMan().getName(), inputJson.getMan().getSurname(), inputJson.getMan().getBirthday(), inputJson.getMan().getHomeAddress(), inputJson.getMan().getPhotoPath(), inputJson.getLogin(), inputJson.getPassword(), inputJson.getEmail())).thenReturn(result);

        mockMvc.perform(
                post("/sign_up")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response))
                );
    }

    @Test
    public void signUpLoginUsed() throws Exception {
        DetectiveWithoutManIdDTO inputJson = createDetectiveJsonObject("testNewName", "testNewSurname", null,
                null, null, "testLogin", "testPassword", "test@gmail.com");

        GenericDTO<String> response = new GenericDTO<>(true, "Пользователь с таким именем уже существует!");

        when(detectiveService.existDetectiveWithLogin(inputJson.getLogin())).thenReturn(true);

        mockMvc.perform(
                post("/sign_up")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response))
                );
    }

    @Test
    public void signUpAddingError() throws Exception {
        DetectiveWithoutManIdDTO inputJson = createDetectiveJsonObject("testNewName", "test", null,
                null, null, "testLogin", "testPassword", "test@gmail.com");
        AddResult result = new AddResult(false,-1);
        GenericDTO<String> response = new GenericDTO<>(true, "Не удалось добавить пользователя!");

        when(detectiveService.existDetectiveWithLogin(inputJson.getLogin())).thenReturn(false);
        when(detectiveService.addDetective(inputJson.getMan().getName(), inputJson.getMan().getSurname(), inputJson.getMan().getBirthday(), inputJson.getMan().getHomeAddress(), inputJson.getMan().getPhotoPath(), inputJson.getLogin(), inputJson.getPassword(), inputJson.getEmail())).thenReturn(result);

        mockMvc.perform(
                post("/sign_up")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(inputJson)))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(objectMapper.writeValueAsString(response))
                );
    }

    private DetectiveWithoutManIdDTO createDetectiveJsonObject(String name, String surname, LocalDate birthday, String homeAddress,
                                                               String photoPath, String login, String password, String email) {
        DetectiveWithoutManIdDTO inputJson = new DetectiveWithoutManIdDTO();
        ManInfoWithoutIdDTO man = new ManInfoWithoutIdDTO();
        man.setName(name);
        man.setSurname(surname);
        man.setBirthday(birthday);
        man.setHomeAddress(homeAddress);
        man.setPhotoPath(photoPath);
        inputJson.setMan(man);
        inputJson.setLogin(login);
        inputJson.setPassword(password);
        inputJson.setEmail(email);
        return inputJson;
    }

    private AuthDTO createAuthJsonObject(String login, String password) {
        AuthDTO inputJson = new AuthDTO();
        inputJson.setLogin(login);
        inputJson.setPassword(password);
        return inputJson;
    }

    private Detective getDetectiveByLogin(String login, String password) {
        Detective detective = new Detective();
        detective.setLogin(login);
        detective.setHashOfPassword(hashService.getMD5Hash(password));
        return detective;
    }
}
