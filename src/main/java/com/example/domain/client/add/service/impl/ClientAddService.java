package com.example.domain.client.add.service.impl;

import com.example.domain.client.add.service.AddClient;
import com.example.model.Client;
import com.example.model.Role;
import com.example.repository.ClientRepository;
import com.example.service.impl.GenerationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientAddService implements AddClient {

    private final ClientRepository clientRepository;
    private final GenerationService generationService;

    @Override
    public Client addClient(Client client) {
        giveClientGeneration(client);
        client.setRole(Role.USER);
        return clientRepository.save(client);
    }

    private void giveClientGeneration(Client client) {
        var generationName = generationService.getGenerationOfDate(client.getBirthDate());
        client.setGeneration(generationName);
    }
}
