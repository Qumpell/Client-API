package com.example.domain.client.delete.service.impl;

import com.example.domain.client.delete.service.DeleteClient;
import com.example.repository.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientDeleteService implements DeleteClient {

    private final ClientRepository clientRepository;
    @Override
    @Transactional
    public void deleteClient(String login) {
        clientRepository.deleteByLogin(login);
    }

}
