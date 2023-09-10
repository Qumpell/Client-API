package com.example.domain.client;

import com.example.domain.client.mapper.ClientMapper;
import com.example.domain.client.service.impl.UpdateClientService;
import com.example.dto.ClientDTO;
import com.example.model.Client;
import com.example.service.impl.ClientServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client-update")
@AllArgsConstructor
public class ClientController {

    private final UpdateClientService updateClientService;
    private final ClientMapper clientMapper;

    //    @GetMapping("/{id}")
//    public ResponseEntity<Client> getClientById(@PathVariable Long id){
//        return clientService.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
    @PostMapping
//    public ResponseEntity<HttpStatus> createClient(
    public ResponseEntity<Client> createClient(
            @RequestBody ClientDTO clientDTO) {
       var updatedClient = updateClientService.updateClient(clientMapper.fromClientDTO(clientDTO));

//        return ResponseEntity.ok(HttpStatus.CREATED);
        return new ResponseEntity<>(updatedClient,HttpStatus.CREATED);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Client> updateClient(@PathVariable Long id,@RequestBody Client client){
//        if(clientService.existsById(id)){
//            client.setId(id);
//            Client updatedClient = clientService.create(client);
//            return ResponseEntity.ok(updatedClient);
//        }
//        else{
//            return ResponseEntity.notFound().build();
//        }
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
//        if(clientService.existsById(id)){
//            clientService.deleteById(id);
//            return ResponseEntity.noContent().build();
//        }
//        else{
//            return ResponseEntity.notFound().build();
//        }
//    }

}
