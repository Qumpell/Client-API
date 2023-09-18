package com.example.domain.client.add;

import com.example.domain.client.add.service.impl.ClientAddService;
import com.example.model.Client;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientAddController {

    private final ClientAddService clientAddService;


    @Operation(description = "Create a new client")
    @ApiResponse(responseCode = "409", description = "Cannot create a new client, because client already exists")
    @ApiResponse(responseCode = "201", description = "Created a new client successfully")
    @PostMapping("/add")
    public ResponseEntity<Client> addClient(@RequestBody @Valid Client client) {

        if (clientAddService.clientExist(client)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            var savedClient = clientAddService.addClient(client);
            return new ResponseEntity<>(savedClient, HttpStatus.CREATED);

        }
    }
}
