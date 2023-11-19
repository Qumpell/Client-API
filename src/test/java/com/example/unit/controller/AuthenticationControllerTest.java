package com.example.unit.controller;

import com.example.auth.AuthenticationController;
import com.example.dto.AuthenticationRequest;
import com.example.auth.AuthenticationService;
import com.example.auth.RegisterRequest;
import com.example.configuration.JwtAuthenticationFilter;
import com.example.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthenticationController.class)
@AutoConfigureMockMvc(addFilters = false)
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private AuthenticationService authenticationService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;



    @Test
    public void should_Return_OK_Status_When_Client_Is_Successfully_Registered() throws Exception {
        //given
        RegisterRequest request = RegisterRequest.builder()
                .firstname("John")
                .lastname("Doe")
                .login("john.doe")
                .password("password123")
                .peselNumber("12345678901")
                .birthDate(LocalDate.of(1990, 5, 15))
                .addressSet(new HashSet<>())
                .build();


        given(authenticationService.loginExists(request.getLogin())).willReturn(false);

        // when
         ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions.andExpect(status().isOk());
        verify(authenticationService, times(1)).loginExists(any());
    }
    @Test
    public void should_Return_CONFLICT_Status_When_Client_Login_Already_Exists() throws Exception {
        //given
        RegisterRequest request = RegisterRequest.builder()
                .firstname("John")
                .lastname("Doe")
                .login("john.doe")
                .password("password123")
                .peselNumber("12345678901")
                .birthDate(LocalDate.of(1990, 5, 15))
                .addressSet(new HashSet<>())
                .build();

        given(authenticationService.loginExists(request.getLogin())).willReturn(true);

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions.andExpect(status().isConflict());
        verify(authenticationService, times(1)).loginExists(any());
    }
    @Test
    public void should_Return_OK_Status_When_Client_Is_Successfully_Authenticated() throws Exception {
        //given
        AuthenticationRequest request = AuthenticationRequest.builder()
                .login("john.doe")
                .password("password123")
                .build();
        //when
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        //then
        resultActions.andExpect(status().isOk());
    }
    @Test
    public void should_Return_NotFound_Status_When_Nonexistent_Client_Tries_To_Authenticate() throws Exception {
        // given
        AuthenticationRequest request = new AuthenticationRequest();
        request.setLogin("nonexistentUser");
        request.setPassword("password");

        doThrow(EntityNotFoundException.class).when(authenticationService).authenticate(any());

        // when
         ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));

         // then
        resultActions.andExpect(status().isNotFound());
        verify(authenticationService, times(1)).authenticate(any());
    }

}