package com.example.jobseeker.service;

import com.example.jobseeker.dto.ClientDto;
import com.example.jobseeker.entity.Client;
import com.example.jobseeker.exception.InvalidClientException;
import com.example.jobseeker.repository.ClientRepository;
import com.example.jobseeker.validator.ClientValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public String createClient(ClientDto clientDto) {
        validateClient(clientDto);
        UUID uuid = UUID.randomUUID();
        Client client = new Client();
        client.setName(clientDto.getName().trim());
        client.setEmail(clientDto.getEmail());
        client.setApiKey(uuid.toString());
        clientRepository.save(client);
        return uuid.toString();
    }

    private void validateClient(ClientDto clientDto) {
        ClientValidator validator = new ClientValidator();
        validator.validateClient(clientDto);
        if (clientRepository.existsClientByEmail(clientDto.getEmail())) {
            throw new InvalidClientException("Email is already registered.");
        }
    }
}
