package com.example.service.impl;

import com.example.model.Client;
import com.example.model.ClientHistory;
import com.example.repository.ClientHistoryRepository;
import com.example.repository.ClientRepository;
import com.example.service.ClientHistoryService;
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
    private final ClientHistoryService clientHistoryService;

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client create(Client client) {
        var generationName = generationService.getGenerationOfDate(client.getBirthDate());
        client.setGeneration(generationName);
        Client savedClient = clientRepository.save(client);
        var history = ClientHistory.builder()
                .action("CREATE")
                .clientId(savedClient.getId())
                .build();
        clientHistoryService.create(history);
        return savedClient;
    }

    @Override
    public Client update(Long id, Client client) {
        client.setId(id);
        var generationName = generationService.getGenerationOfDate(client.getBirthDate());
        client.setGeneration(generationName);
        Client updatedClient  = clientRepository.save(client);
        var history = ClientHistory.builder()
                .action("UPDATE")
                .clientId(updatedClient.getId())
                .build();
        clientHistoryService.create(history);
        return updatedClient;
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
        List<ClientHistory> list = clientHistoryService.findAllByClientId(id);
        list.forEach(history -> clientHistoryService.deleteById(history.getId()));
    }
}
