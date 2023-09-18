package com.example.domain.client.clientone;

import com.example.domain.client.clientone.service.impl.ClientOneService;
import com.example.domain.client.update.ClientUpdateController;
import com.example.domain.client.update.mapper.ClientResponseMapper;
import com.example.dto.ClientResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientOneController {

    private final ClientOneService clientOneService;
    private final ClientResponseMapper clientResponseMapper;

    @Operation(description = "Get a client by its login")
    @ApiResponse(responseCode = "200", description = "Successfully returned the client")
    @ApiResponse(responseCode = "404", description = "Cannot find the client")
    @GetMapping("/{login}")
    public ResponseEntity<ClientResponse> getOneClient(@PathVariable String login) {
        var clientFound = clientResponseMapper.toClientResponse(clientOneService.getOneClient(login));
        Link link = linkTo(ClientUpdateController.class).slash(clientFound.getLogin()).withSelfRel();
        clientFound.add(link);
        return new ResponseEntity<>(clientFound, HttpStatus.OK);
    }

}
