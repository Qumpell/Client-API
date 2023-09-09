package com.example.controller;

import com.example.model.Client;
import com.example.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/")
@AllArgsConstructor
public class ClientController {

    private final ClientServiceImpl clientService;

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id){
        return clientService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client){
        Client createdClient = clientService.create(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id,@RequestBody Client client){
        if(clientService.existsById(id)){
            client.setId(id);
            Client updatedClient = clientService.create(client);
            return ResponseEntity.ok(updatedClient);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        if(clientService.existsById(id)){
            clientService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
