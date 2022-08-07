package com.example.jobseeker.controller;

import com.example.jobseeker.dto.PositionRequestDto;
import com.example.jobseeker.dto.PositionResponseDto;
import com.example.jobseeker.service.ClientService;
import com.example.jobseeker.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/positions/{id}")
    public ResponseEntity<PositionResponseDto> getPositionById(@RequestBody PositionRequestDto positionRequestDto, @PathVariable Long id) {
        clientService.validateApiKey(positionRequestDto.getApiKey());
        PositionResponseDto positionDto = positionService.getPositionById(id);
        return new ResponseEntity<>(positionDto, HttpStatus.OK);
    }

    @GetMapping("/positions")
    public ResponseEntity<List<PositionResponseDto>> searchPosition(@RequestBody PositionRequestDto positionRequestDto,
                                                                    @RequestParam String keyword,
                                                                    @RequestParam String location) {
        clientService.validateApiKey(positionRequestDto.getApiKey());
        List<PositionResponseDto> positions = positionService.searchPosition(keyword, location);
        return new ResponseEntity<>(positions, HttpStatus.OK);
    }
}
