package com.example.service;

import com.example.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    List<Client> findAll();
    Client create(Client client);
    Client update(Long id,Client client);
    Optional<Client> findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
}
