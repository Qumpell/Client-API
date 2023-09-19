package com.example.domain.client.clientone.service.impl;

import com.example.domain.client.clientone.service.ClientOne;
import com.example.exception.EntityNotFoundException;
import com.example.model.Client;
import com.example.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.repository.ClientRepository.hasLogin;

@Service
@AllArgsConstructor
public class ClientOneService implements ClientOne {

    private final ClientRepository clientRepository;

    @Override
    public Client getOneClient(String login) {
        return clientRepository.findOne(hasLogin(login)).
                orElseThrow(() -> new EntityNotFoundException("Client not found", login));
    }
}
