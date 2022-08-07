package com.example.jobseeker.validator;

import com.example.jobseeker.dto.PositionRequestDto;
import com.example.jobseeker.exception.InvalidPositionException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class PositionValidator {

    private final List<String> errorMessages;

    public PositionValidator() {
        errorMessages = new ArrayList<>();
    }

    public void validatePosition(PositionRequestDto positionRequestDto) {
        validateStringProperty(positionRequestDto.getJobTitle(), "job title");
        validateStringProperty(positionRequestDto.getLocation(), "location");
        if (!errorMessages.isEmpty()) {
            throw new InvalidPositionException(errorMessages);
        }
    }

    public void validatePosition(String keyword, String location) {
        validateStringProperty(keyword, "job title");
        validateStringProperty(location, "location");
        if (!errorMessages.isEmpty()) {
            throw new InvalidPositionException(errorMessages);
        }
    }

    private void validateStringProperty(String stringProperty, String propName) {
        if (StringUtils.isEmpty(stringProperty)) {
            errorMessages.add(String.format("The position's %s is missing.", propName));
            return;
        }
        if (stringProperty.trim().length() > 50) {
            errorMessages.add(String.format("The position's %s is too long. Max length is 50 characters.", propName));
        }
    }
}
