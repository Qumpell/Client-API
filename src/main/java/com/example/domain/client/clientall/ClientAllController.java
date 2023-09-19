package com.example.domain.client.clientall;

import com.example.domain.client.clientall.service.impl.ClientAllService;
import com.example.dto.ClientResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/client")
public class ClientAllController {

    private final ClientAllService clientAllService;

    @GetMapping("/all")
    public ResponseEntity<List<ClientResponse>> getAllClients() {
        var clients = clientAllService.getAllClients();
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }
    @Operation(description = "Get all clients with pagination")
    @ApiResponse(responseCode = "200", description = "Returns number of clients on page")
    @ApiResponse(responseCode = "204", description = "No client exists")
    @GetMapping("/clients")
    public ResponseEntity<Page<ClientResponse>> getAllClients(@RequestParam int page, @RequestParam int size) {
        var clients = clientAllService.getAllClientsWithPagination(page, size);
        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }

}
