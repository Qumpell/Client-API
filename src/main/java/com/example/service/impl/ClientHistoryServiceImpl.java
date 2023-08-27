package com.example.service.impl;

import com.example.model.ClientHistory;
import com.example.repository.ClientHistoryRepository;
import com.example.service.ClientHistoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.repository.ClientHistoryRepository.hasClientId;

@Service
@AllArgsConstructor
public class ClientHistoryServiceImpl implements ClientHistoryService {

    private final ClientHistoryRepository clientHistoryRepository;

    @Override
    public List<ClientHistory> findAll() {
        return clientHistoryRepository.findAll();
    }
    @Override
    public List<ClientHistory> findAllByClientId(Long id) {
        return clientHistoryRepository.findAll(hasClientId(id));
    }
    @Override
    public ClientHistory create(ClientHistory clientHistory) {
        return clientHistoryRepository.save(clientHistory);
    }

    @Override
    public ClientHistory update(Long id, ClientHistory clientHistory) {
        clientHistory.setId(id);
        return clientHistoryRepository.save(clientHistory);
    }

    @Override
    public Optional<ClientHistory> findById(Long id) {
        return clientHistoryRepository.findById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return clientHistoryRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        clientHistoryRepository.deleteById(id);
    }


}
