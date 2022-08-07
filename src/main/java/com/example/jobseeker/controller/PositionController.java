package com.example.jobseeker.controller;

import com.example.jobseeker.dto.PositionRequestDto;
import com.example.jobseeker.service.ClientService;
import com.example.jobseeker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PositionController {

    private final PositionService positionService;
    private final ClientService clientService;

    @Autowired
    public PositionController(PositionService positionService, ClientService clientService) {
        this.positionService = positionService;
        this.clientService = clientService;
    }

    @PostMapping("/positions")
    public ResponseEntity<String> createPosition(@RequestBody PositionRequestDto positionRequestDto) {
        clientService.validateApiKey(positionRequestDto.getApiKey());
        String url = positionService.createPosition(positionRequestDto);
        return new ResponseEntity<>(url, HttpStatus.CREATED);
    }
}
