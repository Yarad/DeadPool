package com.ControllerTest;

import com.DTO.DetectiveWithoutManIdDTO;
import com.DTO.GenericDTO;
import com.DTO.ManInfoWithoutIdDTO;
import com.controller.AuthorizationController;
import com.services.HashService;
import com.services.interfaces.IDetectiveService;
import com.services.interfaces.IHashService;
import com.services.interfaces.ITokenService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthorizationControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ITokenService tokenService;

    @Mock
    private IDetectiveService detectiveService;

    private static IHashService hashService;

    @InjectMocks
    private AuthorizationController controller;

    @BeforeClass
    public static void getDAO() {
        hashService = new HashService();
    }

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    public void signUpCorrect() {
        DetectiveWithoutManIdDTO inputJson = new DetectiveWithoutManIdDTO();
        ManInfoWithoutIdDTO man = new ManInfoWithoutIdDTO();
        man.setName("testNewName");
        man.setSurname("testNewSurname");
        man.setBirthday(null);
        man.setHomeAddress(null);
        man.setPhotoPath(null);
        inputJson.setMan(man);
        inputJson.setLogin("testLogin");
        inputJson.setPassword("testPassword");
        inputJson.setEmail("test@gmail.com");

        GenericDTO<String> response = new GenericDTO<>(false, "Вы успешно зарегистрированы в системе!");

        when(detectiveService.existDetectiveWithLogin(inputJson.getLogin())).thenReturn(false);
        when(detectiveService.addDetective(
                inputJson.getMan().getName(), inputJson.getMan().getSurname(), inputJson.getMan().getBirthday(),
                inputJson.getMan().getHomeAddress(), inputJson.getMan().getPhotoPath(), inputJson.getLogin(),
                inputJson.getPassword(), inputJson.getEmail())
        ).thenReturn(true);
        /*
        mockMvc.perform(
                post("/sign_up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(inputJson)
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)))
                .andExpect(content().json(response)
                );
                */
    }
}
