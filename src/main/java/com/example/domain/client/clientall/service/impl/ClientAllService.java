package com.example.domain.client.clientall.service.impl;


import com.example.domain.client.clientall.service.ClientAll;
import com.example.domain.client.update.ClientUpdateController;
import com.example.domain.client.update.mapper.ClientResponseMapper;
import com.example.dto.ClientResponse;
import com.example.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

    public Page<ClientResponse> getAllClientsWithPagination(int page, int size) {
        PageRequest pr = PageRequest.of(page, size);
        var clientsPage = clientRepository.findAll(pr);
        var clients = clientsPage.stream()
                .map(client -> clientResponseMapper
                        .toClientResponse(client)
                        .add(linkTo(ClientUpdateController.class)
                                .slash(client.getLogin())
                                .withSelfRel()))
                .toList();
        return new PageImpl<>(clients, pr, clientsPage.getTotalElements());
    }
}
