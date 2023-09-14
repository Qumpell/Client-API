package com.example.domain.client.delete;

import com.example.domain.client.delete.service.impl.ClientDeleteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientDeleteController {

    private final ClientDeleteService clientDeleteService;

    @DeleteMapping("/{login}")
    public ResponseEntity<Void> deleteClient(@PathVariable String login){

        if(clientDeleteService.clientExist(login)){
            clientDeleteService.deleteClient(login);
            return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
