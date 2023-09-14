package com.example.domain.client.add.service.impl;

import com.example.domain.client.add.service.AddClient;
import com.example.model.Client;
import com.example.repository.ClientRepository;
import com.example.service.impl.GenerationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.repository.ClientRepository.hasLogin;

@Service
@AllArgsConstructor
public class ClientAddService implements AddClient {

    private final ClientRepository clientRepository;
    private final GenerationService generationService;
    @Override
    public Client addClient(Client client) {
        giveClientGeneration(client);
        return clientRepository.save(client);
    }

    public boolean clientExist(Client client){
        Optional<Client> clientOptional = clientRepository.findOne(hasLogin(client.getLogin()));
        return clientOptional.isPresent();
    }
    private void giveClientGeneration(Client client){
        var generationName = generationService.getGenerationOfDate(client.getBirthDate());
        client.setGeneration(generationName);
    }
}
