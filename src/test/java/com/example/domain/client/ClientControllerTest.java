package com.example.domain.client;

import com.example.domain.client.service.impl.UpdateClientService;
import com.example.model.Client;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@SpringBootTest
@WebMvcTest(controllers = ClientController.class)
class ClientControllerTest {
    @Autowired
    private MockMvc mockMvc;
//    @InjectMocks
//    private ClientController clientController;
    @MockBean
    private UpdateClientService updateClientService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void should_Update_Client_When_Client_Exists() throws Exception{
        //given
      Client updatedClient = new Client();
      updatedClient.setId(1L);
      updatedClient.setName("Test");
      updatedClient.setSurname("Tes");
      updatedClient.setPeselNumber("12345678");
      updatedClient.setBirthDate(LocalDate.of(2002, Month.AUGUST,12));
      given(updateClientService.updateClient(any())).willReturn(updatedClient);

      RequestBuilder request = post("/client-updates")
              .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(updatedClient));

      mockMvc.perform(request)
              .andExpect(status().isCreated())
              .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
              .andExpectAll(
                      jsonPath("$.id").value("1"),
                      jsonPath("$.name").value("Test")
              );

    }

}