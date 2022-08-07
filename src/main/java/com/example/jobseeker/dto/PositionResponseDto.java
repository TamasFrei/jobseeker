package com.example.jobseeker.dto;

import com.example.jobseeker.entity.Position;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PositionResponseDto {

    private String jobTitle;
    private String locationName;
    private String jobUrl;

    public PositionResponseDto(Position position) {
        jobTitle = position.getJobTitle();
        locationName = position.getLocation();
        jobUrl = "http://localhost:8080/api/v1/positions/" + position.getId();
    }
}
