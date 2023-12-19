package com.example.domain.client.add;

import com.example.auth.RegisterRequest;
import com.example.auth.mapper.RegisterRequestMapper;
import com.example.domain.client.add.service.impl.ClientAddService;
import com.example.domain.client.exist.ClientExistsService;
import com.example.domain.client.update.mapper.ClientResponseMapper;
import com.example.dto.ClientResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientAddController {

    private final ClientAddService clientAddService;
    private final ClientExistsService clientExistsService;
    private final ClientResponseMapper responseMapper;
    private final PasswordEncoder passwordEncoder;
    private final RegisterRequestMapper requestMapper;

    @Operation(description = "Create a new client")
    @ApiResponse(responseCode = "409", description = "Cannot create a new client, because client already exists")
    @ApiResponse(responseCode = "201", description = "Created a new client successfully")
    @PostMapping("/add")
    public ResponseEntity<ClientResponse> addClient(@RequestBody @Valid RegisterRequest clientRequest) {

        if (clientExistsService.clientExists(clientRequest.getLogin())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        } else {
            var savedClient = clientAddService.addClient(requestMapper.fromRegisterRequest(clientRequest, passwordEncoder));
            var response =  responseMapper.toClientResponse(savedClient);
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        }
    }
}
