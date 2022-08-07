package com.example.jobseeker.validator;

import com.example.jobseeker.dto.ClientDto;
import com.example.jobseeker.exception.InvalidClientException;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public final class ClientValidator {

    private final List<String> errorMessages;

    public ClientValidator() {
        errorMessages = new ArrayList<>();
    }

    public void validateClient(ClientDto clientDto) {
        validateName(clientDto.getName());
        validateEmail(clientDto.getEmail());
        if (!errorMessages.isEmpty()) {
            throw new InvalidClientException(errorMessages);
        }
    }

    private void validateName(String name) {
        if (StringUtils.isEmpty(name)) {
            errorMessages.add("The client's name is missing.");
            return;
        }
        if (name.length() > 100) {
            errorMessages.add("The client's name is too long. Max length is 100 characters.");
        }
    }

    private void validateEmail(String email) {
        if (StringUtils.isEmpty(email)) {
            errorMessages.add("The client's email is missing.");
            return;
        }
        String emailPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (!email.matches(emailPattern)) {
            errorMessages.add("The client's email pattern is not valid.");
        }
    }
}
