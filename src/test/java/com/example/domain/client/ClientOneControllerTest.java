package com.example.domain.client;

import com.example.domain.client.clientone.ClientOneController;
import com.example.domain.client.clientone.service.impl.ClientOneService;
import com.example.domain.client.update.mapper.ClientResponseMapper;
import com.example.dto.ClientResponse;
import com.example.exception.EntityNotFoundException;
import com.example.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientOneController.class)
class ClientOneControllerTest {

    @MockBean
    private ClientResponseMapper clientResponseMapper;
    @MockBean
    private ClientOneService clientOneService;
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void should_Return_Client_When_Client_Exists() throws Exception {
        //given
        Client clientFound = Client.builder()
                .login("testLogin")
                .name("Jan")
                .surname("Kowalski")
                .generation("Alpha")
                .build();
        ClientResponse clientResponse = ClientResponse.builder()
                .login("testLogin")
                .name("Jan")
                .surname("Kowalski")
                .generation("Alpha")
                .build();
        given(clientOneService.getOneClient(any())).willReturn(clientFound);
        given(clientResponseMapper.toClientResponse(clientFound)).willReturn(clientResponse);

        //when //then
        mockMvc.perform(get("/client/testLogin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("testLogin"))
                .andExpect(jsonPath("$.name").value("Jan"))
                .andExpect(jsonPath("$.surname").value("Kowalski"))
                .andExpect(jsonPath("$.generation").value("Alpha"));

        verify(clientOneService, times(1)).getOneClient(any());
    }

    @Test
    public void should_Return_Not_Found_When_Client_Does_Not_Exist() throws Exception {
        //given
        Client clientFound = Client.builder()
                .login("testLogin")
                .name("Jan")
                .surname("Kowalski")
                .generation("Alpha")
                .build();
        ClientResponse clientResponse = ClientResponse.builder()
                .login("testLogin")
                .name("Jan")
                .surname("Kowalski")
                .generation("Alpha")
                .build();
        given(clientOneService.getOneClient(any())).willThrow(EntityNotFoundException.class);

        //when //then
        mockMvc.perform(get("/client/testLogin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(clientOneService, times(1)).getOneClient(any());
    }

}