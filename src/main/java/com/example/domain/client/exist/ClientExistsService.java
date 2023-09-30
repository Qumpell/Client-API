package com.example.domain.client.exist;

import com.example.model.Client;
import com.example.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@RequiredArgsConstructor
@Service
public class ClientExistsService implements ClientExists{
    private final ClientRepository clientRepository;

    @Override
    public boolean clientExists(String login) {
        Optional<Client> clientOptional = clientRepository.findByLogin(login);
        return clientOptional.isPresent();
    }
}
