package ru.netology.netologydiplombackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.netology.netologydiplombackend.dto.UserDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class NetologyDiplomBackendTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;
    private final String LOGIN_PATH = "/login";
    private final String LOGOUT_PATH = "/logout";
    private final String LOGIN = "ivan";
    private final String BAD_LOGIN = "login";
    private final String PASSWORD = "qwerty";

    @Test
    void loginUserUnauthenticated() throws Exception {
        UserDto authRequest = new UserDto(BAD_LOGIN, PASSWORD);
        mvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void loginUserAuthenticated() throws Exception {
        UserDto authRequest = new UserDto(LOGIN, PASSWORD);
        mvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isOk());
    }

    @Test
    void logoutUserTest() throws Exception {
        UserDto authRequest = new UserDto(LOGIN, PASSWORD);
        mvc.perform(post(LOGOUT_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().is3xxRedirection());
    }
}