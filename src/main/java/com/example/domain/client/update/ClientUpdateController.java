package com.example.domain.client.update;

import com.example.domain.client.update.mapper.ClientUpdateRequestMapper;
import com.example.domain.client.update.service.impl.UpdateClientService;
import com.example.dto.ClientUpdateRequest;
import com.example.dto.ClientResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientUpdateController {

    private final UpdateClientService updateClientService;
    private final ClientUpdateRequestMapper clientRequestMapper;

    @Operation(summary = "Update client by its login")
    @ApiResponse(responseCode = "200", description = "Updated the client")
    @ApiResponse(responseCode = "404", description = "Client not found")
    @PutMapping(path = "/client-update/{login}",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> updateClient(
            @PathVariable String login,
            @RequestBody @Valid ClientUpdateRequest clientRequest) {

        var updatedClient = updateClientService.updateClient(
                login,
                clientRequestMapper.fromClientRequest(clientRequest)
        );

        Link link = linkTo(ClientUpdateController.class).slash(updatedClient.getLogin()).withSelfRel();
        updatedClient.add(link);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

}
