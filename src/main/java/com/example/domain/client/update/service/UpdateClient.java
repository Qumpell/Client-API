package com.example.domain.client.update.service;

import com.example.dto.ClientResponse;
import com.example.model.Client;

public interface UpdateClient {

    ClientResponse updateClient(String login, Client client);
}
