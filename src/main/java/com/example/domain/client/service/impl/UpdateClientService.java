package com.example.domain.client.service.impl;

import com.example.domain.client.service.UpdateClient;
import com.example.dto.ClientDTO;
import com.example.exception.GenerationNotFoundException;
import com.example.model.Client;
import com.example.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.repository.ClientRepository.hasPesel;

@Service
@AllArgsConstructor
public class UpdateClientService implements UpdateClient {

    private final ClientRepository clientRepository;

    @Override
    public Client updateClient(Client clientInput) {
        var clientData = getClient(clientInput);
        clientData.setName(clientInput.getName());
        clientData.setSurname(clientInput.getSurname());
        clientData.setPeselNumber(clientInput.getPeselNumber());
        clientData.setBirthDate(clientInput.getBirthDate());
        return clientRepository.save(clientData);
    }
    public Client getClient(Client client){
        return clientRepository.findOne(hasPesel(client.getPeselNumber())).
                 orElseThrow(() -> new GenerationNotFoundException("Client not found",client.getPeselNumber()));
    }
}
