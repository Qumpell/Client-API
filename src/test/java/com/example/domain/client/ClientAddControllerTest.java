package com.example.domain.client;

import com.example.auth.RegisterRequest;
import com.example.auth.mapper.RegisterRequestMapper;
import com.example.configuration.JwtAuthenticationFilter;
import com.example.domain.client.add.ClientAddController;
import com.example.domain.client.add.service.impl.ClientAddService;
import com.example.domain.client.exist.ClientExistsService;
import com.example.domain.client.update.mapper.ClientResponseMapper;
import com.example.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientAddController.class)
@AutoConfigureMockMvc(addFilters = false)
public class ClientAddControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private ClientAddService clientAddService;
    @MockBean
    private ClientExistsService clientExistsService;
    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @MockBean
    private ClientResponseMapper responseMapper;
    @MockBean
    private PasswordEncoder passwordEncoder;
    @MockBean
    private RegisterRequestMapper requestMapper;

    @Test
    public void should_Return_Http_Status_Created_When_Client_Is_Successfully_Created() throws Exception {
        //given
        RegisterRequest clientToCreate = RegisterRequest.builder()
                .login("login")
                .addressSet(Collections.emptySet())
                .firstname("Jan")
                .lastname("Kowalski")
                .birthDate(LocalDate.of(2000, 12, 11))
                .password("123")
                .peselNumber("12345678911")
                .build();

        given(clientExistsService.clientExists(any())).willReturn(false);
        given(clientAddService.addClient(any())).willReturn(any(Client.class));

        //when //then
        mockMvc.perform(post("/client/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientToCreate)))
                .andExpect(status().isCreated());

        verify(clientExistsService, times(1)).clientExists(any());
        verify(clientAddService, times(1)).addClient(any());

    }

    @Test
    public void should_Return_Http_Status_Conflict_When_Client_Already_Exists() throws Exception {
        //given
        RegisterRequest clientToCreate = RegisterRequest.builder()
                .login("login")
                .addressSet(Collections.emptySet())
                .firstname("Jan")
                .lastname("Kowalski")
                .birthDate(LocalDate.of(2000, 12, 11))
                .password("123")
                .peselNumber("12345678911")
                .build();

        given(clientExistsService.clientExists(any())).willReturn(true);

        //when //then
        mockMvc.perform(post("/client/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientToCreate)))
                .andExpect(status().isConflict());

        verify(clientExistsService, times(1)).clientExists(any());
    }
}
