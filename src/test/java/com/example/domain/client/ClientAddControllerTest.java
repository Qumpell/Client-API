package com.example.domain.client;

import com.example.domain.client.add.ClientAddController;
import com.example.domain.client.add.service.impl.ClientAddService;
import com.example.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
public class ClientAddControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    private ClientAddService clientAddService;


    @Test
    public void should_Return_Http_Status_Created_When_Client_Is_Successfully_Created() throws Exception {
        //given
        Client clientToCreate = Client.builder()
                .login("testLogin")
                .name("Jan")
                .surname("Kowalski")
                .addressSet(Collections.emptySet())
                .birthDate(LocalDate.of(12, 1, 12))
                .peselNumber("12345678")
                .build();

        given(clientAddService.clientExist(any())).willReturn(false);
        given(clientAddService.addClient(any())).willReturn(clientToCreate);

        //when //then
        mockMvc.perform(post("/client/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientToCreate)))
                .andExpect(status().isCreated());

        verify(clientAddService, times(1)).clientExist(any());
        verify(clientAddService, times(1)).addClient(any());

    }

    @Test
    public void should_Return_Http_Status_Conflict_When_Client_Already_Exists() throws Exception {
        //given
        Client clientToCreate = Client.builder()
                .login("testLogin")
                .name("Jan")
                .surname("Kowalski")
                .addressSet(Collections.emptySet())
                .birthDate(LocalDate.of(12, 1, 12))
                .peselNumber("12345678")
                .build();

        given(clientAddService.clientExist(any())).willReturn(true);

        //when //then
        mockMvc.perform(post("/client/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clientToCreate)))
                .andExpect(status().isConflict());

        verify(clientAddService, times(1)).clientExist(any());
    }
}
