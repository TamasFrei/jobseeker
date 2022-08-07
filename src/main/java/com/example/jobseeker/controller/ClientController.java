package com.example.jobseeker.controller;

import com.example.jobseeker.dto.ClientDto;
import com.example.jobseeker.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/clients")
    public ResponseEntity<String> registerClient(@RequestBody ClientDto clientDto) {
        String apiKey = clientService.createClient(clientDto);
        return new ResponseEntity<>(apiKey, HttpStatus.CREATED);
    }
}
