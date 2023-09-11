package com.example.domain.client.update;

import com.example.commons.ErrorResponseWeb;
import com.example.domain.client.update.mapper.ClientRequestMapper;
import com.example.domain.client.update.service.impl.UpdateClientService;
import com.example.dto.ClientRequest;
import com.example.dto.ClientResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;


@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientUpdateController {

    private final UpdateClientService updateClientService;
    private final ClientRequestMapper clientRequestMapper;


    @ApiOperation(value = "Update client based of login")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request", response = ErrorResponseWeb.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden", response = ErrorResponseWeb.class),
            @ApiResponse(code = 404, message = "Not found", response = ErrorResponseWeb.class),
            @ApiResponse(code = 500, message = "Unknown", response = ErrorResponseWeb.class)
    })
    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping(path = "/client-update", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientResponse> updateClient(
            @RequestBody @Valid ClientRequest clientRequest) {
        Link link = linkTo(ClientUpdateController.class).slash(clientRequest.getLoginInput()).withSelfRel();

        var updatedClient = updateClientService.updateClient(clientRequestMapper.fromClientRequest(clientRequest));

        updatedClient.add(link);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    //    @GetMapping("/{id}")
//    public ResponseEntity<Client> getClientById(@PathVariable Long id){
//        return clientService.findById(id)
//                .map(ResponseEntity::ok)
//                .orElse(ResponseEntity.notFound().build());
//    }
//    / /
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
