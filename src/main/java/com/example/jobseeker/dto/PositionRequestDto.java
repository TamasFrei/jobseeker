package com.example.jobseeker.dto;

import lombok.Data;

@Data
public class PositionRequestDto {

    private String apiKey;
    private String jobTitle;
    private String location;
}
