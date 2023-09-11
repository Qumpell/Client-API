package com.example.domain.client.update.service.impl;

import com.example.domain.client.update.mapper.ClientResponseMapper;
import com.example.domain.client.update.service.UpdateClient;
import com.example.dto.ClientResponse;
import com.example.exception.EntityNotFoundException;
import com.example.model.Client;
import com.example.repository.ClientRepository;
import com.example.service.impl.GenerationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.repository.ClientRepository.hasLogin;

@Service
@AllArgsConstructor
public class UpdateClientService implements UpdateClient {

    private final ClientRepository clientRepository;
    private final GenerationService generationService;
    private final ClientResponseMapper clientResponseMapper;

    @Override
    public ClientResponse updateClient(Client clientInput) {
        var clientData = getClient(clientInput);
        var generation = generationService.getGenerationOfDate(clientInput.getBirthDate());
        try {
            clientData.setName(clientInput.getName());
            clientData.setSurname(clientInput.getSurname());
            clientData.setPeselNumber(clientInput.getPeselNumber());
            clientData.setBirthDate(clientInput.getBirthDate());
            clientData.setGeneration(generation);
            clientData.setLogin(clientInput.getLogin());
            clientData = clientRepository.save(clientData);
            return clientResponseMapper.toClientResponse(clientData);
        }
        catch (Exception e){
            throw new EntityNotFoundException("Client cannot be updated",e.getMessage());
        }


    }
    public Client getClient(Client client){
        return clientRepository.findOne(hasLogin(client.getLogin())).
                 orElseThrow(() -> new EntityNotFoundException("Client not found",client.getLogin()));
    }
}
