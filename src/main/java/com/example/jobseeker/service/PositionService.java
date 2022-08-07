package com.example.jobseeker.service;

import com.example.jobseeker.dto.PositionRequestDto;
import com.example.jobseeker.entity.Position;
import com.example.jobseeker.repository.PositionRepository;
import com.example.jobseeker.validator.PositionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
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
}
