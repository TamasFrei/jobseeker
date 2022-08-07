package com.example.jobseeker.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApiResponseDto {

    private List<PositionResponseDto> results;
}
