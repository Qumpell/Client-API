package com.example.domain.client;

import com.example.domain.client.update.ClientUpdateController;
import com.example.domain.client.update.mapper.ClientRequestMapper;
import com.example.domain.client.update.service.impl.UpdateClientService;
import com.example.dto.ClientResponse;
import com.example.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientUpdateController.class)
class ClientUpdateControllerTest {
    @MockBean
    private ClientRequestMapper clientRequestMapper;
    @MockBean
    private UpdateClientService updateClientService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void should_Update_Client_When_Client_Exists() throws Exception {
        //given
        ClientResponse updatedClient = ClientResponse.builder()
                .login("test")
                .name("Jan")
                .surname("Kowalski")
                .generation("Alpha")
                .build();
        given(updateClientService.updateClient(any())).willReturn(updatedClient);
        //when //then
        mockMvc.perform(post("/client/client-update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("test"))
                .andExpect(jsonPath("$.name").value("Jan"))
                .andExpect(jsonPath("$.surname").value("Kowalski"))
                .andExpect(jsonPath("$.generation").value("Alpha"));

        verify(updateClientService,times(1)).updateClient(any());
    }
    @Test
    public void should_Return_NotFound_When_Client_Does_Not_Exists() throws Exception {
        //given
        ClientResponse updatedClient = ClientResponse.builder()
                .login("test")
                .name("Jan")
                .surname("Kowalski")
                .generation("Alpha")
                .build();
        given(updateClientService.updateClient(any())).willThrow(EntityNotFoundException.class);
        //when //then
        mockMvc.perform(post("/client/client-update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isNotFound());

        verify(updateClientService,times(1)).updateClient(any());
    }


}