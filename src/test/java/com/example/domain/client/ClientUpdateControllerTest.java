package com.example.domain.client;

import com.example.domain.client.update.service.impl.UpdateClientService;
import com.example.domain.client.update.ClientUpdateController;
import com.example.dto.ClientResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(ClientUpdateController.class)
class ClientUpdateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UpdateClientService updateClientService;

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    private ClientUpdateController clientUpdateController;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientUpdateController).build();
    }
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

        RequestBuilder request = post("/client/client-updates")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedClient));

        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpectAll(
                        jsonPath("$.login").value("test"),
                        jsonPath("$.name").value("Jan")
                );

    }

}