package com.example.service.impl;

import com.example.model.Client;
import com.example.repository.ClientRepository;
import com.example.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final GenerationService generationService;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client create(Client client) {
        var generationName = generationService.getGenerationOfDate(client.getBirthDate());
        client.setGeneration(generationName);
        return clientRepository.save(client);
    }

    @Override
    public Client update(Long id, Client client) {
        client.setId(id);
        var generationName = generationService.getGenerationOfDate(client.getBirthDate());
        client.setGeneration(generationName);
        return clientRepository.save(client);
    }

    @Override
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return clientRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
