package com.example.domain.client.clientall.service.impl;


import com.example.domain.client.clientall.service.ClientAll;
import com.example.domain.client.update.ClientUpdateController;
import com.example.domain.client.update.mapper.ClientResponseMapper;
import com.example.dto.ClientResponse;
import com.example.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
@AllArgsConstructor
public class ClientAllService implements ClientAll {

    private final ClientRepository clientRepository;
    private final ClientResponseMapper clientResponseMapper;
    @Override
    public List<ClientResponse> getAllClients() {

        return clientRepository.findAll().stream()
                .map(client -> clientResponseMapper
                        .toClientResponse(client)
                        .add(linkTo(ClientUpdateController.class)
                                .slash(client.getLogin())
                                .withSelfRel()))
                .toList();
    }
}
