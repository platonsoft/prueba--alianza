package com.alianza.ptecnica.clients.controllers;

import com.alianza.ptecnica.clients.io.ClientNewRequest;
import com.alianza.ptecnica.clients.io.ClientResponse;
import com.alianza.ptecnica.clients.services.ClientService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = {"Clients"})
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/api/clients")
    @ApiOperation(value = "List of available clients")
    public ResponseEntity<List<ClientResponse>> clientList() {
        return new ResponseEntity<>(clientService.list(),HttpStatus.OK);
    }

    @GetMapping("/api/clients/search")
    @ApiOperation(value = "List of available clients by SharedKey")
    public ResponseEntity<List<ClientResponse>> clientsListBySharedKey(@RequestParam String sharedKeySearch) {
        return new ResponseEntity<>(clientService.listBySharedKey(sharedKeySearch),HttpStatus.OK);
    }

    @PostMapping("/api/clients/search/advanced")
    @ApiOperation(value = "List of available clients by Multiple Options")
    public ResponseEntity<List<ClientResponse>> clientsListByOptions(@RequestBody ClientNewRequest request) {
        return new ResponseEntity<>(clientService.listByOptions(request),HttpStatus.OK);
    }

    @PostMapping("/api/client")
    @ApiOperation(value = "Add new client")
    public ResponseEntity<Long> addNewClient(@RequestBody ClientNewRequest request) {
        return new ResponseEntity<>(clientService.newClient(request),HttpStatus.OK);
    }
}
