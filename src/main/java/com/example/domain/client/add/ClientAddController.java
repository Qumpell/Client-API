package com.example.domain.client.add;

import com.example.domain.client.add.service.impl.ClientAddService;
import com.example.model.Client;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientAddController {

    private final ClientAddService clientAddService;
    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody @Valid Client client){

        if(clientAddService.clientExist(client)){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        else{
            var savedClient =  clientAddService.addClient(client);
            return new ResponseEntity<>(savedClient,HttpStatus.CREATED);

        }
    }
}
