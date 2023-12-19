package com.example.unit.controller;

import com.example.configuration.JwtAuthenticationFilter;
import com.example.domain.client.update.ClientUpdateController;
import com.example.domain.client.update.mapper.ClientUpdateRequestMapper;
import com.example.domain.client.update.service.impl.UpdateClientService;
import com.example.dto.ClientUpdateRequest;
import com.example.dto.ClientResponse;
import com.example.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientUpdateController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClientUpdateControllerTest {
    @MockBean
    private ClientUpdateRequestMapper clientRequestMapper;

    @MockBean
    private UpdateClientService updateClientService;

    @MockBean
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    public void should_Update_Client_When_Client_Exists() throws Exception {
        //given
        String userToBeUpdatedLogin = "login";
        ClientUpdateRequest updatedClient = ClientUpdateRequest.builder()
                .nameInput("Jan")
                .surnameInput("Kowalski")
                .addressesInput(Collections.emptySet())
                .birthDateInput(LocalDate.of(2000, 12, 11))
                .peselNumberInput("12345678901")
                .build();
        ClientResponse clientResponse = ClientResponse.builder()
                .name("Jan")
                .surname("Kowalski")
                .generation("Z")
                .login(userToBeUpdatedLogin)
                .build();
        given(updateClientService.updateClient(eq(userToBeUpdatedLogin),any())).willReturn(clientResponse);
        //when //then
        mockMvc.perform(put("/client/client-update/{login}","login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.login").value("login"))
                .andExpect(jsonPath("$.name").value("Jan"))
                .andExpect(jsonPath("$.surname").value("Kowalski"))
                .andExpect(jsonPath("$.generation").value("Z"));

        verify(updateClientService, times(1)).updateClient(any(),any());
    }

    @Test
    public void should_Return_NotFound_When_Client_Does_Not_Exists() throws Exception {
        //given
        String userToBeUpdatedLogin = "login";
        ClientUpdateRequest updatedClient = ClientUpdateRequest.builder()
                .nameInput("Jan")
                .surnameInput("Kowalski")
                .addressesInput(Collections.emptySet())
                .birthDateInput(LocalDate.of(2000, 12, 11))
                .peselNumberInput("12345678901")
                .build();
        given(updateClientService.updateClient(eq(userToBeUpdatedLogin),any())).willThrow(EntityNotFoundException.class);
        //when //then
        mockMvc.perform(put("/client/client-update/{login}","login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedClient)))
                .andExpect(status().isNotFound());

        verify(updateClientService, times(1)).updateClient(any(),any());
    }


}