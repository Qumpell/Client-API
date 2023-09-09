package com.example.service.impl;

import com.example.model.Address;
import com.example.model.Client;
import com.example.model.ClientHistory;
import com.example.repository.AddressRepository;
import com.example.repository.ClientRepository;
import com.example.service.ClientHistoryService;
import com.example.service.ClientService;
import com.example.service.impl.GenerationService;
import jakarta.transaction.Transactional;
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
    private AddressRepository addressRepository;


    public void getAction(Client client,String action){
        switch (action) {
            case "put" -> System.out.println("put");
            case "get" -> System.out.println("get");
            case "post" -> System.out.println("post");
            case "delete" -> System.out.println("delete");
            default -> System.out.println("uknown method");
        }
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client create(Client client) {
        giveClientGeneration(client);
        Client savedClient = clientRepository.save(client);
        buildClientHistory(savedClient.getId(), "CREATE");
        return savedClient;
    }

    @Override
    public Client update(Long id, Client client) {
        client.setId(id);
        giveClientGeneration(client);
        Client updatedClient  = clientRepository.save(client);
        buildClientHistory(updatedClient.getId(), "UPDATE");
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
        buildClientHistory(id, "DELETE");
        clientRepository.deleteById(id);
    }
    @Transactional
    public void removeAddressFromClient(Long clientId, Long addressId){
        Client client = clientRepository.findById(clientId).orElse(null);
        if(client != null){
            Address addressToRemove = addressRepository.findById(addressId).orElse(null);
            if(addressToRemove != null){
                client.getAddressSet().remove(addressToRemove);
                clientRepository.save(client);
            }
        }
    }

    private void buildClientHistory(Long clientId, String action){
        var history = ClientHistory.builder()
                .action(action)
                .clientId(clientId)
                .build();
        clientHistoryService.create(history);
    }
    private void giveClientGeneration(Client client){
        var generationName = generationService.getGenerationOfDate(client.getBirthDate());
        client.setGeneration(generationName);
    }

}
