package com.example.domain.client.delete.service.impl;

import com.example.domain.client.delete.service.DeleteClient;
import com.example.model.Client;
import com.example.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.repository.ClientRepository.hasLogin;

@Service
@AllArgsConstructor
public class ClientDeleteService implements DeleteClient {

    private final ClientRepository clientRepository;

    public boolean clientExist(String login){
        Optional<Client> clientOptional = clientRepository.findOne(hasLogin(login));
        return clientOptional.isPresent();
    }
    @Override
    @Transactional
    public void deleteClient(String login){
        clientRepository.deleteByLogin(login);
    }

}
