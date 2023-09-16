package com.example.domain.client;

import com.example.domain.client.delete.ClientDeleteController;
import com.example.domain.client.delete.service.impl.ClientDeleteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientDeleteController.class)
public class ClientDeleteControllerTest {

    @MockBean
    private ClientDeleteService clientDeleteService;
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void should_Return_No_Content_When_Client_Is_Deleted() throws Exception {

        //given
        given(clientDeleteService.clientExist(any())).willReturn(true);
        doNothing().when(clientDeleteService).deleteClient(any());

        //when //then
        mockMvc.perform(delete("/client/testLogin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(clientDeleteService, times(1)).clientExist(any());
        verify(clientDeleteService, times(1)).deleteClient(any());
    }


    @Test
    public void should_Return_Not_Found_When_Client_Does_Not_Exists() throws Exception {

        //given
        given(clientDeleteService.clientExist(any())).willReturn(false);

        //when //then
        mockMvc.perform(delete("/client/testLogin")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(clientDeleteService,times(1)).clientExist(any());
    }
}

