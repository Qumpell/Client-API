package com.example.service;

import com.example.model.ClientHistory;

import java.util.List;
import java.util.Optional;

public interface ClientHistoryService {
    List<ClientHistory> findAll();
    List<ClientHistory> findAllByClientId(Long id);
    ClientHistory create(ClientHistory clientHistory);
    ClientHistory update(Long id,ClientHistory clientHistory);
    Optional<ClientHistory> findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
}
