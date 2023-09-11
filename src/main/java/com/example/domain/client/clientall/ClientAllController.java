package com.example.domain.client.clientall;

import com.example.domain.client.clientall.service.impl.ClientAllService;
import com.example.dto.ClientResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientAllController {

    private final ClientAllService clientAllService;

    @GetMapping("/all")
    public ResponseEntity<List<ClientResponse>> getAllClients(){
        var clients = clientAllService.getAllClients();
        if(clients.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else{
            return new ResponseEntity<>(clients,HttpStatus.OK);
        }
    }
}
