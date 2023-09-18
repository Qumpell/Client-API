package com.example.domain.client.update;

import com.example.domain.client.update.mapper.ClientRequestMapper;
import com.example.domain.client.update.service.impl.UpdateClientService;
import com.example.dto.ClientRequest;
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
    private final ClientRequestMapper clientRequestMapper;


    //    @ApiOperation(value = "Update client based of login")
//    @ApiResponses(value = {
//            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponseWeb.class),
//            @ApiResponse(c)
//            @ApiResponse(code = 401, message = "Unauthorized"),
//            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponseWeb.class),
//            @ApiResponse(code = 404, message = "Not found", response = ErrorResponseWeb.class),
//            @ApiResponse(code = 500, message = "Unknown", response = ErrorResponseWeb.class)
//    })
    @Operation(summary = "Update client by its login")
    @ApiResponse(responseCode = "200", description = "Updated the client")
    @ApiResponse(responseCode = "404", description = "Client not found")
//    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(path = "/client-update",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> updateClient(
            @RequestBody @Valid ClientRequest clientRequest) {
        Link link = linkTo(ClientUpdateController.class).slash(clientRequest.getLoginInput()).withSelfRel();

        var updatedClient = updateClientService.updateClient(clientRequestMapper.fromClientRequest(clientRequest));

        updatedClient.add(link);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

}
