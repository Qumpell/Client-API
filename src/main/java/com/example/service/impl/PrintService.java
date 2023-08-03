package com.example.service.impl;

import com.example.model.Client;
import com.example.repository.ClientRepository;
import com.example.service.PrintObject;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PrintService implements PrintObject {

    private final ClientRepository clientRepository;

    @Override
    public List<Client> getAllClient() {
        System.out.println(clientRepository.findAll());
        return clientRepository.findAll();
    }
}
