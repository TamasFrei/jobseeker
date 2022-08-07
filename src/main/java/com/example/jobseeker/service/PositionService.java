package com.example.jobseeker.service;

import com.example.jobseeker.config.ThirdPartyApiConfig;
import com.example.jobseeker.dto.ApiResponseDto;
import com.example.jobseeker.dto.PositionRequestDto;
import com.example.jobseeker.dto.PositionResponseDto;
import com.example.jobseeker.entity.Position;
import com.example.jobseeker.exception.ResourceNotFoundException;
import com.example.jobseeker.repository.PositionRepository;
import com.example.jobseeker.validator.PositionValidator;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PositionService {

    private final PositionRepository positionRepository;
    private final ThirdPartyApiConfig thirdPartyApiConfig;

    @Autowired
    public PositionService(PositionRepository positionRepository, ThirdPartyApiConfig thirdPartyApiConfig) {
        this.positionRepository = positionRepository;
        this.thirdPartyApiConfig = thirdPartyApiConfig;
    }

    public String createPosition(PositionRequestDto positionRequestDto) {
        PositionValidator validator = new PositionValidator();
        validator.validatePosition(positionRequestDto);
        Position position = new Position();
        position.setJobTitle(positionRequestDto.getJobTitle());
        position.setLocation(positionRequestDto.getLocation());
        position = positionRepository.save(position);
        return "http://localhost:8080/api/v1/positions/" + position.getId();
    }

    public PositionResponseDto getPositionById(Long id) {
        Optional<Position> optionalPosition = positionRepository.findById(id);
        if (optionalPosition.isPresent()) {
            return new PositionResponseDto(optionalPosition.get());
        }
        throw new ResourceNotFoundException("Position", "id", id);
    }

    public List<PositionResponseDto> searchPosition(String keyword, String location) {
        PositionValidator validator = new PositionValidator();
        validator.validatePosition(keyword, location);
        List<Position> positions =
                positionRepository.findByLocationContainingIgnoreCaseAndJobTitleContainingIgnoreCase(location, keyword);
        List<PositionResponseDto> dtos = positions.stream().map(PositionResponseDto::new).collect(Collectors.toList());
        dtos.addAll(getFromApi(keyword, location));
        return dtos;
    }

    private List<PositionResponseDto> getFromApi(String keyword, String location) {
        String url = String.format(thirdPartyApiConfig.getUrl(), keyword, location);
        String username = thirdPartyApiConfig.getApiKey();
        String credentials = username + ":";
        byte[] credentialBytes = credentials .getBytes();
        byte[] base64CredentialBytes = Base64.encodeBase64(credentialBytes);
        String base64Credentials = new String(base64CredentialBytes);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<ApiResponseDto> response = restTemplate.exchange(url, HttpMethod.GET, request, ApiResponseDto.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getResults();
        } else {
            return new ArrayList<>();
        }
    }
}
